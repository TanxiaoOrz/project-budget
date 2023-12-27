package org.eoa.projectbudget.service.workflow;

import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:31
 * @PackageName: org.eoa.projectbudget.service.workflow
 * @ClassName: WorkflowBackService
 * @Description: 流程模块后端操作接口
 * @Version: 1.0
 **/
public interface WorkflowBackService {

    Workflow getWorkflow(Long dataId, Long userId);

    Long newWorkFlow(Long dataId, Long userId);

    Integer updateWorkFlow(Workflow workflow, Long userId);

    Integer dropWorkFlow(Long dataId, Long userId);


    WorkflowNode getWorkflowNode(Long dataId, Long userId);

    Long newWorkflowNode(Long dataId, Long userId);

    Integer updateWorkflowNode(WorkflowNode workflowNode, Long userId);

    Integer dropWorkflowNode(Long dataId, Long userId);


    WorkflowRoute getWorkflowRoute(Long dataId, Long userId);

    Long newWorkflowRoute(Long dataId, Long userId);

    Integer updateWorkflowRoute(WorkflowRoute workflowRoute, Long userId);

    Integer dropWorkflowRoute(Long dataId, Long userId);
}
