package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.SearchList;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 16:23
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: SearchListOut
 * @Description: 列表输出类
 * @Version: 1.0
 */
public class SearchListOut implements VoOut{
    Long dataId;

    Long moduleTypeId;
    String searchListName;
    String defaultCondition;
    Long tableId;
    String shareAuthority;
    String order;
    Integer isVirtual;

    Long creator;
    Date createTime;

    String tableName;
    String creatorName;
    String moduleTypeName;

    public SearchListOut() {
    }

    public SearchListOut(SearchList searchList) {
        this.dataId = searchList.getDataId();
        this.moduleTypeId = searchList.getModuleTypeId();
        this.searchListName = searchList.getSearchListName();
        this.defaultCondition = searchList.getDefaultCondition();
        this.tableId = searchList.getTableId();
        this.shareAuthority = searchList.getShareAuthority();
        this.order = searchList.getOrder();
        this.isVirtual = searchList.getIsVirtual();
        this.creator = searchList.getCreator();
        this.createTime = searchList.getCreateTime();
    }

    public Long getDataId() {
        return dataId;
    }

    public SearchListOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public SearchListOut setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public String getSearchListName() {
        return searchListName;
    }

    public SearchListOut setSearchListName(String searchListName) {
        this.searchListName = searchListName;
        return this;
    }

    public String getDefaultCondition() {
        return defaultCondition;
    }

    public SearchListOut setDefaultCondition(String defaultCondition) {
        this.defaultCondition = defaultCondition;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public SearchListOut setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public SearchListOut setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public SearchListOut setOrder(String order) {
        this.order = order;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public SearchListOut setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public SearchListOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SearchListOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public SearchListOut setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public SearchListOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getModuleTypeName() {
        return moduleTypeName;
    }

    public SearchListOut setModuleTypeName(String moduleTypeName) {
        this.moduleTypeName = moduleTypeName;
        return this;
    }

    @Override
    public void toBrowser(Long browserId) {
        this.moduleTypeId = null;
        this.searchListName = browserId != 0? null: searchListName;
        this.defaultCondition = null;
        this.tableId = null;
        this.shareAuthority = null;
        this.order = null;
        this.isVirtual = null;
        this.creator = null;
        this.createTime = null;
        this.tableName = null;
        this.creatorName = null;
        this.moduleTypeName = null;
    }

}
