package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.SearchListColumn;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 16:24
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: SearchListColumnOut
 * @Description: 列表字段输出类
 * @Version: 1.0
 */
public class SearchListColumnOut implements VoOut{

    Long dataId;
    Long columnId;
    Long searchListId;

    String title;
    Integer viewNo;
    Integer isVirtual;

    String columnName;
    String searchListName;

    public SearchListColumnOut() {

    }

    public SearchListColumnOut(SearchListColumn searchListColumn) {
        this.dataId = searchListColumn.getDataId();
        this.columnId = searchListColumn.getColumnId();
        this.searchListId = searchListColumn.getSearchListId();
        this.title = searchListColumn.getTitle();
        this.viewNo = searchListColumn.getViewNo();
        this.isVirtual = searchListColumn.getIsVirtual();
    }

    public Long getDataId() {
        return dataId;
    }

    public SearchListColumnOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getColumnId() {
        return columnId;
    }

    public SearchListColumnOut setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    public Long getSearchListId() {
        return searchListId;
    }

    public SearchListColumnOut setSearchListId(Long searchListId) {
        this.searchListId = searchListId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SearchListColumnOut setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public SearchListColumnOut setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public SearchListColumnOut setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public SearchListColumnOut setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getSearchListName() {
        return searchListName;
    }

    public SearchListColumnOut setSearchListName(String searchListName) {
        this.searchListName = searchListName;
        return this;
    }

    @Override
    public void toBrowser(Long browserId) {
        this.columnId = null;
        this.searchListId = null;
        this.title = browserId == 0L? null : title;
        this.viewNo = null;
        this.isVirtual = null;
        this.columnName = null;
        this.searchListName = null;
    }


}
