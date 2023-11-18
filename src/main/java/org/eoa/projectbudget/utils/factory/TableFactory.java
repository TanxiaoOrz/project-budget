package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.vo.out.TableOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/11/19 0:02
 * @PackageName: IntelliJ IDEA
 * @ClassName: TableFactory
 * @Description: Table相关输出类的构造工厂
 * @Version: 1.0
 */

@Component
public class TableFactory implements OutFactory<Table, TableOut> {
    @Autowired
    HumanMapper humanMapper;

    @Override
    public TableOut out(Table table){
        return new TableOut(table)
                .setCreateName(humanMapper.selectById(table.getCreator())
                        .getName());

    }

    @Override
    public List<TableOut> outs(List<? extends Table> tables) {
        return tables.stream().map(this::out).collect(Collectors.toList());
    }
}
