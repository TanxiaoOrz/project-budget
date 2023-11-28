package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.ModuleView;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.vo.out.ModuleOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/10/21 21:02
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: ModuleOutFactory
 * @Description: 创建out输出对象工具类
 * @Version 1.1
 **/

@Component
public class ModuleOutFactory implements OutFactory<ModuleView,ModuleOut> {

    @Autowired
    HumanMapper humanMapper;

    @Override
    public ModuleOut out(ModuleView moduleView) {
        if (moduleView == null) {
            return null;
        }
        return new ModuleOut(moduleView.getModuleTypeId(),
                moduleView.getModuleTypeName(),
                moduleView.getWorkflowRemark(),
                moduleView.getModuleTypeId(),
                humanMapper.selectById(moduleView.getCreator()).getName(),
                moduleView.getCreateTime(),
                moduleView.getSearchCounts(),
                moduleView.getFlowCounts(),
                moduleView.getTableCounts(),
                moduleView.getChartsCounts());
    }

    @Override
    public List<ModuleOut> outs(List<? extends ModuleView> moduleViews) {
        if (null == moduleViews)
            return null;
        return moduleViews.stream().map(this::out).collect(Collectors.toList());
    }
}
