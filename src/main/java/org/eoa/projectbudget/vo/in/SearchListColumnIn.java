package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.SearchListColumn;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 16:22
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: SearchListColumnIn
 * @Description: 列表字段输入类
 * @Version: 1.0
 */
public class SearchListColumnIn implements CheckParameter<SearchListColumn>{
        Long columnId;
        Long searchListId;
        String title;
        Integer viewNo;
        Integer isVirtual;

    @Override
    public void checkSelf() throws ParameterException {
        if (columnId == null) {
            throw new ParameterException("columnId", "", "缺少对应字段编号");
        }
        if (searchListId == null) {
            throw new ParameterException("searchListId", "", "缺少搜索咧白哦编号");
        }
        if (isVirtual == null) {
            throw new ParameterException("isVirtual", "", "缺少字段是否虚拟");
        }
        if (DataProcessUtils.isEmpty(title)) {
            throw new ParameterException("title","","缺少字段列表标题");
        }
    }

    @Override
    public SearchListColumn toEntity(Long dataId) throws ParameterException {
        return new SearchListColumn()
                .setDataId(dataId)
                .setSearchListId(searchListId)
                .setColumnId(columnId)
                .setViewNo(viewNo)
                .setTitle(title)
                .setIsVirtual(isVirtual);
    }

    public Long getColumnId() {
        return columnId;
    }

    public SearchListColumnIn setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    public Long getSearchListId() {
        return searchListId;
    }

    public SearchListColumnIn setSearchListId(Long searchListId) {
        this.searchListId = searchListId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SearchListColumnIn setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public SearchListColumnIn setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public SearchListColumnIn setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }
}
