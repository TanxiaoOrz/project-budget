package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.HumanResourceView;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/29 19:01
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: HumanOut
 * @Description: TODO
 * @Version: 1.0
 */

@Schema(name = "HumanOut", description = "人员输出结构体")
public class HumanOut implements VoOut{
    @Schema(description = "唯一id")
    Long dataId;
    @Schema(description = "登录名,browserId=1")
    String loginName;
    @Schema(description = "姓名,browserId=0")
    String name;
    @Schema(description = "性别男,1女")
    Integer sex;
    @Schema(description = "出生日期")
    Date birth;
    @Schema(description = "年龄")
    Integer age;
    @Schema(description = "手机")
    String telephone;
    @Schema(description = "邮箱")
    String mail;
    @Schema(description = "电话")
    String phone;
    @Schema(description = "传真")
    String fax;
    @Schema(description = "工作编号")
    String workCode;
    @Schema(description = "分部")
    Long section;
    @Schema(description = "部门")
    Long depart;
    @Schema(description = "职位")
    String job;
    @Schema(description = "上级领导")
    Long directorLeader;
    @Schema(description = "秘书")
    Long supporter;
    @Schema(description = "介绍照片")
    Long photo;
    @Schema(description = "个人签名")
    String signature;
    @Schema(description = "上次登录时间")
    Date lastLogin;
    @Schema(description = "安全等级")
    Integer safety;

    @Schema(description = "部门名称")
    String departName;
    @Schema(description = "分部名称")
    String sectionName;
    @Schema(description = "领导名称")
    String leaderName;

    public HumanOut() {
    }

    public HumanOut(HumanResourceView humanResourceView) {
        this.dataId = humanResourceView.getDataId();
        this.loginName = humanResourceView.getLoginName();
        this.name = humanResourceView.getName();
        this.sex = humanResourceView.getSex();
        this.birth = humanResourceView.getBirth();
        this.telephone = humanResourceView.getTelephone();
        this.mail = humanResourceView.getMail();
        this.phone = humanResourceView.getPhone();
        this.fax = humanResourceView.getFax();
        this.workCode = humanResourceView.getWorkCode();
        this.section = humanResourceView.getSection();
        this.depart = humanResourceView.getDepart();
        this.job = humanResourceView.getJob();
        this.directorLeader = humanResourceView.getDirectorLeader();
        this.supporter = humanResourceView.getSupporter();
        this.photo = humanResourceView.getPhoto();
        this.signature = humanResourceView.getSignature();
        this.lastLogin = humanResourceView.getLastLogin();
        this.safety = humanResourceView.getSafety();
        this.age = humanResourceView.getAge();
    }

    public Long getDataId() {
        return dataId;
    }

    public HumanOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public HumanOut setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getName() {
        return name;
    }

    public HumanOut setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public HumanOut setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public Date getBirth() {
        return birth;
    }

    public HumanOut setBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public HumanOut setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public HumanOut setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public HumanOut setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public HumanOut setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFax() {
        return fax;
    }

    public HumanOut setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public String getWorkCode() {
        return workCode;
    }

    public HumanOut setWorkCode(String workCode) {
        this.workCode = workCode;
        return this;
    }

    public Long getSection() {
        return section;
    }

    public HumanOut setSection(Long section) {
        this.section = section;
        return this;
    }

    public Long getDepart() {
        return depart;
    }

    public HumanOut setDepart(Long depart) {
        this.depart = depart;
        return this;
    }

    public String getJob() {
        return job;
    }

    public HumanOut setJob(String job) {
        this.job = job;
        return this;
    }

    public Long getDirectorLeader() {
        return directorLeader;
    }

    public HumanOut setDirectorLeader(Long directorLeader) {
        this.directorLeader = directorLeader;
        return this;
    }

    public Long getSupporter() {
        return supporter;
    }

    public HumanOut setSupporter(Long supporter) {
        this.supporter = supporter;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public HumanOut setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public HumanOut setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public HumanOut setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public Integer getSafety() {
        return safety;
    }

    public HumanOut setSafety(Integer safety) {
        this.safety = safety;
        return this;
    }

    public String getDepartName() {
        return departName;
    }

    public HumanOut setDepartName(String departName) {
        this.departName = departName;
        return this;
    }

    public String getSectionName() {
        return sectionName;
    }

    public HumanOut setSectionName(String sectionName) {
        this.sectionName = sectionName;
        return this;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public HumanOut setLeaderName(String leaderName) {
        this.leaderName = leaderName;
        return this;
    }


    @Override
    public void toBrowser(Long browserId) {

        this.loginName = browserId!=1?null:loginName;
        this.name = browserId!=0?null:name;
        this.sex = null;
        this.birth = null;
        this.age = null;
        this.telephone = null;
        this.mail = null;
        this.phone = null;
        this.fax = null;
        this.workCode = null;
        this.section = null;
        this.depart = null;
        this.job = null;
        this.directorLeader = null;
        this.supporter = null;
        this.photo = null;
        this.signature = null;
        this.lastLogin = null;
        this.safety = null;
        this.departName = null;
        this.sectionName = null;
        this.leaderName = null;
    }
}
