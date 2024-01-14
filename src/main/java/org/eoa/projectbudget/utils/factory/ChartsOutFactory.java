package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Charts;
import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.mapper.TableViewMapper;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.vo.out.ChartsOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 19:59
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: ChartsOutFactory
 * @Description: TODO
 * @Version: 1.0
 */

@Component
public class ChartsOutFactory implements OutFactory<Charts, ChartsOut> {
    @Autowired
    TableEntityMapper entityMapper;
    @Autowired
    TableViewMapper viewMapper;

    @Autowired
    ModuleTypeMapper moduleTypeMapper;
    @Autowired
    HumanMapper humanMapper;

    @Override
    public ChartsOut out(Charts charts) {
        if (charts == null)
            return null;
        HumanResource humanResource = humanMapper.selectById(charts.getCreator());
        ModuleType moduleType = moduleTypeMapper.selectById(charts.getModuleTypeId());
        Table table;
        if (DataProcessUtils.translateIntegerToBoolean(charts.getIsVirtual()))
            table = viewMapper.selectById(charts.getTableId());
        else
            table = entityMapper.selectById(charts.getTableId());
        return new ChartsOut(charts)
                .setCreatorName(humanResource == null ? "" : humanResource.getName())
                .setTableName(table == null ? "" : table.getTableViewName())
                .setModuleTypeName(moduleType == null ? "" : moduleType.getModuleTypeName());
    }

    @Override
    public List<ChartsOut> outs(List<? extends Charts> charts) {
        if (charts == null) {
            return null;
        }
        else
            return charts.stream().map(this::out).toList();
    }
}
