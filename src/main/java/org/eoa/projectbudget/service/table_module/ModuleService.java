package org.eoa.projectbudget.service.table_module;

import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.entity.ModuleView;

import java.util.List;

public interface ModuleService {

    List<ModuleView> getAll(Long userId);

    ModuleView getOne(Long moduleTypeId,Long userId);

    Integer update(ModuleType moduleType,Long userId);

    Integer newOne(ModuleType moduleType,Long userId);

    Integer delete(ModuleType moduleType,Long userId);

}
