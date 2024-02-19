package org.eoa.projectbudget.service.workflow.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.dto.constraint.Constraint;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.*;
import org.eoa.projectbudget.mapper.*;
import org.eoa.projectbudget.service.workflow.RequestService;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 9:09
 * @PackageName: org.eoa.projectbudget.service.workflow.impl
 * @ClassName: RequestServiceImpl
 * @Description: 工作流前端业务类
 * @Version: 1.5
 **/

@Service
public class RequestServiceImpl implements RequestService {

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
    @SuppressWarnings("all")
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    FormDMLMapper formDMLMapper;

    private final Logger log = LoggerFactory.getLogger("WorkflowModule");

    @Override
    public List<RequestDto> getCreateAbleList(QueryWrapper<Workflow> wrapper, Long userId) {
        List<Workflow> workflowsOnUsed = workflowMapper.selectList(wrapper.eq("isDeprecated", DataProcessUtils.translateBooleanToInteger(false)));
        List<RequestDto> workflowsWithCreateNode = workflowsOnUsed.stream().map(
                workflow -> new RequestDto()
                        .setWorkflow(workflow)
                        .setCurrentNode(getCreateNode(workflow.getDataId()))
        ).filter(requestDto -> requestDto.getCurrentNode() != null).toList();
        log.info("用户=>{}获取可创建流程列表,共计流程个数=>{}",userId,workflowsWithCreateNode.size());
        return workflowsWithCreateNode;
    }

    @Override
    public List<Request> getRequests(QueryWrapper<Request> wrapper, Long userId) {
        List<Request> requests = requestMapper.selectList(wrapper);
        log.info("用户=>{}获取列表获取数量=>{}", userId, requests.size());
        return requests;
    }

//    @Override
//    public RequestDto getRequestControl(Long requestId, Long userId) {
//
//        return null;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<Long,Long> dropRequest(Long requestId, Long userId) {
        Request request = requestMapper.selectById(requestId);
        int delete = requestMapper.deleteById(requestId);
        Integer deleteBacks = requestMapper.doRequestAll(requestId);
        Integer deleteDones = requestMapper.deleteDones(requestId);
        log.info("用户=>{}删除流程=>{},删除数量=>{},删除待办数量=>{},删除已办数量=>{}",userId,requestId,delete,deleteBacks,deleteDones);
        if (delete == 0) {
            return null;
        }
        Long tableId = workflowMapper.selectById(request.getWorkflowId()).getTableId();
        HashMap<Long,Long> map = new HashMap<>();
        map.put(tableId, request.getDataId());
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer transferRequest(RequestDto requestDto, Long nodeId, List<Long> receivers, Long userId) {
        log.info("用户=>{}将流程=>{}流转至节点=>{}指定操作人=>{}",userId,requestDto.getRequestId(),nodeId,receivers);
        leaveNode(requestDto, userId);
        requestMapper.doRequestAll(requestDto.getRequestId());
        WorkflowNode node = workflowNodeMapper.selectById(nodeId);
        if (!Objects.equals(requestDto.getWorkflowId(), node.getWorkflowId()))
            throw new ParameterException("nodeId", nodeId.toString(), "该节点不属于该流程");
        arriveNode(requestDto, node);
        if (receivers != null && receivers.size()!=0) {
            requestMapper.doRequestAll(requestDto.getRequestId());
            requestMapper.newBacklogs(receivers, requestDto.getRequestId(), nodeId, requestDto.getWorkflowId());
        }
        return 1;
    }

    @Override
    public List<RequestBacklogView> getBackLogRequest(QueryWrapper<RequestBacklogView> wrapper, Long userId) {
        List<RequestBacklogView> backlogs = requestBacklogMapper.selectList(wrapper.eq("humanId", userId).orderByDesc("arriveTime"));
        log.info("用户=>{}获取待办列表获取数量=>{}", userId, backlogs);
        return backlogs;
    }

    @Override
    public List<RequestDoneView> getHaveDone(QueryWrapper<RequestDoneView> wrapper, Long userId) {
        List<RequestDoneView> does = doneMapper.selectList(wrapper.eq("humanId", userId).orderByDesc("doneTime"));
        log.info("用户=>{}获取已办列表获取数量=>{}", userId, does.size());
        return does;
    }

