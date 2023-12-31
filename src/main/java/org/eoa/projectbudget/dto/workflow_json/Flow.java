package org.eoa.projectbudget.dto.workflow_json;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/31 20:58
 * @PackageName: org.eoa.projectbudget.dto.workflow_json
 * @ClassName: Flow
 * @Description: 流程
 * @Version: 1.0
 */
public class Flow {
    Date date;
    String object;
    Long id;
    String action;

    public Date getDate() {
        return date;
    }

    public Flow setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getObject() {
        return object;
    }

    public Flow setObject(String object) {
        this.object = object;
        return this;
    }

    public String getAction() {
        return action;
    }

    public Flow setAction(String action) {
        this.action = action;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Flow setId(Long id) {
        this.id = id;
        return this;
    }
}
