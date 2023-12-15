package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.content.ContentService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.service.table_module.FormService;
import org.eoa.projectbudget.service.table_module.impl.FormViewServiceImpl;
import org.eoa.projectbudget.service.table_module.impl.FromEntityServiceImpl;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterFormUtils;
import org.eoa.projectbudget.utils.factory.FileOutFactory;
import org.eoa.projectbudget.utils.factory.FormOutFactory;
import org.eoa.projectbudget.vo.in.FormIn;
import org.eoa.projectbudget.vo.out.FileOut;
import org.eoa.projectbudget.vo.out.FormOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/26 10:30
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: TableFrontController
 * @Description: 表单模块前端接口控制器
 * @Version 1.1
 */

@RestController
@RequestMapping("/api/v1/table/front")
@Tag(name = "表单应用前端接口")
@CrossOrigin
public class TableFrontController {
    @Autowired
    FormViewServiceImpl viewService;
    @Autowired
    FromEntityServiceImpl entityService;
    @Autowired
    FormOutFactory factory;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    CacheService cacheService;
    @Autowired
    ChangeFlagUtils changeFlagUtils;

    @Autowired
    ColumnEntityMapper columnEntityMapper;
    @Autowired
    TableEntityMapper tableEntityMapper;
    @Autowired
    ContentService contentService;
    @Autowired
    FileOutFactory fileOutFactory;

    public final Long USER_ID_CACHE = 0L;


