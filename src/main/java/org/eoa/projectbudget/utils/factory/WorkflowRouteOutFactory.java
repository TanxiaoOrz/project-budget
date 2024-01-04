package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.WorkflowMapper;
import org.eoa.projectbudget.mapper.WorkflowNodeMapper;
import org.eoa.projectbudget.vo.out.WorkflowRouteOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 10:40
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: WorkflowRouteFactory
 * @Description: 工作流节点输出工程类
 * @Version: 1.0
 **/

@Component
public class WorkflowRouteOutFactory implements OutFactory<WorkflowRoute, WorkflowRouteOut> {

    @Autowired
    HumanMapper humanMapper;
    @Autowired
    WorkflowMapper workflowMapper;
    @Autowired
    WorkflowNodeMapper workflowNodeMapper;

    @Override
    public WorkflowRouteOut out(WorkflowRoute workflowRoute) {
        if (workflowRoute == null) {
            return null;
        }
        WorkflowRouteOut workflowRouteOut = new WorkflowRouteOut(workflowRoute);
        HumanResource creator = humanMapper.selectById(workflowRoute.getCreator());
        Workflow workflow = workflowMapper.selectById(workflowRouteOut.getWorkflowId());
        WorkflowNode startNode = workflowNodeMapper.selectById(workflowRoute.getStartNodeId());
        WorkflowNode endNode = workflowNodeMapper.selectById(workflowRoute.getEndNodeId());
        return workflowRouteOut.setCreatorName(creator == null ? "" : creator.getName())
                .setWorkflowName(workflow == null ? "" : workflow.getWorkFlowName())
                .setTableId(workflow == null ? null : workflow.getTableId())
                .setStartNodeName(startNode == null ? "" : startNode.getWorkflowNodeName())
                .setEndNodeName(endNode == null ? "" : endNode.getWorkflowNodeName());
    }

    @Override
    public List<WorkflowRouteOut> outs(List<? extends WorkflowRoute> workflowRoutes) {
        if (workflowRoutes == null) {
            return null;
        }
        return workflowRoutes.stream().map(this::out).toList();
    }
}
