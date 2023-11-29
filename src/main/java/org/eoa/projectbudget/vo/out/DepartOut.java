package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Depart;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/29 19:02
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: DepartOut
 * @Description: 部门结构体输出类
 * @Version: 1.0
 */

@Schema(name = "DepartOut", description = "部门结构体输出类")
public class DepartOut {

    @Schema(description = "唯一id")
    Long dataId;
    @Schema(description = "部门名称")
    String departName;
    @Schema(description = "部门编号")
    String departCode;
    @Schema(description = "全名")
    String fullName;
    @Schema(description = "上级部门")
    Long belongDepart;
    @Schema(description = "上级分部")
    Long belongSection;
    @Schema(description = "部门负责人")
    Long departManager;
    @Schema(description = "部门介绍")
    String departIntroduction;
    @Schema(description = "创建时间")
    Date createTime;
    @Schema(description = "照片id")
    Long photo;

    @Schema(description = "上级部门名称")
    String belongDepartName;
    @Schema(description = "上级分部名称")
    String belongSectionName;
    @Schema(description = "负责人名称")
    String managerName;

    public DepartOut() {
    }

    public DepartOut(Depart depart) {
        this.dataId = depart.getDataId();
        this.departName = depart.getDepartName();
        this.departCode = depart.getDepartCode();
        this.fullName = depart.getFullName();
        this.belongDepart = depart.getBelongDepart();
        this.belongSection = depart.getBelongSection();
        this.departManager = depart.getDepartManager();
        this.departIntroduction = depart.getDepartIntroduction();
        this.createTime = depart.getCreateTime();
        this.photo = depart.getPhoto();
    }

    public Long getDataId() {
        return dataId;
    }

    public DepartOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getDepartName() {
        return departName;
    }

    public DepartOut setDepartName(String departName) {
        this.departName = departName;
        return this;
    }

    public String getDepartCode() {
        return departCode;
    }

    public DepartOut setDepartCode(String departCode) {
        this.departCode = departCode;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public DepartOut setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Long getBelongDepart() {
        return belongDepart;
    }

    public DepartOut setBelongDepart(Long belongDepart) {
        this.belongDepart = belongDepart;
        return this;
    }

    public Long getBelongSection() {
        return belongSection;
    }

    public DepartOut setBelongSection(Long belongSection) {
        this.belongSection = belongSection;
        return this;
    }

    public Long getDepartManager() {
        return departManager;
    }

    public DepartOut setDepartManager(Long departManager) {
        this.departManager = departManager;
        return this;
    }

    public String getDepartIntroduction() {
        return departIntroduction;
    }

    public DepartOut setDepartIntroduction(String departIntroduction) {
        this.departIntroduction = departIntroduction;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DepartOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public DepartOut setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }

    public String getBelongDepartName() {
        return belongDepartName;
    }

    public DepartOut setBelongDepartName(String belongDepartName) {
        this.belongDepartName = belongDepartName;
        return this;
    }

    public String getBelongSectionName() {
        return belongSectionName;
    }

    public DepartOut setBelongSectionName(String belongSectionName) {
        this.belongSectionName = belongSectionName;
        return this;
    }

    public String getManagerName() {
        return managerName;
    }

    public DepartOut setManagerName(String managerName) {
        this.managerName = managerName;
        return this;
    }
}
