package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Workflow;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 19:44
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: WorkflowOut
 * @Description: 工作流输出类
 * @Version: 1.0
 */

@Schema(name = "WorkflowOut", description = "工作流输出类")
public class WorkflowOut implements VoOut{

    @Schema(description = "流程编号")
    Long dataId;

    @Schema(description = "所属模块编号")
    Long moduleTypeId;
    @Schema(description = "关联表单id")
    Long tableId;
    @Schema(description = "标题字段名称")
    Long titleColumnId;

    @Schema(description = "流程名称,browserId=0")
    String workFlowName;
    @Schema(description = "流程描述")
    String workFlowDescription;
    @Schema(description = "流程基础标题")
    String workflowBaseTitle;


    @Schema(description = "创建者")
    Long creator;
    @Schema(description = "船舰时间")
    Date createTime;

    @Schema(description = "是否废弃")
    Integer isDeprecated;

    @Schema(description = "所属模块名称")
    String moduleTypeName;
    @Schema(description = "关联表单名称")
    String tableName;

    @Schema(description = "创建者名称")
    String creatorName;
    @Schema(description = "")
    Long createNode;
    @Schema(description = "")
    String createNodeName;

    public WorkflowOut() {
    }

    public WorkflowOut(Workflow workflow) {
        this.dataId = workflow.getDataId();
        this.moduleTypeId = workflow.getModuleTypeId();
        this.tableId = workflow.getTableId();
        this.titleColumnId = workflow.getTitleColumnId();
        this.workFlowName = workflow.getWorkFlowName();
        this.workFlowDescription = workflow.getWorkFlowDescription();
        this.workflowBaseTitle = workflow.getWorkflowBaseTitle();
        this.creator = workflow.getCreator();
        this.createTime = workflow.getCreateTime();
        this.isDeprecated = workflow.getIsDeprecated();
    }

    @Override
    public void toBrowser(Long browserId) {
        this.moduleTypeId = null;
        this.tableId = null;
        this.titleColumnId = null;
        this.workFlowName = browserId != 0?null:workFlowName;
        this.workFlowDescription = null;
        this.workflowBaseTitle = null;
        this.creator = null;
        this.createTime = null;
        this.isDeprecated = null;
        this.moduleTypeName = null;
        this.tableName = null;
        this.createNode = null;
        this.createNodeName = null;
        this.creatorName = null;
    }

    public Long getDataId() {
        return dataId;
    }

    public WorkflowOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public WorkflowOut setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public WorkflowOut setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public Long getTitleColumnId() {
        return titleColumnId;
    }

    public WorkflowOut setTitleColumnId(Long titleColumnId) {
        this.titleColumnId = titleColumnId;
        return this;
    }

    public String getWorkFlowName() {
        return workFlowName;
    }
    public WorkflowOut setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
        return this;
    }

    public String getWorkFlowDescription() {
        return workFlowDescription;
    }

    public WorkflowOut setWorkFlowDescription(String workFlowDescription) {
        this.workFlowDescription = workFlowDescription;
        return this;
    }

    public String getWorkflowBaseTitle() {
        return workflowBaseTitle;
    }

    public WorkflowOut setWorkflowBaseTitle(String workflowBaseTitle) {
        this.workflowBaseTitle = workflowBaseTitle;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public WorkflowOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public WorkflowOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public WorkflowOut setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    public String getModuleTypeName() {
        return moduleTypeName;
    }

    public WorkflowOut setModuleTypeName(String moduleTypeName) {
        this.moduleTypeName = moduleTypeName;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public WorkflowOut setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Long getCreateNode() {
        return createNode;
    }

    public WorkflowOut setCreateNode(Long createNode) {
        this.createNode = createNode;
        return this;
    }

    public String getCreateNodeName() {
        return createNodeName;
    }

    public WorkflowOut setCreateNodeName(String createNodeName) {
        this.createNodeName = createNodeName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public WorkflowOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }
}
