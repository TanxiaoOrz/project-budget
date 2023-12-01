package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 15:20
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: ContentOut
 * @Description: 目录输出结构体
 * @Version: 1.0
 **/

@Schema(name = "ContentOut", description = "目录输出结构体")
public class ContentOut {
    Long dataId;
    Boolean isDeprecated;
    String contentName;
    String contentRemark;
    Long creator;
    Date createTime;
    String defaultEdit;
    String defaultCreate;
    String defaultDelete;
    String defaultShare;
    Long leadContent;
    String creatorName;
    String leadName;

    public ContentOut() {
    }

    public ContentOut(Content content) {
        this.dataId = content.getDataId();
        this.isDeprecated = DataProcessUtils.translateIntegerToBoolean(content.getIsDeprecated());
        this.contentName = content.getContentName();
        this.contentRemark = content.getContentRemark();
        this.creator = content.getCreator();
        this.createTime = content.getCreateTime();
        this.defaultEdit = content.getDefaultEdit();
        this.defaultCreate = content.getDefaultCreate();
        this.defaultDelete = content.getDefaultDelete();
        this.defaultShare = content.getDefaultShare();
        this.leadContent = content.getLeadContent();
    }

    public Long getDataId() {
        return dataId;
    }

    public ContentOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Boolean getDeprecated() {
        return isDeprecated;
    }

    public ContentOut setDeprecated(Boolean deprecated) {
        isDeprecated = deprecated;
        return this;
    }

    public String getContentName() {
        return contentName;
    }

    public ContentOut setContentName(String contentName) {
        this.contentName = contentName;
        return this;
    }

    public String getContentRemark() {
        return contentRemark;
    }

    public ContentOut setContentRemark(String contentRemark) {
        this.contentRemark = contentRemark;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public ContentOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ContentOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDefaultEdit() {
        return defaultEdit;
    }

    public ContentOut setDefaultEdit(String defaultEdit) {
        this.defaultEdit = defaultEdit;
        return this;
    }

    public String getDefaultCreate() {
        return defaultCreate;
    }

    public ContentOut setDefaultCreate(String defaultCreate) {
        this.defaultCreate = defaultCreate;
        return this;
    }

    public String getDefaultDelete() {
        return defaultDelete;
    }

    public ContentOut setDefaultDelete(String defaultDelete) {
        this.defaultDelete = defaultDelete;
        return this;
    }

    public String getDefaultShare() {
        return defaultShare;
    }

    public ContentOut setDefaultShare(String defaultShare) {
        this.defaultShare = defaultShare;
        return this;
    }

    public Long getLeadContent() {
        return leadContent;
    }

    public ContentOut setLeadContent(Long leadContent) {
        this.leadContent = leadContent;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public ContentOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getLeadName() {
        return leadName;
    }

    public ContentOut setLeadName(String leadName) {
        this.leadName = leadName;
        return this;
    }
}
