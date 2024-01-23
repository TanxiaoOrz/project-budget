package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/26 21:41
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: WorkflowRoute
 * @Description: 流程路径实体类
 * @Version: 1.0
 */

@TableName("`workflow_route`")
public class WorkflowRoute {

    @TableId(type = IdType.AUTO)
    Long dataId;

    String routeName;
    Long workflowId;
    Long startNodeId;
    Long endNodeId;
    Integer viewNo;

    String enterCondition;
    String routeAction;

    Long creator;
    Date createTime;

    public Long getDataId() {
        return dataId;
    }

    public WorkflowRoute setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public WorkflowRoute setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public WorkflowRoute setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Long getStartNodeId() {
        return startNodeId;
    }

    public WorkflowRoute setStartNodeId(Long startNodeId) {
        this.startNodeId = startNodeId;
        return this;
    }

    public Long getEndNodeId() {
        return endNodeId;
    }

    public WorkflowRoute setEndNodeId(Long endNodeId) {
        this.endNodeId = endNodeId;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public WorkflowRoute setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public String getEnterCondition() {
        return enterCondition;
    }

    public WorkflowRoute setEnterCondition(String enterCondition) {
        this.enterCondition = enterCondition;
        return this;
    }

    public String getRouteAction() {
        return routeAction;
    }

    public WorkflowRoute setRouteAction(String routeAction) {
        this.routeAction = routeAction;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public WorkflowRoute setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public WorkflowRoute setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "WorkflowRoute{" +
                "dataId=" + dataId +
                ", routeName='" + routeName + '\'' +
                ", workflowId=" + workflowId +
                ", startNodeId=" + startNodeId +
                ", endNodeId=" + endNodeId +
                ", viewNo=" + viewNo +
                ", enterCondition='" + enterCondition + '\'' +
                ", routeAction='" + routeAction + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                '}';
    }
}
