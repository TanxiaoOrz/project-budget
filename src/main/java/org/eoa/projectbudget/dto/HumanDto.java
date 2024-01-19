package org.eoa.projectbudget.dto;

import org.eoa.projectbudget.entity.HumanResourceView;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 16:59
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: HumanDto
 * @Description: 人力资源计算及缓存时的实体对象
 * @Version 1.1
 **/
public class HumanDto extends HumanResourceView implements Serializable {
    HashMap<Long,Integer> characters;
    HashSet<Integer> authorities;
    HashSet<Long> leaderRecursion;
    HashSet<Long> sectionRecursion;

    public HumanDto(Long dataId, String loginName, String password, String name, Integer sex, Date birth, Integer age, String telephone, String mail, String phone, String fax, String workCode, Long section, Long depart, String job, Long directorLeader, Long supporter, Long photo, String signature, Date lastLogin, Integer safety, HashMap<Long, Integer> characters, HashSet<Integer> authorities, HashSet<Long> leaderRecursion, HashSet<Long> sectionRecursion) {
        super(dataId, loginName, password, name, sex, birth, age, telephone, mail, phone, fax, workCode, section, depart, job, directorLeader, supporter, photo, signature, lastLogin, safety);
        this.characters = characters;
        this.authorities = authorities;
        this.leaderRecursion = leaderRecursion;
        this.sectionRecursion = sectionRecursion;
    }

    public HumanDto(HumanResourceView humanResourceView, HashMap<Long, Integer> characters, HashSet<Integer> authorities, HashSet<Long> leaderRecursion, HashSet<Long> sectionRecursion) {
        super(humanResourceView);
        this.characters = characters;
        this.authorities = authorities;
        this.leaderRecursion = leaderRecursion;
        this.sectionRecursion = sectionRecursion;
    }

    public HumanDto() {
    }

    public HashMap<Long, Integer> getCharacters() {
        return characters;
    }

    public HumanDto setCharacters(HashMap<Long, Integer> characters) {
        this.characters = characters;
        return this;
    }

    public HashSet<Integer> getAuthorities() {
        return authorities;
    }

    public HumanDto setAuthorities(HashSet<Integer> authorities) {
        this.authorities = authorities;
        return this;
    }

    public HashSet<Long> getLeaderRecursion() {
        return leaderRecursion;
    }

    public HumanDto setLeaderRecursion(HashSet<Long> leaderRecursion) {
        this.leaderRecursion = leaderRecursion;
        return this;
    }

    public HashSet<Long> getSectionRecursion() {
        return sectionRecursion;
    }

    public HumanDto setSectionRecursion(HashSet<Long> sectionRecursion) {
        this.sectionRecursion = sectionRecursion;
        return this;
    }

    @Override
    public Long getDataId() {
        return super.getDataId();
    }

    @Override
    public String getLoginName() {
        return super.getLoginName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Integer getSex() {
        return super.getSex();
    }

    @Override
    public Date getBirth() {
        return super.getBirth();
    }

    @Override
    public Integer getAge() {
        return super.getAge();
    }

    @Override
    public String getTelephone() {
        return super.getTelephone();
    }

    @Override
    public String getMail() {
        return super.getMail();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public String getFax() {
        return super.getFax();
    }

    @Override
    public String getWorkCode() {
        return super.getWorkCode();
    }

    @Override
    public Long getSection() {
        return super.getSection();
    }

    @Override
    public Long getDepart() {
        return super.getDepart();
    }

    @Override
    public String getJob() {
        return super.getJob();
    }

    @Override
    public Long getDirectorLeader() {
        return super.getDirectorLeader();
    }

    @Override
    public Long getSupporter() {
        return super.getSupporter();
    }

    @Override
    public Long getPhoto() {
        return super.getPhoto();
    }

    @Override
    public String getSignature() {
        return super.getSignature();
    }

    @Override
    public Date getLastLogin() {
        return super.getLastLogin();
    }

    @Override
    public Integer getSafety() {
        return super.getSafety();
    }

    @Override
    public Integer getIsDeprecated() {
        return super.getIsDeprecated();
    }

    @Override
    public HumanResourceView setDataId(Long dataId) {
        return super.setDataId(dataId);
    }

    @Override
    public HumanResourceView setLoginName(String loginName) {
        return super.setLoginName(loginName);
    }

    @Override
    public HumanResourceView setPassword(String password) {
        return super.setPassword(password);
    }

    @Override
    public HumanResourceView setName(String name) {
        return super.setName(name);
    }

    @Override
    public HumanResourceView setSex(Integer sex) {
        return super.setSex(sex);
    }

    @Override
    public HumanResourceView setBirth(Date birth) {
        return super.setBirth(birth);
    }

    @Override
    public HumanResourceView setAge(Integer age) {
        return super.setAge(age);
    }

    @Override
    public HumanResourceView setTelephone(String telephone) {
        return super.setTelephone(telephone);
    }

    @Override
    public HumanResourceView setMail(String mail) {
        return super.setMail(mail);
    }

    @Override
    public HumanResourceView setPhone(String phone) {
        return super.setPhone(phone);
    }

    @Override
    public HumanResourceView setFax(String fax) {
        return super.setFax(fax);
    }

    @Override
    public HumanResourceView setWorkCode(String workCode) {
        return super.setWorkCode(workCode);
    }

    @Override
    public HumanResourceView setSection(Long section) {
        return super.setSection(section);
    }

    @Override
    public HumanResourceView setDepart(Long depart) {
        return super.setDepart(depart);
    }

    @Override
    public HumanResourceView setJob(String job) {
        return super.setJob(job);
    }

    @Override
    public HumanResourceView setDirectorLeader(Long directorLeader) {
        return super.setDirectorLeader(directorLeader);
    }

    @Override
    public HumanResourceView setSupporter(Long supporter) {
        return super.setSupporter(supporter);
    }

    @Override
    public HumanResourceView setPhoto(Long photo) {
        return super.setPhoto(photo);
    }

    @Override
    public HumanResourceView setSignature(String signature) {
        return super.setSignature(signature);
    }

    @Override
    public HumanResourceView setLastLogin(Date lastLogin) {
        return super.setLastLogin(lastLogin);
    }

    @Override
    public HumanResourceView setSafety(Integer safety) {
        return super.setSafety(safety);
    }

    @Override
    public HumanResourceView setIsDeprecated(Integer isDeprecated) {
        return super.setIsDeprecated(isDeprecated);
    }
}
