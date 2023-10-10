package org.eoa.projectbudget.service.table_module;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.TableJmsMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.service.TableModuleBackService;
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
 * @ClassName: TableModuleBackServiceImpl
 * @Description: 表单模块后端操作处理业务类
 * @Version 1.0
 */
@Service
public class TableModuleBackServiceImpl implements TableModuleBackService {

    public final String MAIN_TABLE = "main_table_";
    public final String DETAIL_LAG = "_dt_";

    @Autowired
    ModuleTypeMapper moduleTypeMapper;
    @Autowired
    TableEntityMapper tableMapper;
    @Autowired
    TableJmsMapper tableJmsMapper;

    private final Logger log = LoggerFactory.getLogger("TableModule");

    @Override
    @Transactional
    public Integer createTable(Table abstracts, Long userId) throws ParameterException {
        TableEntity table = (TableEntity) abstracts;

        log.info("用户:编号=>{}执行新建表单操作\ntable=>{}",userId,table);

        table.setCreator(userId).setCreateTime(new Date());

        if (moduleTypeMapper.selectById(table.getModuleNo())==null) {
            log.error("指定模块编号:{}对应模块不存在,回滚",table.getModuleNo());
            throw new ParameterException("moduleNo",table.getModuleNo().toString(),"该id对应的应用模块不存在");
        }
        int insert = tableMapper.insert(table);
        log.info("插入新的表单索引数据,主键编号:{}",table.getTableId());

        String mainTableName = MAIN_TABLE + table.getTableId();
        table.setTableDataName(mainTableName);
        tableMapper.updateById(table);
        log.info("创建主表:表名=>{}",mainTableName);

        tableJmsMapper.createTable(mainTableName,TableJmsMapper.MAIN);
        for (int i = 0; i < table.getDetailCount(); i++) {
            String detailTableName = mainTableName + DETAIL_LAG + i;
            tableJmsMapper.createTable(detailTableName,TableJmsMapper.DETAIL);
            log.info("创建明细表:表名=>{}",detailTableName);
        }

        return insert;
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
            String detailTableName = table.getTableDataName() + DETAIL_LAG + i;
            tableJmsMapper.createTable(detailTableName,TableJmsMapper.DETAIL);
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
    public List<? extends Table> getTableFromModule(Long moduleId, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作\nmoduleId=>{}",userId,moduleId);
        List<TableEntity> tables = tableMapper.selectList(new QueryWrapper<TableEntity>().eq("moduleId", moduleId));
        log.info("查询完成,结果数量=>{}",tables.size());
        return tables;
    }

    @Override
    public Integer addColumn(Column column, Long userId) {
        return null;
    }

    @Override
    public Integer alterColumn(Column column, Long userId) {
        return null;
    }

    @Override
    public Integer deleteColumn(Long columnId, Long userId) {
        return null;
    }

    @Override
    public Column getColumnById(Long columnId, Long userId) {
        return null;
    }

    @Override
    public List<Column> getColumnByTable(Long tableId, Long userId) {
        return null;
    }
}
