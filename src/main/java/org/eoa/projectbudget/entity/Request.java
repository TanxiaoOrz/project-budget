package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.exception.DataException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Request pushDoneHistory(Long nodeId, Long humanId, Integer operation, String comment) {
        ObjectMapper objectMapper = new ObjectMapper();
        Commit commit = new Commit(nodeId, humanId, operation, comment);
        List<Commit> commits;
        try {
            if (doneHistory == null) {
                commits = new ArrayList<>();
            } else {
                commits = objectMapper.readValue(doneHistory, new TypeReference<List<Commit>>() {});
            }
            commits.add(commit);
            this.doneHistory = objectMapper.writeValueAsString(commits);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new DataException("request",dataId.toString(),"doneHistory", doneHistory, "json解析失败");
        }
        return this;
    }

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

    public static class Commit {
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
}



