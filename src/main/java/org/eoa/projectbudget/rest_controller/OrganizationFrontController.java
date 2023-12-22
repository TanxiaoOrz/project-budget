package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.entity.Section;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.mapper.DepartMapper;
import org.eoa.projectbudget.mapper.SectionMapper;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.DepartOutFactory;
import org.eoa.projectbudget.utils.factory.HumanOutFactory;
import org.eoa.projectbudget.utils.factory.SectionOutFactory;
import org.eoa.projectbudget.vo.in.DepartIn;
import org.eoa.projectbudget.vo.in.HumanIn;
import org.eoa.projectbudget.vo.in.SectionIn;
import org.eoa.projectbudget.vo.out.DepartOut;
import org.eoa.projectbudget.vo.out.HumanOut;
import org.eoa.projectbudget.vo.out.SectionOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Autowired
    SectionMapper sectionMapper;
    @Autowired
    DepartMapper departMapper;

    @GetMapping("/humanSelf")
    @Operation(summary = "获取当前登录用户")
    public Vo<HumanOut> getSelf (@RequestAttribute("HumanDto") HumanDto humanDto) {
        HumanOut out = cacheService.getCache(ChangeFlagUtils.HUMAN, humanDto.getDataId().toString(),USER_ID_CACHE,HumanOut.class);
        if (out == null) {
            out = humanOutFactory.out(humanDto);
            cacheService.setCache(ChangeFlagUtils.HUMAN,humanDto.getDataId().toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @PutMapping("/human/{dataId}")
    @Operation(summary = "修改人员")
    @Parameter(name = "dataId", description = "人员编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateHuman(@RequestAttribute("HumanDto") HumanDto humanDto,
                                  @PathVariable("dataId")Long dataId,
                                  @RequestBody HumanIn human) {
        if (!Objects.equals(dataId, humanDto.getDataId()))
            throw new AuthorityException(humanDto.getDataId(),"human",dataId,"编辑");
        Integer update = organizationService.updateHuman(human.toEntity(dataId), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.HUMAN);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @PutMapping("/depart/{dataId}")
    @Operation(summary = "修改部门")
    @Parameter(name = "dataId", description = "分部编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateDepart(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable("dataId")Long dataId,
                                   @RequestBody DepartIn depart) {
        if (Objects.equals(departMapper.selectById(dataId).getDepartManager(),humanDto.getDataId()))
            throw new AuthorityException(humanDto.getDataId(),"depart",dataId,"编辑");
        Integer update = organizationService.updateDepart(depart.toEntity(dataId), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.DEPART);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @PutMapping("/section/{dataId}")
    @Operation(summary = "修改分部")
    @Parameter(name = "dataId", description = "分部编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateSection(@RequestAttribute("HumanDto") HumanDto humanDto,
                                    @PathVariable("dataId")Long dataId,
                                    @RequestBody SectionIn section) {
        Integer update = organizationService.updateSection(section.toEntity(dataId), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SECTION);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

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

    @GetMapping("/section/{dataId}")
    @Operation(summary = "获取指定分部数据")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<SectionOut> getSection(@RequestAttribute("HumanDto") HumanDto sectionDto,
                                     @PathVariable Long dataId) {
        SectionOut out = cacheService.getCache(ChangeFlagUtils.SECTION,dataId.toString(),USER_ID_CACHE,SectionOut.class);
        if (out == null) {
            out = sectionOutFactory.out(organizationService.getSection(dataId, sectionDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.SECTION, dataId.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @GetMapping("/depart/{dataId}")
    @Operation(summary = "获取指定分部数据")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<DepartOut> getDepart(@RequestAttribute("HumanDto") HumanDto departDto,
                                   @PathVariable Long dataId) {
        DepartOut out = cacheService.getCache(ChangeFlagUtils.DEPART,dataId.toString(),USER_ID_CACHE,DepartOut.class);
        if (out == null) {
            out = departOutFactory.out(organizationService.getDepart(dataId, departDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.DEPART, dataId.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @GetMapping("/human/{dataId}")
    @Operation(summary = "获取指定人员数据")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<HumanOut> getHuman(@RequestAttribute("HumanDto") HumanDto humanDto,
                                 @PathVariable Long dataId) {
        HumanOut out = cacheService.getCache(ChangeFlagUtils.HUMAN,dataId.toString(),USER_ID_CACHE,HumanOut.class);
        if (out == null) {
            out = humanOutFactory.out(organizationService.getHuman(dataId, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.HUMAN, dataId.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }


}
