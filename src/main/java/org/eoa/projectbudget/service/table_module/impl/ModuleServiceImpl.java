package org.eoa.projectbudget.service.table_module.impl;

import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.ModuleViewMapper;
import org.eoa.projectbudget.entity.ModuleView;
import org.eoa.projectbudget.service.table_module.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/11/15 15:24
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: ModuleServiceImpl
 * @Description: 表单模块应用业务类
 * @Version 1.0
 */

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleViewMapper viewMapper;

    @Autowired
    ModuleTypeMapper typeMapper;

    private final Logger log = LoggerFactory.getLogger("TableModule");


    @Override
    public List<ModuleView> getAll(Long userId) {
        List<ModuleView> moduleViews = viewMapper.selectList(null);
        log.info("用户=>{}查询模块列表",userId);
        return moduleViews;
    }

    @Override
    public ModuleView getOne(Long moduleTypeId, Long userId) throws ParameterException {
        ModuleView moduleView = viewMapper.selectById(moduleTypeId);
        if (moduleView == null) {
            throw new ParameterException("moduleTypeId",moduleTypeId.toString(),"不存在该模块");
        }
        log.info("用户=>{}查询模块id=>{},success",userId,moduleTypeId);
        return moduleView;
    }

    @Override
    public Integer update(ModuleType moduleType, Long userId) throws ParameterException {
        log.info("用户=>{}修改模块=>{}",userId,moduleType.toString());
        ModuleType old = typeMapper.selectById(moduleType.getModuleTypeId());
        if (old == null) {
            log.error("该数据不存在");
            throw new ParameterException("moduleTypeId",moduleType.getModuleTypeId().toString(),"不存在该模块");
        }
        moduleType.setCreator(old.getCreator()).setCreateTime(old.getCreateTime());
        int i = typeMapper.updateById(moduleType);
        log.info("更新完成");
        return i;
    }

    @Override
    public Long newOne(ModuleType moduleType, Long userId) {
        log.info("用户=>{}新建模块=>{}",userId,moduleType);
        moduleType.setCreateTime(new Date()).setCreator(userId);
        int i = typeMapper.insert(moduleType);
        log.info("新建完成,数量=>{}",i);
        return moduleType.getModuleTypeId();
    }

    @Override
    public Integer delete(Long moduleTypeId, Long userId) {
        log.info("用户=>{}删除模块=>{}",userId,moduleTypeId);
        int i = typeMapper.deleteById(moduleTypeId);
        log.info("删除完成,数量=>{}",moduleTypeId);
        return i;
    }
}
