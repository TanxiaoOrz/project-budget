package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.service.table_module.impl.FromEntityServiceImpl;
import org.eoa.projectbudget.service.workflow.RequestService;
import org.eoa.projectbudget.service.workflow.WorkflowService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.*;
import org.eoa.projectbudget.vo.in.*;
import org.eoa.projectbudget.vo.out.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 22:51
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: WorkflowBackController
 * @Description: 工作流后端控制器
 * @Version: 1.0
 */
@RestController
@Tag(name = "工作流后端控制器")
@CrossOrigin
@RequestMapping("/api/v1/workflow/back")
public class WorkflowBackController {


    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Autowired
    CacheService cacheService;
    @Autowired
    WorkflowService workflowService;
    @Autowired
    RequestService requestService;
    @Autowired
    FromEntityServiceImpl entityService;
    @Autowired
    OrganizationService organizationService;

    @Autowired
    WorkflowOutFactory workflowOutFactory;
    @Autowired
    WorkflowNodeOutFactory workflowNodeOutFactory;
    @Autowired
    WorkflowRouteOutFactory workflowRouteOutFactory;
    @Autowired
    RequestDtoOutFactory requestDtoOutFactory;
    @Autowired
    RequestOutFactory requestOutFactory;


    @Autowired
    ColumnEntityMapper columnEntityMapper;
    @Autowired
    TableEntityMapper tableEntityMapper;


    public static final Long USER_ID_CACHE = 0L;

    @GetMapping("/request")
    @Operation(summary = "批量获取流程数据")
    public Vo<List<RequestOut>> getRequests(@RequestAttribute("HumanDto") HumanDto humanDto,
                                            HttpServletRequest request) {
        FilterUtils<Request> filter = new FilterUtils<>(request.getParameterMap(), Request.class);
        int flag = ChangeFlagUtils.REQUEST;
        String method =  filter.getDescription();

        List<RequestOut> outs;
        RequestOut[] cache = cacheService.getCache(flag, method, humanDto.getDataId(), RequestOut[].class);
        if (cache == null) {
            outs = requestOutFactory.outs(requestService.getRequests(filter.getWrapper(), humanDto.getDataId()));
        } else {
            outs = Arrays.asList(cache);
        }

        return new Vo<>(filter.filt(outs), outs.size());
    }

    @PutMapping("/request")
    @Operation(summary = "流程强制流转")
    public Vo<String> flowRequest(@RequestAttribute("HumanDto") HumanDto humanDto,
                                  @RequestBody RequestIn requestIn) {
        int action = RequestDto.FLOW;
        RequestDto requestDto = requestService.checkAndConsist(requestIn.toEntity((long) action), humanDto.getDataId());
        Long dataId = requestDto.getDataId();


        FormIn formIn = requestIn.getForm();
        Long tableId = formIn.getTableId();

        FormInDto formInDto = formIn.toEntity(dataId).consist(tableEntityMapper,columnEntityMapper);
        entityService.updateForm(formInDto, humanDto.getDataId());
        FormOutDto formOutDto =  entityService.getFormOne(tableId, dataId, humanDto.getDataId());

        requestDto.setCreator(organizationService.getHumanDto(formOutDto.getCreator(),null))
                .setFormOutDto(formOutDto);

        requestService.transferRequest(requestDto, requestDto.getNodeId(), requestIn.getReceivers(), humanDto.getDataId());

        changeFlagUtils.freshDate(ChangeFlagUtils.Form+"-"+tableId);
        return new Vo<>("流转成功");
    }

    @DeleteMapping("/request/{requestId}")
    @Operation(summary = "删除流程")
    @Parameters({
            @Parameter(name = "requestId", description = "对象编号", required = true, in = ParameterIn.PATH),
            @Parameter(name = "deleteForm", description = "是否删除表单", required = true, in = ParameterIn.PATH)
    })
    public Vo<String> deleteRequest(@RequestAttribute("HumanDto") HumanDto humanDto,
                                    @PathVariable("requestId") Long requestId,
                                    @RequestParam Boolean deleteForm) {
        Map<Long, Long> map = requestService.dropRequest(requestId, humanDto.getDataId());
        if (map != null) {
            if (deleteForm) {
                changeFlagUtils.freshDate(ChangeFlagUtils.REQUEST);
                map.keySet().forEach((key) -> {
                    entityService.deleteForm(key, map.get(key), humanDto.getDataId());
                    changeFlagUtils.freshDate(ChangeFlagUtils.Form + "-" + key);
                });
            }
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "该流程不存在");
    }

