package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 17:42
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: PageConfig
 * @Description: 页面配置实体类
 * @Version: 1.0
 **/
@TableName("`page_config`")
public class PageConfig {
    @TableId(type = IdType.AUTO)
    Long dataId;
    String companyName;
    String headerColor;
    String sideColor;
    Integer onUse;
    Long creator;
    Date createTime;

    public PageConfig() {
    }

    public PageConfig(Long dataId, String companyName, String headerColor, String sideColor, Integer onUse) {
        this.dataId = dataId;
        this.companyName = companyName;
        this.headerColor = headerColor;
        this.sideColor = sideColor;
        this.onUse = onUse;
    }

    public Long getDataId() {
        return dataId;
    }

    public PageConfig setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public PageConfig setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getHeaderColor() {
        return headerColor;
    }

    public PageConfig setHeaderColor(String headerColor) {
        this.headerColor = headerColor;
        return this;
    }

    public String getSideColor() {
        return sideColor;
    }

    public PageConfig setSideColor(String sideColor) {
        this.sideColor = sideColor;
        return this;
    }

    public Integer getOnUse() {
        return onUse;
    }

    public PageConfig setOnUse(Integer onUse) {
        this.onUse = onUse;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public PageConfig setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PageConfig setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
