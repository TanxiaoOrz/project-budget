package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 11:08
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: SearchList
 * @Description: 展示表实体类
 * @Version: 1.0
 **/

@TableName("`search_list_base`")
public class SearchList {

    @TableId(type = IdType.AUTO)
    Long dataId;

    Long moduleTypeId;
    String searchListName;
    String defaultCondition;
    Long tableId;
    String shareAuthority;
    String orders;
    Integer isVirtual;

    Long creator;
    Date createTime;

    public Long getDataId() {
        return dataId;
    }

    public SearchList setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public SearchList setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public String getSearchListName() {
        return searchListName;
    }

    public SearchList setSearchListName(String searchListName) {
        this.searchListName = searchListName;
        return this;
    }

    public String getDefaultCondition() {
        return defaultCondition;
    }

    public SearchList setDefaultCondition(String defaultCondition) {
        this.defaultCondition = defaultCondition;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public SearchList setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public SearchList setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public String getOrders() {
        return orders;
    }

    public SearchList setOrders(String orders) {
        this.orders = orders;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public SearchList setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public SearchList setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SearchList setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
