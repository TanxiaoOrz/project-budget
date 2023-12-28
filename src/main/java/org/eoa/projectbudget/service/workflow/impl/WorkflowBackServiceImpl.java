package org.eoa.projectbudget.service.workflow.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.RequestOutDto;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;
import org.eoa.projectbudget.service.workflow.WorkflowBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 9:01
 * @PackageName: org.eoa.projectbudget.service.workflow.impl
 * @ClassName: WorkflowBackServiceImpl
 * @Description: 流程后端业务类实现
 * @Version: 1.0
 **/

@Service
public class WorkflowBackServiceImpl implements WorkflowBackService {

    private final Logger log = LoggerFactory.getLogger("WorkflowModule");

    @Override
    public List<Request> getRequests(QueryWrapper<Request> wrapper, Long userId) {
        return null;
    }

    @Override
    public RequestOutDto getRequest(Long requestId, Long userId) {
        return null;
    }

    @Override
    public Integer dropRequest(Long requestId, Long userId) {
        return null;
    }

    @Override
    public Integer transferRequest(Long requestId, Long nodeId, List<Long> receivers, Long userId) {
        return null;
    }

    @Override
    public Workflow getWorkflow(Long dataId, Long userId) {

        return null;
    }

    @Override
    public Long newWorkFlow(Workflow workflow, Long userId) {
        return null;
    }

    @Override
    public Integer updateWorkFlow(Workflow workflow, Long userId) {
        return null;
    }

    @Override
    public Integer dropWorkFlow(Long dataId, Long userId) {
        return null;
    }

    @Override
    public WorkflowNode getWorkflowNode(Long dataId, Long userId) {
        return null;
    }

    @Override
    public Long newWorkflowNode(WorkflowNode workflowNode, Long userId) {
        return null;
    }

    @Override
    public Integer updateWorkflowNode(WorkflowNode workflowNode, Long userId) {
        return null;
    }

    @Override
    public Integer dropWorkflowNode(Long dataId, Long userId) {
        return null;
    }

    @Override
    public WorkflowRoute getWorkflowRoute(Long dataId, Long userId) {
        return null;
    }

    @Override
    public Long newWorkflowRoute(WorkflowRoute workflowRoute, Long userId) {
        return null;
    }

    @Override
    public Integer updateWorkflowRoute(WorkflowRoute workflowRoute, Long userId) {
        return null;
    }

    @Override
    public Integer dropWorkflowRoute(Long dataId, Long userId) {
        return null;
    }
}
