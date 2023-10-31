package org.eoa.projectbudget.dto;

import org.eoa.projectbudget.entity.HumanResourceView;

import java.util.HashMap;
import java.util.HashSet;
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
    HashMap<Long,Integer> characters;
    HashSet<Integer> authorities;
    HashSet<Long> leaderRecursion;
    HashSet<Long> sectionRecursion;

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
