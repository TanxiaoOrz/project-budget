package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/3 16:37
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Section
 * @Description: 分部实体
 * @Version: 1.0
 **/
@TableName(value = "section_resource")
public class Section {

    @TableId(type = IdType.AUTO)
    Long dataId;
    String sectionName;
    String sectionCode;
    String fullName;
    Long belongSection;
    Long sectionManager;
    String sectionIntroduction;
    Date createTime;
    Long photo;
    Integer isDeprecated;
    public Long getDataId() {
        return dataId;
    }

    public Section setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getSectionName() {
        return sectionName;
    }

    public Section setSectionName(String sectionName) {
        this.sectionName = sectionName;
        return this;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public Section setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Section setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Long getBelongSection() {
        return belongSection;
    }

    public Section setBelongSection(Long belongSection) {
        this.belongSection = belongSection;
        return this;
    }

    public Long getSectionManager() {
        return sectionManager;
    }

    public Section setSectionManager(Long sectionManager) {
        this.sectionManager = sectionManager;
        return this;
    }

    public String getSectionIntroduction() {
        return sectionIntroduction;
    }

    public Section setSectionIntroduction(String sectionIntroduction) {
        this.sectionIntroduction = sectionIntroduction;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Section setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public Section setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public Section setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    @Override
    public String toString() {
        return "Section{" +
                "dataId=" + dataId +
                ", sectionName='" + sectionName + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", belongSection=" + belongSection +
                ", sectionManager=" + sectionManager +
                ", sectionIntroduction='" + sectionIntroduction + '\'' +
                ", createTime=" + createTime +
                ", photo=" + photo +
                ", isDeprecated=" + isDeprecated +
                '}';
    }
}
