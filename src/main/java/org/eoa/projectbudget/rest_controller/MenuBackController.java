package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.LoginConfig;
import org.eoa.projectbudget.entity.Menu;
import org.eoa.projectbudget.entity.PageConfig;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.menu.ConfigService;
import org.eoa.projectbudget.service.menu.MenuService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.LoginConfigOutFactory;
import org.eoa.projectbudget.utils.factory.MenuOutFactory;
import org.eoa.projectbudget.utils.factory.PageConfigOutFactory;
import org.eoa.projectbudget.vo.in.LoginConfigIn;
import org.eoa.projectbudget.vo.in.MenuIn;
import org.eoa.projectbudget.vo.in.PageConfigIn;
import org.eoa.projectbudget.vo.out.LoginConfigOut;
import org.eoa.projectbudget.vo.out.MenuOut;
import org.eoa.projectbudget.vo.out.PageConfigOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:59
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: MenuBackController
 * @Description: 菜单模块后端接口
 * @Version: 1.0
 */

@RestController
@Tag(name = "菜单模块后端接口")
@RequestMapping("/api/v1/menu/back")
@CrossOrigin
public class MenuBackController {
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
    LoginConfigOutFactory loginConfigOutFactory;
    @Autowired
    PageConfigOutFactory pageConfigOutFactory;


    private final long USER_ID_CACHE = 0L;


