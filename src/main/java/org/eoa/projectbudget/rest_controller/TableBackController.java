package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.table_module.ModuleService;
import org.eoa.projectbudget.service.table_module.impl.TableEntityServiceImpl;
import org.eoa.projectbudget.service.table_module.impl.TableViewServiceImpl;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.ColumnOutFactory;
import org.eoa.projectbudget.utils.factory.ModuleOutFactory;
import org.eoa.projectbudget.utils.factory.TableFactory;
import org.eoa.projectbudget.vo.in.ColumnIn;
import org.eoa.projectbudget.vo.in.ModuleIn;
import org.eoa.projectbudget.vo.in.TableIn;
import org.eoa.projectbudget.vo.out.ColumnOut;
import org.eoa.projectbudget.vo.out.ModuleOut;
import org.eoa.projectbudget.vo.out.TableOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/10 16:48
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: TableBackController
 * @Description: 表单模块后端控制器
 * @Version 1.5
 */

@RestController
@RequestMapping("/api/v1/table/back")
@Tag(name = "表单应用后端接口",description = "需要拥有表单权限的登录用户进行操作")
@CrossOrigin
public class TableBackController {
    public static final Long USER_ID_CACHE = 0L;
    @Autowired
    CacheService cacheService;
    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Autowired
    ModuleService moduleService;
    @Autowired
    ModuleOutFactory moduleOutFactory;
    @Autowired
    TableEntityServiceImpl entityService;
    @Autowired
    TableViewServiceImpl viewService;
    @Autowired
    TableFactory tableFactory;
    @Autowired
    ColumnOutFactory columnFactory;

    @Operation(summary = "获取所有模块信息")
    @GetMapping("/module")
    public Vo<List<ModuleOut>> getAllModule(@RequestAttribute("HumanDto") HumanDto humanDto,
                                            HttpServletRequest request) throws EoaException {
        FilterUtils<ModuleView> filter = new FilterUtils<>(request.getParameterMap(), ModuleView.class);
        List<ModuleOut> outs;
        String method = filter.getDescription();
        ModuleOut[] cache = cacheService.getCache(ChangeFlagUtils.MODULE, method, USER_ID_CACHE, ModuleOut[].class);
        if (cache == null) {
            outs = moduleOutFactory.outs(moduleService.getAll(humanDto.getDataId(),filter.getWrapper()));
            cacheService.setCache(ChangeFlagUtils.Flags[ChangeFlagUtils.MODULE], method, USER_ID_CACHE,outs);
        }else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @Operation(summary = "获取单个模块信息")
    @GetMapping("/module/{id}")
    public Vo<ModuleOut> getModule(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable("id") Long id) throws EoaException {
        final String method = id.toString();
        ModuleOut cache = cacheService.getCache(ChangeFlagUtils.MODULE, method, USER_ID_CACHE, ModuleOut.class);
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
        changeFlagUtils.freshDate(ChangeFlagUtils.MODULE);
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

    @Operation(summary = "获取表单列表")
    @GetMapping("/table")
    @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY)
    public Vo<List<TableOut>> getTableList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                           @RequestParam("isVirtual") Boolean isVirtual,
                                           HttpServletRequest request) throws EoaException {
        List<TableOut> tableOuts;
        List<TableOut> returns;
        TableOut[] cache;
        if (isVirtual) {
            FilterUtils<TableView> filter = new FilterUtils<>(request.getParameterMap(), TableView.class);
            String method = filter.getDescription();
            cache = cacheService.getCache(ChangeFlagUtils.TABLE_VIEW, method,USER_ID_CACHE,TableOut[].class);
            if (cache == null) {
                tableOuts = tableFactory.outs(viewService.getTableList(filter.getWrapper(),humanDto.getDataId()));
                cacheService.setCache(ChangeFlagUtils.TABLE_VIEW,method,USER_ID_CACHE,tableOuts);
            } else {
                tableOuts = List.of(cache);
            }
            returns = filter.filt(tableOuts);
        }else {
            FilterUtils<TableEntity> filter = new FilterUtils<>(request.getParameterMap(), TableEntity.class);
            String method = filter.getDescription();
            cache = cacheService.getCache(ChangeFlagUtils.TABLE_ENTITY, method,USER_ID_CACHE,TableOut[].class);
            if (cache == null) {
                tableOuts = tableFactory.outs(entityService.getTableList(filter.getWrapper(),humanDto.getDataId()));
                cacheService.setCache(ChangeFlagUtils.TABLE_ENTITY,method,USER_ID_CACHE,tableOuts);
            } else {
                tableOuts = List.of(cache);
            }
            returns = filter.filt(tableOuts);
        }
        return new Vo<>(returns, tableOuts.size());
    }

