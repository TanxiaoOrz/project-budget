package org.eoa.projectbudget.rest_controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
    @Parameters({
            @Parameter(name = "loginName",description = "登录名",required = true,in = ParameterIn.QUERY),
            @Parameter(name = "password",description = "密码",required = true,in = ParameterIn.QUERY)
    })
    public Vo<String> newTokens(@RequestParam("loginName") String loginName, @RequestParam("password") String password)
            throws EoaException, JsonProcessingException {
        HumanDto humanDto = organizationService.getHumanDto(loginName,password);
        cacheService.setCache(flag,method,humanDto.getDataId().toString(),humanDto);
        Tokens tokens = authorityService.getTokens(humanDto.getDataId());
        return new Vo<>(JWT.create().withClaim("tokens",new ObjectMapper().writeValueAsString(tokens)).sign(algorithm));
    }

    @GetMapping
    @Operation(summary = "获取登录页面基本设置")
    public Vo<LoginConfigOut> getLoginConfig() {
        // TODO
        return new Vo<>(null);
    }

    @GetMapping("/check")
    @Operation(summary = "验证token是否登录")
    @Parameter(name = "tokens",description = "tokens字符串",required = true,in = ParameterIn.QUERY)
    public Vo<Boolean> checkToken(@RequestParam("tokens")String tokens) {
        try {
            String jwt = DataProcessUtils.nullToString(tokens);
            String tokensString = JWT.require(algorithm).build().verify(jwt).getClaim("tokens").asString();
            Tokens t = new ObjectMapper().readValue(tokensString, Tokens.class);
            authorityService.getUser(t);
            return new Vo<>(true);
        } catch (ParameterException | JsonProcessingException | LoginException e) {
            return new Vo<>(false);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        algorithm = Algorithm.HMAC256(secretPass);
        flag = ChangeFlagUtils.Flags[ChangeFlagUtils.HUMAN];
    }
}
