package org.eoa.projectbudget.rest_controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.MenuDto;
import org.eoa.projectbudget.dto.constraint.Constraint;
import org.eoa.projectbudget.entity.Menu;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.menu.ConfigService;
import org.eoa.projectbudget.service.menu.MenuService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.factory.LoginConfigOutFactory;
import org.eoa.projectbudget.utils.factory.MenuDtoOutFactory;
import org.eoa.projectbudget.utils.factory.MenuOutFactory;
import org.eoa.projectbudget.utils.factory.PageConfigOutFactory;
import org.eoa.projectbudget.vo.out.MenuDtoOut;
import org.eoa.projectbudget.vo.out.MenuOut;
import org.eoa.projectbudget.vo.out.PageConfigOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:59
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: MenuFrontController
 * @Description: 菜单模块前端接口
 * @Version: 1.0
 */

@RestController
@Tag(name = "菜单模块前端接口")
@RequestMapping("/api/v1/menu/front")
@CrossOrigin
public class MenuFrontController {

    @Autowired
    CacheService cacheService;
    @Autowired
    ConfigService configService;
    @Autowired
    MenuService menuService;


    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Autowired
    MenuOutFactory menuOutFactory;
    @Autowired
    MenuDtoOutFactory menuDtoOutFactory;
    @Autowired
    LoginConfigOutFactory loginConfigOutFactory;
    @Autowired
    PageConfigOutFactory pageConfigOutFactory;
    @Autowired
    OrganizationService organizationService;

    private final long USER_ID_CACHE = 0L;

    @GetMapping(value = "/menu")
    @Operation(summary = "获取根菜单")
    public Vo<List<MenuOut>> getMenusRoot(@RequestAttribute("HumanDto") HumanDto humanDto) {
        List<MenuOut> outs;
        String method = "root";
        MenuOut[] cache = cacheService.getCache(ChangeFlagUtils.MENU,method, humanDto.getDataId(), MenuOut[].class);
        if (cache == null) {
            List<Menu> menuList = menuService.getMenuList(new QueryWrapper<Menu>().isNull("belongContent").orderByAsc("viewNo"), humanDto.getDataId());
            outs = menuOutFactory.outs(menuList.stream().filter(menu -> {
                Constraint constraint = AuthorityUtils.getConstraint(menu.getShareAuthority());
                return AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(menu.getCreator(),null),constraint);
            }).collect(Collectors.toList()));
            cacheService.setCache(ChangeFlagUtils.MENU,method, humanDto.getDataId(), outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(outs,outs.size());
    }

    @GetMapping(value = "/menu/{id}")
    @Operation(summary = "获取菜单树")
    public Vo<MenuDtoOut> getMenuDtoOut(@RequestAttribute("HumanDto")HumanDto humanDto,
    @PathVariable Long id) throws EoaException {
        Boolean view = cacheService.getCache(ChangeFlagUtils.MENU,id+"view", humanDto.getDataId(), Boolean.class);
        MenuDto menuDto = null;
        if (view == null) {
            menuDto = menuService.getMenuDto(id, humanDto.getDataId());
            Constraint constraint = AuthorityUtils.getConstraint(menuDto.getShareAuthority());
            view = AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(menuDto.getCreator(),null),constraint);
            cacheService.setCache(ChangeFlagUtils.MENU,id+"view", humanDto.getDataId(), view);
        }
        MenuDtoOut out = cacheService.getCache(ChangeFlagUtils.MENU,id+"dtoOut", humanDto.getDataId(), MenuDtoOut.class);
        if (out == null) {
            menuDto =menuDto!=null?menuDto: menuService.getMenuDto(id, humanDto.getDataId());
            menuDto.filter(humanDto, organizationService);
            out = menuDtoOutFactory.out(menuDto);
            cacheService.setCache(ChangeFlagUtils.MENU,id+"dtoOut",USER_ID_CACHE,out);
        }
        return new Vo<>(out);

    }

    @GetMapping(value = "/pageConfig/onUse")
    @Operation(summary = "获取页面配置")
    @SuppressWarnings("Duplicates")
    public Vo<PageConfigOut> getPageConfig(@RequestAttribute("HumanDto")HumanDto humanDto) throws EoaException {
        PageConfigOut out = cacheService.getCache(ChangeFlagUtils.PAGE_CONFIG,"onUse", USER_ID_CACHE, PageConfigOut.class);
        if (out == null) {
            out = pageConfigOutFactory.out(configService.getPageConfigOnUsed(humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.PAGE_CONFIG,"onUse",USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

}
