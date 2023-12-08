package org.eoa.projectbudget.service.table_module;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.FilterFormUtils;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:31
 * @PackageName: org.eoa.projectbudget.service
 * @ClassName: FormService
 * @Description: 表单模块后端操作业务声明接口
 * @Version 1.1
 */
public interface FormService<C extends Column,T extends Table> {

    /**
     * 获取单个表单的单行数据
     * @param tableId 表单id
     * @param dataId 数据id
     * @param userId 用户id
     * @return form数据对象
     * @throws EoaException 过程处理异常
     */
    FormOutDto<C,T> getFormOne(Long tableId, Long dataId, Long userId) throws EoaException;

    /**
     * 修改数据
     * @param formOutDto
     * @param userId
     * @return
     * @throws EoaException
     */
    Integer updateForm(FormOutDto<C,T> formOutDto, Long userId) throws EoaException;

    Integer createForm(FormOutDto<C,T> formOutDto, Long userId) throws EoaException;

    Integer deleteForm(Long tableId,Long dataId,Long userId) throws EoaException;

    List<FormOutDto<C,T>> getFormSort(Long tableId, FilterFormUtils filter, Long userId) throws EoaException;

}
