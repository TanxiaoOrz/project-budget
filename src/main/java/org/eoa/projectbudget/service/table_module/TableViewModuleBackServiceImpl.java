package org.eoa.projectbudget.service.table_module;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.TableMapper;
import org.eoa.projectbudget.mapper.TableMapperAdvance;
import org.eoa.projectbudget.service.TableModuleBackService;
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
 * @ClassName: TableViewModuleBackServiceImpl
 * @Description: 表单操作接口的视图实现
 * @Version 1.0
 */

@Service
public class TableViewModuleBackServiceImpl implements TableModuleBackService {

    @Autowired
    ModuleTypeMapper moduleTypeMapper;
    @Autowired
    TableMapper tableMapper;
    @Autowired
    TableMapperAdvance tableMapperAdvance;

    private final Logger log = LoggerFactory.getLogger("TableModule");

    @Override
    public Integer createTable(Table table, Long userId) throws ParameterException {
        log.info("用户:编号=>{}执行新建表单操作\ntable=>{}",userId,table);

        table.setCreator(userId).setCreateTime(new Date());

        if (moduleTypeMapper.selectById(table.getModuleNo())==null) {
            log.error("指定模块编号:{}对应模块不存在,回滚",table.getModuleNo());
            throw new ParameterException("moduleNo",table.getModuleNo().toString(),"该id对应的应用模块不存在");
        }
        int insert = tableMapper.insert(table);
        log.info("插入新的表单索引数据,主键编号:{}",table.getTableId());
        return insert;
    }

    @Override
    public Integer updateTable(Table table, Long userId) throws ParameterException {
        log.info("用户:编号=>{}执行更新表单操作\ntable=>{}",userId,table);

        Table old = tableMapper.selectById(table.getTableId());
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
    public Table getOne(Long tableId, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作\ntableId=>{}",userId,tableId);
        Table table = tableMapper.selectById(tableId);
        log.info("查询完成,结果数量=>{}",table==null?0:1);
        return table;
    }

    @Override
    public List<Table> getTableFromModule(Long moduleId, Long userId) {
        log.info("用户:编号=>{}执行查询表单操作\nmoduleId=>{}",userId,moduleId);
        List<Table> tables = tableMapper.selectList(new QueryWrapper<Table>().eq("moduleId", moduleId));
        log.info("查询完成,结果数量=>{}",tables.size());
        return tables;
    }
}
