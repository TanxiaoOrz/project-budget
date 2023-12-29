package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.workflow.WorkflowBackService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.WorkflowNodeOutFactory;
import org.eoa.projectbudget.utils.factory.WorkflowOutFactory;
import org.eoa.projectbudget.utils.factory.WorkflowRouteOutFactory;
import org.eoa.projectbudget.vo.in.WorkflowIn;
import org.eoa.projectbudget.vo.in.WorkflowNodeIn;
import org.eoa.projectbudget.vo.in.WorkflowRouteIn;
import org.eoa.projectbudget.vo.out.Vo;
import org.eoa.projectbudget.vo.out.WorkflowNodeOut;
import org.eoa.projectbudget.vo.out.WorkflowOut;
import org.eoa.projectbudget.vo.out.WorkflowRouteOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    WorkflowBackService workflowBackService;

    @Autowired
    WorkflowOutFactory workflowOutFactory;
    @Autowired
    WorkflowNodeOutFactory workflowNodeOutFactory;
    @Autowired
    WorkflowRouteOutFactory workflowRouteOutFactory;


    public static final Long USER_ID_CACHE = 0L;

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
            outs = workflowOutFactory.outs(workflowBackService.getWorkflows(filter.getWrapper(), humanDto.getDataId()));
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
            out = workflowOutFactory.out(workflowBackService.getWorkflow(dataId, humanDto.getDataId()));
            cacheService.setCache(flag, dataId.toString(), USER_ID_CACHE, out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/workflow")
    @Operation(summary = "新建工作流")
    public Vo<Long> newWorkflow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody WorkflowIn workflow) {
        Long id = workflowBackService.newWorkflow(workflow.toEntity(null), humanDto.getDataId());
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
        Integer update = workflowBackService.updateWorkflow(workflow.toEntity(dataId), humanDto.getDataId());
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
        Integer deletes = workflowBackService.dropWorkflow(dataId, humanDto.getDataId());
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
            outs = workflowNodeOutFactory.outs(workflowBackService.getWorkflowNodes(filter.getWrapper(), humanDto.getDataId()));
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
            out = workflowNodeOutFactory.out(workflowBackService.getWorkflowNode(dataId, humanDto.getDataId()));
            cacheService.setCache(flag, dataId.toString(), USER_ID_CACHE, out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/workflowNode")
    @Operation(summary = "新建工作流节点")
    public Vo<Long> newWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody WorkflowNodeIn workflowNode) {
        Long id = workflowBackService.newWorkflowNode(workflowNode.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_NODE);
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
        Integer update = workflowBackService.updateWorkflowNode(workflowNode.toEntity(dataId), humanDto.getDataId());
        if (update == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_NODE);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行修改,没有变动项");
    }

    @DeleteMapping("/workflowNode/{dataId}")
    @Operation(summary = "删除工作流节点")
    @Parameter(name = "dataId", description = "工作流节点id", in = ParameterIn.PATH)
    public Vo<String> deleteWorkflowNode(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable("dataId") Long dataId) {
        Integer deletes = workflowBackService.dropWorkflowNode(dataId, humanDto.getDataId());
        if (deletes == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_NODE);
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
            outs = workflowRouteOutFactory.outs(workflowBackService.getWorkflowRoutes(filter.getWrapper(), humanDto.getDataId()));
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
            out = workflowRouteOutFactory.out(workflowBackService.getWorkflowRoute(dataId, humanDto.getDataId()));
            cacheService.setCache(flag, dataId.toString(), USER_ID_CACHE, out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/workflowRoute")
    @Operation(summary = "新建工作流路径")
    public Vo<Long> newWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody WorkflowRouteIn workflowRoute) {
        Long id = workflowBackService.newWorkflowRoute(workflowRoute.toEntity(null), humanDto.getDataId());
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
        Integer update = workflowBackService.updateWorkflowRoute(workflowRoute.toEntity(dataId), humanDto.getDataId());
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
        Integer deletes = workflowBackService.dropWorkflowRoute(dataId, humanDto.getDataId());
        if (deletes == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW_ROUTE);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行删除,该数据不存在");
    }

}
