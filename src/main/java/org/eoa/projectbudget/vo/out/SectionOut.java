package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Section;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/29 19:02
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: SectionOut
 * @Description: 分部结构体输出类
 * @Version: 1.0
 */

@Schema(name = "SectionOut", description = "分部结构体输出类")
public class SectionOut implements VoOut{
    @Schema(description = "唯一id")
    Long dataId;
    @Schema(description = "分部名称,browserId=0")
    String sectionName;
    @Schema(description = "分布编号,browserId=1")
    String sectionCode;
    @Schema(description = "全程,browserId=2")
    String fullName;
    @Schema(description = "上级分部")
    Long belongSection;
    @Schema(description = "分部负责人")
    Long sectionManager;
    @Schema(description = "分部介绍")
    String sectionIntroduction;
    @Schema(description = "创建时间")
    Date createTime;
    @Schema(description = "介绍照片编号")
    Long photo;

    @Schema(description = "上级部门名称")
    String belongSectionName;
    @Schema(description = "部门负责人姓名")
    String managerName;
    @Schema(description = "是否废弃")
    Integer isDeprecated;
    public SectionOut() {
    }

    public SectionOut(Section section) {
        this.dataId = section.getDataId();
        this.sectionName = section.getSectionName();
        this.sectionCode = section.getSectionCode();
        this.fullName = section.getFullName();
        this.belongSection = section.getBelongSection();
        this.sectionManager = section.getSectionManager();
        this.sectionIntroduction = section.getSectionIntroduction();
        this.createTime = section.getCreateTime();
        this.photo = section.getPhoto();
        this.isDeprecated = section.getIsDeprecated();
    }

    public Long getDataId() {
        return dataId;
    }

    public SectionOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getSectionName() {
        return sectionName;
    }

    public SectionOut setSectionName(String sectionName) {
        this.sectionName = sectionName;
        return this;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public SectionOut setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public SectionOut setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Long getBelongSection() {
        return belongSection;
    }

    public SectionOut setBelongSection(Long belongSection) {
        this.belongSection = belongSection;
        return this;
    }

    public Long getSectionManager() {
        return sectionManager;
    }

    public SectionOut setSectionManager(Long sectionManager) {
        this.sectionManager = sectionManager;
        return this;
    }

    public String getSectionIntroduction() {
        return sectionIntroduction;
    }

    public SectionOut setSectionIntroduction(String sectionIntroduction) {
        this.sectionIntroduction = sectionIntroduction;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SectionOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public SectionOut setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }

    public String getBelongSectionName() {
        return belongSectionName;
    }

    public SectionOut setBelongSectionName(String belongSectionName) {
        this.belongSectionName = belongSectionName;
        return this;
    }

    public String getManagerName() {
        return managerName;
    }

    public SectionOut setManagerName(String managerName) {
        this.managerName = managerName;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public SectionOut setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    @Override
    public void toBrowser(Long browserId) {

        this.sectionName = browserId!=0?null:sectionName;
        this.sectionCode = browserId!=1?null:sectionCode;
        this.fullName = browserId!=2?null:fullName;
        this.belongSection = null;
        this.sectionManager = null;
        this.sectionIntroduction = null;
        this.createTime = null;
        this.photo = null;
        this.belongSectionName = null;
        this.managerName = null;
        this.isDeprecated = null;
    }

}
