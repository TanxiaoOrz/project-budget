package org.eoa.projectbudget.vo.constriant;

import lombok.Data;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:17
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Creator
 * @Description: TODO
 * @Version 1.0
 **/
@Data
public class CreatorConstraint implements AuthoritySolve {
    Boolean self;
    Boolean leader;
    Boolean leaderRecursion;
    Boolean depart;
    Boolean section;
    Boolean sectionRecursive;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
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
}
