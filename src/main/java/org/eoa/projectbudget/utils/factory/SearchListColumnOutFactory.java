package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.entity.SearchListColumn;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.ColumnViewMapper;
import org.eoa.projectbudget.mapper.SearchListMapper;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.vo.out.SearchListColumnOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 17:37
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: SearchListColumnOutFactory
 * @Description: 列表字段输出工厂
 * @Version: 1.0
 */

@Component
public class SearchListColumnOutFactory implements OutFactory<SearchListColumn, SearchListColumnOut> {

    @Autowired
    SearchListMapper searchListMapper;
    @Autowired
    ColumnEntityMapper entityMapper;
    @Autowired
    ColumnViewMapper viewMapper;
    @Override
    public SearchListColumnOut out(SearchListColumn searchListColumn) {
        if (searchListColumn == null)
            return null;
        SearchList searchList = searchListMapper.selectById(searchListColumn.getSearchListId());
        Column column;
        if (DataProcessUtils.translateIntegerToBoolean(searchListColumn.getIsVirtual()))
            column = viewMapper.selectById(searchListColumn.getColumnId());
        else
            column = entityMapper.selectById(searchListColumn.getColumnId());
        return new SearchListColumnOut(searchListColumn)
                .setSearchListName(searchList == null ? "" : searchList.getSearchListName())
                .setColumnName(column == null ? "" : column.getColumnViewName());
    }

    @Override
    public List<SearchListColumnOut> outs(List<? extends SearchListColumn> searchListColumns) {
        if (searchListColumns == null) {
            return null;
        }
        return searchListColumns.stream().map(this::out).toList();
    }
}
