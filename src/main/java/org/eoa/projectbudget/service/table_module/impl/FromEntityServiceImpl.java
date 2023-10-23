package org.eoa.projectbudget.service.table_module.impl;

import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.Form;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.service.table_module.FormService;
import org.springframework.stereotype.Service;

/**
 * @Author 张骏山
 * @Date 2023/10/23 16:15
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: FromEntityServiceImpl
 * @Description: 表单模块自定义表单前端操作处理业务类
 * @Version 1.0
 */
@Service
public class FromEntityServiceImpl implements FormService<ColumnEntity, TableEntity> {

    @Override
    public Form<ColumnEntity, TableEntity> getFormOne(Long tableId, Long dataId, Long userId) {
        return null;
    }

    @Override
    public Integer updateForm(Form<ColumnEntity, TableEntity> form, Long userId) {
        return null;
    }

    @Override
    public Integer createForm(Form<ColumnEntity, TableEntity> form, Long userId) {
        return null;
    }

    @Override
    public Integer deleteForm(Long tableId, Long dataId, Long userId) {
        return null;
    }


}
