package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.RequestBacklogView;
import org.eoa.projectbudget.entity.RequestDoneView;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/1 15:51
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: RequestOut
 * @Description: 流程请求基本输出类
 * @Version: 1.0
 */

@Schema(name = "RequestOut",description = "流程表单输出类")
public class RequestOut implements VoOut {
    Long requestId;

    Long dataId;
    Long workflowId;
    Long currentNode;

    Integer requestStatus;

    String doneHistory;

    String flowHistory;
    Date submitTime;
    Date finishTime;

    Long creator;

    Date time;
    String timeSelectName;

    String workflowName;
    String currentNodeName;
    String creatorName;

    String requestTitle;

    public RequestOut(Request request) {
        this.requestId = request.getRequestId();
        this.dataId = request.getDataId();
        this.workflowId = request.getWorkflowId();
        this.currentNode = request.getCurrentNode();
        this.requestStatus = request.getRequestStatus();
        this.doneHistory = request.getDoneHistory();
        this.flowHistory = request.getFlowHistory();
        this.submitTime = request.getSubmitTime();
        this.finishTime = request.getFinishTime();
        this.creator = request.getCreator();
        this.requestTitle =request.getRequestTitle();
        switch (request.getClass().getSimpleName()) {
            case  "RequestBacklogView"->{
                time = ((RequestBacklogView) request).getArriveTime();
                timeSelectName = "arriveTime";
            }
            case "RequestDoneView" -> {
                time = ((RequestDoneView) request).getDoneTime();
                timeSelectName = "doneTime";
            }
        }
    }

    public Long getRequestId() {
        return requestId;
    }

    public RequestOut setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public Long getDataId() {
        return dataId;
    }

    public RequestOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public RequestOut setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Long getCurrentNode() {
        return currentNode;
    }

    public RequestOut setCurrentNode(Long currentNode) {
        this.currentNode = currentNode;
        return this;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public RequestOut setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public String getDoneHistory() {
        return doneHistory;
    }

    public RequestOut setDoneHistory(String doneHistory) {
        this.doneHistory = doneHistory;
        return this;
    }

    public String getFlowHistory() {
        return flowHistory;
    }

    public RequestOut setFlowHistory(String flowHistory) {
        this.flowHistory = flowHistory;
        return this;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public RequestOut setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
        return this;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public RequestOut setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public RequestOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public RequestOut setTime(Date time) {
        this.time = time;
        return this;
    }

    public String getTimeSelectName() {
        return timeSelectName;
    }

    public RequestOut setTimeSelectName(String timeSelectName) {
        this.timeSelectName = timeSelectName;
        return this;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public RequestOut setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
        return this;
    }

    public String getCurrentNodeName() {
        return currentNodeName;
    }

    public RequestOut setCurrentNodeName(String currentNoedeName) {
        this.currentNodeName = currentNoedeName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public RequestOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public RequestOut setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
        return this;
    }

    public RequestOut() {
    }

    @Override
    public void toBrowser(Long browserId) {
        this.dataId = null;
        this.workflowId = null;
        this.currentNode = null;
        this.requestStatus = null;
        this.doneHistory = null;
        this.flowHistory = null;
        this.submitTime = null;
        this.finishTime = null;
        this.creator = null;
        this.time = null;
        this.timeSelectName = null;
        this.workflowName = null;
        this.currentNodeName = null;
        this.creatorName = null;
        this.requestTitle = browserId==0?requestTitle:null;
    }
}
