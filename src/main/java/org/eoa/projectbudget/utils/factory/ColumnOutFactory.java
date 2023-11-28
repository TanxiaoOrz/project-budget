package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.vo.out.ColumnOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/11/23 20:16
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: ColumnOutFactory
 * @Description: TODO
 * @Version: 1.0
 */

@Component
public class ColumnOutFactory implements OutFactory<Column, ColumnOut> {
    @Autowired
    HumanMapper humanMapper;
    @Override
    public ColumnOut out(Column column) {
        if (column == null) {
            return null;
        }
        return new ColumnOut(column)
                .setCreatorName(humanMapper.selectById(column.getCreator()).getName());
    }

    @Override
    public List<ColumnOut> outs(List<? extends Column> columns) {
        if (columns == null) {
            return null;
        }
        return columns.stream().map(this::out).collect(Collectors.toList());
    }
}
