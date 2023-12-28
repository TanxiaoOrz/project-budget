package org.eoa.projectbudget.service.workflow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.RequestOutDto;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:31
 * @PackageName: org.eoa.projectbudget.service.workflow
 * @ClassName: WorkflowBackService
 * @Description: 流程模块后端操作接口
 * @Version: 1.1
 **/
public interface WorkflowBackService {



    /**
     * 获取所有请求
     * @param wrapper 条件构造器
     * @param userId 操作人
     * @return 请求数组
     */
    List<Request> getRequests(QueryWrapper<Request> wrapper, Long userId);

    /**
     * 获取请求监控页面
     * @param requestId 请求编号
     * @param userId 操作人
     * @return 请求展示数据
     */
    RequestOutDto getRequest(Long requestId, Long userId);

    /**
     * 删除请求和相关数据
     * @param requestId 请求编号
     * @param userId 操作人
     * @return 结果数字
     */
    Integer dropRequest(Long requestId, Long userId);

    /**
     * 请求流转
     * @param requestId 请求编号
     * @param nodeId 目标节点编号
     * @param receivers 强制指定接收人,为空数组则为节点默认接收人
     * @param userId 操作人
     * @return 结果数字
     */
    Integer transferRequest(Long requestId, Long nodeId, List<Long> receivers, Long userId);

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
    Long newWorkFlow(Workflow workflow, Long userId);

    /**
     * 修改流程
     * @param workflow 流程数据
     * @param userId 操作人
     * @return 结果数字
     */
    Integer updateWorkFlow(Workflow workflow, Long userId);

    /**
     * 废弃流程
     * @param dataId 流程编号
     * @param userId 操作人
     * @return 结果数字
     */
    Integer dropWorkFlow(Long dataId, Long userId);

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
