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
public class ContentOut implements VoOut{
    @Schema(description = "数据编号")
    Long dataId;
    @Schema(description = "是否废弃")
    Boolean deprecated;
    @Schema(description = "目录名称,browserId=0")
    String contentName;
    @Schema(description = "目录憋住,browserId=1")
    String contentRemark;
    @Schema(description = "创建者,,browserId=2")
    Long creator;
    @Schema(description = "创建时间")
    Date createTime;
    @Schema(description = "默认编辑权限")
    String defaultEdit;
    @Schema(description = "默认创建权限")
    String defaultCreate;
    @Schema(description = "默认删除权限")
    String defaultDelete;
    @Schema(description = "默认共享权限")
    String defaultShare;
    @Schema(description = "上级目录")
    Long leadContent;
    @Schema(description = "创建者姓名")
    String creatorName;
    @Schema(description = "上级目录名称")
    String leadName;

    public ContentOut() {
    }

    public ContentOut(Content content) {
        this.dataId = content.getDataId();
        this.deprecated = DataProcessUtils.translateIntegerToBoolean(content.getIsDeprecated());
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
        return deprecated;
    }

    public ContentOut setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
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

    @Override
    public void toBrowser(Long browserId) {

        this.deprecated = null;
        this.contentName = browserId!=0L?null:contentName;
        this.contentRemark = browserId!=1L?null:contentRemark;
        this.creator = browserId!=2L?null:creator;
        this.createTime = browserId!=3L?null:createTime;
        this.defaultEdit = null;
        this.defaultCreate = null;
        this.defaultDelete = null;
        this.defaultShare = null;
        this.leadContent = null;
        this.creatorName = null;
        this.leadName = null;
    }


}
