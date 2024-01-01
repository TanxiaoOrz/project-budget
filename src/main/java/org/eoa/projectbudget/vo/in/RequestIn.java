package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 21:13
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: RequestIn
 * @Description: 请求操作传入类
 * @Version: 1.1
 */
public class RequestIn implements CheckParameter<RequestDto> {
    Long requestId;
    Long nodeId;
    Long workflowId;
    String comment;
    FormIn form;
    @Schema(description = "流程流转指定专用")
    List<Long> receivers;

    @Override
    public void checkSelf() throws ParameterException {
        if (requestId == null) {
            throw new ParameterException("requestId","","缺少请求编号");
        }
        if (workflowId == null) {
            throw new ParameterException("workflowId","","缺少对应流程编号");
        }
        if (nodeId == null) {
            throw new ParameterException("nodeId","","缺少当前节点id");
        }
        if (form == null) {
            throw new ParameterException("form","","缺少表单数据");
        }
        form.checkSelf();
    }

    /**
     *
     * @param action 对应操作
     * @return 待组装的requestDto对象
     * @throws ParameterException check出错
     */
    @Override
    public RequestDto toEntity(Long action) throws ParameterException {
        checkSelf();
        RequestDto requestDto = new RequestDto();
        return requestDto.setRequestId(requestId)
                .setComment(comment)
                .setAction(Math.toIntExact(action))
                .setWorkflowId(workflowId)
                .setNodeId(nodeId);
    }

    public List<Long> getReceivers() {
        return receivers;
    }

    public RequestIn setReceivers(List<Long> receivers) {
        this.receivers = receivers;
        return this;
    }

    public Long getRequestId() {
        return requestId;
    }

    public RequestIn setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public RequestIn setNodeId(Long nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public RequestIn setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public RequestIn setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public FormIn getForm() {
        return form;
    }

    public RequestIn setForm(FormIn form) {
        this.form = form;
        return this;
    }
}
