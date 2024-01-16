package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.PageConfig;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 23:02
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: PageConfigOut
 * @Description: 页面设置输出类
 * @Version: 1.0
 */
public class PageConfigOut implements VoOut{

    Long dataId;
    String companyName;
    String headerColor;
    String sideColor;
    Integer onUse;
    Long creator;
    Date createTime;

    String creatorName;

    public PageConfigOut() {
    }

    public PageConfigOut(PageConfig pageConfig) {
        this.dataId = pageConfig.getDataId();
        this.companyName = pageConfig.getCompanyName();
        this.headerColor = pageConfig.getHeaderColor();
        this.sideColor = pageConfig.getSideColor();
        this.onUse = pageConfig.getOnUse();
        this.creator = pageConfig.getCreator();
        this.createTime = pageConfig.getCreateTime();
    }

    @Override
    public void toBrowser(Long browserId) {
        this.companyName = browserId!=0L?null:companyName;
        this.headerColor = null;
        this.sideColor = null;
        this.onUse = null;
        this.creator = null;
        this.createTime = null;
        this.creatorName = null;
    }

    public Long getDataId() {
        return dataId;
    }

    public PageConfigOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public PageConfigOut setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getHeaderColor() {
        return headerColor;
    }

    public PageConfigOut setHeaderColor(String headerColor) {
        this.headerColor = headerColor;
        return this;
    }

    public String getSideColor() {
        return sideColor;
    }

    public PageConfigOut setSideColor(String sideColor) {
        this.sideColor = sideColor;
        return this;
    }

    public Integer getOnUse() {
        return onUse;
    }

    public PageConfigOut setOnUse(Integer onUse) {
        this.onUse = onUse;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public PageConfigOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PageConfigOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public PageConfigOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }
}
