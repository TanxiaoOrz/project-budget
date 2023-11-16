package org.eoa.projectbudget.rest_controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.LoginException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.AuthorityService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.vo.Tokens;
import org.eoa.projectbudget.vo.in.Login;
import org.eoa.projectbudget.vo.in.TokensIn;
import org.eoa.projectbudget.vo.out.LoginConfigOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:41
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: TokenController
 * @Description: Token相关处理接口
 * @Version: 1.0
 **/

@RestController
@RequestMapping("/api/v1/token")
@Tag(name = "token相关操作控制器")
@CrossOrigin
public class TokenController implements InitializingBean {

    @Value("${eoa.secret-pass}")
    String secretPass;

    Algorithm algorithm;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    CacheService cacheService;

    String flag;
    String method = "TOKEN";

    @PostMapping
    @Operation(summary = "获取token认证", description = "输入登入名和密码,回传token字符串")
    public Vo<String> newTokens(
            @Parameter(description = "登录结构体")
            @RequestBody Login login)
            throws EoaException, JsonProcessingException {
        login.checkSelf();
        HumanDto humanDto = organizationService.getHumanDto(login.getLoginName(),login.getPassword());
        cacheService.setCache(flag,method,humanDto.getDataId(),humanDto);
        Tokens tokens = authorityService.getTokens(humanDto.getDataId());
        return new Vo<>(JWT.create().withClaim("tokens",new ObjectMapper().writeValueAsString(tokens)).sign(algorithm));
    }

    @GetMapping
    @Operation(summary = "获取登录页面基本设置")
    public Vo<LoginConfigOut> getLoginConfig() {
        // TODO
        return new Vo<>(null);
    }

    @PostMapping("/check")
    @Operation(summary = "验证token是否登录",description = "post方法的RequestBody之间传输tokens字符串")
    public Vo<Boolean> checkToken(
            @Parameter(description = "tokens字符串")
            @RequestBody(required = false) TokensIn tokensIn) {
        String tokens = tokensIn.getTokens();
        try {
            String jwt = DataProcessUtils.nullToString(tokens);
            String tokensString = JWT.require(algorithm).build().verify(jwt).getClaim("tokens").asString();
            Tokens t = new ObjectMapper().readValue(tokensString, Tokens.class);
            authorityService.getUser(t);
            return new Vo<>(true);
        } catch (ParameterException | JsonProcessingException | LoginException | JWTVerificationException e) {
            return new Vo<>(false);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        algorithm = Algorithm.HMAC256(secretPass);
        flag = ChangeFlagUtils.Flags[ChangeFlagUtils.HUMAN];
    }
}
