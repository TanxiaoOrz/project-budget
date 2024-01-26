package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 11:12
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Content
 * @Description: 目录实体类
 * @Version: 1.0
 **/

@TableName("content_list")
public class Content {
    @TableId(type = IdType.AUTO)
    Long dataId;
    Integer isDeprecated;
    String contentName;
    String contentRemark;
    Long creator;
    Date createTime;
    String defaultEdit;
    String defaultCreate;
    String defaultDelete;
    String defaultShare;


    Long leadContent;
    String defaultView;
    public String getDefaultView() {
        return defaultView;
    }

    public Content setDefaultView(String defaultView) {
        this.defaultView = defaultView;
        return this;
    }

    public Long getDataId() {
        return dataId;
    }

    public Content setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public Content setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    public String getContentName() {
        return contentName;
    }

    public Content setContentName(String contentName) {
        this.contentName = contentName;
        return this;
    }

    public String getContentRemark() {
        return contentRemark;
    }

    public Content setContentRemark(String contentRemark) {
        this.contentRemark = contentRemark;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public Content setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Content setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDefaultEdit() {
        return defaultEdit;
    }

    public Content setDefaultEdit(String defaultEdit) {
        this.defaultEdit = defaultEdit;
        return this;
    }

    public String getDefaultCreate() {
        return defaultCreate;
    }

    public Content setDefaultCreate(String defaultCreate) {
        this.defaultCreate = defaultCreate;
        return this;
    }

    public String getDefaultDelete() {
        return defaultDelete;
    }

    public Content setDefaultDelete(String defaultDelete) {
        this.defaultDelete = defaultDelete;
        return this;
    }

    public String getDefaultShare() {
        return defaultShare;
    }

    public Content setDefaultShare(String defaultShare) {
        this.defaultShare = defaultShare;
        return this;
    }

    public Long getLeadContent() {
        return leadContent;
    }

    public Content setLeadContent(Long leadContent) {
        this.leadContent = leadContent;
        return this;
    }

    @Override
    public String toString() {
        return "Content{" +
                "dataId=" + dataId +
                ", isDeprecated=" + isDeprecated +
                ", contentName='" + contentName + '\'' +
                ", contentRemark='" + contentRemark + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                ", defaultEdit='" + defaultEdit + '\'' +
                ", defaultCreate='" + defaultCreate + '\'' +
                ", defaultDelete='" + defaultDelete + '\'' +
                ", defaultShare='" + defaultShare + '\'' +
                ", leadContent=" + leadContent +
                '}';
    }
}
