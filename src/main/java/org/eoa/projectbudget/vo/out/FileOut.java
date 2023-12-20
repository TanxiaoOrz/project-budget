package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.File;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/1 15:22
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: FIleOut
 * @Description: 文件输出结构体
 * @Version: 1.0
 **/
public class FileOut implements VoOut{
    @Schema(description = "数据编号")
    Long dataId;
    @Schema(description = "是否废弃")
    Boolean deprecated;
    @Schema(description = "文件名称,browserId=0")
    String fileName;
    @Schema(description = "文件路径,browserId=0")
    String fileRoute;
    @Schema(description = "创建者")
    Long creator;
    @Schema(description = "创建时间")
    Date createTime;
    @Schema(description = "编辑权限")
    String editAuthority;
    @Schema(description = "查看权限")
    String viewAuthority;
    @Schema(description = "删除权限")
    String deleteAuthority;
    @Schema(description = "所属目录")
    Long leadContent;
    @Schema(description = "创建者姓名")
    String creatorName;
    @Schema(description = "上级目录名称")
    String leadName;

    public FileOut() {

    }

    public FileOut(File file) {
        this.dataId = file.getDataId();
        this.deprecated = DataProcessUtils.translateIntegerToBoolean(file.getIsDeprecated());
        this.fileName = file.getFileName();
        this.fileRoute = file.getFileRoute();
        this.creator = file.getCreator();
        this.createTime = file.getCreateTime();
        this.editAuthority = file.getEditAuthority();
        this.viewAuthority = file.getViewAuthority();
        this.deleteAuthority = file.getDeleteAuthority();
        this.leadContent = file.getLeadContent();
    }

    public Long getDataId() {
        return dataId;
    }

    public FileOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public FileOut setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileOut setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileRoute() {
        return fileRoute;
    }

    public FileOut setFileRoute(String fileRoute) {
        this.fileRoute = fileRoute;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public FileOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FileOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEditAuthority() {
        return editAuthority;
    }

    public FileOut setEditAuthority(String editAuthority) {
        this.editAuthority = editAuthority;
        return this;
    }

    public String getViewAuthority() {
        return viewAuthority;
    }

    public FileOut setViewAuthority(String viewAuthority) {
        this.viewAuthority = viewAuthority;
        return this;
    }

    public String getDeleteAuthority() {
        return deleteAuthority;
    }

    public FileOut setDeleteAuthority(String deleteAuthority) {
        this.deleteAuthority = deleteAuthority;
        return this;
    }

    public Long getLeadContent() {
        return leadContent;
    }

    public FileOut setLeadContent(Long leadContent) {
        this.leadContent = leadContent;
        return this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public FileOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public String getLeadName() {
        return leadName;
    }

    public FileOut setLeadName(String leadName) {
        this.leadName = leadName;
        return this;
    }

    public FileOut(Long dataId, Boolean deprecated, String fileName, String fileRoute, Long creator, Date createTime, String editAuthority, String viewAuthority, String deleteAuthority, Long leadContent, String creatorName, String leadName) {
        this.dataId = dataId;
        this.deprecated = deprecated;
        this.fileName = fileName;
        this.fileRoute = fileRoute;
        this.creator = creator;
        this.createTime = createTime;
        this.editAuthority = editAuthority;
        this.viewAuthority = viewAuthority;
        this.deleteAuthority = deleteAuthority;
        this.leadContent = leadContent;
        this.creatorName = creatorName;
        this.leadName = leadName;
    }

    @Override
    public void toBrowser(Long browserId) {

        this.deprecated = null;
        this.fileName = browserId!=0?null:fileName;
        this.fileRoute = browserId!=1?null:fileRoute;
        this.creator = null;
        this.createTime = null;
        this.editAuthority = null;
        this.viewAuthority = null;
        this.deleteAuthority = null;
        this.leadContent = null;
        this.creatorName = null;
        this.leadName = null;
    }
}
