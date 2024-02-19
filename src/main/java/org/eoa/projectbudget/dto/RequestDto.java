package org.eoa.projectbudget.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.dto.workflow_json.Action;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.*;
import org.eoa.projectbudget.extension.WorkflowAction;
import org.eoa.projectbudget.extension.WorkflowCheck;
import org.eoa.projectbudget.mapper.FormDMLMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:42
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: RequestDto
 * @Description: 流程操作Dto类
 * @Version: 1.0
 **/
public class RequestDto {

    public final static int CREATE = 0;
    public final static int SUBMIT = 1;
    public final static int ADMIT = 2;
    public final static int REFUSE = 3;
    public final static int FLOW = 4;

    ObjectMapper objectMapper = new ObjectMapper();

    Long dataId;
    Long requestId;
    Long nodeId;
    Long workflowId;
    Integer action;
    String comment;

    Request request;
    Workflow workflow;
    WorkflowNode currentNode;
    FormOutDto formOutDto;

    List<WorkflowRoute> nextRoutes;
    HumanDto creator;


    public void arriveNode(JdbcTemplate jdbcTemplate, FormDMLMapper formDMLMapper) {
        if (WorkflowNode.FILE == (currentNode.getNodeType())) {
            request.setFinishTime(new Date());
        }
        String beforeAction = currentNode.getBeforeAction();
        doActions(jdbcTemplate,formDMLMapper,beforeAction);
        request.pushFlowHistory(nodeId,WorkflowNode.class,"到达节点");
    }

    public WorkflowRoute passRoute(JdbcTemplate jdbcTemplate, FormDMLMapper formDMLMapper) {
        WorkflowRoute nextRoute = null;
        for (WorkflowRoute route:
             nextRoutes) {
            String checkAction = route.getRouteAction();
            if (doChecks(jdbcTemplate, checkAction)) {
                nextRoute = route;
                break;
            }
        }
        if (nextRoute == null) {
            throw new ServerException("不存在符合条件的流转路径");
        }
        String routeAction = nextRoute.getRouteAction();
        doActions(jdbcTemplate, formDMLMapper, routeAction);
        request.pushFlowHistory(nextRoute.getDataId(), nextRoute.getClass(),"经过路径");
        return nextRoute;
    }


    public void checkNode(Long userId) throws EoaException {
        if (nodeId == null) {
            throw new ServerException("检查节点正确性出错,缺少nodeId");
        }
        if (currentNode == null) {
            throw new ServerException("检查节点正确性出错,缺少currentNode");
        }
        if (!nodeId.equals(currentNode.getDataId())) {
            throw new ParameterException("nodeId", nodeId.toString(), "不等于审批请求的当前节点");
        }

        if (action == null) {
            throw new ServerException("检查节点正确性出错,缺少action");
        }
        switch (action) {
            case CREATE -> {
                if (!Objects.equals(currentNode.getNodeType(), WorkflowNode.CREATE))
                    throw new AuthorityException(userId,"node", nodeId, "创建请求");
            }
            case SUBMIT -> {
                if (!Objects.equals(currentNode.getNodeType(), WorkflowNode.SUBMIT))
                    throw new AuthorityException(userId,"node", nodeId, "请求提交");
            }
            case ADMIT -> {
                if (!Objects.equals(currentNode.getNodeType(), WorkflowNode.ADMIT))
                    throw new AuthorityException(userId,"node", nodeId, "请求同意");
            }
            case REFUSE -> {
                if (!Objects.equals(currentNode.getNodeType(), WorkflowNode.ADMIT))
                    throw new AuthorityException(userId,"node", nodeId, "请求拒绝");
            }
            case FLOW -> {}
            default ->  throw new ParameterException("action",action.toString(),"错误的流程操作编号");
        }
    }

    public void leaveNode(JdbcTemplate jdbcTemplate, FormDMLMapper formDMLMapper, Long userId) {
        if (WorkflowNode.CREATE == (currentNode.getNodeType())) {
            request.setSubmitTime(new Date());
        }
        String checkAction = currentNode.getCheckAction();
        StringBuilder falseReason = new StringBuilder();
        if (!doChecks(jdbcTemplate, checkAction, falseReason)) {
            throw new ServerException(falseReason.toString());
        }
        request.pushDoneHistory(nodeId,currentNode.getWorkflowNodeName(),userId,action, comment);
        String afterAction = currentNode.getAfterAction();
        doActions(jdbcTemplate, formDMLMapper, afterAction);
        request.pushFlowHistory(nodeId,WorkflowNode.class,"离开节点");
    }

