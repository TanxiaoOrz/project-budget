package org.eoa.projectbudget.service.workflow.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.RequestOutDto;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.exception.ServerException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.WorkflowMapper;
import org.eoa.projectbudget.service.workflow.WorkflowBackService;
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
 * @ClassName: WorkflowBackServiceImpl
 * @Description: 流程后端业务类实现
 * @Version: 1.0
 **/

@Service
public class WorkflowBackServiceImpl implements WorkflowBackService {

    @Autowired
    WorkflowMapper workflowMapper;

    @Autowired
    ColumnEntityMapper columnEntityMapper;

    private final Logger log = LoggerFactory.getLogger("WorkflowModule");

    @Override
    public List<Request> getRequests(QueryWrapper<Request> wrapper, Long userId) {
        // TODO
        return null;
    }

    @Override
    public RequestOutDto getRequest(Long requestId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer dropRequest(Long requestId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer transferRequest(Long requestId, Long nodeId, List<Long> receivers, Long userId) {
        // TODO
        return null;
    }

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
    public Long newWorkFlow(Workflow workflow, Long userId) {
        if (workflow.getTitleColumnId() == null) {
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
    public Integer updateWorkFlow(Workflow workflow, Long userId) {
        Workflow old = workflowMapper.selectById(workflow.getDataId());
        if (old == null) {
            log.error("用户=>{}修改工作流出错,workflow=>{}不存在", userId, workflow.getDataId());
            throw new ParameterException("dataId",workflow.getDataId().toString(),"数据不存在");
        }
        workflow.setCreateTime(old.getCreateTime())
                .setCreator(old.getCreator());

        if (workflow.getTitleColumnId() == null) {
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
    public Integer dropWorkFlow(Long dataId, Long userId) {
        Workflow old = workflowMapper.selectById(dataId);
        if (old == null) {
            log.error("用户=>{}废弃工作流出错,workflow=>{}不存在", userId, dataId);
            throw new ParameterException("dataId",dataId.toString(),"数据不存在");
        }
        old.setIsDeprecated(1);
        int update = workflowMapper.updateById(old);
        log.info("用户=>{}修改工作流=>{},修改数量=>{}", userId, dataId, update);
        return update;
    }

    @Override
    public List<WorkflowNode> getWorkflowNodes(QueryWrapper<WorkflowNode> wrapper, Long userId) {
        return null;
    }

    @Override
    public WorkflowNode getWorkflowNode(Long dataId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Long newWorkflowNode(WorkflowNode workflowNode, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer updateWorkflowNode(WorkflowNode workflowNode, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer dropWorkflowNode(Long dataId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public List<WorkflowRoute> getWorkflowRoutes(QueryWrapper<WorkflowRoute> wrapper, Long userId) {
        return null;
    }

    @Override
    public WorkflowRoute getWorkflowRoute(Long dataId, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Long newWorkflowRoute(WorkflowRoute workflowRoute, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer updateWorkflowRoute(WorkflowRoute workflowRoute, Long userId) {
        // TODO
        return null;
    }

    @Override
    public Integer dropWorkflowRoute(Long dataId, Long userId) {
        // TODO
        return null;
    }
}
