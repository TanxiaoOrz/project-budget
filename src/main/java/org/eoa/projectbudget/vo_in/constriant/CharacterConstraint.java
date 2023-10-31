package org.eoa.projectbudget.vo_in.constriant;

import lombok.Data;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:15
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Character
 * @Description: TODO
 * @Version 1.0
 **/
@Data
public class CharacterConstraint implements AuthoritySolve {
    List<Group> characters;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        for (Group group :
                characters) {
            Integer userGrade = user.getCharacters().get(group.characterId);
            if (userGrade == null) {
                continue;
            }
            if (userGrade <= group.grade)
                switch (userGrade) {
                    case 0:
                        return true;
                    case 1:
                        if (creator.getSectionRecursion().contains(user.getSection()))
                            return true;
                    case 2:
                        if (creator.getDepart().equals(user.getDepart()))
                            return true;
                }
        }
        return false;
    }

    @Data
    static class Group {
        Long characterId;
        Integer grade;
    }
}
