package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.AuthorityService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.vo.Tokens;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TokenController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    CacheService cacheService;

    final String flag = "HUMAN";
    final String method = "TOKEN";

    @PostMapping
    public Vo<Tokens> newTokens(@RequestParam("loginName") String loginName, @RequestParam("password") String password)
            throws EoaException {
        HumanDto humanDto = organizationService.getHumanDto(loginName,password);
        cacheService.setCache(flag,method,humanDto.getDataId().toString(),humanDto);
        Tokens tokens = authorityService.getTokens(humanDto.getDataId());
        return new Vo<>(tokens);
    }
}
