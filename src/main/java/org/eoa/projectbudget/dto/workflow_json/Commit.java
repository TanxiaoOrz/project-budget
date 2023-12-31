package org.eoa.projectbudget.dto.workflow_json;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/31 20:58
 * @PackageName: org.eoa.projectbudget.dto.workflow_json
 * @ClassName: Commit
 * @Description: 流程操作历史记录
 * @Version: 1.0
 */
public class Commit {
    Long nodeId;
    Long humanId;
    Date time;
    Integer operation;
    String comment;

    public Commit() {
    }

    public Commit(Long nodeId, Long humanId, Integer operation, String comment) {
        this.nodeId = nodeId;
        this.humanId = humanId;
        this.operation = operation;
        this.comment = comment;
        this.time = new Date();
    }

    public Long getNodeId() {
        return nodeId;
    }

    public Commit setNodeId(Long nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public Long getHumanId() {
        return humanId;
    }

    public Commit setHumanId(Long humanId) {
        this.humanId = humanId;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public Commit setTime(Date time) {
        this.time = time;
        return this;
    }

    public Integer getOperation() {
        return operation;
    }

    public Commit setOperation(Integer operation) {
        this.operation = operation;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Commit setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