    private boolean doChecks(JdbcTemplate jdbcTemplate, String checkAction, StringBuilder falseReason) {
        Action action;
        AtomicBoolean checkConclusion = new AtomicBoolean(true);
        if (checkAction != null) {
            try {
                action = objectMapper.readValue(checkAction, Action.class);
            } catch (JsonProcessingException e) {
                throw new DataException("request",dataId.toString(),"checkAction", checkAction, "json解析失败");
            }
            action.getClassNames().forEach(name->{
                try {
                    Class<?> clazz = Class.forName(name);
                    WorkflowCheck workflowCheck = (WorkflowCheck) clazz.getDeclaredConstructor().newInstance();
                    boolean check1 = workflowCheck.check(this, jdbcTemplate);
                    checkConclusion.set(check1);
                    if (!check1)
                        falseReason.append(name).append("检测失败;");
                } catch (ClassNotFoundException |InstantiationException | IllegalAccessException |ClassCastException| NoSuchMethodException | InvocationTargetException e) {
                    throw new DataException("request",dataId.toString(),"checkAction", checkAction, name+"不存在或未继承WorkflowCheck");
                }
            });
            action.getTasks().forEach(task -> {
                Long columnId = task.getColumnId();
                Object mainValue = formOutDto.getMainValue(columnId);
                if (mainValue == null && formOutDto.getColumn(columnId) == null) {
                    throw new DataException("request",dataId.toString(),"checkAction", checkAction,formOutDto.table.getTableViewName()+"的主表不存在字段id" + columnId);
                }
                String admit;
                String warn;
                switch (task.getType()) {
                    case Action.INPUT -> {
                        admit = task.getInput();
                        warn = task.getInput();
                    }
                    case Action.SQL -> {
                        admit = jdbcTemplate.queryForObject(task.getInput(), String.class);
                        warn = "sql语句:" + task.getInput() + "结果" + admit;
                    }
                    case default ->
                            throw new DataException("request", dataId.toString(), "checkAction", checkAction, "task type 设置错误");
                }
                if (admit != null && !admit.equals(mainValue!=null?mainValue.toString():"")) {
                    checkConclusion.set(false);
                    falseReason.append("字段").append(formOutDto.getColumn(columnId).getColumnViewName()).append("值为").append(mainValue).append("不等于").append(warn).append(";");
                }
            });

        }
        return checkConclusion.get();
    }

    private boolean doChecks(JdbcTemplate jdbcTemplate, String checkAction) {
        Action action;
        AtomicBoolean checkConclusion = new AtomicBoolean(true);
        if (checkAction != null) {
            try {
                action = objectMapper.readValue(checkAction, Action.class);
            } catch (JsonProcessingException e) {
                throw new DataException("request",dataId.toString(),"checkAction", checkAction, "json解析失败");
            }
            action.getTasks().forEach(task -> {
                Long columnId = task.getColumnId();
                Object mainValue = formOutDto.getMainValue(columnId);
                if (mainValue == null && formOutDto.getColumn(columnId) == null) {
                    throw new DataException("request",dataId.toString(),"checkAction", checkAction,formOutDto.table.getTableViewName()+"的主表不存在字段id" + columnId);
                }
                String admit;
                switch (task.getType()) {
                    case Action.INPUT -> admit = task.getInput();
                    case Action.SQL -> admit = jdbcTemplate.queryForObject(task.getInput(), String.class);
                    case default ->
                            throw new DataException("request", dataId.toString(), "checkAction", checkAction, "task type 设置错误");
                }
                if (admit != null && !admit.equals(mainValue.toString())) {
                    checkConclusion.set(false);
                }
            });
            action.getClassNames().forEach(name->{
                try {
                    Class<?> clazz = Class.forName(name);
                    WorkflowCheck workflowCheck = (WorkflowCheck) clazz.getDeclaredConstructor().newInstance();
                    boolean check = workflowCheck.check(this, jdbcTemplate);
                    checkConclusion.set(check);

                } catch (ClassNotFoundException |InstantiationException | IllegalAccessException |ClassCastException| NoSuchMethodException | InvocationTargetException e) {
                    throw new DataException("request",dataId.toString(),"checkAction", checkAction, name+"不存在或未继承WorkflowCheck");
                }
            });
        }
        return checkConclusion.get();
    }

