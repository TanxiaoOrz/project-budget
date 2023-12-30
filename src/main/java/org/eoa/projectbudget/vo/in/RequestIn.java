package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.ParameterException;

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
}
