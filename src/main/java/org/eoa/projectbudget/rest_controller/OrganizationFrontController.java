package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.entity.Section;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.DepartOutFactory;
import org.eoa.projectbudget.utils.factory.HumanOutFactory;
import org.eoa.projectbudget.utils.factory.SectionOutFactory;
import org.eoa.projectbudget.vo.out.DepartOut;
import org.eoa.projectbudget.vo.out.HumanOut;
import org.eoa.projectbudget.vo.out.SectionOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/29 19:00
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: OrganizationFrontController
 * @Description: 组织结构前端控制器
 * @Version: 1.0
 */

@RestController
@Tag(name = "组织结构前端控制器")
@CrossOrigin
@RequestMapping("/api/v1/organization/front")
public class OrganizationFrontController {
    public static final Long USER_ID_CACHE = 0L;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    CacheService cacheService;
    @Autowired
    HumanOutFactory humanOutFactory;
    @Autowired
    DepartOutFactory departOutFactory;
    @Autowired
    SectionOutFactory sectionOutFactory;

    @GetMapping("/human")
    @Operation(summary = "获取人员列表")
    public Vo<List<HumanOut>> getHumanList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                           HttpServletRequest request) throws EoaException {
        FilterUtils<HumanResourceView> filter = new FilterUtils<>(request.getParameterMap(), HumanResourceView.class);
        List<HumanOut> outs;
        String methods = filter.getDescription();
        int flag = ChangeFlagUtils.HUMAN;
        HumanOut[] cache = cacheService.getCache(flag, methods, USER_ID_CACHE, HumanOut[].class);
        if (cache == null) {
            outs = humanOutFactory.outs(organizationService.getHumanList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(flag,methods,USER_ID_CACHE,outs);
        } else {
            outs =  Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping("/depart")
    @Operation(summary = "获取部门列表")
    public Vo<List<DepartOut>> getDepartList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                             HttpServletRequest request) throws EoaException {
        FilterUtils<Depart> filter = new FilterUtils<>(request.getParameterMap(), Depart.class);
        List<DepartOut> outs;
        String methods = filter.getDescription();
        int flag = ChangeFlagUtils.DEPART;
        DepartOut[] cache = cacheService.getCache(flag, methods, USER_ID_CACHE, DepartOut[].class);
        if (cache == null) {
            outs = departOutFactory.outs(organizationService.getDepartList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(flag,methods,USER_ID_CACHE,outs);
        } else {
            outs =  Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping("/section")
    @Operation(summary = "获取分部列表")
    public Vo<List<SectionOut>> getSectionList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                               HttpServletRequest request) throws EoaException {
        FilterUtils<Section> filter = new FilterUtils<>(request.getParameterMap(), Section.class);
        List<SectionOut> outs;
        String methods = filter.getDescription();
        int flag = ChangeFlagUtils.SECTION;
        SectionOut[] cache = cacheService.getCache(flag, methods, USER_ID_CACHE, SectionOut[].class);
        if (cache == null) {
            outs = sectionOutFactory.outs(organizationService.getSectionList(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(flag,methods,USER_ID_CACHE,outs);
        } else {
            outs =  Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }
}
