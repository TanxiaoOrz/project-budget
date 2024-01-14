package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.SearchList;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 16:21
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: SearchListIn
 * @Description: 列表输入类
 * @Version: 1.0
 */
public class SearchListIn implements CheckParameter<SearchList> {
    Long moduleTypeId;
    String searchListName;
    String defaultCondition;
    Long tableId;
    String shareAuthority;
    String order;
    Integer isVirtual;

    @Override
    public void checkSelf() throws ParameterException {
        if (moduleTypeId == null) {
            throw new ParameterException("moduleTypeId", "", "缺少所属模块编号");
        }
        if (tableId == null) {
            throw new ParameterException("tableId", "", "缺少展示表单编号");
        }
        if (isVirtual == null) {
            throw new ParameterException("isVirtual", "", "缺少表单是否虚拟");
        }
        if (DataProcessUtils.isEmpty(searchListName)) {
            throw new ParameterException("searchListName","","缺少展示表单名称");
        }
    }

    @Override
    public SearchList toEntity(Long dataId) throws ParameterException {
        SearchList searchList = new SearchList();
        return searchList.setDataId(dataId)
                .setModuleTypeId(moduleTypeId)
                .setDefaultCondition(defaultCondition)
                .setTableId(tableId)
                .setShareAuthority(shareAuthority)
                .setOrder(order)
                .setIsVirtual(isVirtual);
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public SearchListIn setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public String getSearchListName() {
        return searchListName;
    }

    public SearchListIn setSearchListName(String searchListName) {
        this.searchListName = searchListName;
        return this;
    }

    public String getDefaultCondition() {
        return defaultCondition;
    }

    public SearchListIn setDefaultCondition(String defaultCondition) {
        this.defaultCondition = defaultCondition;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public SearchListIn setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public SearchListIn setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public SearchListIn setOrder(String order) {
        this.order = order;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public SearchListIn setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }
}
