package org.eoa.projectbudget.rest_controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import org.eoa.projectbudget.vo.in.DepartIn;
import org.eoa.projectbudget.vo.in.HumanIn;
import org.eoa.projectbudget.vo.in.SectionIn;
import org.eoa.projectbudget.vo.out.DepartOut;
import org.eoa.projectbudget.vo.out.HumanOut;
import org.eoa.projectbudget.vo.out.SectionOut;
import org.eoa.projectbudget.vo.out.ViewData.GraphNode;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/17 22:20
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: OrganizationBackController
 * @Description: 组织结构后端控制器
 * @Version: 1.0
 */

@RestController
@Tag(name = "组织结构后端控制器")
@CrossOrigin
@RequestMapping("/api/v1/organization/back")
public class OrganizationBackController {

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

    @PostMapping("/human")
    @Operation(summary = "新建人员")
    public Vo<Long> newHuman(@RequestAttribute("HumanDto") HumanDto humanDto,
                                 @RequestBody HumanIn human) {
        Long id = organizationService.newHuman(human.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.HUMAN);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }

    @PutMapping("/human/{dataId}")
    @Operation(summary = "修改人员")
    @Parameter(name = "dataId", description = "人员编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateHuman(@RequestAttribute("HumanDto") HumanDto humanDto,
                                      @PathVariable("dataId")Long dataId,
                                      @RequestBody HumanIn human) {
        Integer update = organizationService.updateHuman(human.toEntity(dataId), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.HUMAN);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping("/human/{dataId}")
    @Operation(summary = "删除人员")
    @Parameter(name = "dataId", description = "人员编号", required = true, in = ParameterIn.PATH)
    public Vo<String> deleteHuman(@RequestAttribute("HumanDto") HumanDto humanDto,
                                      @PathVariable("dataId")Long dataId) {
        Integer deletes = organizationService.dropHuman(dataId, humanDto.getDataId());
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.HUMAN);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }



    @GetMapping("/human")
    @Operation(summary = "获取人员列表")
    public Vo<List<HumanOut>> getHumanList(@RequestAttribute("HumanDto") HumanDto humanDto,
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

    @GetMapping("/depart/{dataId}")
    @Operation(summary = "获取指定部门数据")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<DepartOut> getDepart(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable Long dataId) {
        DepartOut out = cacheService.getCache(ChangeFlagUtils.DEPART,dataId.toString(),USER_ID_CACHE,DepartOut.class);
        if (out == null) {
            out = departOutFactory.out(organizationService.getDepart(dataId, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.DEPART, dataId.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/depart")
    @Operation(summary = "新建部门")
    public Vo<Long> newDepart(@RequestAttribute("HumanDto") HumanDto humanDto,
                              @RequestBody DepartIn depart) {
        Long id = organizationService.newDepart(depart.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.DEPART);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }

    @PutMapping("/depart/{dataId}")
    @Operation(summary = "修改部门")
    @Parameter(name = "dataId", description = "分部编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateDepart(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable("dataId")Long dataId,
                                   @RequestBody DepartIn depart) {
        Integer update = organizationService.updateDepart(depart.toEntity(dataId), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.DEPART);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping("/depart/{dataId}")
    @Operation(summary = "删除部门")
    @Parameter(name = "dataId", description = "分部编号", required = true, in = ParameterIn.PATH)
    @Parameter(name = "dataId", description = "是否递归删除子部门", in = ParameterIn.PATH)
    public Vo<String> deleteDepart(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable("dataId")Long dataId,
                                   @RequestParam(required = false,defaultValue = "false") Boolean recursion) {
        Integer deletes = organizationService.dropDepart(dataId, humanDto.getDataId(),recursion);
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.DEPART);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
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

    @GetMapping("/section/{dataId}")
    @Operation(summary = "获取指定分部数据")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<SectionOut> getSection(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable Long dataId) {
        SectionOut out = cacheService.getCache(ChangeFlagUtils.SECTION,dataId.toString(),USER_ID_CACHE,SectionOut.class);
        if (out == null) {
            out = sectionOutFactory.out(organizationService.getSection(dataId, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.SECTION, dataId.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/section")
    @Operation(summary = "新建分部")
    public Vo<Long> newSection(@RequestAttribute("HumanDto") HumanDto humanDto,
                               @RequestBody SectionIn section) {
        Long id = organizationService.newSection(section.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SECTION);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
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

    @DeleteMapping("/section/{dataId}")
    @Operation(summary = "删除分部")
    @Parameter(name = "dataId", description = "是否递归删除子部门及子分部", in = ParameterIn.PATH)
    @Parameter(name = "dataId", description = "分部编号", required = true, in = ParameterIn.PATH)

    public Vo<String> deleteSection(@RequestAttribute("HumanDto") HumanDto humanDto,
                                    @PathVariable("dataId")Long dataId,
                                    @RequestParam(required = false,defaultValue = "false") Boolean recursion) {
        Integer deletes = organizationService.dropSection(dataId, humanDto.getDataId(),recursion);
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.SECTION);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
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

    @GetMapping("/tree")
    @Operation(summary = "获取组织架构树图")
    public  Vo<GraphNode> getOrganizationTree(@RequestAttribute("HumanDto")HumanDto humanDto) throws EoaException {
        String methods = "";

        List<SectionOut> sectionOuts;
        int sectionFlag = ChangeFlagUtils.SECTION;
        SectionOut[] sectionCache = cacheService.getCache(sectionFlag, methods, USER_ID_CACHE, SectionOut[].class);
        if (sectionCache == null) {
            sectionOuts = sectionOutFactory.outs(organizationService.getSectionList(new QueryWrapper<>(), humanDto.getDataId()));
            cacheService.setCache(sectionFlag,methods,USER_ID_CACHE,sectionOuts);
        } else {
            sectionOuts =  Arrays.asList(sectionCache);
        }

        List<DepartOut> departOuts;
        int flag = ChangeFlagUtils.DEPART;
        DepartOut[] departCache = cacheService.getCache(flag, methods, USER_ID_CACHE, DepartOut[].class);
        if (departCache == null) {
            departOuts = departOutFactory.outs(organizationService.getDepartList(new QueryWrapper<>(), humanDto.getDataId()));
            cacheService.setCache(flag,methods,USER_ID_CACHE,departOuts);
        } else {
            departOuts =  Arrays.asList(departCache);
        }

        GraphNode base = new GraphNode("0", "总部", null, "sum", null);


        return new Vo<>(base.consist(sectionOuts,departOuts));
    }

}
