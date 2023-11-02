package org.eoa.projectbudget.dto;

import org.eoa.projectbudget.entity.HumanResourceView;

import java.util.*;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 16:59
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: HumanDto
 * @Description: 人力资源计算及缓存时的实体对象
 * @Version 1.0
 **/
public class HumanDto extends HumanResourceView {
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
}
