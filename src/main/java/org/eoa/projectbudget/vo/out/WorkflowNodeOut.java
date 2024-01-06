package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.WorkflowNode;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 9:39
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: WorkflowNodeOut
 * @Description: 工作流节点输出类
 * @Version: 1.0
 **/
public class WorkflowNodeOut implements VoOut{

    Long dataId;

    String workflowNodeName;
    Integer isCounterSign;
    Integer nodeType;
    Long workflowId;
    Integer viewNo;


    String userAuthorityLimit;
    String tableModifyAuthority;
    String beforeAction;
    String checkAction;
    String afterAction;

    Long creator;
    Date createTime;

    String creatorName;
    String workflowName;
    Long tableId;

    public WorkflowNodeOut() {
    }

    public WorkflowNodeOut(WorkflowNode workflowNode) {
        this.dataId = workflowNode.getDataId();
        this.workflowNodeName = workflowNode.getWorkflowNodeName();
        this.isCounterSign = workflowNode.getIsCounterSign();
        this.nodeType = workflowNode.getNodeType();
        this.workflowId = workflowNode.getWorkflowId();
        this.viewNo = workflowNode.getViewNo();
        this.userAuthorityLimit = workflowNode.getUserAuthorityLimit();
        this.tableModifyAuthority = workflowNode.getTableModifyAuthority();
        this.beforeAction = workflowNode.getBeforeAction();
        this.checkAction = workflowNode.getCheckAction();
        this.afterAction = workflowNode.getAfterAction();
        this.creator = workflowNode.getCreator();
        this.createTime = workflowNode.getCreateTime();
    }

    public Long getDataId() {
        return dataId;
    }

    public WorkflowNodeOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getWorkflowNodeName() {
        return workflowNodeName;
    }

    public WorkflowNodeOut setWorkflowNodeName(String workflowNodeName) {
        this.workflowNodeName = workflowNodeName;
        return this;
    }

    public Integer getIsCounterSign() {
        return isCounterSign;
    }

    public WorkflowNodeOut setIsCounterSign(Integer isCounterSign) {
        this.isCounterSign = isCounterSign;
        return this;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public WorkflowNodeOut setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public WorkflowNodeOut setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public WorkflowNodeOut setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public String getUserAuthorityLimit() {
        return userAuthorityLimit;
    }

    public WorkflowNodeOut setUserAuthorityLimit(String userAuthorityLimit) {
        this.userAuthorityLimit = userAuthorityLimit;
        return this;
    }

    public String getTableModifyAuthority() {
        return tableModifyAuthority;
    }

    public WorkflowNodeOut setTableModifyAuthority(String tableModifyAuthority) {
        this.tableModifyAuthority = tableModifyAuthority;
        return this;
    }

    public String getBeforeAction() {
        return beforeAction;
    }

    public WorkflowNodeOut setBeforeAction(String beforeAction) {
        this.beforeAction = beforeAction;
        return this;
    }

    public String getCheckAction() {
        return checkAction;
    }

    public WorkflowNodeOut setCheckAction(String checkAction) {
        this.checkAction = checkAction;
        return this;
    }

    public String getAfterAction() {
        return afterAction;
    }

    public WorkflowNodeOut setAfterAction(String afterAction) {
        this.afterAction = afterAction;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public WorkflowNodeOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public WorkflowNodeOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public WorkflowNodeOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public WorkflowNodeOut setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public WorkflowNodeOut setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    @Override
    public void toBrowser(Long browserId) {
        this.workflowNodeName = browserId != 0L?null: workflowNodeName;
        this.isCounterSign = null;
        this.nodeType = null;
        this.workflowId = null;
        this.viewNo = null;
        this.userAuthorityLimit = null;
        this.beforeAction = null;
        this.checkAction = null;
        this.afterAction = null;
        this.creator = null;
        this.createTime = null;
        this.creatorName = null;
        this.workflowName = null;
        this.tableId = null;
    }
}
