package org.eoa.projectbudget.service.table_module.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.ColumnView;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.entity.TableView;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ColumnViewMapper;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.TableViewMapper;
import org.eoa.projectbudget.service.table_module.TableColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/9 18:48
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: TableViewServiceImpl
 * @Description: 表单操作接口的视图实现
 * @Version 1.0
 */


@Service
public class TableViewServiceImpl implements TableColumnService<ColumnView,TableView> {

    @Autowired
    ModuleTypeMapper moduleTypeMapper;
    @Autowired
    TableViewMapper tableMapper;
    @Autowired
    ColumnViewMapper columnMapper;


    private final Logger log = LoggerFactory.getLogger("TableModule");

    @Override
    public Long createTable(Table abstracts, Long userId) throws ParameterException {
        TableView table = (TableView) abstracts;

        log.info("用户:编号=>{}执行新建表单操作\ntable=>{}",userId,table);

        table.setCreator(userId).setCreateTime(new Date());

        if (moduleTypeMapper.selectById(table.getModuleNo())==null) {
            log.error("指定模块编号:{}对应模块不存在,回滚",table.getModuleNo());
            throw new ParameterException("moduleNo",table.getModuleNo().toString(),"该id对应的应用模块不存在");
        }
        int insert = tableMapper.insert(table);
        log.info("插入新的表单索引数据,主键编号:{}",table.getTableId());
        return table.getTableId();
    }

    @Override
    public Integer updateTable(Table abstracts, Long userId) throws ParameterException {
        TableView table = (TableView) abstracts;

        log.info("用户:编号=>{}执行更新表单操作\ntable=>{}",userId,table);

        TableView old = tableMapper.selectById(table.getTableId());
        if (old == null) {
            log.error("该数据不存在tableId=>{}",table.getTableId());
            throw new ParameterException("tableId",table.getTableId().toString(),"该数据编号的表单不存在或未进行修改");
        }

        table.setCreateTime(old.getCreateTime())
                .setCreator(old.getCreator());
        int update = tableMapper.updateById(table);
        log.info("更新完成,新的数据=>{}",table);

        return update;
    }

    @Override
    public Integer deleteTable(Long tableId, Long userId) {
        log.info("用户:编号=>{}执行更新表单操作\ntableId=>{}",userId,tableId);
        int delete = tableMapper.deleteById(tableId);
        log.info("删除完成,删除数量=>{}",delete);
        return delete;
    }

    @Override
    public Table getTableById(Long tableId, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作\ntableId=>{}",userId,tableId);
        TableView table = tableMapper.selectById(tableId);
        log.info("查询完成,结果数量=>{}",table==null?0:1);
        return table;
    }

    @Override
    public List<TableView> getTableList(QueryWrapper<TableView> wrapper, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作",userId);
        List<TableView> tables = tableMapper.selectList(wrapper);
        log.info("查询完成,结果数量=>{}",tables.size());
        return tables;
    }

    @Override
    public Long addColumn(Column base, Long userId) throws ParameterException {
        ColumnView column = (ColumnView) base;
        column.setCreateTime(new Date());
        column.setCreator(userId);
        log.info("用户:编号=>{}执行新建视图字段操作\ncolumn=>{}",userId,column);
        if (tableMapper.selectById(column.getTableNo()) == null) {
            log.error("不存在表单编号=>{}的表单，操作中断",column.getTableNo());
            throw new ParameterException("tableNo",column.getTableNo().toString(),"不存在该表单编号");
        }
        if (column.getColumnViewNo() == null) {
            Integer columnViewNo = columnMapper.getColumnViewNoNew(column.getTableNo());
            log.info("该字段的显示顺序编号=>{}",columnViewNo);
            column.setColumnViewNo(columnViewNo);
        }
        Integer insert = columnMapper.insert(column);
        log.info("插入完成,插入后数据=>{}",column);
        return column.getColumnId();
    }

    @Override
    public Integer alterColumn(Column base, Long userId) throws ParameterException {
        ColumnView column = (ColumnView) base;
        log.info("用户:编号=>{}执行修改视图字段操作\ncolumn=>{}",userId,column);
        ColumnView old = columnMapper.selectById(column.getColumnId());
        if (old == null) {
            log.error("不存在字段编号=>{}的表单，操作中断",column.getColumnId());
            throw new ParameterException("columnId",column.getColumnId().toString(),"不存在该字段编号");
        }
        if (!old.getTableNo().equals(column.getTableNo())) {
            log.error("不允许修改字段所属的表单,原表单编号=>{},新表单编号=>{}",old.getTableNo(),column.getTableNo());
            throw new ParameterException("tableNo",column.getTableNo().toString(),"不允许修改所属表单");
        }
        int update = columnMapper.updateById(column);
        log.info("修改完成,修改数量=>{}",update);
        return update;
    }

    @Override
    public Integer deleteColumn(Long columnId, Long userId) throws ParameterException {
        log.info("用户:编号=>{}执行删除视图字段操作\ncolumnId=>{}",userId,columnId);
        ColumnView old = columnMapper.selectById(columnId);
        if (old == null) {
            log.error("不存在字段编号=>{}的表单，操作中断",columnId);
            throw new ParameterException("columnId",columnId.toString(),"不存在该字段编号");
        }
        int delete = columnMapper.deleteById(columnId);
        log.info("删除完成,删除数量=>{}",delete);
        return delete;
    }

    @Override
    public Column getColumnById(Long columnId, Long userId) {
        log.info("用户:编号=>{}执行查询视图字段操作\ncolumnId=>{}",userId,columnId);
        Column column = columnMapper.selectById(columnId);
        log.info("查询完成,结果数量=>{}",column==null?0:1);
        return column;
    }

    @Override
    public List<ColumnView> getColumnList(QueryWrapper<ColumnView> wrapper, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作",userId);
        List<ColumnView> columns = columnMapper.selectList(wrapper);
        log.info("查询完成,结果数量=>{}",columns.size());
        return columns;
    }
}
