package org.eoa.projectbudget.service;

import org.eoa.projectbudget.mapper.ModuleViewMapper;
import org.eoa.projectbudget.vo.ModuleOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleViewMapper mapper;


    @Override
    public List<ModuleOut> getAll(Long userId) {
        return mapper.selectList(null);
    }
}