    private void doActions(JdbcTemplate jdbcTemplate, FormDMLMapper formDMLMapper, String actionString) {
        if (actionString != null) {
            Action action;
            try {
                action = objectMapper.readValue(actionString, Action.class);
            } catch (JsonProcessingException e) {
                throw new DataException("request",dataId.toString(),"action", actionString, "json解析失败");
            }

            Map<String,Object> updates = new HashMap<>();
            action.getTasks().forEach(task -> {
                String admit;
                switch (task.getType()) {
                    case Action.INPUT -> admit = task.getInput();
                    case Action.SQL -> admit = jdbcTemplate.queryForObject(task.getInput(), String.class);
                    case default ->
                            throw new DataException("request", dataId.toString(), "action", actionString, "task type 设置错误");
                }
                Long columnId = task.getColumnId();
                Column column = formOutDto.getColumn(columnId);
                if (column == null) {
                    throw new DataException("request", dataId.toString(), "action", actionString, formOutDto.table.getTableViewName()+"的主表不存在字段id" + columnId);
                }
                updates.put(column.getColumnDataName(),admit);
            });
            formDMLMapper.updateMainForm(updates, dataId, formOutDto.getTable().getTableDataName());
            action.getClassNames().forEach(name->{
                try {
                    Class<?> clazz = Class.forName(name);
                    WorkflowAction workflowAction = (WorkflowAction) clazz.getDeclaredConstructor().newInstance();
                    workflowAction.action(this, jdbcTemplate);
                } catch (ClassNotFoundException |InstantiationException | IllegalAccessException |ClassCastException| NoSuchMethodException | InvocationTargetException e) {
                    throw new DataException("request",dataId.toString(),"action", actionString, name+"不存在或未继承WorkflowAction");
                }
            });
        }
    }


    public Long getDataId() {
        return dataId;
    }

    public RequestDto setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getRequestId() {
        return requestId;
    }

    public RequestDto setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public RequestDto setNodeId(Long nodeId) {
        this.nodeId = nodeId;
        if (request != null) {
            request.setCurrentNode(nodeId);
        }
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public RequestDto setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Integer getAction() {
        return action;
    }

    public RequestDto setAction(Integer action) {
        this.action = action;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public RequestDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Request getRequest() {
        return request;
    }

    public RequestDto setRequest(Request request) {
        this.request = request;
        return this;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public RequestDto setWorkflow(Workflow workflow) {
        this.workflow = workflow;
        return this;
    }

    public WorkflowNode getCurrentNode() {
        return currentNode;
    }

    public RequestDto setCurrentNode(WorkflowNode currentNode) {
        this.currentNode = currentNode;
        if (currentNode != null) {
            this.nodeId = currentNode.getDataId();
            if (request != null)
                this.request.setCurrentNode(this.nodeId);
        }
        return this;
    }

    public RequestDto setCurrentNode(WorkflowNode currentNode, Boolean update) {
        if (update)
            return setCurrentNode(currentNode);
        else
            this.currentNode = currentNode;
        return this;
    }

    public FormOutDto getFormOutDto() {
        return formOutDto;
    }

    public RequestDto setFormOutDto(FormOutDto formOutDto) {
        this.formOutDto = formOutDto;
        if (formOutDto != null) {
            dataId = formOutDto.getDataId();
        }
        return this;
    }

    public List<WorkflowRoute> getNextRoutes() {
        return nextRoutes;
    }

    public RequestDto setNextRoutes(List<WorkflowRoute> nextRoutes) {
        this.nextRoutes = nextRoutes;
        return this;
    }

    public HumanDto getCreator() {
        return creator;
    }

    public RequestDto setCreator(HumanDto creator) {
        this.creator = creator;
        return this;
    }
}
