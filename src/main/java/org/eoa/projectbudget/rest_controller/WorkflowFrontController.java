package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.exception.ServerException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.RequestMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.service.table_module.impl.FromEntityServiceImpl;
import org.eoa.projectbudget.service.workflow.WorkflowFrontService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.vo.in.FormIn;
import org.eoa.projectbudget.vo.in.RequestIn;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 20:29
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: WorkflowFrontController
 * @Description: 工作流前端接口控制器
 * @Version: 1.0
 */
@RestController
@Tag(name = "工作流前端控制器")
@CrossOrigin
@RequestMapping("/api/v1/workflow/front")
public class WorkflowFrontController {

    @Autowired
    WorkflowFrontService workflowFrontService;
    @Autowired
    FromEntityServiceImpl entityService;
    @Autowired
    TableEntityMapper tableEntityMapper;
    @Autowired
    ColumnEntityMapper columnEntityMapper;
    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    CacheService cacheService;
    @Autowired
    RequestMapper requestMapper;

    public final Long USER_ID_CACHE = 0L;

    @PutMapping("/action")
    @Operation(summary = "流程操作")
    @Parameter(name = "action", description = "操作编码", required = true, in = ParameterIn.QUERY)
    public Vo<List<Long>> requestAction(@RequestAttribute("HumanDto") HumanDto humanDto,
                                        @RequestParam Integer action,
                                        @RequestBody RequestIn requestIn) {
        RequestDto requestDto = workflowFrontService.checkAndConsist(requestIn.toEntity(Long.valueOf(action)), humanDto);
        FormIn formIn = requestIn.getForm();

        if (action == RequestDto.CREATE) {
            FormInDto formInDto = formIn.toEntity(null).consist(tableEntityMapper,columnEntityMapper);
            Long dataId = entityService.createForm(formInDto, humanDto.getDataId());
            if (dataId == null) {
                throw new ServerException("未进行新建,请联系管理员");
            }

            Long tableId = formIn.getTableId();
            changeFlagUtils.freshDate(ChangeFlagUtils.Form+"-"+tableId);

            FormOutDto formOutDto = entityService.getFormOne(tableId, dataId, humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.Form+"-"+ tableId, dataId.toString(), USER_ID_CACHE, formOutDto);
            requestDto.setCreator(humanDto)
                    .setFormOutDto(formOutDto);
            Request request = requestDto.getRequest();
            request.setDataId(dataId);

            requestMapper.updateById(request);
            changeFlagUtils.freshDate(ChangeFlagUtils.REQUEST);

        } else {
            Long dataId = requestDto.getDataId();
            FormInDto formInDto = formIn.toEntity(dataId).consist(tableEntityMapper,columnEntityMapper);
            entityService.updateForm(formInDto, humanDto.getDataId());

            Long tableId = formIn.getTableId();
            changeFlagUtils.freshDate(ChangeFlagUtils.Form+"-"+tableId);

            FormOutDto formOutDto = entityService.getFormOne(tableId, dataId, humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.Form+"-"+ tableId, dataId.toString(), USER_ID_CACHE, formOutDto);
            requestDto.setCreator(organizationService.getHumanDto(formOutDto.getCreator(),null))
                    .setFormOutDto(formOutDto);
        }
        List<Long> lefts;
        switch (action) {
            case RequestDto.CREATE -> {
                lefts = new ArrayList<>();
                workflowFrontService.createRequest(requestDto, humanDto);
            }
            case RequestDto.ADMIT -> lefts = workflowFrontService.admitRequest(requestDto, humanDto);
            case RequestDto.SUBMIT -> lefts = workflowFrontService.submitRequest(requestDto, humanDto);
            case RequestDto.REFUSE -> lefts = workflowFrontService.refuseRequest(requestDto, humanDto);
            default -> throw new ParameterException("action", action.toString(), "错误的操作数值");
        }



        return new Vo<>(lefts);

    }

}
