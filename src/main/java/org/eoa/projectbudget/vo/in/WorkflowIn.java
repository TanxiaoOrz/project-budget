package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 20:14
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: WorkflowIn
 * @Description: 工作流传入类
 * @Version: 1.0
 */

@Schema(name = "WorkflowIn", description = "工作流输入类")
public class WorkflowIn implements CheckParameter<Workflow> {

    @Schema(description = "所属模块编号")
    Long moduleTypeId;
    @Schema(description = "关联表单id")
    Long tableId;
    @Schema(description = "标题字段名称")
    Long titleColumnId;
    @Schema(description = "流程名称")
    String workFlowName;
    @Schema(description = "流程描述")
    String workFlowDescription;
    @Schema(description = "流程基础标题")
    String workflowBaseTitle;

    @Override
    public void checkSelf() throws ParameterException {
        if (moduleTypeId == null) {
            throw new ParameterException("moduleTypeId","","缺少所属模块");
        }
        if (tableId == null) {
            throw new ParameterException("tableId","","缺少关联表单");
        }
        if (DataProcessUtils.isEmpty(workFlowName)) {
            throw new ParameterException("workFlowName","","缺少工作流名称");
        }
    }

    @Override
    public Workflow toEntity(Long dataId) throws ParameterException {
        checkSelf();

        Workflow workflow = new Workflow();

        return workflow.setDataId(dataId)
                .setModuleTypeId(moduleTypeId)
                .setTableId(tableId)
                .setTitleColumnId(titleColumnId)
                .setWorkFlowName(workFlowName)
                .setWorkFlowDescription(workFlowDescription)
                .setWorkflowBaseTitle(workflowBaseTitle == null ? workFlowName : workflowBaseTitle);
    }
}
