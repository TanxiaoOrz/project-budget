package org.eoa.projectbudget.service.workflow.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.entity.WorkflowRoute;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.exception.ServerException;
import org.eoa.projectbudget.mapper.*;
import org.eoa.projectbudget.service.workflow.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 9:01
 * @PackageName: org.eoa.projectbudget.service.workflow.impl
 * @ClassName: WorkflowServiceImpl
 * @Description: 流程后端业务类实现
 * @Version: 1.5
 **/

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    WorkflowMapper workflowMapper;
    @Autowired
    WorkflowNodeMapper workflowNodeMapper;
    @Autowired
    WorkflowRouteMapper workflowRouteMapper;
    @Autowired
    RequestMapper requestMapper;

    @Autowired
    ColumnEntityMapper columnEntityMapper;

    private final Logger log = LoggerFactory.getLogger("WorkflowModule");



    @Override
    public List<Workflow> getWorkflows(QueryWrapper<Workflow> wrapper, Long userId) {
        List<Workflow> workflows = workflowMapper.selectList(wrapper);
        log.info("用户=>{}获取工作流数组=>{}", userId, workflows.stream().map(Workflow::getDataId).toList());
        return workflows;
    }

    @Override
    public Workflow getWorkflow(Long dataId, Long userId) {
        Workflow workflow = workflowMapper.selectById(dataId);
        if (workflow == null) {
            log.error("用户=>{}获取工作流=>{},没有该数据", userId, dataId);
        }
        log.info("用户=>{}获取工作流=>{}", userId, dataId);
        return workflow;
    }

    @Override
    public Long newWorkflow(Workflow workflow, Long userId) {
        if (workflow.getTitleColumnId() != null) {
            ColumnEntity columnEntity = columnEntityMapper.selectById(workflow.getTitleColumnId());
            if (!Objects.equals(columnEntity.getTableNo(), workflow.getTableId())) {
                log.error("用户=>{}新建工作流出错,标题字段=>{}不属于关联表单=>{}", userId, workflow.getTitleColumnId(), workflow.getTableId());
                throw new ParameterException("TitleColumnId|TableNo", workflow.getTitleColumnId() + "|" + workflow.getTableId(), "该字段不属于关联表单");
            }
        }
        workflow.setCreator(userId)
                .setCreateTime(new Date());
        workflowMapper.insert(workflow);
        if (workflow.getDataId() != null) {
            log.info("用户=>{}新建工作流=>{}", userId, workflow);
            return workflow.getDataId();
        }
        log.error("用户=>{}新建工作流=>{}失败", userId, workflow);
        throw new ServerException("新增数据失败");
    }

    @Override
    public Integer updateWorkflow(Workflow workflow, Long userId) {
        Workflow old = workflowMapper.selectById(workflow.getDataId());
        if (old == null) {
            log.error("用户=>{}修改工作流出错,workflow=>{}不存在", userId, workflow.getDataId());
            throw new ParameterException("dataId", workflow.getDataId().toString(), "数据不存在");
        }
        workflow.setCreateTime(old.getCreateTime())
                .setCreator(old.getCreator());

        if (workflow.getTitleColumnId() != null) {
            ColumnEntity columnEntity = columnEntityMapper.selectById(workflow.getTitleColumnId());
            if (!Objects.equals(columnEntity.getTableNo(), workflow.getTableId())) {
                log.error("用户=>{}修改工作流出错,标题字段=>{}不属于关联表单=>{}", userId, workflow.getTitleColumnId(), workflow.getTableId());
                throw new ParameterException("TitleColumnId|TableNo", workflow.getTitleColumnId() + "|" + workflow.getTableId(), "该字段不属于关联表单");
            }
        }
        Integer update = workflowMapper.updateById(workflow);
        log.info("用户=>{}修改工作流=>{},修改数量=>{}", userId, workflow, update);
        return update;
    }

    @Override
    public Integer dropWorkflow(Long dataId, Long userId) {
        Workflow old = workflowMapper.selectById(dataId);
        if (old == null) {
            log.error("用户=>{}废弃工作流出错,workflow=>{}不存在", userId, dataId);
            throw new ParameterException("dataId", dataId.toString(), "数据不存在");
        }
        old.setIsDeprecated(1);
        int update = workflowMapper.updateById(old);
        log.info("用户=>{}修改工作流=>{},修改数量=>{}", userId, dataId, update);
        return update;
    }

    @Override
    public List<WorkflowNode> getWorkflowNodes(QueryWrapper<WorkflowNode> wrapper, Long userId) {
        List<WorkflowNode> workflowNodes = workflowNodeMapper.selectList(wrapper.orderByAsc("workflowId").orderByAsc("viewNo"));
        log.info("用户=>{}获取工作流节点数组=>{}", userId, workflowNodes.stream().map(WorkflowNode::getDataId).toList());
        return workflowNodes;
    }

    @Override
    public WorkflowNode getWorkflowNode(Long dataId, Long userId) {
        WorkflowNode workflowNode = workflowNodeMapper.selectById(dataId);
        if (workflowNode == null) {
            log.error("用户=>{}获取工作流节点=>{},没有该数据", userId, dataId);
        }
        log.info("用户=>{}获取工作流节点=>{}", userId, dataId);
        return workflowNode;
    }

    @Override
    public Long newWorkflowNode(WorkflowNode workflowNode, Long userId) {

        Long workflowId = workflowNode.getWorkflowId();
        Workflow workflow = workflowMapper.selectById(workflowId);

        if (Objects.equals(workflowNode.getNodeType(), WorkflowNode.CREATE)) {
            if (workflowNodeMapper.getCreateNodeElseCounts(workflowNode.getWorkflowId(), workflowNode.getDataId())!=0) {
                log.error("用户=>{}修改工作流节点出错,workflowNode=>{},创建节点已有", userId, workflowNode.getDataId());
                throw new ParameterException("nodeType", "0", "创建节点只能添加一个");
            }
        }

        if (workflow == null) {
            throw new ParameterException("workflowId", workflowId.toString(), "不存在该流程");
        }
        if (workflowNode.getViewNo() == null) {
            workflowNode.setViewNo(workflowNodeMapper.getViewNo(workflowId));
        }
        workflowNode.setCreator(userId)
                .setCreateTime(new Date());
        workflowNodeMapper.insert(workflowNode);
        if (workflowNode.getDataId() != null) {
            log.info("用户=>{}新建工作流节点=>{}", userId, workflowNode);
            return workflowNode.getDataId();
        }
        log.error("用户=>{}新建工作流节点=>{}失败", userId, workflowNode);
        throw new ServerException("新增数据失败");
    }

    @Override
    public Integer updateWorkflowNode(WorkflowNode workflowNode, Long userId) {
        if (Objects.equals(workflowNode.getNodeType(), WorkflowNode.CREATE)) {
            if (workflowNodeMapper.getCreateNodeElseCounts(workflowNode.getWorkflowId(), workflowNode.getDataId())!=0) {
                log.error("用户=>{}修改工作流节点出错,workflowNode=>{},创建节点已有", userId, workflowNode.getDataId());
                throw new ParameterException("nodeType", "0", "创建节点只能添加一个");
            }
        }

        WorkflowNode old = workflowNodeMapper.selectById(workflowNode.getDataId());
        if (old == null) {
            log.error("用户=>{}修改工作流节点出错,workflowNode=>{}不存在", userId, workflowNode.getDataId());
            throw new ParameterException("dataId", workflowNode.getDataId().toString(), "数据不存在");
        }
        if (!old.getWorkflowId().equals(workflowNode.getWorkflowId())) {
            log.error("用户=>{}修改工作流节点出错,workflowId不一致,原所属流程=>{},现所属流程=>{}", userId, old.getWorkflowId(), workflowNode.getWorkflowId());
            throw new ParameterException("dataId", workflowNode.getDataId().toString(), "和原有节点所属流程不一致");
        }
        if (workflowNode.getViewNo() == null) {
            workflowNode.setViewNo(old.getViewNo());
        }
        workflowNode.setCreateTime(old.getCreateTime())
                .setCreator(old.getCreator());

        Integer update = workflowNodeMapper.updateById(workflowNode);
        log.info("用户=>{}修改工作流节点=>{},修改数量=>{}", userId, workflowNode, update);
        return update;
    }

    @Override
    public WorkflowNode dropWorkflowNode(Long dataId, Long userId) {
        int update = workflowNodeMapper.deleteById(dataId);
        log.info("用户=>{}修改工作流节点=>{},修改数量=>{}", userId, dataId, update);
        return workflowNodeMapper.selectById(dataId);
    }

    @Override
    public List<WorkflowRoute> getWorkflowRoutes(QueryWrapper<WorkflowRoute> wrapper, Long userId) {
        List<WorkflowRoute> workflowRoutes = workflowRouteMapper.selectList(wrapper.orderByAsc("workflowId").orderByAsc("viewNo"));
        log.info("用户=>{}获取工作流路径数组=>{}", userId, workflowRoutes.stream().map(WorkflowRoute::getDataId).toList());
        return workflowRoutes;
    }

    @Override
    public WorkflowRoute getWorkflowRoute(Long dataId, Long userId) {
        WorkflowRoute workflowRoute = workflowRouteMapper.selectById(dataId);
        if (workflowRoute == null) {
            log.error("用户=>{}获取工作流路径=>{},没有该数据", userId, dataId);
        }
        log.info("用户=>{}获取工作流路径=>{}", userId, dataId);
        return workflowRoute;
    }

    @Override
    public Long newWorkflowRoute(WorkflowRoute workflowRoute, Long userId) {
        Long workflowId = workflowRoute.getWorkflowId();
        Workflow workflow = workflowMapper.selectById(workflowId);
        if (workflow == null) {
            throw new ParameterException("workflowId", workflowId.toString(), "不存在该流程");
        }
        if (workflowRoute.getViewNo() == null) {
            workflowRoute.setViewNo(workflowRouteMapper.getViewNo(workflowRoute.getWorkflowId()));
        }
        workflowRoute.setCreator(userId)
                .setCreateTime(new Date());
        workflowRouteMapper.insert(workflowRoute);
        if (workflowRoute.getDataId() != null) {
            log.info("用户=>{}新建工作流路径=>{}", userId, workflowRoute);
            return workflowRoute.getDataId();
        }
        log.error("用户=>{}新建工作流路径=>{}失败", userId, workflowRoute);
        throw new ServerException("新增数据失败");
    }

    @Override
    public Integer updateWorkflowRoute(WorkflowRoute workflowRoute, Long userId) {
        WorkflowRoute old = workflowRouteMapper.selectById(workflowRoute.getDataId());
        if (old == null) {
            log.error("用户=>{}修改工作流路径出错,workflowRoute=>{}不存在", userId, workflowRoute.getDataId());
            throw new ParameterException("dataId", workflowRoute.getDataId().toString(), "数据不存在");
        }
        if (!old.getWorkflowId().equals(workflowRoute.getWorkflowId())) {
            log.error("用户=>{}修改工作流路径出错,workflowId不一致,原所属流程=>{},现所属流程=>{}", userId, old.getWorkflowId(), workflowRoute.getWorkflowId());
            throw new ParameterException("dataId", workflowRoute.getDataId().toString(), "和原有路径所属流程不一致");
        }
        if (workflowRoute.getViewNo() == null) {
            workflowRoute.setViewNo(old.getViewNo());
        }
        workflowRoute.setCreateTime(old.getCreateTime())
                .setCreator(old.getCreator());

        Integer update = workflowRouteMapper.updateById(workflowRoute);
        log.info("用户=>{}修改工作流路径=>{},修改数量=>{}", userId, workflowRoute, update);
        return update;
    }

    @Override
    public Integer dropWorkflowRoute(Long dataId, Long userId) {
        int update = workflowRouteMapper.deleteById(dataId);
        log.info("用户=>{}修改工作流路径=>{},修改数量=>{}", userId, dataId, update);
        return update;
    }
}
