package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/3 16:36
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Depart
 * @Description: 部门实体类
 * @Version: 1.0
 **/

@TableName("depart_resource")
public class Depart {

    @TableId(type = IdType.AUTO)
    Long dataId;
    String departName;
    String departCode;
    String fullName;
    Long belongDepart;
    Long belongSection;
    Long departManager;
    String departIntroduction;
    Date createTime;
    Long photo;

    Integer isDeprecated;

    public Long getDataId() {
        return dataId;
    }

    public Depart setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getDepartName() {
        return departName;
    }

    public Depart setDepartName(String departName) {
        this.departName = departName;
        return this;
    }

    public String getDepartCode() {
        return departCode;
    }

    public Depart setDepartCode(String departCode) {
        this.departCode = departCode;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Depart setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Long getBelongDepart() {
        return belongDepart;
    }

    public Depart setBelongDepart(Long belongDepart) {
        this.belongDepart = belongDepart;
        return this;
    }

    public Long getBelongSection() {
        return belongSection;
    }

    public Depart setBelongSection(Long belongSection) {
        this.belongSection = belongSection;
        return this;
    }

    public Long getDepartManager() {
        return departManager;
    }

    public Depart setDepartManager(Long departManager) {
        this.departManager = departManager;
        return this;
    }

    public String getDepartIntroduction() {
        return departIntroduction;
    }

    public Depart setDepartIntroduction(String departIntroduction) {
        this.departIntroduction = departIntroduction;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Depart setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public Depart setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }

    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    public Depart setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    @Override
    public String toString() {
        return "Depart{" +
                "dataId=" + dataId +
                ", departName='" + departName + '\'' +
                ", departCode='" + departCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", belongDepart=" + belongDepart +
                ", belongSection=" + belongSection +
                ", departManager=" + departManager +
                ", departIntroduction='" + departIntroduction + '\'' +
                ", createTime=" + createTime +
                ", photo=" + photo +
                ", isDeprecated=" + isDeprecated +
                '}';
    }
}
