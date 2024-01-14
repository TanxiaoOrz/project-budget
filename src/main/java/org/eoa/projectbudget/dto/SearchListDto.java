package org.eoa.projectbudget.dto;

import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.entity.SearchListColumn;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 16:02
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: SearchListDto
 * @Description: 展示模块DTO类
 * @Version: 1.0
 **/
public class SearchListDto {
    SearchList searchList;
    List<SearchListColumn> searchListColumns;
    List<? extends Column> columns;

    public List<SearchListColumn> getSearchListColumns() {
        return searchListColumns;
    }

    public SearchListDto setSearchListColumns(List<SearchListColumn> searchListColumns) {
        this.searchListColumns = searchListColumns;
        return this;
    }

    public List<? extends Column> getColumns() {
        return columns;
    }

    public SearchListDto setColumns(List<? extends Column> columns) {
        this.columns = columns;
        return this;
    }

    public SearchList getSearchList() {
        return searchList;
    }

    public SearchListDto setSearchList(SearchList searchList) {
        this.searchList = searchList;
        return this;
    }

    public SearchListDto() {
    }

    public SearchListDto(SearchList searchList, List<SearchListColumn> searchListColumns, List<? extends Column> columns) {
        this.searchList = searchList;
        this.searchListColumns = searchListColumns;
        this.columns = columns;
    }
}
