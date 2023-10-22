package org.eoa.projectbudget.service;

import org.eoa.projectbudget.mapper.ModuleViewMapper;
import org.eoa.projectbudget.entity.ModuleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleViewMapper mapper;


    @Override
    public List<ModuleView> getAll(Long userId) {
        return mapper.selectList(null);
    }
}