    @GetMapping("/request/{requestId}")
    @Operation(summary = "获取流程监控数据")
    @Parameter(name = "requestId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<RequestDtoOut> getRequest(@RequestAttribute("HumanDto") HumanDto humanDto,
                                        @PathVariable("requestId") Long requestId) {
        Long userId = humanDto.getDataId();
        RequestDtoOut requestDtoOut = cacheService.getCache(ChangeFlagUtils.REQUEST, requestId.toString(), USER_ID_CACHE, RequestDtoOut.class);
        if (requestDtoOut == null) {
            RequestDto requestDto = requestService.getRequest(requestId, userId);

            Long dataId = requestDto.getDataId();
            FormOutDto formOutDto = entityService.getFormOne(requestDto.getWorkflow().getTableId(), dataId, userId);

            requestDto.setFormOutDto(formOutDto);
            Long nodeId = requestDto.getRequest().getCurrentNode();
            WorkflowNode current = workflowService.getWorkflowNode(nodeId, userId);
            requestDto.setCurrentNode(current);
            requestDtoOut = requestDtoOutFactory.out(requestDto);
            cacheService.setCache(ChangeFlagUtils.REQUEST, requestId.toString(), USER_ID_CACHE, requestDtoOut);
        }

        return new Vo<>(requestDtoOut);
    }


