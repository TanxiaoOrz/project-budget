package org.eoa.projectbudget.service.table_module.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.FormDDLMapper;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.service.table_module.TableColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:24
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: TableEntityServiceImpl
 * @Description: 表单模块后端操作处理业务类,实体表部分
 * @Version 1.0
 */
@Service
public class TableEntityServiceImpl implements TableColumnService<ColumnEntity,TableEntity> {

    public final String MAIN_TABLE = "form_table_";
    public final String DETAIL_LAG = "_dt_";

    @Autowired
    ModuleTypeMapper moduleTypeMapper;
    @Autowired
    TableEntityMapper tableMapper;
    @Autowired
    FormDDLMapper formDDLMapper;
    @Autowired
    ColumnEntityMapper columnMapper;

    private final Logger log = LoggerFactory.getLogger("TableModule");

    @Override
    @Transactional
    public Long createTable(Table abstracts, Long userId) throws ParameterException {
        TableEntity table = (TableEntity) abstracts;

        log.info("用户:编号=>{}执行新建表单操作\ntable=>{}",userId,table);

        table.setCreator(userId).setCreateTime(new Date());

        if (moduleTypeMapper.selectById(table.getModuleNo())==null) {
            log.error("指定模块编号:{}对应模块不存在,回滚",table.getModuleNo());
            throw new ParameterException("moduleNo",table.getModuleNo().toString(),"该id对应的应用模块不存在");
        }

        table.setTableDataName("");

        int insert = tableMapper.insert(table);
        log.info("插入新的表单索引数据,主键编号:{}",table.getTableId());

        String mainTableName = MAIN_TABLE + table.getTableId();
        table.setTableDataName(mainTableName);

        tableMapper.updateById(table);

        tableMapper.updateById(table);
        log.info("创建主表:表名=>{}",mainTableName);

        formDDLMapper.createTable(mainTableName, FormDDLMapper.MAIN);
        for (int i = 0; i < table.getDetailCount(); i++) {
            int detailNo = i + 1;
            String detailTableName = mainTableName + DETAIL_LAG + detailNo;
            formDDLMapper.createTable(detailTableName, FormDDLMapper.DETAIL);
            log.info("创建明细表:表名=>{}",detailTableName);
        }

        return table.getTableId();
    }

    @Override
    @Transactional
    public Integer updateTable(Table abstracts, Long userId) throws ParameterException {
        TableEntity table = (TableEntity) abstracts;

        log.info("用户:编号=>{}执行更新表单操作\ntable=>{}",userId,table);

        TableEntity old = tableMapper.selectById(table.getTableId());
        if (old == null) {
            log.error("该数据不存在tableId=>{}",table.getTableId());
            throw new ParameterException("tableId",table.getTableId().toString(),"该数据编号的表单不存在或未进行修改");
        }
        log.info("历史数据=>{}",table);
        if (!old.getTableDataName().equals(table.getTableDataName())) {
            log.error("数据库存储名不一致,保存名字=>{},要修改的名字=>{}",old.getTableDataName(),table.getTableDataName());
            throw new ParameterException("TableDataName",table.getTableDataName(),"不允许修改存储数据表名");
        }
        table.setCreateTime(old.getCreateTime())
                .setCreator(old.getCreator());
        int update = tableMapper.updateById(table);
        log.info("更新完成,新的数据=>{}",table);

        for (int i = old.getDetailCount(); i < table.getDetailCount(); i++) {
            int detailNo = i + 1;
            String detailTableName = table.getTableDataName() + DETAIL_LAG + detailNo;
            formDDLMapper.createTable(detailTableName, FormDDLMapper.DETAIL);
            log.info("创建新的明细表,表名=>{}",detailTableName);
        }

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
        TableEntity table = tableMapper.selectById(tableId);
        log.info("查询完成,结果数量=>{}",table==null?0:1);
        return table;
    }

    @Override
    public List<TableEntity> getTableList(QueryWrapper<TableEntity> wrapper, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作",userId);
        List<TableEntity> tables = tableMapper.selectList(wrapper);
        log.info("查询完成,结果数量=>{}",tables.size());
        return tables;
    }

    @Override
    @Transactional
    public Long addColumn(Column base, Long userId) throws ParameterException {
        ColumnEntity column = (ColumnEntity) base;
        column.setCreateTime(new Date());
        column.setCreator(userId);
        log.info("用户:编号=>{}执行新建表单字段操作\ncolumn=>{}",userId,column);
        TableEntity tableEntity = tableMapper.selectById(column.getTableNo());
        if (tableEntity == null) {
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

        String dateType = column.createDateType();
        String tableName;
        if (column.getColumnDetailNo()==null||column.getColumnDetailNo()==-1)
            tableName = tableEntity.getTableDataName();
        else
            tableName = tableEntity.getTableDataName()+DETAIL_LAG+column.getColumnDetailNo();

        formDDLMapper.createColumn(tableName,column.getColumnDataName(), dateType);

        log.info("创建字段名=>{},字段类型=>{},在表单=>{}",column.getColumnDataName(),dateType,tableName);
        return column.getColumnId();
    }

    @Override
    public Integer alterColumn(Column base, Long userId) throws ParameterException {
        ColumnEntity column = (ColumnEntity) base;
        log.info("用户:编号=>{}执行修改表单字段操作\ncolumn=>{}",userId,column);
        ColumnEntity old = columnMapper.selectById(column.getColumnId());
        if (old == null) {
            log.error("不存在字段编号=>{}的表单，操作中断",column.getColumnId());
            throw new ParameterException("columnId",column.getColumnId().toString(),"不存在该字段编号");
        }
        if (!old.getColumnType().equals(column.getColumnType())) {
            log.error("不允许修改字段类型,原字段类型=>{},新字段类型=>{}",old.getColumnType(),column.getColumnType());
            throw new ParameterException("tableNo",column.getTableNo().toString(),"不允许修改字段类型");
        }
        if (!old.getTableNo().equals(column.getTableNo())) {
            log.error("不允许修改字段所属的表单,原表单编号=>{},新表单编号=>{}",old.getTableNo(),column.getTableNo());
            throw new ParameterException("tableNo",column.getTableNo().toString(),"不允许修改所属表单");
        }
        column.setCreator(old.getCreator())
                .setCreateTime(old.getCreateTime());
        int update = columnMapper.updateById(column);
        log.info("修改完成,修改数量=>{}",update);
        return update;
    }

    @Override
    public Integer deleteColumn(Long columnId, Long userId) throws ParameterException {
        log.info("用户:编号=>{}执行删除表单字段操作\ncolumnId=>{}",userId,columnId);
        ColumnEntity old = columnMapper.selectById(columnId);
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
    public List<ColumnEntity> getColumnList(QueryWrapper<ColumnEntity> wrapper, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作",userId);
        List<ColumnEntity> columns = columnMapper.selectList(wrapper);
        log.info("查询完成,结果数量=>{}",columns.size());
        return columns;
    }
}
