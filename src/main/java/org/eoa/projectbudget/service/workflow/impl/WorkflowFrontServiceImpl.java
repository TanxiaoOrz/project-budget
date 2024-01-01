package org.eoa.projectbudget.service.workflow.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.dto.constraint.Constraint;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.mapper.*;
import org.eoa.projectbudget.service.workflow.WorkflowFrontService;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 9:09
 * @PackageName: org.eoa.projectbudget.service.workflow.impl
 * @ClassName: WorkflowFrontServiceImpl
 * @Description: 工作流前端业务类
 * @Version: 1.0
 **/

@Service
public class WorkflowFrontServiceImpl implements WorkflowFrontService {

    @Autowired
    RequestMapper requestMapper;
    @Autowired
    RequestDoneMapper doneMapper;
    @Autowired
    RequestBacklogMapper requestBacklogMapper;
    @Autowired
    WorkflowNodeMapper workflowNodeMapper;
    @Autowired
    WorkflowRouteMapper workflowRouteMapper;
    @Autowired
    WorkflowMapper workflowMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    FormDMLMapper formDMLMapper;

    private final Logger log = LoggerFactory.getLogger("WorkflowModule");

    @Override
    public List<Workflow> getCreateAbleList(QueryWrapper<RequestBacklogView> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public List<RequestBacklogView> getBackLogRequest(QueryWrapper<RequestBacklogView> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public List<RequestDoneView> getHaveDone(QueryWrapper<RequestDoneView> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public List<Request> getRequestsSelf(QueryWrapper<Request> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public RequestDto getRequest(Long requestId, HumanDto user) {
        return null;
    }

    @Override
    @Transactional
    public Long createRequest(RequestDto requestDto, HumanDto user) {
        log.info("用户=>{}创建流程=>{}", requestDto.getDataId(), user.getDataId());

        leaveNode(requestDto, user);
        WorkflowNode workflowNode = processRoute(requestDto);
        arriveNode(requestDto, workflowNode);
        return requestDto.getRequestId();
    }

    @Override
    @Transactional
    public List<Long> refuseRequest(RequestDto requestDto, HumanDto user) {
        log.info("用户=>{}拒绝流程=>{}", requestDto.getDataId(), user.getDataId());

        leaveNode(requestDto, user);
        ArrayList<Long> humans = new ArrayList<>();

        WorkflowNode workflowNode = getCreateNode(requestDto.getWorkflowId());
        requestMapper.doRequestAll(requestDto.getRequestId());
        arriveNode(requestDto, workflowNode);

        return humans;
    }

    @Override
    @Transactional
    public List<Long> submitRequest(RequestDto requestDto, HumanDto user) {
        log.info("用户=>{}提交流程=>{}", requestDto.getDataId(), user.getDataId());
        leaveNode(requestDto, user);
        ArrayList<Long> humans = new ArrayList<>();
        if (checkAllCommit(requestDto, humans)) {
            WorkflowNode workflowNode = processRoute(requestDto);
            arriveNode(requestDto, workflowNode);
        }
        return humans;
    }

    @Override
    @Transactional
    public List<Long> admitRequest(RequestDto requestDto, HumanDto user) {
        log.info("用户=>{}批准流程=>{}", requestDto.getDataId(), user.getDataId());


        leaveNode(requestDto, user);
        ArrayList<Long> humans = new ArrayList<>();
        if (checkAllCommit(requestDto, humans)) {
            WorkflowNode workflowNode = processRoute(requestDto);
            arriveNode(requestDto, workflowNode);
        }
        return humans;
    }

    @Override
    public Long getViewNode(Long requestId, HumanDto user) {
        Long viewNode = requestMapper.getViewNode(requestId, user.getDataId());
        if (viewNode == null) {
            log.warn("用户=>{}查看请求=>{},无权限", user.getDataId(), requestId);
            throw new AuthorityException(user.getDataId(), "request", requestId, "查看");
        }
        log.info("用户=>{}查看请求=>{},对应节点=>{}", user.getDataId(), requestId, viewNode);
        return viewNode;
    }

    @Override
    public RequestDto checkAndConsist(RequestDto requestDto, HumanDto user) throws EoaException {
        Long workflowId = requestDto.getWorkflowId();
        Workflow workflow = workflowMapper.selectById(workflowId);
        requestDto.setWorkflow(workflow);

        Long requestId = requestDto.getRequestId();
        Request request = requestBacklogMapper.selectOne(new QueryWrapper<RequestBacklogView>()
                .eq("requestId", requestId)
                .eq("humanId", user.getDataId()));
        if (request == null) {
            if (requestDto.getAction() == RequestDto.CREATE)
                throw new AuthorityException(user.getDataId(), "request", requestId, "流程流转");
            else {
                request = new Request()
                        .setWorkflowId(workflowId)
                        .setCreator(user.getDataId());
                requestDto.setRequest(request);
                requestDto.setCurrentNode(getCreateNode(workflowId));
                requestMapper.insert(request);
                requestDto.setRequestId(request.getRequestId());
            }
        } else {
            requestDto.setRequest(request);
            requestDto.setCurrentNode(workflowNodeMapper.selectById(request.getCurrentNode()));
        }

        requestDto.checkNode(user.getDataId());

        requestDto.setNextRoutes(workflowRouteMapper.selectList(new QueryWrapper<WorkflowRoute>()
                .eq("startNodeId", requestDto.getNodeId())
                .orderByDesc("viewNo")));
        return requestDto;
    }

    private WorkflowNode getCreateNode(Long workflowId) {
        return workflowNodeMapper.selectOne(new QueryWrapper<WorkflowNode>()
                .eq("workflowId", workflowId)
                .eq("nodeType", WorkflowNode.CREATE));
    }

    private void leaveNode(RequestDto requestDto, HumanDto user) {
        try {
            requestDto.leaveNode(jdbcTemplate, formDMLMapper, user.getDataId());
            requestMapper.doRequest(user.getDataId(), requestDto.getRequestId());
            try {
                requestMapper.addDone(user.getDataId(), requestDto.getRequestId(), requestDto.getNodeId(), requestDto.getWorkflowId());
            } catch (Exception e) {
                requestMapper.updateDone(user.getDataId(), requestDto.getRequestId(), requestDto.getNodeId());
            }
        } catch (EoaException e) {
            log.warn("request=>{}离开节点=>{},失败原因{}", requestDto.getRequestId(), requestDto.getNodeId(), e.description);
            throw e;
        }
    }

    private boolean checkAllCommit(RequestDto requestDto, List<Long> humans) {
        if (DataProcessUtils.translateIntegerToBoolean(requestDto.getCurrentNode().getIsCounterSign())) {
            List<Long> lastHumans = requestBacklogMapper.getBackLogHumanFromRequest(requestDto.getRequestId());
            if (lastHumans.size() == 0) {
                log.info("request=>{}提交完成检查是否可以离开节点=>{},结果=>true", requestDto.getRequestId(), requestDto.getNodeId());
                return true;
            } else {
                log.info("request=>{}提交完成检查是否可以离开节点=>{},结果=>{}未处理", requestDto.getRequestId(), requestDto.getNodeId(), lastHumans);
                humans.clear();
                humans.addAll(lastHumans);
                return false;
            }
        } else {
            log.info("request=>{}提交完成检查是否可以离开节点=>{},结果=>true", requestDto.getRequestId(), requestDto.getNodeId());
            requestMapper.doRequestAll(requestDto.getRequestId());
            return true;
        }
    }

    private WorkflowNode processRoute(RequestDto requestDto) {
        log.info("request=>{}开始流转", requestDto.getRequestId());
        WorkflowNode workflowNode;
        try {
            WorkflowRoute passRoute = requestDto.passRoute(jdbcTemplate, formDMLMapper);
            Long endNodeId = passRoute.getEndNodeId();
            workflowNode = workflowNodeMapper.selectById(endNodeId);
            if (workflowNode == null) {
                throw new DataException("workflowRoute", passRoute.getDataId().toString(), "endNodeId", endNodeId.toString(), "不存在该节点");
            }
        } catch (EoaException e) {
            log.warn("request=>{}流转失败,失败原因{}", requestDto.getRequestId(), e.description);
            throw e;
        }
        return workflowNode;
    }

    private void arriveNode(RequestDto requestDto, WorkflowNode node) {
        requestDto.setNodeId(node.getDataId());
        requestDto.setCurrentNode(node);
        try {
            Constraint constraint = AuthorityUtils.getConstraint(node.getUserAuthorityLimit());
            List<Long> backLogHumans = AuthorityUtils.getInHumansForm(requestDto.getCreator(), requestDto.getFormOutDto(), constraint);
            requestMapper.newBacklogs(backLogHumans, requestDto.getRequestId(), node.getDataId(), requestDto.getWorkflowId());
        } catch (EoaException e) {
            log.warn("request=>{}到达获取节点=>{}操作人失败,失败原因{}", requestDto.getRequestId(), node.getDataId(), e.description);
            throw e;
        }
        try {
            requestDto.arriveNode(jdbcTemplate, formDMLMapper);
        } catch (EoaException e) {
            log.warn("request=>{}到达获取节点=>{}节点前操作失败,失败原因{}", requestDto.getRequestId(), node.getDataId(), e.description);
            throw e;
        }
        requestMapper.updateById(requestDto.getRequest());
    }
}
