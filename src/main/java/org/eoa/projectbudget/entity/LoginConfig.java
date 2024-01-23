package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 17:36
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: LoginConfig
 * @Description: 登录设置实体类
 * @Version: 1.0
 **/

@TableName("`login_config`")
public class LoginConfig {
    @TableId(type = IdType.AUTO)
    Long dataId;
    String backgroundUrl;
    String logoUrl;
    String backgroundVideoUrl;
    String loginTitle;
    String loginSubTitle;
    String activeMainTitle;
    String activeIntroduction;
    String contactManager;
    String linkUrl;
    String linkStr;
    Long creator;
    Date createTime;
    Integer onUse;

    public LoginConfig() {
    }

    public LoginConfig(Long dataId, String backgroundUrl, String logoUrl, String backgroundVideoUrl, String loginTitle, String loginSubTitle, String activeMainTitle, String activeIntroduction, String contactManager, String linkUrl, String linkStr, Integer onUse) {
        this.dataId = dataId;
        this.backgroundUrl = backgroundUrl;
        this.logoUrl = logoUrl;
        this.backgroundVideoUrl = backgroundVideoUrl;
        this.loginTitle = loginTitle;
        this.loginSubTitle = loginSubTitle;
        this.activeMainTitle = activeMainTitle;
        this.activeIntroduction = activeIntroduction;
        this.contactManager = contactManager;
        this.linkUrl = linkUrl;
        this.linkStr = linkStr;
        this.onUse = onUse;
    }

    public Long getDataId() {
        return dataId;
    }

    public LoginConfig setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public LoginConfig setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public LoginConfig setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getBackgroundVideoUrl() {
        return backgroundVideoUrl;
    }

    public LoginConfig setBackgroundVideoUrl(String backgroundVideoUrl) {
        this.backgroundVideoUrl = backgroundVideoUrl;
        return this;
    }

    public String getLoginTitle() {
        return loginTitle;
    }

    public LoginConfig setLoginTitle(String loginTitle) {
        this.loginTitle = loginTitle;
        return this;
    }

    public String getLoginSubTitle() {
        return loginSubTitle;
    }

    public LoginConfig setLoginSubTitle(String loginSubTitle) {
        this.loginSubTitle = loginSubTitle;
        return this;
    }

    public String getActiveMainTitle() {
        return activeMainTitle;
    }

    public LoginConfig setActiveMainTitle(String activeMainTitle) {
        this.activeMainTitle = activeMainTitle;
        return this;
    }

    public String getActiveIntroduction() {
        return activeIntroduction;
    }

    public LoginConfig setActiveIntroduction(String activeIntroduction) {
        this.activeIntroduction = activeIntroduction;
        return this;
    }

    public String getContactManager() {
        return contactManager;
    }

    public LoginConfig setContactManager(String contactManager) {
        this.contactManager = contactManager;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public LoginConfig setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public String getLinkStr() {
        return linkStr;
    }

    public LoginConfig setLinkStr(String linkStr) {
        this.linkStr = linkStr;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public LoginConfig setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LoginConfig setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getOnUse() {
        return onUse;
    }

    public LoginConfig setOnUse(Integer onUse) {
        this.onUse = onUse;
        return this;
    }

    @Override
    public String toString() {
        return "LoginConfig{" +
                "dataId=" + dataId +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", backgroundVideoUrl='" + backgroundVideoUrl + '\'' +
                ", loginTitle='" + loginTitle + '\'' +
                ", loginSubTitle='" + loginSubTitle + '\'' +
                ", activeMainTitle='" + activeMainTitle + '\'' +
                ", activeIntroduction='" + activeIntroduction + '\'' +
                ", contactManager='" + contactManager + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", linkStr='" + linkStr + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", onUse=" + onUse +
                '}';
    }
}
