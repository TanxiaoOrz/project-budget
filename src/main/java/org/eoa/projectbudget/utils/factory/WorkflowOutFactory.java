package org.eoa.projectbudget.utils.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.mapper.WorkflowNodeMapper;
import org.eoa.projectbudget.vo.out.WorkflowOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 19:55
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: WorkflowOutFactory
 * @Description: TODO
 * @Version: 1.0
 */

@Component
public class WorkflowOutFactory implements OutFactory<Workflow, WorkflowOut> {
    @Autowired
    ModuleTypeMapper moduleTypeMapper;
    @Autowired
    WorkflowNodeMapper nodeMapper;
    @Autowired
    HumanMapper humanMapper;
    @Autowired
    TableEntityMapper tableEntityMapper;

    @Override
    public WorkflowOut out(Workflow workflow) {
        if (workflow == null) {
            return null;
        }
        WorkflowOut out = new WorkflowOut(workflow);
        ModuleType moduleType = moduleTypeMapper.selectById(workflow.getModuleTypeId());
        WorkflowNode workflowNode = nodeMapper.selectOne(new QueryWrapper<WorkflowNode>()
                .eq("workflowId", workflow.getDataId())
                .eq("nodeType", WorkflowNode.CREATE));
        Table table = tableEntityMapper.selectById(workflow.getTableId());
        HumanResource humanResource = humanMapper.selectById(workflow.getCreator());
        return out.setModuleTypeName(moduleType==null?"": moduleType.getModuleTypeName())
                .setCreateNode(workflowNode==null?null:workflowNode.getDataId())
                .setCreateNodeName(workflowNode==null?"": workflowNode.getName())
                .setTableName(table==null?"":table.getTableViewName())
                .setCreatorName(humanResource==null?"":humanResource.getName());
    }

    @Override
    public List<WorkflowOut> outs(List<? extends Workflow> workflows) {
        if (workflows == null) {
            return null;
        }
        return workflows.stream().map(this::out).toList();
    }
}
