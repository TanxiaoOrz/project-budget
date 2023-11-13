package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.vo.in.ModuleIn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/11/13 17:03
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: ModuleFactory
 * @Description: Module的工厂构造类
 * @Version: 1.0
 **/

@Component
public class ModuleFactory implements VoFactory<ModuleIn, ModuleType> {
    @Override
    public ModuleType translate(ModuleIn moduleIn, int type){
        ModuleType moduleType = new ModuleType();
        switch (type) {
            case VoFactory.UPDATE :
                moduleType.setModuleTypeId(moduleType.getModuleTypeId());
            case VoFactory.NEW :
                moduleType.setModuleTypeName(moduleIn.getModuleTypeName())
                    .setWorkflowRemark(moduleIn.getWorkflowRemark());
        }
        return moduleType;
    }

    @Override
    public List<ModuleType> translate(List<ModuleIn> moduleIns, int type){
        return moduleIns.stream().map(moduleIn ->
            translate(moduleIn, type)).collect(Collectors.toList());
    }
}
