package org.eoa.projectbudget.service.table_module;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:30
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: TableColumnService
 * @Description: 表单字段操作业务类
 * @Version 1.2
 */
public interface TableColumnService<C extends Column,T extends Table> {

    /**
     *
     * @param table 表单对象
     * @param userId 创建人id
     * @return 创建好的表单id
     */
    Long createTable(Table table, Long userId) throws ParameterException;

    /**
     *
     * @param table 表单对象
     * @return 创建好的表单id
     */
    Integer updateTable(Table table, Long userId) throws ParameterException;

    /**
     *
     * @param tableId 表单对象id
     * @return 删除结果
     */
    Integer deleteTable(Long tableId, Long userId);

    /**
     *
     * @param tableId 表单编号
     * @return 指定id的表单对象
     */
    Table getTableById(Long tableId, Long userId);

    /**
     *
     * @param userId 用户编号
     * @param wrapper 筛选构造器
     * @return 该应用模块下的所有表单
     */
    List<T> getTableList(QueryWrapper<T> wrapper, Long userId);

    /**
     * 添加字段
     * @param column 字段对象
     * @param userId 用户编号
     * @return 创建主键值
     * @throws ParameterException column参数异常
     */
    Long addColumn(Column column, Long userId) throws ParameterException;

    /**
     * 编辑字段
     * @param column 字段对象
     * @param userId 用户编号
     * @return 操作数量
     * @throws ParameterException column参数异常
     */
    Integer alterColumn(Column column,Long userId) throws ParameterException;

    /**
     * 删除字段
     * @param columnId 字段编号
     * @param userId 用户编号
     * @return 操作数量
     * @throws ParameterException column参数异常
     */
    Integer deleteColumn(Long columnId,Long userId) throws ParameterException;

    /**
     * 获取Column对象
     * @param columnId column编号
     * @param userId 用户编号
     * @return column对象
     */
    Column getColumnById(Long columnId,Long userId);

    /**
     * 获取column数组
     * @param wrapper 筛选器
     * @param userId 用户编号
     * @return 对象数组
     */
    List<C> getColumnList(QueryWrapper<C> wrapper, Long userId);

}