    @GetMapping("/workflow")
    @Operation(summary = "批量获取工作流")
    public Vo<List<WorkflowOut>> getWorkflowList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                 HttpServletRequest request) {
        FilterUtils<Workflow> filter = new FilterUtils<>(request.getParameterMap(), Workflow.class);
        List<WorkflowOut> outs;
        String methods = filter.getDescription();
        int flag = ChangeFlagUtils.WORKFLOW;
        WorkflowOut[] cache = cacheService.getCache(flag, methods, USER_ID_CACHE, WorkflowOut[].class);
        if (cache == null) {
            outs = workflowOutFactory.outs(workflowService.getWorkflows(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(flag, methods, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs), outs.size());
    }

    @GetMapping("/workflow/{dataId}")
    @Operation(summary = "获取工作流")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<WorkflowOut> getWorkflow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                       @PathVariable("dataId") Long dataId) {
        int flag = ChangeFlagUtils.WORKFLOW;
        WorkflowOut out = cacheService.getCache(flag, dataId.toString(), USER_ID_CACHE, WorkflowOut.class);
        if (out == null) {
            out = workflowOutFactory.out(workflowService.getWorkflow(dataId, humanDto.getDataId()));
            cacheService.setCache(flag, dataId.toString(), USER_ID_CACHE, out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/workflow")
    @Operation(summary = "新建工作流")
    public Vo<Long> newWorkflow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody WorkflowIn workflow) {
        Long id = workflowService.newWorkflow(workflow.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行新建,请联系管理员");
    }

    @PutMapping("/workflow/{dataId}")
    @Operation(summary = "修改工作流")
    @Parameter(name = "dataId", description = "工作流编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateWorkflow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable("dataId") Long dataId,
                                     @RequestBody WorkflowIn workflow) {
        Integer update = workflowService.updateWorkflow(workflow.toEntity(dataId), humanDto.getDataId());
        if (update == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行修改,没有变动项");
    }

    @DeleteMapping("/workflow/{dataId}")
    @Operation(summary = "删除工作流")
    @Parameter(name = "dataId", description = "工作流id", in = ParameterIn.PATH)
    public Vo<String> deleteWorkflow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable("dataId") Long dataId) {
        Integer deletes = workflowService.dropWorkflow(dataId, humanDto.getDataId());
        if (deletes == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行删除,该数据不存在");
    }


    @GetMapping("/workflowNode")
    @Operation(summary = "批量获取工作流节点")
    public Vo<List<WorkflowNodeOut>> getWorkflowNodeList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                     HttpServletRequest request) {
        FilterUtils<WorkflowNode> filter = new FilterUtils<>(request.getParameterMap(), WorkflowNode.class);
        List<WorkflowNodeOut> outs;
        String methods = filter.getDescription();
        int flag = ChangeFlagUtils.WORKFLOW_NODE;
        WorkflowNodeOut[] cache = cacheService.getCache(flag, methods, USER_ID_CACHE, WorkflowNodeOut[].class);
        if (cache == null) {
            outs = workflowNodeOutFactory.outs(workflowService.getWorkflowNodes(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(flag, methods, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs), outs.size());
    }

    @GetMapping("/workflowNode/{dataId}")
    @Operation(summary = "获取工作流节点")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<WorkflowNodeOut> getWorkflowNode(@RequestAttribute("HumanDto") HumanDto humanDto,
                                           @PathVariable("dataId") Long dataId) {
        int flag = ChangeFlagUtils.WORKFLOW_NODE;
        WorkflowNodeOut out = cacheService.getCache(flag, dataId.toString(), USER_ID_CACHE, WorkflowNodeOut.class);
        if (out == null) {
            out = workflowNodeOutFactory.out(workflowService.getWorkflowNode(dataId, humanDto.getDataId()));
            cacheService.setCache(flag, dataId.toString(), USER_ID_CACHE, out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/workflowNode")
    @Operation(summary = "新建工作流节点")
    public Vo<Long> newWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody WorkflowNodeIn workflowNode) {
        Long id = workflowService.newWorkflowNode(workflowNode.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_NODE);
            if (workflowNode.getNodeType().equals(WorkflowNode.CREATE))
                changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行新建,请联系管理员");
    }

    @PutMapping("/workflowNode/{dataId}")
    @Operation(summary = "修改工作流节点")
    @Parameter(name = "dataId", description = "工作流节点编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateWorkflowNode(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable("dataId") Long dataId,
                                     @RequestBody WorkflowNodeIn workflowNode) {
        Integer update = workflowService.updateWorkflowNode(workflowNode.toEntity(dataId), humanDto.getDataId());
        if (update == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_NODE);
            if (workflowNode.getNodeType().equals(WorkflowNode.CREATE))
                changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行修改,没有变动项");
    }

    @DeleteMapping("/workflowNode/{dataId}")
    @Operation(summary = "删除工作流节点")
    @Parameter(name = "dataId", description = "工作流节点id", in = ParameterIn.PATH)
    public Vo<String> deleteWorkflowNode(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable("dataId") Long dataId) {
        WorkflowNode workflowNode = workflowService.dropWorkflowNode(dataId, humanDto.getDataId());
        if (workflowNode != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_NODE);
            if (workflowNode.getNodeType().equals(WorkflowNode.CREATE))
                changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行删除,该数据不存在");
    }

    @GetMapping("/workflowRoute")
    @Operation(summary = "批量获取工作流路径")
    public Vo<List<WorkflowRouteOut>> getWorkflowRouteList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                           HttpServletRequest request) {
        FilterUtils<WorkflowRoute> filter = new FilterUtils<>(request.getParameterMap(), WorkflowRoute.class);
        List<WorkflowRouteOut> outs;
        String methods = filter.getDescription();
        int flag = ChangeFlagUtils.WORKFLOW_ROUTE;
        WorkflowRouteOut[] cache = cacheService.getCache(flag, methods, USER_ID_CACHE, WorkflowRouteOut[].class);
        if (cache == null) {
            outs = workflowRouteOutFactory.outs(workflowService.getWorkflowRoutes(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(flag, methods, USER_ID_CACHE, outs);
        } else {
            outs = Arrays.asList(cache);
        }
        return new Vo<>(filter.filt(outs), outs.size());
    }

    @GetMapping("/workflowRoute/{dataId}")
    @Operation(summary = "获取工作流路径")
    @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.PATH)
    public Vo<WorkflowRouteOut> getWorkflowRoute(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                 @PathVariable("dataId") Long dataId) {
        int flag = ChangeFlagUtils.WORKFLOW_ROUTE;
        WorkflowRouteOut out = cacheService.getCache(flag, dataId.toString(), USER_ID_CACHE, WorkflowRouteOut.class);
        if (out == null) {
            out = workflowRouteOutFactory.out(workflowService.getWorkflowRoute(dataId, humanDto.getDataId()));
            cacheService.setCache(flag, dataId.toString(), USER_ID_CACHE, out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/workflowRoute")
    @Operation(summary = "新建工作流路径")
    public Vo<Long> newWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody WorkflowRouteIn workflowRoute) {
        Long id = workflowService.newWorkflowRoute(workflowRoute.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_ROUTE);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行新建,请联系管理员");
    }

    @PutMapping("/workflowRoute/{dataId}")
    @Operation(summary = "修改工作流路径")
    @Parameter(name = "dataId", description = "工作流路径编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateWorkflowRoute(@RequestAttribute("HumanDto") HumanDto humanDto,
                                          @PathVariable("dataId") Long dataId,
                                          @RequestBody WorkflowRouteIn workflowRoute) {
        Integer update = workflowService.updateWorkflowRoute(workflowRoute.toEntity(dataId), humanDto.getDataId());
        if (update == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_ROUTE);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行修改,没有变动项");
    }

    @DeleteMapping("/workflowRoute/{dataId}")
    @Operation(summary = "删除工作流路径")
    @Parameter(name = "dataId", description = "工作流路径id", in = ParameterIn.PATH)
    public Vo<String> deleteWorkflowRoute(@RequestAttribute("HumanDto") HumanDto humanDto,
                                          @PathVariable("dataId") Long dataId) {
        Integer deletes = workflowService.dropWorkflowRoute(dataId, humanDto.getDataId());
        if (deletes == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_ROUTE);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行删除,该数据不存在");
    }

}