    @Operation(summary = "获取单个表单")
    @GetMapping("/table/{id}")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "id",description = "表单编号",required = true,in = ParameterIn.PATH)
    })
    public Vo<TableOut> getTable(@PathVariable Long id,
                                 @RequestParam("isVirtual") Boolean isVirtual,
                                 @RequestAttribute("HumanDto")HumanDto humanDto) throws EoaException {
        TableOut out;
        String method = id.toString();
        out = cacheService.getCache(isVirtual?ChangeFlagUtils.TABLE_VIEW:ChangeFlagUtils.TABLE_ENTITY,
                method,USER_ID_CACHE,TableOut.class);
        if (out == null) {
            out = tableFactory.out(isVirtual? viewService.getTableById(id, humanDto.getDataId()) : entityService.getTableById(id, humanDto.getDataId()));
            cacheService.setCache(isVirtual?ChangeFlagUtils.TABLE_VIEW:ChangeFlagUtils.TABLE_ENTITY,method,USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @Operation(summary = "创建表单")
    @PostMapping("/table")
    public Vo<Long> newTable(@RequestAttribute("HumanDto") HumanDto humanDto,
                             @RequestBody TableIn tableIn) throws EoaException {
        Long id;
        if (tableIn.getVirtual()) {
            id = viewService.createTable(tableIn.toEntity(null), humanDto.getDataId());
            changeFlagUtils.freshDate(ChangeFlagUtils.TABLE_VIEW);
        }else {
            id = entityService.createTable(tableIn.toEntity(null), humanDto.getDataId());
            changeFlagUtils.freshDate(ChangeFlagUtils.TABLE_ENTITY);
        }
        if (id!=null)
            return new Vo<>(id);
        else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }

    @Operation(summary = "修改表单")
    @PutMapping("/table/{id}")
    @Parameters({
            @Parameter(name = "id",description = "表单编号",required = true,in = ParameterIn.PATH)
    })
    public Vo<String> updateTable(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody TableIn tableIn,
                                @PathVariable Long id) throws EoaException {
        Integer update;
        int flag;
        if (tableIn.getVirtual()) {
            update = viewService.updateTable(tableIn.toEntity(id), humanDto.getDataId());
            flag = (ChangeFlagUtils.TABLE_VIEW);
        }else {
            update = entityService.updateTable(tableIn.toEntity(id), humanDto.getDataId());
            flag = (ChangeFlagUtils.TABLE_ENTITY);
        }
        if (update==1) {
            changeFlagUtils.freshDate(flag);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @Operation(summary = "删除表单")
    @DeleteMapping("/table/{id}")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "id",description = "表单编号",required = true,in = ParameterIn.PATH)
    })
    public Vo<String> deleteTable(@RequestParam("isVirtual") Boolean isVirtual,
                                   @RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable Long id) {
        Integer delete;
        int flag;
        if (isVirtual) {
            delete = viewService.deleteTable(id, humanDto.getDataId());
            flag = ChangeFlagUtils.TABLE_VIEW;
        }else {
            delete = entityService.deleteTable(id, humanDto.getDataId());
            flag = ChangeFlagUtils.TABLE_ENTITY;
        }
        if (delete==1) {
            changeFlagUtils.freshDate(flag);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }
    @Operation(summary = "获取字段列表")
    @GetMapping("/column")
    @Parameter(name = "isVirtual", description = "是否虚拟视图字段", required = true, in = ParameterIn.QUERY)
    public Vo<List<ColumnOut>> getColumnList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                             @RequestParam("isVirtual") Boolean isVirtual,
                                             HttpServletRequest request) throws EoaException {
        List<ColumnOut> outs;
        List<ColumnOut> returns;
        ColumnOut[] cache;
        if (isVirtual) {
            FilterUtils<ColumnView> filters = new FilterUtils<>(request.getParameterMap(),ColumnView.class);
            String method = filters.getDescription();
            cache = cacheService.getCache(ChangeFlagUtils.COLUMN_VIEW,method,USER_ID_CACHE,ColumnOut[].class);
            if (cache == null) {
                outs = columnFactory.outs(viewService.getColumnList(filters.getWrapper(), humanDto.getDataId()));
                cacheService.setCache(ChangeFlagUtils.COLUMN_VIEW,method,USER_ID_CACHE,outs);
            }else {
                outs = List.of(cache);
            }
            returns = filters.filt(outs);
        }else {
            FilterUtils<ColumnEntity> filters = new FilterUtils<>(request.getParameterMap(),ColumnEntity.class);
            String method = filters.getDescription();
            cache = cacheService.getCache(ChangeFlagUtils.COLUMN_ENTITY,method,USER_ID_CACHE,ColumnOut[].class);
            if (cache == null) {
                outs = columnFactory.outs(entityService.getColumnList(filters.getWrapper(), humanDto.getDataId()));
                cacheService.setCache(ChangeFlagUtils.COLUMN_ENTITY,method,USER_ID_CACHE,outs);
            }else {
                outs = List.of(cache);
            }
            returns = filters.filt(outs);
        }
        return new Vo<>(returns,outs.size());
    }

    @Operation(summary = "获取单个字段")
    @GetMapping("/column/{id}")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    })
    public Vo<ColumnOut> getColumn(@RequestParam("isVirtual") Boolean isVirtual,
                                   @RequestAttribute("HumanDto") HumanDto humanDto,
                                   @PathVariable Long id) throws EoaException {
        ColumnOut out;
        String method = id.toString();
        if (isVirtual) {
            out = cacheService.getCache(ChangeFlagUtils.COLUMN_VIEW,method,USER_ID_CACHE,ColumnOut.class);
            if (out == null) {
                out = columnFactory.out(viewService.getColumnById(id, humanDto.getDataId()));
                cacheService.setCache(ChangeFlagUtils.COLUMN_VIEW,method, USER_ID_CACHE,out);
            }
        } else {
            out = cacheService.getCache(ChangeFlagUtils.COLUMN_ENTITY,method,USER_ID_CACHE,ColumnOut.class);
            if (out == null) {
                out = columnFactory.out(entityService.getColumnById(id, humanDto.getDataId()));
                cacheService.setCache(ChangeFlagUtils.COLUMN_ENTITY,method, USER_ID_CACHE,out);
            }
        }
        return new Vo<>(out);
    }

    @Operation(summary = "新建字段")
    @PostMapping("/column")
    public Vo<Long> newColumn(@RequestAttribute("HumanDto") HumanDto humanDto,
                              @RequestBody ColumnIn columnIn) throws ParameterException {
        Long id;
        if (columnIn.getVirtual()) {
            id = viewService.addColumn(columnIn.toEntity(null), humanDto.getDataId());
        } else {
            id = entityService.addColumn(columnIn.toEntity(null), humanDto.getDataId());
        }
        if (id != null)
            return new Vo<>(id);
        else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }

    @Operation(summary = "更新字段属性")
    @PutMapping("/column/{id}")
    @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    public Vo<String> updateColumn(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @RequestBody ColumnIn columnIn,
                                   @PathVariable Long id) throws ParameterException {
        Integer update;
        int flag;
        if (columnIn.getVirtual()) {
            update = viewService.alterColumn(columnIn.toEntity(id), humanDto.getDataId());
            flag = ChangeFlagUtils.COLUMN_VIEW;
        } else {
            update = viewService.alterColumn(columnIn.toEntity(id), humanDto.getDataId());
            flag = ChangeFlagUtils.COLUMN_ENTITY;
        }
        if (update==1) {
            changeFlagUtils.freshDate(flag);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @Operation(summary = "更新字段属性")
    @DeleteMapping("/column/{id}")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "id",description = "字段编号",required = true,in = ParameterIn.PATH)
    })
    public Vo<String> deleteColumn(@RequestAttribute("HumanDto") HumanDto humanDto,
                                   @RequestParam("isVirtual") Boolean isVirtual,
                                   @PathVariable Long id) throws ParameterException {
        Integer delete;
        int flag;

        if (isVirtual) {
            delete = viewService.deleteColumn(id, humanDto.getDataId());
            flag = ChangeFlagUtils.COLUMN_VIEW;
        } else {
            delete = entityService.deleteColumn(id, humanDto.getDataId());
            flag = ChangeFlagUtils.COLUMN_ENTITY;
        }
        if (delete==1) {
            changeFlagUtils.freshDate(flag);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

}
