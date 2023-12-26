package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/26 21:24
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Request
 * @Description: 流程请求数据实体类
 * @Version: 1.0
 */

@TableName("`request`")
public class Request {

    @TableId(type = IdType.AUTO)
    Long requestId;

    Long dataId;
    Long workflowId;
    Long currentNode;

    String doneHistory;
    Date submitTime;
    Date finishTime;

    public Long getRequestId() {
        return requestId;
    }

    public Request setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public Long getDataId() {
        return dataId;
    }

    public Request setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public Request setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    public Long getCurrentNode() {
        return currentNode;
    }

    public Request setCurrentNode(Long currentNode) {
        this.currentNode = currentNode;
        return this;
    }

    public String getDoneHistory() {
        return doneHistory;
    }

    public Request setDoneHistory(String doneHistory) {
        this.doneHistory = doneHistory;
        return this;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public Request setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
        return this;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public Request setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        return this;
    }
}
