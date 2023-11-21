package org.eoa.projectbudget.service.organization_module.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.eoa.projectbudget.exception.LoginException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.organization_module.AuthorityService;
import org.eoa.projectbudget.vo.Tokens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:08
 * @PackageName: org.eoa.projectbudget.service.organization_module.impl
 * @ClassName: AuthorityServiceImpl
 * @Description: TODO
 * @Version: 1.0
 **/

@Service
public class AuthorityServiceImpl implements AuthorityService, InitializingBean {

    @Value("${eoa.token-short-time}")
    Integer tokenShortTime;

    @Value("${eoa.token-long-time}")
    Integer tokenLongTime;

    @Value("${eoa.token-pass}")
    String tokenPass;

    Algorithm algorithm;

    Logger log = LoggerFactory.getLogger("AuthorityModule");


    @Override
    public Character getCharacterById(Long characterId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public List<Character> getAllCharacter(Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer newCharacter(Character character, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer updateCharacter(Character character, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer dropCharacter(Long characterId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer characterLinkAuthority(Long characterId, Long authorityId) {
        // TODO
        return null;
    }

    @Override
    public Integer characterLinkUser(Long characterId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer userLinkAuthority(Long authorityId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Tokens getTokens(Long userId) {
        log.info("用户=>{}申请tokens",userId);
        Calendar shortToken = Calendar.getInstance();
        shortToken.add(Calendar.MINUTE,tokenShortTime);
        Calendar longTime = Calendar.getInstance();
        longTime.add(Calendar.MINUTE,tokenLongTime);

        Tokens tokens = new Tokens();

        tokens.setTimeStamp(new Date());
        tokens.setShortToken(JWT.create()
                    .withClaim("dataId",userId)
                    .withClaim("type","short")
                    .withExpiresAt(shortToken.getTime())
                    .sign(algorithm))
        .setLongToken(JWT.create()
                    .withClaim("dataId",userId)
                    .withClaim("type","long")
                    .withExpiresAt(longTime.getTime())
                    .sign(algorithm));


        return tokens;
    }

    @Override
    public UserWithToken getUser(Tokens tokens) throws ParameterException, LoginException {
        JWTVerifier build = JWT.require(algorithm).build();
        DecodedJWT decodeShort = null;
        DecodedJWT decodeLong;
        boolean shortOut = false;

        try {
            decodeLong = build.verify(tokens.getLongToken());
        } catch (TokenExpiredException e) {
            throw new LoginException(true);
        } catch (JWTVerificationException e) {
            throw new LoginException(false);
        }

        try {
            decodeShort = build.verify(tokens.getShortToken());
        } catch (TokenExpiredException e) {
            shortOut = true;
        } catch (JWTVerificationException e) {
            throw new LoginException(false);
        }
        Long userId = decodeLong.getClaim("dataId").asLong();
        log.info("token解析成功,获取用户=>{},是否更新=>{}",userId,shortOut);
        return new UserWithToken().setUserId(userId).setUpdate(shortOut);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        algorithm = Algorithm.HMAC256(tokenPass);
    }
}
