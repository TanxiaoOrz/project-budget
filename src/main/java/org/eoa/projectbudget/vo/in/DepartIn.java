package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/17 19:33
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: DepartIn
 * @Description: 部门传入结构体
 * @Version: 1.0
 */

@Schema(name = "DepartIn", description = "部门传入结构体")
public class DepartIn implements CheckParameter<Depart>{

    String departName;
    String departCode;
    @Schema(description = "部门全程,若传入空值会自动替换为部门名称")
    String fullName;
    Long belongDepart;
    Long belongSection;
    Long departManager;
    String departIntroduction;
    Date createTime;
    Long photo;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(departName))
            throw new ParameterException("departName","","缺少部门名称");
        if (DataProcessUtils.isEmpty(departCode))
            throw new ParameterException("departCode","","缺少部门名称");
        if (belongSection == null)
            throw new ParameterException("belongSection","","缺少上级分部");

    }

    @Override
    public Depart toEntity(Long dataId) throws ParameterException {
        checkSelf();

        Depart depart = new Depart();
        depart.setDataId(dataId)
                .setDepartName(departName)
                .setDepartCode(departCode)
                .setFullName(DataProcessUtils.isEmpty(fullName)?departName:fullName)
                .setBelongDepart(belongDepart)
                .setBelongSection(belongSection)
                .setDepartManager(departManager)
                .setDepartIntroduction(departIntroduction)
                .setCreateTime(createTime)
                .setPhoto(photo);
        return depart;
    }

    public String getDepartName() {
        return departName;
    }

    public DepartIn setDepartName(String departName) {
        this.departName = departName;
        return this;
    }

    public String getDepartCode() {
        return departCode;
    }

    public DepartIn setDepartCode(String departCode) {
        this.departCode = departCode;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public DepartIn setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Long getBelongDepart() {
        return belongDepart;
    }

    public DepartIn setBelongDepart(Long belongDepart) {
        this.belongDepart = belongDepart;
        return this;
    }

    public Long getBelongSection() {
        return belongSection;
    }

    public DepartIn setBelongSection(Long belongSection) {
        this.belongSection = belongSection;
        return this;
    }

    public Long getDepartManager() {
        return departManager;
    }

    public DepartIn setDepartManager(Long departManager) {
        this.departManager = departManager;
        return this;
    }

    public String getDepartIntroduction() {
        return departIntroduction;
    }

    public DepartIn setDepartIntroduction(String departIntroduction) {
        this.departIntroduction = departIntroduction;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public DepartIn setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }
}
