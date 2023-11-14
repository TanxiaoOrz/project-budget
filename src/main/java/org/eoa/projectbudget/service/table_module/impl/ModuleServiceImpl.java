package org.eoa.projectbudget.service.table_module.impl;

import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.mapper.ModuleViewMapper;
import org.eoa.projectbudget.entity.ModuleView;
import org.eoa.projectbudget.service.table_module.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleViewMapper mapper;

    private final Logger log = LoggerFactory.getLogger("TableModule");


    @Override
    public List<ModuleView> getAll(Long userId) {
        List<ModuleView> moduleViews = mapper.selectList(null);
        log.info("用户=>{}查询模块列表",userId);
        return moduleViews;
    }

    @Override
    public ModuleView getOne(Long moduleTypeId, Long userId) {
        return null;
    }

    @Override
    public Integer update(ModuleType moduleType, Long userId) {
        return null;
    }

    @Override
    public Integer newOne(ModuleType moduleType, Long userId) {
        return null;
    }

    @Override
    public Integer delete(ModuleType moduleType, Long userId) {
        return null;
    }
}
