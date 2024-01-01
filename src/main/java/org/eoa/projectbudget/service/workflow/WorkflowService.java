package org.eoa.projectbudget.service.workflow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:31
 * @PackageName: org.eoa.projectbudget.service.workflow
 * @ClassName: WorkflowService
 * @Description: 流程模块后端操作接口
 * @Version: 1.2
 **/
public interface WorkflowService {




    /**
     * 批量获取工作流
     * @param wrapper 条件构造器
     * @param userId 操作人
     * @return 工作流数组
     */
    List<Workflow> getWorkflows(QueryWrapper<Workflow> wrapper, Long userId);

    /**
     * 获取流程
     * @param dataId 流程编号
     * @param userId 操作人
     * @return 流程
     */
    Workflow getWorkflow(Long dataId, Long userId);

    /**
     * 新建流程
     * @param workflow 流程数据
     * @param userId 操作人
     * @return 数据编号
     */
    Long newWorkflow(Workflow workflow, Long userId);

    /**
     * 修改流程
     * @param workflow 流程数据
     * @param userId 操作人
     * @return 结果数字
     */
    Integer updateWorkflow(Workflow workflow, Long userId);

    /**
     * 废弃流程
     * @param dataId 流程编号
     * @param userId 操作人
     * @return 结果数字
     */
    Integer dropWorkflow(Long dataId, Long userId);

    /**
     * 批量获取工作节点
     * @param wrapper 条件构造器
     * @param userId 操作人
     * @return 工作流节点数组
     */
    List<WorkflowNode> getWorkflowNodes(QueryWrapper<WorkflowNode> wrapper, Long userId);


    /**
     * 获取节点
     * @param dataId 节点编号
     * @param userId 操作人
     * @return 节点
     */
    WorkflowNode getWorkflowNode(Long dataId, Long userId);

    /**
     * 新建节点
     * @param workflowNode 节点编号
     * @param userId 操作人
     * @return 节点编号
     */
    Long newWorkflowNode(WorkflowNode workflowNode, Long userId);

    /**
     * 更新节点
     * @param workflowNode 节点数据
     * @param userId 操作人
     * @return 结果数字
     */
    Integer updateWorkflowNode(WorkflowNode workflowNode, Long userId);

    /**
     * 废弃节点
     * @param dataId 节点编号
     * @param userId 操作人
     * @return 结果数字
     */
    Integer dropWorkflowNode(Long dataId, Long userId);

    /**
     * 批量获取工作流路径
     * @param wrapper 条件构造器
     * @param userId 操作人
     * @return 工作流路径数组
     */
    List<WorkflowRoute> getWorkflowRoutes(QueryWrapper<WorkflowRoute> wrapper, Long userId);


    /**
     * 获取路径
     * @param dataId 路径编号
     * @param userId 操作人
     * @return 路径
     */
    WorkflowRoute getWorkflowRoute(Long dataId, Long userId);

    /**
     * 新建路径
     * @param workflowRoute 路径
     * @param userId 操作人
     * @return 路径编号
     */
    Long newWorkflowRoute(WorkflowRoute workflowRoute, Long userId);

    /**
     * 修改路径
     * @param workflowRoute 路径数据
     * @param userId 操作人
     * @return 结果数字
     */
    Integer updateWorkflowRoute(WorkflowRoute workflowRoute, Long userId);

    /**
     * 废弃路径
     * @param dataId 路径编号
     * @param userId 操作人
     * @return 结果数字
     */
    Integer dropWorkflowRoute(Long dataId, Long userId);
}
