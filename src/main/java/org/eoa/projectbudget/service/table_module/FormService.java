package org.eoa.projectbudget.service.table_module;

import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Form;
import org.eoa.projectbudget.entity.Table;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:31
 * @PackageName: org.eoa.projectbudget.service
 * @ClassName: FormService
 * @Description: 表单模块后端操作业务声明接口
 * @Version 1.0
 */
public interface FormService<C extends Column,T extends Table> {

    Form<C,T> getFormOne(Long tableId,Long dataId,Long userId);

    Integer updateForm(Form<C,T> form,Long userId);

    Integer createForm(Form<C,T> form,Long userId);

    Integer deleteForm(Long tableId,Long dataId,Long userId);

}
