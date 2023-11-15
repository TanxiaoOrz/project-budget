package org.eoa.projectbudget.service.table_module;

import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.entity.ModuleView;
import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;

public interface ModuleService {

    List<ModuleView> getAll(Long userId);

    ModuleView getOne(Long moduleTypeId,Long userId) throws ParameterException;

    Integer update(ModuleType moduleType,Long userId) throws ParameterException;

    Integer newOne(ModuleType moduleType,Long userId);

    Integer delete(Long moduleTypeId,Long userId);

}
