package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 11:04
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: File
 * @Description: 文件实体类
 * @Version: 1.0
 **/

@TableName("file_storage")
public class File {

    @TableId(type = IdType.AUTO)
    Long dataId;
    Integer isDeprecated;
    String fileName;
    String fileRoute;
    Long creator;
    Date createTime;
    String editAuthority;
    String viewAuthority;
    String deleteAuthority;
    Long leadContent;

    public Long getDataId() {
        return dataId;
    }

    public File setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public File setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public File setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileRoute() {
        return fileRoute;
    }

    public File setFileRoute(String fileRoute) {
        this.fileRoute = fileRoute;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public File setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public File setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEditAuthority() {
        return editAuthority;
    }

    public File setEditAuthority(String editAuthority) {
        this.editAuthority = editAuthority;
        return this;
    }

    public String getViewAuthority() {
        return viewAuthority;
    }

    public File setViewAuthority(String viewAuthority) {
        this.viewAuthority = viewAuthority;
        return this;
    }

    public String getDeleteAuthority() {
        return deleteAuthority;
    }

    public File setDeleteAuthority(String deleteAuthority) {
        this.deleteAuthority = deleteAuthority;
        return this;
    }

    public Long getLeadContent() {
        return leadContent;
    }

    public File setLeadContent(Long leadContent) {
        this.leadContent = leadContent;
        return this;
    }
}