    @GetMapping(value = "/menu")
    @Operation(summary = "获取菜单")
    @SuppressWarnings("Duplicates")
    public Vo<List<MenuOut>> getMenuList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                         HttpServletRequest request) throws EoaException {
        FilterUtils<Menu> filter = new FilterUtils<>(request.getParameterMap(), Menu.class);
        List<MenuOut> outs;
        String method = filter.getDescription();
        MenuOut[] cache = cacheService.getCache(ChangeFlagUtils.MENU,method, USER_ID_CACHE, MenuOut[].class);
        if (cache == null) {
            outs = menuOutFactory.outs(menuService.getMenuList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.MENU,method, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/menu/{id}")
    @Operation(summary = "获取菜单")
    @SuppressWarnings("Duplicates")
    public Vo<MenuOut> getMenu(@RequestAttribute("HumanDto")HumanDto humanDto,
                               @PathVariable Long id) throws EoaException {
        MenuOut out = cacheService.getCache(ChangeFlagUtils.MENU,id.toString(), USER_ID_CACHE, MenuOut.class);
        if (out == null) {
            out = menuOutFactory.out(menuService.getMenu(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.MENU,id.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);

    }

    @PostMapping(value = "/menu")
    @Operation(summary = "新建菜单")
    public Vo<Long> newMenu(@RequestAttribute("HumanDto")HumanDto humanDto,
                            @RequestBody MenuIn menu) throws ParameterException {
        Long id = menuService.newMenu(menu.toEntity(null), humanDto.getDataId());
        if (id!=null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.MENU);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }


    @PutMapping(value = "/menu/{id}")
    @Operation(summary = "修改菜单")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateMenu(@RequestAttribute("HumanDto")HumanDto humanDto,
                                 @RequestBody MenuIn menu,
                                 @PathVariable Long id) throws ParameterException {

        Integer update = menuService.updateMenu(menu.toEntity(id), humanDto.getDataId());
        if (update != 0) {
            changeFlagUtils.freshDate(ChangeFlagUtils.MENU);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping(value = "/menu/{id}")
    @Operation(summary = "废弃菜单")
    @Parameter(name = "id",description = "菜单编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropMenu(@RequestAttribute("HumanDto")HumanDto humanDto,
                               @PathVariable Long id) {

        Integer deletes = menuService.deleteMenu(id, humanDto.getDataId());
        if (deletes != 0) {
            changeFlagUtils.freshDate(ChangeFlagUtils.MENU);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

    @GetMapping(value = "/loginConfig")
    @Operation(summary = "获取登录配置")
    @SuppressWarnings("Duplicates")
    public Vo<List<LoginConfigOut>> getLoginConfigList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                       HttpServletRequest request) throws EoaException {
        FilterUtils<LoginConfig> filter = new FilterUtils<>(request.getParameterMap(), LoginConfig.class);
        List<LoginConfigOut> outs;
        String method = filter.getDescription();
        LoginConfigOut[] cache = cacheService.getCache(ChangeFlagUtils.LOGIN_CONFIG,method, USER_ID_CACHE, LoginConfigOut[].class);
        if (cache == null) {
            outs = loginConfigOutFactory.outs(configService.getLoginConfigList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.LOGIN_CONFIG,method, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/loginConfig/{id}")
    @Operation(summary = "获取登录配置")
    @SuppressWarnings("Duplicates")
    public Vo<LoginConfigOut> getLoginConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                             @PathVariable Long id) throws EoaException {
        LoginConfigOut out = cacheService.getCache(ChangeFlagUtils.LOGIN_CONFIG,id.toString(), USER_ID_CACHE, LoginConfigOut.class);
        if (out == null) {
            out = loginConfigOutFactory.out(configService.getLoginConfig(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.LOGIN_CONFIG,id.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @PostMapping(value = "/loginConfig")
    @Operation(summary = "新建登录配置")
    public Vo<Long> newLoginConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                   @RequestBody LoginConfigIn loginConfig) throws ParameterException {
        Long id = configService.newLoginConfig(loginConfig.toEntity(null), humanDto.getDataId());
        if (id!=null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.LOGIN_CONFIG);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }


    @PutMapping(value = "/loginConfig/{id}")
    @Operation(summary = "修改登录配置")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateLoginConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                        @RequestBody LoginConfigIn loginConfig,
                                        @PathVariable Long id) throws ParameterException {

        Integer update = configService.updateLoginConfig(loginConfig.toEntity(id), humanDto.getDataId());
        if (update!= 0) {
            changeFlagUtils.freshDate(ChangeFlagUtils.LOGIN_CONFIG);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping(value = "/loginConfig/{id}")
    @Operation(summary = "废弃登录配置")
    @Parameter(name = "id",description = "登录配置编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropLoginConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                      @PathVariable Long id) {

        Integer deletes = configService.deleteLoginConfig(id, humanDto.getDataId());
        if (deletes!= 0) {
            changeFlagUtils.freshDate(ChangeFlagUtils.LOGIN_CONFIG);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

    @GetMapping(value = "/pageConfig")
    @Operation(summary = "获取页面配置")
    @SuppressWarnings("Duplicates")
    public Vo<List<PageConfigOut>> getPageConfigList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                     HttpServletRequest request) throws EoaException {
        FilterUtils<PageConfig> filter = new FilterUtils<>(request.getParameterMap(), PageConfig.class);
        List<PageConfigOut> outs;
        String method = filter.getDescription();
        PageConfigOut[] cache = cacheService.getCache(ChangeFlagUtils.PAGE_CONFIG,method, USER_ID_CACHE, PageConfigOut[].class);
        if (cache == null) {
            outs = pageConfigOutFactory.outs(configService.getPageConfigList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.PAGE_CONFIG,method, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping(value = "/pageConfig/{id}")
    @Operation(summary = "获取页面配置")
    @SuppressWarnings("Duplicates")
    public Vo<PageConfigOut> getPageConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                           @PathVariable Long id) throws EoaException {
        PageConfigOut out = cacheService.getCache(ChangeFlagUtils.PAGE_CONFIG,id.toString(), USER_ID_CACHE, PageConfigOut.class);
        if (out == null) {
            out = pageConfigOutFactory.out(configService.getPageConfig(id, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.PAGE_CONFIG,id.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @PostMapping(value = "/pageConfig")
    @Operation(summary = "新建页面配置")
    public Vo<Long> newPageConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                  @RequestBody PageConfigIn pageConfig) throws ParameterException {
        Long id = configService.newPageConfig(pageConfig.toEntity(null), humanDto.getDataId());
        if (id!=null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.PAGE_CONFIG);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }


    @PutMapping(value = "/pageConfig/{id}")
    @Operation(summary = "修改页面配置")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updatePageConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                       @RequestBody PageConfigIn pageConfig,
                                       @PathVariable Long id) throws ParameterException {

        Integer update = configService.updatePageConfig(pageConfig.toEntity(id), humanDto.getDataId());
        if (update!= 0) {
            changeFlagUtils.freshDate(ChangeFlagUtils.PAGE_CONFIG);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping(value = "/pageConfig/{id}")
    @Operation(summary = "废弃页面配置")
    @Parameter(name = "id",description = "页面配置编号",required = true,in = ParameterIn.PATH)
    public Vo<String> dropPageConfig(@RequestAttribute("HumanDto")HumanDto humanDto,
                                     @PathVariable Long id) {

        Integer deletes = configService.deletePageConfig(id, humanDto.getDataId());
        if (deletes!= 0) {
            changeFlagUtils.freshDate(ChangeFlagUtils.PAGE_CONFIG);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

}
