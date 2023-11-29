package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.vo.out.TableOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/11/19 0:02
 * @PackageName: IntelliJ IDEA
 * @ClassName: TableOutFactory
 * @Description: Table相关输出类的构造工厂
 * @Version: 1.0
 */

@Component
public class TableOutFactory implements OutFactory<Table, TableOut> {
    @Autowired
    HumanMapper humanMapper;
    @Autowired
    ModuleTypeMapper moduleTypeMapper;

    @Override
    public TableOut out(Table table){
        if (table == null) {
            return null;
        }
        return new TableOut(table)
                .setCreateName(humanMapper.selectById(table.getCreator())
                        .getName())
                .setModuleName(moduleTypeMapper.selectById(table.getModuleNo())
                        .getModuleTypeName());

    }

    @Override
    public List<TableOut> outs(List<? extends Table> tables) {
        if (tables == null) {
            return null;
        }
        return tables.stream().map(this::out).collect(Collectors.toList());
    }
}
