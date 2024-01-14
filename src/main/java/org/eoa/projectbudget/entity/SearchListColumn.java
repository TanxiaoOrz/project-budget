package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 11:09
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: SearchListColumn
 * @Description: 展示表字段配置实体类
 * @Version: 1.0
 **/

@TableName("`search_list_column`")
public class SearchListColumn {

    @TableId(type = IdType.AUTO)
    Long dataId;
    Long columnId;
    Long searchListId;

    String title;
    Integer viewNo;
    Integer isVirtual;

    public Long getDataId() {
        return dataId;
    }

    public SearchListColumn setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getColumnId() {
        return columnId;
    }

    public SearchListColumn setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SearchListColumn setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public SearchListColumn setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public SearchListColumn setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }

    public Long getSearchListId() {
        return searchListId;
    }

    public SearchListColumn setSearchListId(Long searchListId) {
        this.searchListId = searchListId;
        return this;
    }
}
