package org.eoa.projectbudget.rest_controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.dto.constraint.Constraint;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.exception.ServerException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.RequestMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.service.table_module.impl.FromEntityServiceImpl;
import org.eoa.projectbudget.service.workflow.RequestService;
import org.eoa.projectbudget.service.workflow.WorkflowService;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.RequestDtoOutFactory;
import org.eoa.projectbudget.utils.factory.RequestOutFactory;
import org.eoa.projectbudget.utils.factory.WorkflowOutFactory;
import org.eoa.projectbudget.vo.in.FormIn;
import org.eoa.projectbudget.vo.in.RequestIn;
import org.eoa.projectbudget.vo.out.RequestDtoOut;
import org.eoa.projectbudget.vo.out.RequestOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.eoa.projectbudget.vo.out.WorkflowOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    RequestService requestService;
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
    @Autowired
    RequestOutFactory requestOutFactory;
    @Autowired
    WorkflowService workflowService;
    @Autowired
    RequestDtoOutFactory requestDtoOutFactory;
    @Autowired
    WorkflowOutFactory workflowOutFactory;

    public final Long USER_ID_CACHE = 0L;

    @GetMapping("/request")
    @Operation(summary = "批量获取流程数据")
    @Parameter(name = "type", description = "获取烈性", required = true, in = ParameterIn.QUERY)
    @SuppressWarnings("unchecked")
    public Vo<List<RequestOut>> getRequests(@RequestAttribute("HumanDto") HumanDto humanDto,
                                      @RequestParam String type,
                                      HttpServletRequest request) {
        Class<? extends Request> requestClass;
        switch (type) {
            case "backlog" -> requestClass = RequestBacklogView.class;
            case "done" -> requestClass = RequestDoneView.class;
            case "self" -> requestClass = Request.class;
            case default -> throw new ParameterException("type", type , "错误的类型");
        }
        FilterUtils<? extends Request> filter = new FilterUtils<>(request.getParameterMap(), requestClass);
        int flag = ChangeFlagUtils.REQUEST;
        String method = type + "-" + filter.getDescription();

        List<RequestOut> outs;
        RequestOut[] cache = cacheService.getCache(flag, method, humanDto.getDataId(), RequestOut[].class);
        if (cache == null) {
            switch (type) {
                case "backlog" -> outs =requestOutFactory.outs(requestService.getBackLogRequest((QueryWrapper<RequestBacklogView>) filter.getWrapper(), humanDto.getDataId())) ;
                case "done" -> outs =requestOutFactory.outs(requestService.getHaveDone((QueryWrapper<RequestDoneView>)filter.getWrapper(), humanDto.getDataId()));
                case "self" -> outs =requestOutFactory.outs(requestService.getRequestsSelf((QueryWrapper<Request>)filter.getWrapper(), humanDto.getDataId()));
                case default -> throw new ParameterException("type", type , "错误的类型");
            }
        } else {
            outs = Arrays.asList(cache);
        }

        return new Vo<>(filter.filt(outs), outs.size());
    }


    @GetMapping("/workflow")
    @Operation(summary = "获取可创建流程")
    public Vo<List<WorkflowOut>> getCreateWorkflow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                   HttpServletRequest request) {
        int flag = ChangeFlagUtils.WORKFLOW;
        FilterUtils<Workflow> filter = new FilterUtils<>(request.getParameterMap(), Workflow.class);
        String method = "createMethod-" + filter.getDescription();
        List<WorkflowOut> out;
        WorkflowOut[] cache = cacheService.getCache(flag, method, humanDto.getDataId(), WorkflowOut[].class);
        if (cache == null) {
            List<RequestDto> createsAll;
            RequestDto[] cacheCreate = cacheService.getCache(flag, method, humanDto.getDataId(), RequestDto[].class);
            if (cacheCreate == null) {
                createsAll = requestService.getCreateAbleList(filter.getWrapper(), humanDto.getDataId());
                cacheService.setCache(flag, method, USER_ID_CACHE, createsAll);
            } else {
                createsAll = Arrays.asList(cacheCreate);
            }

            out = createsAll.stream().filter(requestDto -> {
                String userAuthorityLimit = requestDto.getCurrentNode().getUserAuthorityLimit();
                Constraint constraint = AuthorityUtils.getConstraint(userAuthorityLimit);
                return AuthorityUtils.checkAuthority(humanDto,humanDto,constraint);
            }).map(requestDto -> workflowOutFactory.out(requestDto.getWorkflow())).toList();
            cacheService.setCache(flag, method, humanDto.getDataId(), out);
        } else {
            out = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(out), out.size());
    }

    @GetMapping("/request/{requestId}")
    @Operation(summary = "获取流程具体数据")
    @Parameter(name = "requestId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<RequestDtoOut> getRequest(@RequestAttribute("HumanDto") HumanDto humanDto,
                                        @PathVariable("requestId") Long requestId,
                                        @RequestParam(required = false) Long workflowId) {
        Long userId = humanDto.getDataId();
        if (requestId == 0) {
            Boolean couldCreate = cacheService.getCache(ChangeFlagUtils.REQUEST, requestId.toString()+"-"+workflowId, userId, Boolean.class);
            if (couldCreate == null) {
                RequestDtoOut requestDtoOut = getRequestCreate(requestId, workflowId, userId);
                String userAuthorityLimit = requestDtoOut.getCurrentNode().getUserAuthorityLimit();
                Constraint constraint = AuthorityUtils.getConstraint(userAuthorityLimit);
                couldCreate = AuthorityUtils.checkAuthority(humanDto, humanDto, constraint);
                cacheService.setCache(ChangeFlagUtils.REQUEST, requestId.toString()+"-"+workflowId, userId, couldCreate);
            }
            if (couldCreate)
                return new Vo<>(getRequestCreate(requestId, workflowId, userId));
            else
                throw new AuthorityException(userId,"workflow",workflowId,"创建流程");
        }


        Long nodeId = cacheService.getCache(ChangeFlagUtils.REQUEST, requestId.toString()+"node", userId, Long.class);
        if (nodeId == null) {
            try {
                nodeId = requestService.getViewNode(requestId, userId);
                cacheService.setCache(ChangeFlagUtils.REQUEST, requestId.toString()+"node", userId, nodeId);
            } catch (EoaException e) {
                nodeId = 0L;
                cacheService.setCache(ChangeFlagUtils.REQUEST, requestId.toString()+"node", userId, nodeId);
                throw e;
            }
        }
        if (nodeId == 0)
            throw new AuthorityException(userId, "request", requestId, "查看");
        RequestDtoOut requestDtoOut = cacheService.getCache(ChangeFlagUtils.REQUEST, requestId.toString(), userId, RequestDtoOut.class);
        if (requestDtoOut == null) {
            RequestDto requestDto = requestService.getRequest(requestId, userId);

            Long dataId = requestDto.getDataId();
            FormOutDto formOutDto = getFormOutDto(userId, requestDto, dataId);
            requestDto.setFormOutDto(formOutDto);
            WorkflowNode current = workflowService.getWorkflowNode(nodeId, userId);
            requestDto.setCurrentNode(current);
            requestDtoOut = requestDtoOutFactory.out(requestDto);
            cacheService.setCache(ChangeFlagUtils.REQUEST, requestId.toString(), userId, requestDtoOut);
        }
        if (WorkflowNode.FILE == (requestDtoOut.getCurrentNode().getNodeType()))
            requestMapper.doRequest(requestId, userId);
        return new Vo<>(requestDtoOut);
    }

    private FormOutDto getFormOutDto(Long userId, RequestDto requestDto, Long dataId) {
        FormOutDto formOutDto = cacheService.getCache(ChangeFlagUtils.Form, dataId +"dto", USER_ID_CACHE, FormOutDto.class);
        if (formOutDto == null) {
            formOutDto = entityService.getFormOne(requestDto.getWorkflow().getTableId(), dataId, userId);
            cacheService.setCache(ChangeFlagUtils.Form, dataId +"dto", USER_ID_CACHE, formOutDto);
        }
        return formOutDto;
    }

    private RequestDtoOut getRequestCreate(Long requestId, Long nodeId, Long userId) {
        RequestDtoOut requestDtoOut = cacheService.getCache(ChangeFlagUtils.REQUEST, requestId.toString()+"-"+ nodeId, USER_ID_CACHE, RequestDtoOut.class);
        if (requestDtoOut == null) {
            RequestDto requestDto = requestService.getRequestCreate(nodeId, userId);
            requestDto.setFormOutDto(getFormOutDto(userId, requestDto, null));
            requestDtoOut = requestDtoOutFactory.out(requestDto);
            cacheService.setCache(ChangeFlagUtils.REQUEST, requestId.toString()+"-"+ nodeId, USER_ID_CACHE, requestDtoOut);
        }
        return requestDtoOut;
    }

    @PutMapping("/request")
    @Operation(summary = "流程操作")
    @Parameter(name = "action", description = "操作编码", required = true, in = ParameterIn.QUERY)
    public Vo<List<Long>> requestAction(@RequestAttribute("HumanDto") HumanDto humanDto,
                                        @RequestParam Integer action,
                                        @RequestParam Boolean onlySave,
                                        @RequestBody RequestIn requestIn) {
        if (action == RequestDto.FLOW)
            throw new ParameterException("action",action.toString(),"本接口禁止进行流转操作");
        RequestDto requestDto = requestService.checkAndConsist(requestIn.toEntity(Long.valueOf(action)), humanDto.getDataId());
        FormIn formIn = requestIn.getForm();

        if (action == RequestDto.CREATE) {
            FormInDto formInDto = formIn.toEntity(null).consist(tableEntityMapper,columnEntityMapper).setRequestId(requestDto.getRequestId());
            Long dataId = entityService.createForm(formInDto, humanDto.getDataId());
            if (dataId == null) {
                throw new ServerException("未进行新建,请联系管理员");
            }

            Long tableId = formIn.getTableId();
            changeFlagUtils.freshDate(ChangeFlagUtils.Form+"-"+tableId);

            FormOutDto formOutDto = entityService.getFormOne(tableId, dataId, humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.Form+"-"+ tableId, dataId+"dto", USER_ID_CACHE, formOutDto);
            requestDto.setCreator(humanDto)
                    .setFormOutDto(formOutDto);
            Request request = requestDto.getRequest();
            request.setDataId(dataId);

            requestMapper.updateById(request);

        } else {
            Long dataId = requestDto.getDataId();
            FormInDto formInDto = formIn.toEntity(dataId).consist(tableEntityMapper,columnEntityMapper);
            entityService.updateForm(formInDto, humanDto.getDataId());

            Long tableId = formIn.getTableId();
            changeFlagUtils.freshDate(ChangeFlagUtils.Form+"-"+tableId);

            FormOutDto formOutDto = entityService.getFormOne(tableId, dataId, humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.Form+"-"+ tableId, dataId+"dto", USER_ID_CACHE, formOutDto);
            requestDto.setCreator(organizationService.getHumanDto(formOutDto.getCreator(),null))
                    .setFormOutDto(formOutDto);
        }
        List<Long> lefts;
        if (onlySave)
            return new Vo<>(Collections.singletonList(requestDto.getRequestId()));
        switch (action) {
            case RequestDto.CREATE -> {
                lefts = new ArrayList<>();
                requestService.createRequest(requestDto, humanDto.getDataId());
            }
            case RequestDto.ADMIT -> lefts = requestService.admitRequest(requestDto, humanDto.getDataId());
            case RequestDto.SUBMIT -> lefts = requestService.submitRequest(requestDto, humanDto.getDataId());
            case RequestDto.REFUSE -> lefts = requestService.refuseRequest(requestDto, humanDto.getDataId());
            default -> throw new ParameterException("action", action.toString(), "错误的操作数值");
        }
        changeFlagUtils.freshDate(ChangeFlagUtils.Form+"-"+formIn.getTableId());
        changeFlagUtils.freshDate(ChangeFlagUtils.REQUEST);
        return new Vo<>(lefts);

    }

}
