package org.eoa.projectbudget.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.LoginException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.AuthorityService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.vo.Tokens;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author 张骏山
 * @Date 2023/9/26 16:20
 * @PackageName: org.eoa.projectbudget.config
 * @ClassName: SleepInterceptor
 * @Description: TODO
 * @Version 1.0
 */

@Component
public class TokenInterceptor implements HandlerInterceptor, InitializingBean {

    @Value("${eoa.secret-pass}")
    String secretPass;
    @Value("${eoa.release}")
    private boolean isRelease;
    Algorithm algorithm;

    @Autowired
    AuthorityService authorityService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    CacheService cacheService;
    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Resource
    RedisTemplate<Long,Date> redisTemplate;

    String flag = "HUMAN";
    String method = "TOKEN";



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("OPTIONS"))
            return true;
        String jwt = DataProcessUtils.nullToString(request.getHeader("tokens"));
        String tokensString;
        try {
            tokensString = JWT.require(algorithm).build().verify(jwt).getClaim("tokens").asString();
        } catch (JWTVerificationException e) {
            throw new LoginException(false);
        }
        Tokens tokens;
        try {
            tokens = new ObjectMapper().readValue(tokensString, Tokens.class);
        } catch (Exception e) {
            throw new LoginException(false);
        }

        AuthorityService.UserWithToken gets = authorityService.getUser(tokens);
        Long userId = gets.getUserId();
        boolean has = false;
        if (isRelease) {
            ValueOperations<Long, Date> ops = redisTemplate.opsForValue();
            Date last = null;
            has = Boolean.TRUE.equals(redisTemplate.hasKey(userId));
            if (has) {
                last = ops.get(userId);
            }
            if (last == null) {
                Calendar instance = Calendar.getInstance();
                instance.add(Calendar.MINUTE,-5);
                if (tokens.getTimeStamp().before(instance.getTime())) {
                    throw new LoginException(false);
                }
            }
            if (last != null && last.after(tokens.getTimeStamp())) {
                throw new LoginException(false);
            }
        }

        HumanDto cache = cacheService.getCache(flag, method, userId, changeFlagUtils.getDate("HUMAN"), HumanDto.class);
        if (cache == null) {
            cache = organizationService.getHumanDto(userId, userId);
            cacheService.setCache(flag,method,userId,cache);
        }
        if (gets.isUpdate()) {
            tokens = authorityService.getTokens(userId);
            if (isRelease) {
                if (has) {
                    redisTemplate.delete(userId);
                }
                redisTemplate.opsForValue().set(userId,new Date(),5, TimeUnit.MINUTES);
            }
        }
        else
            tokens.setTimeStamp(new Date());
        String s = new ObjectMapper().writeValueAsString(tokens);
        String jwtN = JWT.create().withClaim("tokens", s).sign(algorithm);
        response.setHeader("tokens", jwtN);
        request.setAttribute("HumanDto", cache);
        response.setHeader("Access-Control-Expose-Headers", "tokens");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterPropertiesSet() {
        algorithm = Algorithm.HMAC256(secretPass);
        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        flag = ChangeFlagUtils.Flags[ChangeFlagUtils.HUMAN];
    }
}
