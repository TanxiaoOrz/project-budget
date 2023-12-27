package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:04
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: RequestDoneView
 * @Description: 已办流程视图实体类
 * @Version: 1.0
 **/

@TableName("`request_done_view`")
public class RequestDoneView extends Request{
    Long humanId;
    Date doneTime;

    public Long getHumanId() {
        return humanId;
    }

    public RequestDoneView setHumanId(Long humanId) {
        this.humanId = humanId;
        return this;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public RequestDoneView setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
        return this;
    }
}
