package org.eoa.projectbudget.dto;

import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:42
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: RequestDto
 * @Description: 流程操作Dto类
 * @Version: 1.0
 **/
public class RequestDto {

    public static int CREATE = 0;
    public static int SUBMIT = 1;
    public static int ADMIT = 2;
    public static int REFUSE = 3;

    FormInDto formInDto;
    Long dataId;
    Long requestId;
    Long nodeId;
    Integer action;
    String commit;

    Request request;
    Workflow workflow;
    WorkflowNode currentNode;
    FormOutDto formOutDto;

    boolean hasConsistedIn = false;
    boolean isHasConsistedOut = false;


    public RequestDto setFormInDto(FormInDto formInDto) {
        this.formInDto = formInDto;
        return this;
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
        return this;
    }

    public Integer getAction() {
        return action;
    }

    public RequestDto setAction(Integer action) {
        this.action = action;
        return this;
    }

    public String getCommit() {
        return commit;
    }

    public RequestDto setCommit(String commit) {
        this.commit = commit;
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
        return this;
    }

    public FormOutDto getFormOutDto() {
        return formOutDto;
    }

    public RequestDto setFormOutDto(FormOutDto formOutDto) {
        this.formOutDto = formOutDto;
        return this;
    }
}
