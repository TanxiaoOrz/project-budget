package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Authority;
import org.eoa.projectbudget.entity.Character;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.AuthorityService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.CharacterOutFactory;
import org.eoa.projectbudget.vo.out.CharacterOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/27 16:50
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: AuthorityFrontController
 * @Description: 前端的权限对象获取控制器
 * @Version: 1.0
 **/

@RestController
@Tag(name = "前端的权限对象获取控制器")
@CrossOrigin
@RequestMapping("/api/v1/front/authority")
public class AuthorityFrontController {

    @Autowired
    CacheService cacheService;

    @Autowired
    AuthorityService authorityService;
    @Autowired
    CharacterOutFactory characterOutFactory;

    static final Long USER_ID_CACHE = 0L;

    @GetMapping("/character")
    @Operation(description = "获取角色列表")
    public Vo<List<CharacterOut>> getCharacterList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                                   HttpServletRequest request) throws EoaException {
        FilterUtils<Character> filter = new FilterUtils<>(request.getParameterMap(),Character.class);
        String method = filter.getDescription();
        List<CharacterOut> outs;
        CharacterOut[] cache = cacheService.getCache(ChangeFlagUtils.CHARACTER, method, USER_ID_CACHE, CharacterOut[].class);
        if (cache == null) {
            outs = characterOutFactory.outs(authorityService.getAllCharacter(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.CHARACTER,method, USER_ID_CACHE, outs);
        } else {
            outs = List.of(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping("/authority")
    @Operation(description = "获取权限列表")
    public Vo<List<Authority>> getAuthorityList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                          HttpServletRequest request) throws EoaException {
        FilterUtils<Authority> filter = new FilterUtils<>(request.getParameterMap(),Authority.class);
        String method = filter.getDescription();
        List<Authority> outs;
        Authority[] cache = cacheService.getCache(ChangeFlagUtils.AUTHORITY, method, USER_ID_CACHE, Authority[].class);
        if (cache == null) {
            outs = authorityService.getAllAuthority(filter.getWrapper(), humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.AUTHORITY,method, USER_ID_CACHE, outs);
        } else {
            outs = List.of(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }
}
