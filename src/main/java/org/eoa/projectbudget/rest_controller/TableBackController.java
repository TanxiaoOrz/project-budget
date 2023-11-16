package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.entity.ModuleView;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.table_module.ModuleService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.factory.ModuleOutFactory;
import org.eoa.projectbudget.vo.in.ModuleIn;
import org.eoa.projectbudget.vo.out.ModuleOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/table/back")
@Tag(name = "表单应用后端接口",description = "需要拥有表单权限的登录用户进行操作")
@CrossOrigin
public class TableBackController {
    @Autowired
    CacheService cacheService;
    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Autowired
    ModuleService moduleService;
    @Autowired
    ModuleOutFactory moduleOutFactory;

    @Operation(summary = "获取所有模块信息")
    @GetMapping("/module")
    public Vo<List<ModuleOut>> getAllModule(@RequestAttribute("HumanDto") HumanDto humanDto,
                                            @RequestBody Map<String,String> filter) throws EoaException {
        final String method = filter.toString();
        final Long userIdCache = null;

        List<ModuleOut> outs;

        ModuleOut[] cache = cacheService.getCache(ChangeFlagUtils.MODULE, method, userIdCache, ModuleOut[].class);
        if (cache == null) {
            outs = moduleOutFactory.outs(moduleService.getAll(humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.Flags[ChangeFlagUtils.MODULE], method, userIdCache,outs);
        }else {
            outs = Arrays.asList(cache);
        }

        return new Vo<>(outs);
    }

    @Operation(summary = "获取单个模块信息")
    @GetMapping("/module/{id}")
    public Vo<ModuleOut> getModule(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable("id") Long id) throws EoaException {
        final String method = "one";
        ModuleOut cache = cacheService.getCache(ChangeFlagUtils.MODULE, method, id, ModuleOut.class);
        ModuleOut out;
        if (cache == null) {
            ModuleView moduleView = moduleService.getOne(id, humanDto.getDataId());
            out = moduleOutFactory.out(moduleView);
        }else {
            out = cache;
        }
        return new Vo<>(out);
    }

    @Operation(summary = "创建模块应用")
    @PostMapping("/module")
    public Vo<Long> newModule(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody ModuleIn moduleIn) throws ParameterException {
        ModuleType entity = moduleIn.toEntity(null);
        Long id = moduleService.newOne(entity, humanDto.getDataId());
        if (id!=null)
            return new Vo<>(id);
        else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }

    @Operation(summary = "修改模块描述")
    @PutMapping("/module/{id}")
    @Parameter(name = "id",description = "数据编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateModule(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @RequestBody ModuleIn moduleIn,
                                   @PathVariable("id")Long id) throws ParameterException {
        ModuleType entity = moduleIn.toEntity(id);
        Integer integer = moduleService.update(entity, humanDto.getDataId());
        if (integer==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.MODULE);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @Operation(summary = "删除应用")
    @DeleteMapping("/module/{id}")
    @Parameter(name = "id",description = "数据编号",required = true,in = ParameterIn.PATH)
    public Vo<String> deleteModule(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable("id") Long id) {
        Integer delete = moduleService.delete(id, humanDto.getDataId());
        if (delete==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.MODULE);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

}
