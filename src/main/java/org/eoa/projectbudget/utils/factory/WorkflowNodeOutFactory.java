package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.WorkflowMapper;
import org.eoa.projectbudget.vo.out.WorkflowNodeOut;
import org.eoa.projectbudget.vo.out.WorkflowRouteOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 11:00
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: WorkflowNodeOutFactory
 * @Description: 工作流节点输出工厂类
 * @Version: 1.0
 **/

@Component
public class WorkflowNodeOutFactory implements OutFactory<WorkflowNode, WorkflowNodeOut>{

    @Autowired
    HumanMapper humanMapper;
    @Autowired
    WorkflowMapper workflowMapper;

    @Override
    public WorkflowNodeOut out(WorkflowNode workflowNode) {
        if (workflowNode == null) {
            return null;
        }
        WorkflowNodeOut WorkflowNodeOut = new WorkflowNodeOut(workflowNode);
        HumanResource creator = humanMapper.selectById(workflowNode.getCreator());
        Workflow workflow = workflowMapper.selectById(WorkflowNodeOut.getWorkflowId());
        return WorkflowNodeOut.setCreatorName(creator == null ? "" : creator.getName())
                .setWorkflowName(workflow == null ? "" : WorkflowNodeOut.getWorkflowName());
    }

    @Override
    public List<WorkflowNodeOut> outs(List<? extends WorkflowNode> workflowNodes) {
        if (workflowNodes == null) {
            return null;
        }
        return workflowNodes.stream().map(this::out).toList();
    }
}
