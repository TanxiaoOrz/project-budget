package org.eoa.projectbudget.vo.constraint;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:17
 * @PackageName: org.eoa.projectbudget.vo.constraint
 * @ClassName: CreatorConstraint
 * @Description: 创建者限制校验
 * @Version 1.0
 **/

public class CreatorConstraint implements AuthoritySolve, FormSolve {
    Boolean self;
    Boolean leader;
    Boolean leaderRecursion;
    Boolean depart;
    Boolean section;
    Boolean sectionRecursive;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        if (creator == null) {
            return false;
        }
        Long userId = creator.getDataId();
        if (self && user.getDataId().equals(userId))
            return true;
        if (leader && creator.getDirectorLeader().equals(user.getDataId()))
            return true;
        if (leaderRecursion && creator.getLeaderRecursion().contains(userId))
            return true;
        if (depart && creator.getDepart().equals(user.getDepart()))
            return true;
        if (section && creator.getSection().equals(user.getSection()))
            return true;
        return sectionRecursive && creator.getSectionRecursion().contains(user.getSection());
    }

    @Override
    public boolean solve(HumanDto user, FormOutDto<Column, Table> formOutDto) throws EoaException {
        return false;
    }

    public Boolean getSelf() {
        return self;
    }

    public CreatorConstraint setSelf(Boolean self) {
        this.self = self;
        return this;
    }

    public Boolean getLeader() {
        return leader;
    }

    public CreatorConstraint setLeader(Boolean leader) {
        this.leader = leader;
        return this;
    }

    public Boolean getLeaderRecursion() {
        return leaderRecursion;
    }

    public CreatorConstraint setLeaderRecursion(Boolean leaderRecursion) {
        this.leaderRecursion = leaderRecursion;
        return this;
    }

    public Boolean getDepart() {
        return depart;
    }

    public CreatorConstraint setDepart(Boolean depart) {
        this.depart = depart;
        return this;
    }

    public Boolean getSection() {
        return section;
    }

    public CreatorConstraint setSection(Boolean section) {
        this.section = section;
        return this;
    }

    public Boolean getSectionRecursive() {
        return sectionRecursive;
    }

    public CreatorConstraint setSectionRecursive(Boolean sectionRecursive) {
        this.sectionRecursive = sectionRecursive;
        return this;
    }
}