    @Override
    public List<Request> getRequestsSelf(QueryWrapper<Request> wrapper, Long userId) {
        List<Request> requests = requestMapper.selectList(wrapper.eq("creator", userId).orderByDesc("finishTime").orderByDesc("submitTime"));
        log.info("用户=>{}获取我的请求获取数量=>{}", userId, requests.size());
        return requests;
    }

    @Override
    public RequestDto getRequest(Long requestId, Long userId) {
        log.info("用户=>{}获取流程=>{}", userId, requestId);
        RequestDto requestDto = new RequestDto();
        Request request = requestMapper.selectById(requestId);
        if (request == null) {
            log.error("用户=>{}获取表单=>{},success=>{}",userId,requestId, true);
            throw new ParameterException("requestId",requestId.toString(),"不存在该流程");
        }
        requestDto.setRequestId(requestId)
                .setRequest(request)
                .setDataId(request.getDataId());
        Long workflowId = request.getWorkflowId();
        Workflow workflow = workflowMapper.selectById(workflowId);
        requestDto.setWorkflowId(workflowId)
                .setWorkflow(workflow);

        return requestDto;
    }

    @Override
    public RequestDto getRequestCreate(Long workflowId, Long userId) {
        Workflow workflow = workflowMapper.selectById(workflowId);
        if (workflow == null) {
            throw new ParameterException("workflowId",workflowId.toString(),"不存在该流程");
        }
        if (DataProcessUtils.translateIntegerToBoolean(workflow.getIsDeprecated()))
            throw new ParameterException("workflowId",workflowId.toString(),"该流程已废弃");
        WorkflowNode workflowNode = getCreateNode(workflowId);

        log.info("用户=>{}创建工作流=>{}流程", userId, workflowId);
        RequestDto requestDto = new RequestDto();

        return requestDto.setDataId(0L)
                .setWorkflow(workflow)
                .setCurrentNode(workflowNode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRequest(RequestDto requestDto, Long userId) {
        log.info("用户=>{}创建流程=>{}", requestDto.getDataId(), userId);
        StringBuilder title = new StringBuilder();
        Request request = requestDto.getRequest();
        Workflow workflow = requestDto.getWorkflow();

        String baseTitle = workflow.getWorkflowBaseTitle() == null ?workflow.getWorkFlowName():workflow.getWorkflowBaseTitle();
        title.append(baseTitle);

        Object mainValue = requestDto.getFormOutDto().getMainValue(workflow.getTitleColumnId());
        if (mainValue != null) {
            title.append(mainValue);
        }

        request.setRequestTitle(title.toString());
        leaveNode(requestDto, userId);
        WorkflowNode workflowNode = processRoute(requestDto);
        arriveNode(requestDto, workflowNode);
        return requestDto.getRequestId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> refuseRequest(RequestDto requestDto, Long userId) {
        log.info("用户=>{}拒绝流程=>{}", requestDto.getDataId(), userId);

        leaveNode(requestDto, userId);
        ArrayList<Long> humans = new ArrayList<>();

        WorkflowNode workflowNode = getCreateNode(requestDto.getWorkflowId());
        requestMapper.doRequestAll(requestDto.getRequestId());
        arriveNode(requestDto, workflowNode);

        return humans;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> submitRequest(RequestDto requestDto, Long userId) {
        log.info("用户=>{}提交流程=>{}", requestDto.getDataId(), userId);
        leaveNode(requestDto, userId);
        ArrayList<Long> humans = new ArrayList<>();
        if (checkAllCommit(requestDto, humans)) {
            WorkflowNode workflowNode = processRoute(requestDto);
            arriveNode(requestDto, workflowNode);
        }
        return humans;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> admitRequest(RequestDto requestDto, Long userId) {
        log.info("用户=>{}批准流程=>{}", requestDto.getDataId(), userId);

        leaveNode(requestDto, userId);
        ArrayList<Long> humans = new ArrayList<>();
        if (checkAllCommit(requestDto, humans)) {
            WorkflowNode workflowNode = processRoute(requestDto);
            arriveNode(requestDto, workflowNode);
        }
        return humans;
    }

    @Override
    public Long getViewNode(Long requestId, Long userId) {
        Long viewNode = requestMapper.getViewNode(requestId, userId);
        if (viewNode == null) {
            log.warn("用户=>{}查看请求=>{},无权限", userId, requestId);
            throw new AuthorityException(userId, "request", requestId, "查看");
        }
        log.info("用户=>{}查看请求=>{},对应节点=>{}", userId, requestId, viewNode);
        return viewNode;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RequestDto checkAndConsist(RequestDto requestDto, Long userId) throws EoaException {

        Long workflowId = requestDto.getWorkflowId();
        Workflow workflow = workflowMapper.selectById(workflowId);
        requestDto.setWorkflow(workflow);

        Long requestId = requestDto.getRequestId();
        Request request = requestBacklogMapper.selectOne(new QueryWrapper<RequestBacklogView>()
                .eq("requestId", requestId)
                .eq("humanId", userId));
        if (request == null) {
            switch (requestDto.getAction()) {
                case RequestDto.CREATE -> {
                        if (DataProcessUtils.translateIntegerToBoolean(workflow.getIsDeprecated()))
                            throw new ParameterException("workflowId", workflowId.toString(), "该流程已废弃");
                        request = new Request()
                                .setWorkflowId(workflowId)
                                .setCreator(userId);
                        requestDto.setRequest(request);
                        requestDto.setCurrentNode(getCreateNode(workflowId));
                        requestMapper.insert(request);
                        requestDto.setRequestId(request.getRequestId());
                        requestMapper.newBacklogs(Collections.singletonList(userId), request.getRequestId(), request.getCurrentNode(), workflowId);
                }
                case RequestDto.FLOW -> {
                    request = requestMapper.selectById(requestId);
                    requestDto.setRequest(request);
                    requestDto.setCurrentNode(workflowNodeMapper.selectById(request.getCurrentNode()));
                }
                default -> throw new AuthorityException(userId, "request", requestId, "流程流转");
            }
        } else {
            requestDto.setRequest(request);
            requestDto.setCurrentNode(workflowNodeMapper.selectById(request.getCurrentNode()));
        }
        requestDto.setDataId(request.getDataId());
        requestDto.checkNode(userId);

        requestDto.setNextRoutes(workflowRouteMapper.selectList(new QueryWrapper<WorkflowRoute>()
                .eq("startNodeId", requestDto.getNodeId())
                .orderByAsc("viewNo")));
        return requestDto;
    }

    @Override
    public WorkflowNode getCreateNode(Long workflowId) {
        return workflowNodeMapper.selectOne(new QueryWrapper<WorkflowNode>()
                .eq("workflowId", workflowId)
                .eq("nodeType", WorkflowNode.CREATE));
    }

    private void leaveNode(RequestDto requestDto, Long userId) {
        Long requestId = requestDto.getRequestId();
        Long nodeId = requestDto.getNodeId();
        Long workflowId = requestDto.getWorkflowId();
        try {
            requestDto.leaveNode(jdbcTemplate, formDMLMapper, userId);
            doneRequest(userId, requestId, nodeId, workflowId);
        } catch (EoaException e) {
            log.warn("request=>{}离开节点=>{},失败原因{}", requestId, nodeId, e.description);
            throw e;
        }
    }

    @Override
    public void doneRequest(Long userId, Long requestId, Long nodeId, Long workflowId) {
        Integer deletes = requestMapper.doRequest(userId, requestId);
        if (deletes != 0) {
            try {
                requestMapper.addDone(userId, requestId, nodeId, workflowId);
            } catch (Exception e) {
                requestMapper.updateDone(userId, requestId, nodeId);
            }
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
            log.info("途径路径=>{}",passRoute.getDataId());
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
        log.info("request=>{}到达节点=>{}",requestDto.getRequestId(),node.getDataId());
        try {
            Constraint constraint = AuthorityUtils.getConstraint(node.getUserAuthorityLimit());
            List<Long> backLogHumans = AuthorityUtils.getInHumansForm(requestDto.getCreator(), requestDto.getFormOutDto(), constraint);
            if (backLogHumans.size() == 0)
                throw new ServerException("下一个节点:"+node.getWorkflowNodeName()+"没有操作人,请联系管理员");
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
