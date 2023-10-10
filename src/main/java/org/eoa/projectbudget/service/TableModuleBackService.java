package org.eoa.projectbudget.service;

import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:30
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: TableModuleBackService
 * @Description: TODO
 * @Version 1.0
 */
public interface TableModuleBackService {

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
     * @param tableId
     * @return 指定id的表单对象
     */
    Table getTableById(Long tableId, Long userId);

    /**
     *
     * @param moduleId
     * @return 该应用模块下的所有表单
     */
    List<? extends Table> getTableFromModule(Long moduleId, Long userId);

    Integer addColumn(Column column, Long userId);

    Integer alterColumn(Column column,Long userId);

    Integer deleteColumn(Long columnId,Long userId);

    Column getColumnById(Long columnId,Long userId);

    List<? extends Column> getColumnByTable(Long tableId,Long userId);

}
