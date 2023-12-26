package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/26 21:15
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Workflow
 * @Description: 流程表单实体类
 * @Version: 1.0
 */

@TableName("`request`")
public class Workflow {
    @TableId(type = IdType.AUTO)
    Long dataId;

    Long moduleTypeId;
    Long tableId;
    Long titleColumnId;

    String workFlowName;
    String workFlowDescription;
    String workflowBaseTitle;


    Long creator;
    Date createTime;

    Integer isDeprecated;


    public Long getDataId() {
        return dataId;
    }

    public Workflow setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public Workflow setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public Workflow setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public Long getTitleColumnId() {
        return titleColumnId;
    }

    public Workflow setTitleColumnId(Long titleColumnId) {
        this.titleColumnId = titleColumnId;
        return this;
    }

    public String getWorkFlowName() {
        return workFlowName;
    }

    public Workflow setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
        return this;
    }

    public String getWorkFlowDescription() {
        return workFlowDescription;
    }

    public Workflow setWorkFlowDescription(String workFlowDescription) {
        this.workFlowDescription = workFlowDescription;
        return this;
    }

    public String getWorkflowBaseTitle() {
        return workflowBaseTitle;
    }

    public Workflow setWorkflowBaseTitle(String workflowBaseTitle) {
        this.workflowBaseTitle = workflowBaseTitle;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public Workflow setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Workflow setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public Workflow setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }
}
