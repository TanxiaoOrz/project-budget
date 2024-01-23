package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:22
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: RequestBacklogView
 * @Description: 代办流程视图实体类
 * @Version: 1.0
 **/

@TableName("`request_back_log_view`")
public class RequestBacklogView extends Request{

    Long humanId;
    Date arriveTime;

    public Long getHumanId() {
        return humanId;
    }

    public RequestBacklogView setHumanId(Long humanId) {
        this.humanId = humanId;
        return this;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public RequestBacklogView setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
        return this;
    }

    @Override
    public String toString() {
        return "RequestBacklogView{" +
                "humanId=" + humanId +
                ", arriveTime=" + arriveTime +
                ", requestId=" + requestId +
                ", dataId=" + dataId +
                ", workflowId=" + workflowId +
                ", currentNode=" + currentNode +
                ", creator=" + creator +
                ", requestTitle='" + requestTitle + '\'' +
                ", requestStatus=" + requestStatus +
                ", doneHistory='" + doneHistory + '\'' +
                ", flowHistory='" + flowHistory + '\'' +
                ", submitTime=" + submitTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
