package org.eoa.projectbudget.dto.constraint;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.mapper.CharacterMapper;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.utils.ContextUtils;

import java.util.ArrayList;
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
    public List<Long> get(HumanDto creator) {
        CharacterMapper characterMapper = ContextUtils.getInstance().getCharacterMapper();
        HumanMapper humanMapper = ContextUtils.getInstance().getHumanMapper();
        ArrayList<Long> longs = new ArrayList<>();
        for (Group character:
                characters) {
            List<CharacterMapper.Grade> humans = characterMapper.getHumanFromCharacter(character.characterId, character.grade);
            longs.addAll(humans.stream().filter(grade -> {
                Integer userGrade = grade.getGrade();
                if (userGrade != null) {
                    if (userGrade <= character.grade)
                        switch (userGrade) {
                            case 0:
                                return true;
                            case 1: {
                                if (creator == null) {
                                    return false;
                                }
                                if (creator.getSectionRecursion().contains(humanMapper.selectById(grade.getHumanId()).getSection()))
                                    return true;
                            }
                            case 2: {
                                if (creator == null) {
                                    return false;
                                }
                                if (creator.getDepart().equals(humanMapper.selectById(grade.getHumanId()).getDepart()))
                                    return true;
                            }
                            default:
                                return false;
                        }
                }
                return false;
            }).map(CharacterMapper.Grade::getHumanId).toList());
        }
        return longs.stream().distinct().toList();
    }

    @Override
    public boolean solve(HumanDto user, FormOutDto formOutDto) throws EoaException {
        for (Group character:
             characters) {
            Set<Long> asked = translateValue(formOutDto, character);
            for (Long characterId:
                 asked) {
                Integer userGrade = user.getCharacters().get(characterId);
                if (userGrade != null && userGrade <= character.getGrade())
                    return true;
            }
        }
        return false;
    }

    private Set<Long> translateValue(FormOutDto formOutDto, Group character) {
        Set<Long> asked = new HashSet<>();
        if (formOutDto.getColumn(character.characterId)==null) {
            throw new DataException(formOutDto.getTable().getTableDataName(), formOutDto.getDataId().toString(),"authority", character.getCharacterId().toString(),
                    String.format("%d不在该表单的字段中", character.getCharacterId()));
        }
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
            }
        }
        return asked;
    }

    @Override
    public List<Long> get(FormOutDto formOutDto) {
        CharacterMapper characterMapper = ContextUtils.getInstance().getCharacterMapper();
        ArrayList<Long> longs = new ArrayList<>();
        for (Group character:
                characters) {
            for (Long characterId:translateValue(formOutDto, character)) {
                List<CharacterMapper.Grade> humans = characterMapper.getHumanFromCharacter(characterId, character.grade);
                longs.addAll(humans.stream().filter(grade -> {
                    Integer userGrade = grade.getGrade();
                    if (userGrade != null) {
                        return  (userGrade <= character.grade);
                    } else
                        return false;
                }).map(CharacterMapper.Grade::getHumanId).toList());
            }

        }
        return longs.stream().distinct().toList();
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
