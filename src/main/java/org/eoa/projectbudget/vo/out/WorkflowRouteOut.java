package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.WorkflowRoute;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 9:39
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: WorkflowRouteOut
 * @Description: 工作流路径输出类
 * @Version: 1.0
 **/
public class WorkflowRouteOut implements VoOut{

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

    String creatorName;
    String workflowName;
    String startNodeName;
    String endNodeName;
    Long tableId;

    public WorkflowRouteOut() {
    }

    public WorkflowRouteOut(WorkflowRoute workflowRoute) {
        this.dataId = workflowRoute.getDataId();
        this.routeName = workflowRoute.getRouteName();
        this.workflowId = workflowRoute.getWorkflowId();
        this.startNodeId = workflowRoute.getStartNodeId();
        this.endNodeId = workflowRoute.getEndNodeId();
        this.viewNo = workflowRoute.getViewNo();
        this.enterCondition = workflowRoute.getEnterCondition();
        this.routeAction = workflowRoute.getRouteAction();
        this.creator = workflowRoute.getCreator();
        this.createTime = workflowRoute.getCreateTime();
    }

    public Long getDataId() {
        return dataId;
    }

    public WorkflowRouteOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public WorkflowRouteOut setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public WorkflowRouteOut setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Long getStartNodeId() {
        return startNodeId;
    }

    public WorkflowRouteOut setStartNodeId(Long startNodeId) {
        this.startNodeId = startNodeId;
        return this;
    }

    public Long getEndNodeId() {
        return endNodeId;
    }

    public WorkflowRouteOut setEndNodeId(Long endNodeId) {
        this.endNodeId = endNodeId;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public WorkflowRouteOut setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public String getEnterCondition() {
        return enterCondition;
    }

    public WorkflowRouteOut setEnterCondition(String enterCondition) {
        this.enterCondition = enterCondition;
        return this;
    }

    public String getRouteAction() {
        return routeAction;
    }

    public WorkflowRouteOut setRouteAction(String routeAction) {
        this.routeAction = routeAction;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public WorkflowRouteOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public WorkflowRouteOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public WorkflowRouteOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public WorkflowRouteOut setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
        return this;
    }

    public String getStartNodeName() {
        return startNodeName;
    }

    public WorkflowRouteOut setStartNodeName(String startNodeName) {
        this.startNodeName = startNodeName;
        return this;
    }

    public String getEndNodeName() {
        return endNodeName;
    }

    public WorkflowRouteOut setEndNodeName(String endNodeName) {
        this.endNodeName = endNodeName;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public WorkflowRouteOut setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    @Override
    public void toBrowser(Long browserId) {
        this.routeName = browserId!=0L?null:routeName;
        this.workflowId = null;
        this.startNodeId = null;
        this.endNodeId = null;
        this.viewNo = null;
        this.enterCondition = null;
        this.routeAction = null;
        this.creator = null;
        this.createTime = null;
        this.creatorName = null;
        this.workflowName = null;
        this.startNodeName = null;
        this.endNodeName = null;
        this.tableId = null;
    }

}
