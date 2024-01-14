package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.Charts;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 19:51
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: ChartsOut
 * @Description: TODO
 * @Version: 1.0
 */
public class ChartsOut implements VoOut{

    Long dataId;
    Long moduleTypeId;
    String chartName;
    String defaultCondition;
    Long tableId;
    String shareAuthority;
    String order;

    String rows;
    String config;

    Integer isVirtual;

    Long creator;
    Date createTime;

    String tableName;
    String creatorName;
    String moduleTypeName;


    public ChartsOut(Charts charts) {
        this.dataId = charts.getDataId();
        this.moduleTypeId = charts.getModuleTypeId();
        this.chartName = charts.getChartName();
        this.defaultCondition = charts.getDefaultCondition();
        this.tableId = charts.getTableId();
        this.shareAuthority = charts.getShareAuthority();
        this.order = charts.getOrder();
        this.rows = charts.getRows();
        this.config = charts.getConfig();
        this.isVirtual = charts.getIsVirtual();
        this.createTime = charts.getCreateTime();
        this.creator = charts.getCreator();
    }

    public ChartsOut() {
    }


    @Override
    public void toBrowser(Long browserId) {
        this.moduleTypeId = null;
        this.chartName = browserId != 0 ? null :chartName;
        this.defaultCondition = null;
        this.tableId = null;
        this.shareAuthority = null;
        this.order = null;
        this.rows = null;
        this.config = null;
        this.isVirtual = null;
        this.tableName = null;
        this.creatorName = null;
        this.moduleTypeName = null;
        this.createTime = null;
        this.creator = null;
    }

    public Long getDataId() {
        return dataId;
    }

    public ChartsOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public ChartsOut setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public ChartsOut setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public String getDefaultCondition() {
        return defaultCondition;
    }

    public ChartsOut setDefaultCondition(String defaultCondition) {
        this.defaultCondition = defaultCondition;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public ChartsOut setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public ChartsOut setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public ChartsOut setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getRows() {
        return rows;
    }

    public ChartsOut setRows(String rows) {
        this.rows = rows;
        return this;
    }

    public String getConfig() {
        return config;
    }

    public ChartsOut setConfig(String config) {
        this.config = config;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public ChartsOut setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public ChartsOut setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public ChartsOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getModuleTypeName() {
        return moduleTypeName;
    }

    public ChartsOut setModuleTypeName(String moduleTypeName) {
        this.moduleTypeName = moduleTypeName;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public ChartsOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ChartsOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
