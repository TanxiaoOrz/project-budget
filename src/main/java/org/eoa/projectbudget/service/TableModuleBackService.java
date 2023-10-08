package org.eoa.projectbudget.service;

import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.vo.TableView;

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
    Integer createTable(Table table,Long userId);

    /**
     *
     * @param table 表单对象
     * @return 创建好的表单id
     */
    Integer updateTable(Table table);

    /**
     *
     * @param table 表单对象
     * @return 删除结果
     */
    Integer deleteTable(Table table);

    Table getOne(Long tableId);

    List<Table> getTableFromModule(Long moduleId);

}
