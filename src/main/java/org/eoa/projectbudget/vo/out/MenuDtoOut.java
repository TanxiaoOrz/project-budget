package org.eoa.projectbudget.vo.out;

import org.eoa.projectbudget.dto.MenuDto;

import java.util.Date;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 23:35
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: MenuDtoOut
 * @Description: 菜单运算输出类
 * @Version: 1.0
 */
public class MenuDtoOut implements VoOut{

    Long dataId;
    String contentName;
    Long belongContent;
    String contentUrl;
    Integer viewNo;
    Integer isDeprecated;
    String shareAuthority;
    Long creator;
    Date createTime;

    String creatorName;
    String belongContentName;

    List<MenuDtoOut> children;

    public MenuDtoOut() {
    }

    public MenuDtoOut(MenuDto menu) {
        this.dataId = menu.getDataId();
        this.contentName = menu.getContentName();
        this.belongContent = menu.getBelongContent();
        this.contentUrl = menu.getContentUrl();
        this.viewNo = menu.getViewNo();
        this.isDeprecated = menu.getIsDeprecated();
        this.shareAuthority = menu.getShareAuthority();
        this.creator = menu.getCreator();
        this.createTime = menu.getCreateTime();
    }

    @Override
    public void toBrowser(Long browserId) {
        this.contentName = browserId!=0L?null:contentName;
        this.belongContent = null;
        this.contentUrl = null;
        this.viewNo = null;
        this.isDeprecated = null;
        this.shareAuthority = null;
        this.creator = null;
        this.createTime = null;
        this.creatorName = null;
        this.belongContentName = null;
        children= null;
    }

    public Long getDataId() {
        return dataId;
    }

    public MenuDtoOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getContentName() {
        return contentName;
    }

    public MenuDtoOut setContentName(String contentName) {
        this.contentName = contentName;
        return this;
    }

    public Long getBelongContent() {
        return belongContent;
    }

    public MenuDtoOut setBelongContent(Long belongContent) {
        this.belongContent = belongContent;
        return this;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public MenuDtoOut setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public MenuDtoOut setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public MenuDtoOut setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public MenuDtoOut setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public MenuDtoOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MenuDtoOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public MenuDtoOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getBelongContentName() {
        return belongContentName;
    }

    public MenuDtoOut setBelongContentName(String belongContentName) {
        this.belongContentName = belongContentName;
        return this;
    }

    public List<MenuDtoOut> getChildren() {
        return children;
    }

    public MenuDtoOut setChildren(List<MenuDtoOut> children) {
        this.children = children;
        return this;
    }
}
