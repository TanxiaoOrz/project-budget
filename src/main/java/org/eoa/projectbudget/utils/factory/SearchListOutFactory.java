package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.ModuleType;
import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.ModuleTypeMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.mapper.TableViewMapper;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.vo.out.SearchListOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 17:15
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: SearchListOutFactory
 * @Description: 列表输出工厂
 * @Version: 1.0
 */

@Component
public class SearchListOutFactory implements OutFactory<SearchList, SearchListOut> {
    @Autowired
    TableEntityMapper entityMapper;
    @Autowired
    TableViewMapper viewMapper;

    @Autowired
    ModuleTypeMapper moduleTypeMapper;
    @Autowired
    HumanMapper humanMapper;

    @Override
    public SearchListOut out(SearchList searchList) {
        if (searchList == null) {
            return null;
        }
        HumanResource humanResource = humanMapper.selectById(searchList.getCreator());
        ModuleType moduleType = moduleTypeMapper.selectById(searchList.getModuleTypeId());
        Table table;
        if (DataProcessUtils.translateIntegerToBoolean(searchList.getIsVirtual()))
            table = viewMapper.selectById(searchList.getTableId());
        else
            table = entityMapper.selectById(searchList.getTableId());
        return new SearchListOut(searchList)
                .setCreatorName(humanResource == null ? "" : humanResource.getName())
                .setTableName(table == null ? "" : table.getTableViewName())
                .setModuleTypeName(moduleType == null ? "" : moduleType.getModuleTypeName());
    }

    @Override
    public List<SearchListOut> outs(List<? extends SearchList> searchLists) {
        if (searchLists == null) {
            return null;
        }
        else
            return searchLists.stream().map(this::out).toList();
    }
}
