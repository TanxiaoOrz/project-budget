package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/12/17 18:47
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: HumanIn
 * @Description: 人员传入结构体
 * @Version: 1.0
 */

@Schema(name = "HumanIn", description = "人员传入结构体")
public class HumanIn implements CheckParameter<HumanResource>{
    String loginName;
    String password;
    String name;
    Integer sex;
    Date birth;
    String telephone;
    String mail;
    String phone;
    String fax;
    String workCode;
    Long depart;
    String job;
    Long directorLeader;
    Long supporter;
    Long photo;
    String signature;
    Integer safety;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(loginName))
            throw new ParameterException("loginName","","登录名为空");
        if (DataProcessUtils.isEmpty(name))
            throw new ParameterException("name","","姓名为空");
        if (depart == null) {
            throw new ParameterException("depart","","所属部门为空");
        }
        if (safety == null) {
            throw new ParameterException("safety","","安全等级为空");

        }
    }

    @Override
    public HumanResource toEntity(Long dataId) throws ParameterException {
        checkSelf();

        HumanResource humanResource = new HumanResource();
        humanResource.setDataId(dataId)
                .setLoginName(loginName)
                .setPassword(password)
                .setSex(sex)
                .setBirth(birth)
                .setTelephone(telephone)
                .setMail(mail)
                .setPhone(phone)
                .setFax(fax)
                .setWorkCode(workCode)
                .setDepart(depart)
                .setJob(job)
                .setDirectorLeader(directorLeader)
                .setSupporter(supporter)
                .setPhoto(photo)
                .setSignature(signature)
                .setSafety(safety);

        return humanResource;
    }

    public String getLoginName() {
        return loginName;
    }

    public HumanIn setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public HumanIn setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public HumanIn setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public HumanIn setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public Date getBirth() {
        return birth;
    }

    public HumanIn setBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public HumanIn setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public HumanIn setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public HumanIn setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFax() {
        return fax;
    }

    public HumanIn setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public String getWorkCode() {
        return workCode;
    }

    public HumanIn setWorkCode(String workCode) {
        this.workCode = workCode;
        return this;
    }


    public Long getDepart() {
        return depart;
    }

    public HumanIn setDepart(Long depart) {
        this.depart = depart;
        return this;
    }

    public String getJob() {
        return job;
    }

    public HumanIn setJob(String job) {
        this.job = job;
        return this;
    }

    public Long getDirectorLeader() {
        return directorLeader;
    }

    public HumanIn setDirectorLeader(Long directorLeader) {
        this.directorLeader = directorLeader;
        return this;
    }

    public Long getSupporter() {
        return supporter;
    }

    public HumanIn setSupporter(Long supporter) {
        this.supporter = supporter;
        return this;
    }

    public Long getPhoto() {
        return photo;
    }

    public HumanIn setPhoto(Long photo) {
        this.photo = photo;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public HumanIn setSignature(String signature) {
        this.signature = signature;
        return this;
    }


    public Integer getSafety() {
        return safety;
    }

    public HumanIn setSafety(Integer safety) {
        this.safety = safety;
        return this;
    }
}
