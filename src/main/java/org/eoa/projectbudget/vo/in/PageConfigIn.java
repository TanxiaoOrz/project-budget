package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.PageConfig;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 23:02
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: PageConfigIn
 * @Description: 页面设置输入类
 * @Version: 1.0
 */
public class PageConfigIn implements CheckParameter<PageConfig> {

    String companyName;
    String headerColor;
    String sideColor;
    Integer onUse;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(companyName)) {
            throw new ParameterException("companyName","","缺少公司名称");
        }
        if (DataProcessUtils.isEmpty(headerColor)) {
            throw new ParameterException("headerColor","","头部导航栏颜色");
        }
        if (DataProcessUtils.isEmpty(sideColor)) {
            throw new ParameterException("sideColor","","侧边导航栏颜色");
        }
        if (onUse == null) {
            throw new ParameterException("onUse","","缺少是否使用");
        }
    }

    @Override
    public PageConfig toEntity(Long dataId) throws ParameterException {
        checkSelf();
        return new PageConfig(dataId, companyName, headerColor, sideColor, onUse);
    }

    public String getCompanyName() {
        return companyName;
    }

    public PageConfigIn setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getHeaderColor() {
        return headerColor;
    }

    public PageConfigIn setHeaderColor(String headerColor) {
        this.headerColor = headerColor;
        return this;
    }

    public String getSideColor() {
        return sideColor;
    }

    public PageConfigIn setSideColor(String sideColor) {
        this.sideColor = sideColor;
        return this;
    }

    public Integer getOnUse() {
        return onUse;
    }

    public PageConfigIn setOnUse(Integer onUse) {
        this.onUse = onUse;
        return this;
    }
}
