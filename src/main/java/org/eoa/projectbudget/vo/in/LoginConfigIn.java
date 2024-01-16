package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.LoginConfig;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 23:03
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: LoginConfigIn
 * @Description: 登录设置输入类
 * @Version: 1.0
 */
public class LoginConfigIn implements CheckParameter<LoginConfig>{

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
    Integer onUse;
    @Override
    public void checkSelf() throws ParameterException {

        if (DataProcessUtils.isEmpty(loginSubTitle)) {
            throw new ParameterException("loginSubTitle","","缺少登录副标题");
        }
        if (DataProcessUtils.isEmpty(loginTitle)) {
            throw new ParameterException("loginTitle","","缺少登录主标题");
        }
        if (DataProcessUtils.isEmpty(activeMainTitle)) {
            throw new ParameterException("activeMainTitle","","缺少活动主标题");
        }
        if (DataProcessUtils.isEmpty(activeIntroduction)) {
            throw new ParameterException("activeIntroduction","","缺少活动介绍");
        }
        if (DataProcessUtils.isEmpty(contactManager)) {
            throw new ParameterException("contactManager","","缺少管理员联系方式");
        }
        if (DataProcessUtils.isEmpty(linkUrl)) {
            throw new ParameterException("linkUrl","","缺少活动跳转地址");
        }
        if (DataProcessUtils.isEmpty(linkStr)) {
            throw new ParameterException("linkStr","","缺少活动跳转文本");
        }
        if (onUse == null) {
            throw new ParameterException("onUse","","缺少是否使用");
        }
    }

    @Override
    public LoginConfig toEntity(Long dataId) throws ParameterException {
        checkSelf();
        return new LoginConfig(dataId,backgroundUrl,logoUrl,backgroundVideoUrl,loginTitle, loginSubTitle, activeMainTitle, activeIntroduction,contactManager,linkUrl, linkStr, onUse);
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public LoginConfigIn setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public LoginConfigIn setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getBackgroundVideoUrl() {
        return backgroundVideoUrl;
    }

    public LoginConfigIn setBackgroundVideoUrl(String backgroundVideoUrl) {
        this.backgroundVideoUrl = backgroundVideoUrl;
        return this;
    }

    public String getLoginTitle() {
        return loginTitle;
    }

    public LoginConfigIn setLoginTitle(String loginTitle) {
        this.loginTitle = loginTitle;
        return this;
    }

    public String getLoginSubTitle() {
        return loginSubTitle;
    }

    public LoginConfigIn setLoginSubTitle(String loginSubTitle) {
        this.loginSubTitle = loginSubTitle;
        return this;
    }

    public String getActiveMainTitle() {
        return activeMainTitle;
    }

    public LoginConfigIn setActiveMainTitle(String activeMainTitle) {
        this.activeMainTitle = activeMainTitle;
        return this;
    }

    public String getActiveIntroduction() {
        return activeIntroduction;
    }

    public LoginConfigIn setActiveIntroduction(String activeIntroduction) {
        this.activeIntroduction = activeIntroduction;
        return this;
    }

    public String getContactManager() {
        return contactManager;
    }

    public LoginConfigIn setContactManager(String contactManager) {
        this.contactManager = contactManager;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public LoginConfigIn setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public String getLinkStr() {
        return linkStr;
    }

    public LoginConfigIn setLinkStr(String linkStr) {
        this.linkStr = linkStr;
        return this;
    }

    public Integer getOnUse() {
        return onUse;
    }

    public LoginConfigIn setOnUse(Integer onUse) {
        this.onUse = onUse;
        return this;
    }
}
