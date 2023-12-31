package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/11/13 16:45
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: ModuleIn
 * @Description: TODO
 * @Version: 1.0
 **/

@Schema(name = "ModuleIn",description = "模块传入结构体")
public class ModuleIn implements CheckParameter<ModuleType>{
    @Schema(description = "应用名称")
    String moduleTypeName;
    @Schema(description = "应用备注")
    String workflowRemark;

    public String getModuleTypeName() {
        return moduleTypeName;
    }

    public ModuleIn setModuleTypeName(String moduleTypeName) {
        this.moduleTypeName = moduleTypeName;
        return this;
    }

    public String getWorkflowRemark() {
        return workflowRemark;
    }

    public ModuleIn setWorkflowRemark(String workflowRemark) {
        this.workflowRemark = workflowRemark;
        return this;
    }

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(moduleTypeName)) {
            throw new ParameterException("moduleTypeName","","没有应用名称");
        }
    }

    @Override
    public ModuleType toEntity(Long dataId) throws ParameterException {
        checkSelf();
        ModuleType moduleType = new ModuleType();
        moduleType.setModuleTypeId(dataId)
                .setModuleTypeName(moduleTypeName)
                .setWorkflowRemark(workflowRemark);
        return moduleType;
    }

}
