package org.eoa.projectbudget.dto.workflow_json;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/30 19:10
 * @PackageName: org.eoa.projectbudget.dto.workflow_json
 * @ClassName: Action
 * @Description: 流程操作json转换对象
 * @Version: 1.0
 */
public class Action {
    public final static int SQL = 0;
    public final static int INPUT = 1;
    List<String> classNames;
    List<Task> tasks;

    public static class Task {
        Long columnId;
        Integer type;
        String input;

        public Long getColumnId() {
            return columnId;
        }

        public Task setColumnId(Long columnId) {
            this.columnId = columnId;
            return this;
        }

        public Integer getType() {
            return type;
        }

        public Task setType(Integer type) {
            this.type = type;
            return this;
        }

        public String getInput() {
            return input;
        }

        public Task setInput(String input) {
            this.input = input;
            return this;
        }
    }

    public List<String> getClassNames() {
        return classNames;
    }

    public Action setClassNames(List<String> classNames) {
        this.classNames = classNames;
        return this;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Action setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }


}

