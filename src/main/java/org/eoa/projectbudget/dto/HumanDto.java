package org.eoa.projectbudget.dto;

import org.eoa.projectbudget.entity.HumanResourceView;

import java.util.List;
import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 16:59
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: HumanDto
 * @Description: TODO
 * @Version 1.0
 **/
public class HumanDto extends HumanResourceView {
    Map<Long,Integer> characters;
    List<Integer> authorities;
    List<Long> leaderRecursion;
    List<Long> sectionRecursion;

    public Map<Long, Integer> getCharacters() {
        return characters;
    }

    public HumanDto setCharacters(Map<Long, Integer> characters) {
        this.characters = characters;
        return this;
    }

    public List<Integer> getAuthorities() {
        return authorities;
    }

    public HumanDto setAuthorities(List<Integer> authorities) {
        this.authorities = authorities;
        return this;
    }

    public List<Long> getLeaderRecursion() {
        return leaderRecursion;
    }

    public HumanDto setLeaderRecursion(List<Long> leaderRecursion) {
        this.leaderRecursion = leaderRecursion;
        return this;
    }

    public List<Long> getSectionRecursion() {
        return sectionRecursion;
    }

    public HumanDto setSectionRecursion(List<Long> sectionRecursion) {
        this.sectionRecursion = sectionRecursion;
        return this;
    }
}
