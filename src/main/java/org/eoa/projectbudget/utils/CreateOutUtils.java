package org.eoa.projectbudget.utils;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.ModuleView;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.vo.ModuleOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/10/21 21:02
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: CreateOutUtils
 * @Description: 创建out输出对象工具类
 * @Version 1.0
 **/

@Component
public class CreateOutUtils {

    @Autowired
    HumanMapper humanMapper;


    /**
     * 将应用模块视图实体转化成输出类
     * @param moduleView 待转化的实体
     * @return 输出类
     */
    public ModuleOut moduleOut(ModuleView moduleView) {
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

    public List<ModuleOut> moduleOuts(List<ModuleView> moduleViews) {
        return moduleViews.stream().map(this::moduleOut).collect(Collectors.toList());
    }
}
