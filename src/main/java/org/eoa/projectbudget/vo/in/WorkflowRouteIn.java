package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.WorkflowRoute;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.vo.out.Vo;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 11:32
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: WorkflowRouteIn
 * @Description: 工作流路径输入类
 * @Version: 1.0
 **/
public class WorkflowRouteIn implements CheckParameter<WorkflowRoute> {
    String routeName;
    Long workflowId;
    Long startNodeId;
    Long endNodeId;
    Integer viewNo;

    String enterCondition;
    String routeCondition;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(routeName)) {
            throw new ParameterException("routeName","","缺少路径名称");
        }
        if (startNodeId == null) {
            throw new ParameterException("startNodeId","","缺少起点");
        }
        if (endNodeId == null) {
            throw new ParameterException("endNodeId","","缺少重点");
        }
        if (workflowId == null) {
            throw new ParameterException("workflowId","","缺少所属流程");
        }
    }

    @Override
    public WorkflowRoute toEntity(Long dataId) throws ParameterException {
        WorkflowRoute workflowRoute = new WorkflowRoute();

        return workflowRoute.setDataId(dataId)
                .setRouteName(routeName)
                .setWorkflowId(workflowId)
                .setStartNodeId(startNodeId)
                .setEndNodeId(endNodeId)
                .setViewNo(viewNo)
                .setEnterCondition(enterCondition)
                .setRouteCondition(routeCondition);
    }

    public String getRouteName() {
        return routeName;
    }

    public WorkflowRouteIn setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public WorkflowRouteIn setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Long getStartNodeId() {
        return startNodeId;
    }

    public WorkflowRouteIn setStartNodeId(Long startNodeId) {
        this.startNodeId = startNodeId;
        return this;
    }

    public Long getEndNodeId() {
        return endNodeId;
    }

    public WorkflowRouteIn setEndNodeId(Long endNodeId) {
        this.endNodeId = endNodeId;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public WorkflowRouteIn setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public String getEnterCondition() {
        return enterCondition;
    }

    public WorkflowRouteIn setEnterCondition(String enterCondition) {
        this.enterCondition = enterCondition;
        return this;
    }

    public String getRouteCondition() {
        return routeCondition;
    }

    public WorkflowRouteIn setRouteCondition(String routeCondition) {
        this.routeCondition = routeCondition;
        return this;
    }
}
