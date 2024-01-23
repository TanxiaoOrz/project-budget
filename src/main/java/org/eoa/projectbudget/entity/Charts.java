package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/12 11:32
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Charts
 * @Description: 图标配置实体类
 * @Version: 1.0
 **/

@TableName("`charts_base`")
public class Charts {

    @TableId(type = IdType.AUTO)
    Long dataId;

    String chartName;

    Long moduleTypeId;

    String defaultCondition;
    Long tableId;
    String shareAuthority;
    String orders;

    Integer isVirtual;

    String rows;
    String config;

    Long creator;
    Date createTime;

    public Long getDataId() {
        return dataId;
    }

    public Charts setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public Charts setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public Charts setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public String getDefaultCondition() {
        return defaultCondition;
    }

    public Charts setDefaultCondition(String defaultCondition) {
        this.defaultCondition = defaultCondition;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public Charts setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public Charts setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public String getOrders() {
        return orders;
    }

    public Charts setOrders(String orders) {
        this.orders = orders;
        return this;
    }

    public String getRows() {
        return rows;
    }

    public Charts setRows(String rows) {
        this.rows = rows;
        return this;
    }

    public String getConfig() {
        return config;
    }

    public Charts setConfig(String config) {
        this.config = config;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public Charts setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Charts setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public Charts setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }

    @Override
    public String toString() {
        return "Charts{" +
                "dataId=" + dataId +
                ", chartName='" + chartName + '\'' +
                ", moduleTypeId=" + moduleTypeId +
                ", defaultCondition='" + defaultCondition + '\'' +
                ", tableId=" + tableId +
                ", shareAuthority='" + shareAuthority + '\'' +
                ", orders='" + orders + '\'' +
                ", isVirtual=" + isVirtual +
                ", rows='" + rows + '\'' +
                ", config='" + config + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                '}';
    }
}
