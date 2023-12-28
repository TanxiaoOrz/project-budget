package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.workflow.WorkflowBackService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.WorkflowOutFactory;
import org.eoa.projectbudget.vo.in.WorkflowIn;
import org.eoa.projectbudget.vo.out.Vo;
import org.eoa.projectbudget.vo.out.WorkflowOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 22:51
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: WorkflowFrontController
 * @Description: 工作流后端控制器
 * @Version: 1.0
 */
@RestController
@Tag(name = "工作流后端控制器")
@CrossOrigin
@RequestMapping("/api/v1/workflow/back")
public class WorkflowFrontController {


    @Autowired
    ChangeFlagUtils changeFlagUtils;
    @Autowired
    CacheService cacheService;
    @Autowired
    WorkflowBackService workflowBackService;

    @Autowired
    WorkflowOutFactory workflowOutFactory;


    public static final Long USER_ID_CACHE = 0L;

    @GetMapping("/workflow")
    @Operation(summary = "批量获取工作流")
    public Vo<List<WorkflowOut>> getWorkFlowList(@RequestAttribute("HumanDto") HumanDto humanDto,
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
    public Vo<WorkflowOut> getWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
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
    public Vo<Long> newWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                @RequestBody WorkflowIn workflow) {
        Long id = workflowBackService.newWorkFlow(workflow.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行新建,请联系管理员");
    }

    @PutMapping("/workflow/{dataId}")
    @Operation(summary = "修改工作流")
    @Parameter(name = "dataId", description = "分部编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable("dataId") Long dataId,
                                     @RequestBody WorkflowIn workflow) {
        Integer update = workflowBackService.updateWorkFlow(workflow.toEntity(dataId), humanDto.getDataId());
        if (update == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行修改,没有变动项");
    }

    @DeleteMapping("/workflow/{dataId}")
    @Operation(summary = "删除分部")
    @Parameter(name = "dataId", description = "是否递归删除子部门及子分部", in = ParameterIn.PATH)
    public Vo<String> deleteWorkFlow(@RequestAttribute("HumanDto") HumanDto humanDto,
                                     @PathVariable("dataId") Long dataId) {
        Integer deletes = workflowBackService.dropWorkFlow(dataId, humanDto.getDataId());
        if (deletes == 1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.WORKFLOW);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR, "未进行删除,该数据不存在");
    }
}
