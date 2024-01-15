package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.SearchListColumn;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 16:24
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: SearchListDtoOut
 * @Description: TODO
 * @Version: 1.0
 */
public class SearchListDtoOut implements VoOut{
    SearchListOut searchListOut;
    List<SearchListColumn> searchListColumns;
    List<ColumnOut> columns;

    public SearchListOut getSearchListOut() {
        return searchListOut;
    }

    public SearchListDtoOut setSearchListOut(SearchListOut searchListOut) {
        this.searchListOut = searchListOut;
        return this;
    }

    public List<SearchListColumn> getSearchListColumns() {
        return searchListColumns;
    }

    public SearchListDtoOut setSearchListColumns(List<SearchListColumn> searchListColumns) {
        this.searchListColumns = searchListColumns;
        return this;
    }

    public List<ColumnOut> getColumns() {
        return columns;
    }

    public SearchListDtoOut setColumns(List<ColumnOut> columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public void toBrowser(Long browserId) {
        searchListOut.toBrowser(browserId);
        searchListColumns = null;
        columns = null;
    }
}