    @GetMapping("/file/{dataId}")
    @Operation(summary = "从表单中获取文件结构")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "dataId",description = "文件编号",required = true,in = ParameterIn.PATH),
            @Parameter(name = "tableId", description = "表单编号", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "formId", description = "数据编号", required = true, in = ParameterIn.QUERY)
    })
    public Vo<FileOut> getFile(@RequestAttribute("HumanDto") HumanDto humanDto,
                               @PathVariable("dataId")Long dataId,
                               @RequestParam("isVirtual")Boolean isVirtual,
                               @RequestParam("tableId")Long tableId,
                               @RequestParam("formId")Long formId) {
        Boolean viewCache = cacheService.getCache(ChangeFlagUtils.Form, formId.toString(), humanDto.getDataId(), Boolean.class);
        if (viewCache == null) {
            if (formId.equals(0L)) {
                viewCache = true;
            } else {
                FormOutDto cache = cacheService.getCache(ChangeFlagUtils.Form, formId + "dto", USER_ID_CACHE, FormOutDto.class);
                if (cache == null) {
                    cache = entityService.getFormOne(tableId, formId, humanDto.getDataId());
                    cacheService.setCache(ChangeFlagUtils.Form,formId + "dto", USER_ID_CACHE,cache);
                }
                viewCache = cache.checkView(organizationService.getHumanDto(cache.getCreator(), null), humanDto);
            }
            cacheService.setCache(ChangeFlagUtils.Form, formId.toString(), humanDto.getDataId(),viewCache);
        }
        if (viewCache) {
            FileOut out = cacheService.getCache(ChangeFlagUtils.FILE, dataId.toString(), USER_ID_CACHE, FileOut.class);
            if (out == null) {
                out = fileOutFactory.out(contentService.getFile(dataId, humanDto.getDataId()));
                cacheService.setCache(ChangeFlagUtils.FILE, dataId.toString(), USER_ID_CACHE, out);
            }
            return new Vo<>(out);
        } else
            throw  new AuthorityException(humanDto.getDataId(), "File来自table="+tableId+"form="+formId+"isVirtual="+isVirtual, dataId, "查看");

    }

    @GetMapping("/authority/{dataId}")
    @Operation(summary = "获取指定实体表单或虚拟视图的操作权限")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "dataId",description = "表单编号",required = false,in = ParameterIn.PATH),
            @Parameter(name = "tableId", description = "表单编号", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "type", description = "操作行为",required = false, in = ParameterIn.QUERY, examples = {
                    @ExampleObject(value = "1",description = "EDIT"),@ExampleObject(value = "0",description = "CREATE"),@ExampleObject(value = "2",description = "DELETE")})
    })
    public Vo<Boolean> getFormAuthority(@RequestAttribute("HumanDto") HumanDto humanDto,
                                        @PathVariable("dataId")Long dataId,
                                        @RequestParam("isVirtual")Boolean isVirtual,
                                        @RequestParam("tableId")Long tableId,
                                        @RequestParam("type")int type) throws EoaException {
        if (isVirtual)
            return new Vo<>(viewService.checkAuthority(tableId,dataId,type,humanDto));
        else
            return new Vo<>(entityService.checkAuthority(tableId,dataId,type,humanDto));
    }

    @GetMapping("/form/{dataId}")
    @Operation(summary = "获取指定实体表单或虚拟视图的指定数据")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "dataId",description = "表单编号",required = true,in = ParameterIn.PATH),
            @Parameter(name = "tableId", description = "表单编号", required = true, in = ParameterIn.QUERY)
    })
    public Vo<FormOut> getFormOne(@RequestAttribute("HumanDto") HumanDto humanDto,
                                  @PathVariable("dataId")Long dataId,
                                  @RequestParam("isVirtual")Boolean isVirtual,
                                  @RequestParam("tableId")Long tableId) throws EoaException {
        if (isVirtual) {
            return new Vo<>(factory.out(viewService.getFormOne(tableId, dataId, humanDto.getDataId())));
        } else {
            Boolean viewCache = cacheService.getCache(ChangeFlagUtils.Form, dataId.toString(), humanDto.getDataId(), Boolean.class);
            boolean create = dataId.equals(0L);
            FormOut out;
            if (viewCache == null) {
                FormOutDto cache = cacheService.getCache(ChangeFlagUtils.Form,dataId+"dto", USER_ID_CACHE, FormOutDto.class);
                if (cache == null) {
                    cache = entityService.getFormOne(tableId, create?null:dataId, humanDto.getDataId());
                }
                out = factory.out(cache);
                viewCache = create?entityService.checkAuthority(tableId, null, FormService.CREATE, humanDto):
                    cache.checkView(organizationService.getHumanDto(cache.getCreator(), null), humanDto);

                cacheService.setCache(ChangeFlagUtils.Form,dataId+"dto", USER_ID_CACHE, cache);
                cacheService.setCache(ChangeFlagUtils.Form, dataId.toString(), USER_ID_CACHE, out);
                cacheService.setCache(ChangeFlagUtils.Form, dataId.toString(), humanDto.getDataId(), viewCache);

            } else {
                out = cacheService.getCache(ChangeFlagUtils.Form,dataId.toString(), USER_ID_CACHE, FormOut.class);
            }
            if (viewCache) {
                return new Vo<>(out);
            } else {
                throw new AuthorityException(humanDto.getDataId(), "Form"+tableId, dataId, "查看");
            }

        }
    }

    @GetMapping("/form")
    @Operation(summary = "批量获取实体表单或虚拟视图的符合要求数据")
    @Parameters({
            @Parameter(name = "columnId", description = "如果获取显示浏览框传输对应显示字段id", required = false, in = ParameterIn.QUERY),
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "tableId", description = "表单编号", required = true, in = ParameterIn.QUERY)
    })
    public Vo<List<FormOut>> getForms(@RequestAttribute("HumanDto") HumanDto humanDto,
                                      @RequestParam("isVirtual")Boolean isVirtual,
                                      @RequestParam("tableId")Long tableId,
                                      @RequestParam(value = "columnId",required = false)Long columnId,
                                      HttpServletRequest request) throws EoaException {
        FilterFormUtils filter = new FilterFormUtils(request.getParameterMap());
        if (isVirtual) {
            List<FormOut> outs = factory.outs(viewService.getFormSort(tableId, filter, humanDto.getDataId()));
            if (columnId != null) {
                outs.forEach(formOut -> formOut.toBrowser(columnId));
                return new Vo<>(outs);
            }
            return new Vo<>(filter.filt(outs),outs.size());
        } else {
            List<FormOut> outs;
            FormOut[] cache = cacheService.getCache(ChangeFlagUtils.Form, filter.getDescription(), humanDto.getDataId(), FormOut[].class);
            if (cache == null) {
                outs = factory.outs(entityService.getFormSort(tableId, filter, humanDto.getDataId()).stream().
                        filter(dto -> dto.checkView(organizationService.getHumanDto(dto.getCreator(), null), humanDto)).toList());
                cacheService.setCache(ChangeFlagUtils.Form, filter.getDescription(), humanDto.getDataId(), outs);
            } else {
                outs = Arrays.asList(cache);
            }
            if (columnId != null) {
                outs.forEach(formOut -> formOut.toBrowser(columnId));
                return new Vo<>(outs,outs.size());
            }
            return new Vo<>(filter.filt(outs),outs.size());
        }
    }

    @PostMapping("/form")
    @Operation(summary = "创建获取实体表单或虚拟视图的数据")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "tableId", description = "表单编号", required = true, in = ParameterIn.QUERY)
    })
    public Vo<Long> newForm(@RequestAttribute("HumanDto") HumanDto humanDto,
                              @RequestParam("isVirtual")Boolean isVirtual,
                              @RequestParam("tableId")Long tableId,
                              @RequestBody FormIn form) {
        if (isVirtual)
            viewService.createForm(null, humanDto.getDataId());

        if (entityService.checkAuthority(tableId, null, FormService.CREATE, humanDto)) {
            FormInDto entity = form.toEntity(null).consist(tableEntityMapper,columnEntityMapper);
            Long dataId = entityService.createForm(entity, humanDto.getDataId());
            if (dataId == null) {
                return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
            } else {
                changeFlagUtils.freshDate(ChangeFlagUtils.Form);
                return new Vo<>(dataId);
            }
        } else {
            throw new AuthorityException(humanDto.getDataId(), "Table", tableId, "创建数据");
        }

    }

    @PutMapping("/form/{dataId}")
    @Operation(summary = "创建获取实体表单或虚拟视图的数据")
    @Parameters({
            @Parameter(name = "dataId",description = "表单编号",required = true,in = ParameterIn.PATH),
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "tableId", description = "表单编号", required = true, in = ParameterIn.QUERY)
    })
    public Vo<String> updateForm(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestParam("isVirtual")Boolean isVirtual,
                                @RequestParam("tableId")Long tableId,
                                @PathVariable("dataId")Long dataId,
                                @RequestBody FormIn form) {
        if (isVirtual)
            viewService.updateForm(null, humanDto.getDataId());

        if (entityService.checkAuthority(tableId, dataId, FormService.EDIT, humanDto)) {
            FormInDto entity = form.toEntity(dataId).consist(tableEntityMapper,columnEntityMapper);
            Integer update = entityService.updateForm(entity, humanDto.getDataId());
            if (update == 0) {
                return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
            } else {
                changeFlagUtils.freshDate(ChangeFlagUtils.Form);
                return new Vo<>("修改成功");
            }
        } else {
            throw new AuthorityException(humanDto.getDataId(), "Form"+tableId, dataId, "编辑");
        }
    }

    @DeleteMapping("/form/{dataId}")
    @Operation(summary = "删除指定实体表单或虚拟视图的指定数据")
    @Parameters({
            @Parameter(name = "isVirtual", description = "是否虚拟视图", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "dataId",description = "表单编号",required = true,in = ParameterIn.PATH),
            @Parameter(name = "tableId", description = "表单编号", required = true, in = ParameterIn.QUERY)
    })
    public Vo<String> deleteForm(@RequestAttribute("HumanDto") HumanDto humanDto,
                                  @PathVariable("dataId")Long dataId,
                                  @RequestParam("isVirtual")Boolean isVirtual,
                                  @RequestParam("tableId")Long tableId) {
        if (isVirtual)
            viewService.deleteForm(tableId, dataId, humanDto.getDataId());

        if (entityService.checkAuthority(tableId, dataId, FormService.DELETE, humanDto)) {
            Integer delete = entityService.deleteForm(tableId, dataId, humanDto.getDataId());
            if (delete == 0) {
                return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
            } else {
                changeFlagUtils.freshDate(ChangeFlagUtils.Form);
                return new Vo<>("删除成功");
            }
        } else {
            throw new AuthorityException(humanDto.getDataId(), "Form"+tableId, dataId, "删除");
        }
    }
}
