package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/26 21:29
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: WorkflowNode
 * @Description: 流程节点传入类
 * @Version: 1.0
 */

@TableName("`workflow_node`")
public class WorkflowNode {

    public static Integer CREATE = 0;
    public static Integer ADMIT = 1;
    public static Integer SUBMIT = 2;
    public static Integer FILE = 3;


    @TableId(type = IdType.AUTO)
    Long dataId;

    String workflowNodeName;
    Integer isCounterSign;
    Integer nodeType;
    Long workflowId;
    Integer viewNo;


    String userAuthorityLimit;
    String beforeAction;
    String checkAction;
    String afterAction;

    Long creator;
    Date createTime;

    public Long getDataId() {
        return dataId;
    }

    public WorkflowNode setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getWorkflowNodeName() {
        return workflowNodeName;
    }

    public WorkflowNode setWorkflowNodeName(String workflowNodeName) {
        this.workflowNodeName = workflowNodeName;
        return this;
    }

    public Integer getIsCounterSign() {
        return isCounterSign;
    }

    public WorkflowNode setIsCounterSign(Integer isCounterSign) {
        this.isCounterSign = isCounterSign;
        return this;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public WorkflowNode setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public WorkflowNode setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public WorkflowNode setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public String getUserAuthorityLimit() {
        return userAuthorityLimit;
    }

    public WorkflowNode setUserAuthorityLimit(String userAuthorityLimit) {
        this.userAuthorityLimit = userAuthorityLimit;
        return this;
    }

    public String getBeforeAction() {
        return beforeAction;
    }

    public WorkflowNode setBeforeAction(String beforeAction) {
        this.beforeAction = beforeAction;
        return this;
    }

    public String getCheckAction() {
        return checkAction;
    }

    public WorkflowNode setCheckAction(String checkAction) {
        this.checkAction = checkAction;
        return this;
    }

    public String getAfterAction() {
        return afterAction;
    }

    public WorkflowNode setAfterAction(String afterAction) {
        this.afterAction = afterAction;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public WorkflowNode setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public WorkflowNode setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
