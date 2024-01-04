package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 11:08
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: WorkflowNodeIn
 * @Description: 工作流节点输入类
 * @Version: 1.0
 **/
public class WorkflowNodeIn implements CheckParameter<WorkflowNode>{

    String nodeName;
    Integer isCounterSign;
    Integer nodeType;
    Long workflowId;
    Integer viewNo;

    String userAuthorityLimit;
    String beforeAction;
    String checkAction;
    String afterAction;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(nodeName)) {
            throw new ParameterException("nodeName","","缺少节点名称");
        }
        if (nodeType == null) {
            throw new ParameterException("nodeType","","缺少节点类型");
        }
        if (isCounterSign == null) {
            throw new ParameterException("isCounterSign","","缺少是否会签");
        }
        if (workflowId == null) {
            throw new ParameterException("workflowId","","缺少所属流程");
        }
    }

    @Override
    public WorkflowNode toEntity(Long dataId) throws ParameterException {
        checkSelf();
        WorkflowNode workflowNode = new WorkflowNode();
        return workflowNode.setDataId(dataId)
                .setName(nodeName)
                .setIsCounterSign(isCounterSign)
                .setNodeType(nodeType)
                .setWorkflowId(workflowId)
                .setViewNo(viewNo)
                .setUserAuthorityLimit(userAuthorityLimit)
                .setCheckAction(checkAction)
                .setAfterAction(afterAction)
                .setBeforeAction(beforeAction);
    }

    public WorkflowNodeIn() {
    }

    public String getNodeName() {
        return nodeName;
    }

    public WorkflowNodeIn setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public Integer getIsCounterSign() {
        return isCounterSign;
    }

    public WorkflowNodeIn setIsCounterSign(Integer isCounterSign) {
        this.isCounterSign = isCounterSign;
        return this;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public WorkflowNodeIn setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public WorkflowNodeIn setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public WorkflowNodeIn setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public String getUserAuthorityLimit() {
        return userAuthorityLimit;
    }

    public WorkflowNodeIn setUserAuthorityLimit(String userAuthorityLimit) {
        this.userAuthorityLimit = userAuthorityLimit;
        return this;
    }

    public String getBeforeAction() {
        return beforeAction;
    }

    public WorkflowNodeIn setBeforeAction(String beforeAction) {
        this.beforeAction = beforeAction;
        return this;
    }

    public String getCheckAction() {
        return checkAction;
    }

    public WorkflowNodeIn setCheckAction(String checkAction) {
        this.checkAction = checkAction;
        return this;
    }

    public String getAfterAction() {
        return afterAction;
    }

    public WorkflowNodeIn setAfterAction(String afterAction) {
        this.afterAction = afterAction;
        return this;
    }
}
