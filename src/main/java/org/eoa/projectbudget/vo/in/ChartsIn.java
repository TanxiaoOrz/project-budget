package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.Charts;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 19:39
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: ChartsIn
 * @Description: TODO
 * @Version: 1.0
 */
public class ChartsIn implements CheckParameter<Charts> {

    Long moduleTypeId;
    String chartName;
    String defaultCondition;
    Long tableId;
    String shareAuthority;
    String orders;

    String rows;
    String config;

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
        if (DataProcessUtils.isEmpty(chartName)) {
            throw new ParameterException("chartName","","缺少展示表单名称");
        }
    }

    @Override
    public Charts toEntity(Long dataId) throws ParameterException {
        return new Charts()
                .setDataId(dataId)
                .setChartName(chartName)
                .setDefaultCondition(defaultCondition)
                .setTableId(tableId)
                .setShareAuthority(shareAuthority)
                .setOrders(orders)
                .setRows(rows)
                .setConfig(config);
    }

    public Long getModuleTypeId() {
        return moduleTypeId;
    }

    public ChartsIn setModuleTypeId(Long moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
        return this;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public ChartsIn setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
        return this;
    }

    public String getChartName() {
        return chartName;
    }

    public ChartsIn setChartName(String chartName) {
        this.chartName = chartName;
        return this;
    }

    public String getDefaultCondition() {
        return defaultCondition;
    }

    public ChartsIn setDefaultCondition(String defaultCondition) {
        this.defaultCondition = defaultCondition;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public ChartsIn setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public ChartsIn setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public String getOrders() {
        return orders;
    }

    public ChartsIn setOrders(String orders) {
        this.orders = orders;
        return this;
    }

    public String getRows() {
        return rows;
    }

    public ChartsIn setRows(String rows) {
        this.rows = rows;
        return this;
    }

    public String getConfig() {
        return config;
    }

    public ChartsIn setConfig(String config) {
        this.config = config;
        return this;
    }
}
