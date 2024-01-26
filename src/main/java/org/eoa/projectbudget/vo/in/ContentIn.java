package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Content;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 16:25
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: ContentIn
 * @Description: 目录传入结构体
 * @Version: 1.1
 **/

@Schema(name = "ContentIn", description = "目录传入结构体")
public class ContentIn implements CheckParameter<Content>{
    @Schema(description = "目录名称")
    String contentName;
    String contentRemark;
    String defaultEdit;
    String defaultCreate;
    String defaultDelete;
    String defaultShare;
    Long leadContent;
    String defaultView;
    public String getDefaultView() {
        return defaultView;
    }

    public ContentIn setDefaultView(String defaultView) {
        this.defaultView = defaultView;
        return this;
    }
    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(contentName)) {
            throw new ParameterException("contentName","","缺少值");
        }
    }

    @Override
    public Content toEntity(Long dataId) throws ParameterException {
        checkSelf();
        return new Content()
                .setDataId(dataId)
                .setContentName(contentName)
                .setContentRemark(contentRemark)
                .setDefaultCreate(defaultCreate)
                .setDefaultEdit(defaultEdit)
                .setDefaultDelete(defaultDelete)
                .setDefaultShare(defaultShare)
                .setLeadContent(leadContent)
                .setIsDeprecated(0)
                .setDefaultView(defaultView);
    }

    public String getContentName() {
        return contentName;
    }

    public ContentIn setContentName(String contentName) {
        this.contentName = contentName;
        return this;
    }

    public String getContentRemark() {
        return contentRemark;
    }

    public ContentIn setContentRemark(String contentRemark) {
        this.contentRemark = contentRemark;
        return this;
    }

    public String getDefaultEdit() {
        return defaultEdit;
    }

    public ContentIn setDefaultEdit(String defaultEdit) {
        this.defaultEdit = defaultEdit;
        return this;
    }

    public String getDefaultCreate() {
        return defaultCreate;
    }

    public ContentIn setDefaultCreate(String defaultCreate) {
        this.defaultCreate = defaultCreate;
        return this;
    }

    public String getDefaultDelete() {
        return defaultDelete;
    }

    public ContentIn setDefaultDelete(String defaultDelete) {
        this.defaultDelete = defaultDelete;
        return this;
    }

    public String getDefaultShare() {
        return defaultShare;
    }

    public ContentIn setDefaultShare(String defaultShare) {
        this.defaultShare = defaultShare;
        return this;
    }

    public Long getLeadContent() {
        return leadContent;
    }

    public ContentIn setLeadContent(Long leadContent) {
        this.leadContent = leadContent;
        return this;
    }
}
