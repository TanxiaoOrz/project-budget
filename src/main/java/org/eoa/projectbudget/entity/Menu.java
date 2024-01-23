package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 17:33
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Menu
 * @Description: 菜单实体类
 * @Version: 1.0
 **/

@TableName("`menu_base`")
public class Menu {
    @TableId(type = IdType.AUTO)
    Long dataId;
    String contentName;
    Long belongContent;
    String contentUrl;
    Integer viewNo;
    Integer isDeprecated;
    String shareAuthority;
    Long creator;
    Date createTime;

    public Menu() {
    }

    public Menu(Long dataId, String contentName, Long belongContent, String contentUrl, Integer viewNo, Integer isDeprecated, String shareAuthority) {
        this.dataId = dataId;
        this.contentName = contentName;
        this.belongContent = belongContent;
        this.contentUrl = contentUrl;
        this.viewNo = viewNo;
        this.isDeprecated = isDeprecated;
        this.shareAuthority = shareAuthority;
    }

    public Long getDataId() {
        return dataId;
    }

    public Menu setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getContentName() {
        return contentName;
    }

    public Menu setContentName(String contentName) {
        this.contentName = contentName;
        return this;
    }

    public Long getBelongContent() {
        return belongContent;
    }

    public Menu setBelongContent(Long belongContent) {
        this.belongContent = belongContent;
        return this;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public Menu setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public Menu setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public Menu setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public Menu setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public Menu setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Menu setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "dataId=" + dataId +
                ", contentName='" + contentName + '\'' +
                ", belongContent=" + belongContent +
                ", contentUrl='" + contentUrl + '\'' +
                ", viewNo=" + viewNo +
                ", isDeprecated=" + isDeprecated +
                ", shareAuthority='" + shareAuthority + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                '}';
    }
}