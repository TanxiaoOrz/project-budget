package org.eoa.projectbudget.service;

import org.eoa.projectbudget.entity.ModuleView;

import java.util.List;

public interface ModuleService {

    List<ModuleView> getAll(Long userId);

}
