package org.eoa.projectbudget.vo.constriant;

import lombok.Data;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Form;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:15
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Character
 * @Description: TODO
 * @Version 1.0
 **/
@Data
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

    @Override
    public boolean solve(HumanDto user, Form<Column, Table> form) throws EoaException {
        for (Group character:
             characters) {
            Set<Long> asked = new HashSet<>();
            AtomicBoolean isFound = new AtomicBoolean(false);
            form.getGroups().forEach(
                group -> group.getColumns().forEach(((column, o) -> {
                    if (column.getColumnId().equals(character.getCharacterId())) {
                        asked.add(Long.valueOf(o.toString()));
                        isFound.set(true);
                    }
                }))
            );
            if (!isFound.get()) {
                throw new DataException(form.getTable().getTableDataName(),form.getDataId().toString(),"authority",authorities.toString(),
                        String.format("%d不在该表单的字段中",character.getCharacterId()));
            }
            if (!user.getCharacters().containsAll(asked))
                return false;
        }
        return false;
    }

    @Data
    static class Group {
        Long characterId;
        Integer grade;
    }
}
