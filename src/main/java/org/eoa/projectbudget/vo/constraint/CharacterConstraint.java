package org.eoa.projectbudget.vo.constraint;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:15
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Character
 * @Description: 角色限制校验
 * @Version 1.1
 **/
@Data
@Accessors(chain = true)
public class CharacterConstraint implements AuthoritySolve, FormSolve {
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
                    case 1: {
                        if (creator == null) {
                            return false;
                        }
                        if (creator.getSectionRecursion().contains(user.getSection()))
                            return true;
                    }
                    case 2: {
                        if (creator == null) {
                            return false;
                        }
                        if (creator.getDepart().equals(user.getDepart()))
                            return true;
                    }
                }
        }
        return false;
    }

    @Override
    public boolean solve(HumanDto user, FormOutDto formOutDto) throws EoaException {
        for (Group character:
             characters) {
            Set<Long> asked = new HashSet<>();
            boolean isFound = false;
            Object mainValue = formOutDto.getMainValue(character.getCharacterId());

            if (mainValue != null) {
                asked.add((Long) mainValue);
                isFound = true;
            }

            if (!isFound) {
                List<Object> detailsValues = formOutDto.getDetailsValues(character.getCharacterId());
                if (detailsValues != null) {
                    asked.addAll(detailsValues.stream().map(o -> (Long) o).toList());
                    isFound = true;
                }
            }
            if (!isFound) {
                throw new DataException(formOutDto.getTable().getTableDataName(), formOutDto.getDataId().toString(),"authority",character.getCharacterId().toString(),
                        String.format("%d不在该表单的字段中",character.getCharacterId()));
            }
            for (Long characterId:
                 asked) {
                Integer userGrade = user.getCharacters().get(characterId);
                if (userGrade != null && userGrade <= character.getGrade())
                    return true;
            }
        }
        return false;
    }

    public static class Group {
        Long characterId;
        Integer grade;

        public Long getCharacterId() {
            return characterId;
        }

        public Group setCharacterId(Long characterId) {
            this.characterId = characterId;
            return this;
        }

        public Integer getGrade() {
            return grade;
        }

        public Group setGrade(Integer grade) {
            this.grade = grade;
            return this;
        }
    }
}
