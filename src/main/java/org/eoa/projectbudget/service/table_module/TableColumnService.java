package org.eoa.projectbudget.service.table_module;

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
 * @Version 1.0
 */
public interface TableColumnService {

    /**
     *
     * @param table 表单对象
     * @param userId 创建人id
     * @return 创建好的表单id
     */
    Integer createTable(Table table, Long userId) throws ParameterException;

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
     * @param moduleId 模块编号
     * @return 该应用模块下的所有表单
     */
    List<? extends Table> getTableFromModule(Long moduleId, Long userId);

    Integer addColumn(Column column, Long userId) throws ParameterException;

    Integer alterColumn(Column column,Long userId) throws ParameterException;

    Integer deleteColumn(Long columnId,Long userId) throws ParameterException;

    Column getColumnById(Long columnId,Long userId);

    List<? extends Column> getColumnByTable(Long tableId,Long userId);

}
