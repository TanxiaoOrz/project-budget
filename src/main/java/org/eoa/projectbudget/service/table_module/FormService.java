package org.eoa.projectbudget.service.table_module;

import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
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
public interface FormService {

    int CREATE = 0;
    int EDIT = 1;
    int DELETE = 2;

    /**
     * 获取单个表单的单行数据
     * @param tableId 表单id
     * @param dataId 数据id
     * @param userId 用户id
     * @return form数据对象
     * @throws EoaException 过程处理异常
     */
    FormOutDto getFormOne(Long tableId, Long dataId, Long userId) throws EoaException;

    /**
     * 修改数据
     * @param form 传入表单数据
     * @param userId 操作用户
     * @return 更新数量
     * @throws EoaException 过程处理异常
     */
    Integer updateForm(FormInDto form, Long userId) throws EoaException;

    /**
     * 创建数据
     * @param form 传入数据表单
     * @param userId 操作用户
     * @return 主键id
     * @throws EoaException
     */
    Long createForm(FormInDto form, Long userId) throws EoaException;

    /**
     * 删除数据
     * @param tableId 表单id
     * @param dataId 数据id
     * @param userId 操作用户
     * @return 删除数量
     * @throws EoaException 过程处理异常
     */
    Integer deleteForm(Long tableId,Long dataId,Long userId) throws EoaException;

    /**
     * 获取符合要求的数据集
     * @param tableId
     * @param filter 数据过滤工具
     * @param userId 操作用户
     * @return 数据集
     * @throws EoaException 过程处理异常
     */
    List<FormOutDto> getFormSort(Long tableId, FilterFormUtils filter, Long userId) throws EoaException;

    /**
     * 验证操作符合权限范围
     * @param tableId 表单id
     * @param dataId 数据id
     * @param type 操作类型
     * @param user 操作用户
     * @return 是否允许操作
     * @throws EoaException 过程处理异常
     */
    Boolean checkAuthority(Long tableId, Long dataId, int type, HumanDto user) throws EoaException;
}
