package org.eoa.projectbudget.service;

import org.eoa.projectbudget.vo.ModuleOut;

import java.util.List;

public interface ModuleService {

    List<ModuleOut> getAll(Long userId);

}
