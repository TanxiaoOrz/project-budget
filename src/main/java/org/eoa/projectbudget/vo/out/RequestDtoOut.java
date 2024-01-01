package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.entity.WorkflowNode;

/**
 * @Author: 张骏山
 * @Date: 2024/1/1 15:57
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: RequestDtoOut
 * @Description: TODO
 * @Version: 1.0
 */
public class RequestDtoOut implements VoOut{

    RequestOut requestOut;
    Workflow workflow;
    FormOut formOut;

    WorkflowNode currentNode;


    public RequestOut getRequestOut() {
        return requestOut;
    }

    public RequestDtoOut setRequestOut(RequestOut requestOut) {
        this.requestOut = requestOut;
        return this;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public RequestDtoOut setWorkflow(Workflow workflow) {
        this.workflow = workflow;
        return this;
    }

    public FormOut getFormOut() {
        return formOut;
    }

    public RequestDtoOut setFormOut(FormOut formOut) {
        this.formOut = formOut;
        return this;
    }

    public WorkflowNode getCurrentNode() {
        return currentNode;
    }

    public RequestDtoOut setCurrentNode(WorkflowNode currentNode) {
        this.currentNode = currentNode;
        return this;
    }

    @Override
    public void toBrowser(Long browserId) {
        this.requestOut.toBrowser(browserId);
        this.workflow = null;
        this.formOut = null;
        this.currentNode = null;
    }
}
