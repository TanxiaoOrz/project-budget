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
public class SectionOut {
    @Schema(description = "唯一id")
    Long dataId;
    @Schema(description = "分部名称")
    String sectionName;
    @Schema(description = "分布编号")
    String sectionCode;
    @Schema(description = "全程")
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

    @Schema(description = "")
    String belongSectionName;
    @Schema(description = "")
    String managerName;

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
}
