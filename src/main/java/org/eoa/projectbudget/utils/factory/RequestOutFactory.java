package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.WorkflowMapper;
import org.eoa.projectbudget.mapper.WorkflowNodeMapper;
import org.eoa.projectbudget.vo.out.RequestOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/1 16:50
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: RequestOutFactory
 * @Description: 流程请求基本输出类构造工厂
 * @Version: 1.0
 */

@Component
public class RequestOutFactory implements OutFactory<Request, RequestOut> {

    @Autowired
    HumanMapper humanMapper;
    @Autowired
    WorkflowMapper workflowMapper;
    @Autowired
    WorkflowNodeMapper workflowNodeMapper;

    @Override
    public RequestOut out(Request request) {
        if (request == null)
            return null;
        Workflow workflow = workflowMapper.selectById(request.getWorkflowId());
        WorkflowNode currentNode = workflowNodeMapper.selectById(request.getCurrentNode());
        HumanResource humanResource = humanMapper.selectById(request.getCreator());
        return new RequestOut(request)
                .setCreatorName(humanResource == null?"":humanResource.getName())
                .setCurrentNodeName(currentNode == null?"": currentNode.getNodeName())
                .setWorkflowName(workflow == null?"":workflow.getWorkFlowName());
    }

    @Override
    public List<RequestOut> outs(List<? extends Request> requests) {
        if (requests == null)
            return null;
        return requests.stream().map(this::out).toList();
    }
}
