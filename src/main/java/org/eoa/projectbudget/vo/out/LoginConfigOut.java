package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.entity.LoginConfig;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/11 22:03
 * @PackageName: IntelliJ IDEA
 * @ClassName: LoginConfigOut
 * @Description: 登录页设置输出类
 * @Version: 1.0
 */
public class LoginConfigOut implements VoOut{
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
    String creatorName;

    public LoginConfigOut() {
    }

    public LoginConfigOut(LoginConfig loginConfig) {
        this.dataId = loginConfig.getDataId();
        this.backgroundUrl = loginConfig.getBackgroundUrl();
        this.logoUrl = loginConfig.getLogoUrl();
        this.backgroundVideoUrl = loginConfig.getBackgroundVideoUrl();
        this.loginTitle = loginConfig.getLoginTitle();
        this.loginSubTitle = loginConfig.getLoginSubTitle();
        this.activeMainTitle = loginConfig.getActiveMainTitle();
        this.activeIntroduction = loginConfig.getActiveIntroduction();
        this.contactManager = loginConfig.getContactManager();
        this.linkUrl = loginConfig.getLinkUrl();
        this.linkStr = loginConfig.getLinkStr();
        this.creator = loginConfig.getCreator();
        this.createTime = loginConfig.getCreateTime();
        this.onUse = loginConfig.getOnUse();
    }

    @Override
    public void toBrowser(Long browserId) {
        this.backgroundUrl = null;
        this.logoUrl = browserId!=0L?null:logoUrl;
        this.backgroundVideoUrl = null;
        this.loginTitle = null;
        this.loginSubTitle = null;
        this.activeMainTitle = null;
        this.contactManager = null;
        this.linkUrl = null;
        this.linkStr = null;
        this.creator = null;
        this.createTime = null;
        this.onUse = null;
        this.creatorName = null;
    }

    public Long getDataId() {
        return dataId;
    }

    public LoginConfigOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public LoginConfigOut setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public LoginConfigOut setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getBackgroundVideoUrl() {
        return backgroundVideoUrl;
    }

    public LoginConfigOut setBackgroundVideoUrl(String backgroundVideoUrl) {
        this.backgroundVideoUrl = backgroundVideoUrl;
        return this;
    }

    public String getLoginTitle() {
        return loginTitle;
    }

    public LoginConfigOut setLoginTitle(String loginTitle) {
        this.loginTitle = loginTitle;
        return this;
    }

    public String getLoginSubTitle() {
        return loginSubTitle;
    }

    public LoginConfigOut setLoginSubTitle(String loginSubTitle) {
        this.loginSubTitle = loginSubTitle;
        return this;
    }

    public String getActiveMainTitle() {
        return activeMainTitle;
    }

    public LoginConfigOut setActiveMainTitle(String activeMainTitle) {
        this.activeMainTitle = activeMainTitle;
        return this;
    }

    public String getActiveIntroduction() {
        return activeIntroduction;
    }

    public LoginConfigOut setActiveIntroduction(String activeIntroduction) {
        this.activeIntroduction = activeIntroduction;
        return this;
    }

    public String getContactManager() {
        return contactManager;
    }

    public LoginConfigOut setContactManager(String contactManager) {
        this.contactManager = contactManager;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public LoginConfigOut setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public String getLinkStr() {
        return linkStr;
    }

    public LoginConfigOut setLinkStr(String linkStr) {
        this.linkStr = linkStr;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public LoginConfigOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LoginConfigOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getOnUse() {
        return onUse;
    }

    public LoginConfigOut setOnUse(Integer onUse) {
        this.onUse = onUse;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public LoginConfigOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }
}
