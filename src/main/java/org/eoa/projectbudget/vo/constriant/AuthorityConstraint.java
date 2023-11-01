package org.eoa.projectbudget.vo.constriant;

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
 * @Date: 2023/10/31 11:38
 * @PackageName org.eoa.projectbudget.utils.authority
 * @ClassName: TableColumn
 * @Description: 根据表单字段进行字段限定
 * @Version 1.0
 **/
public class AuthorityConstraint implements AuthoritySolve, FormSolve {
    List<Long> authorities;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        if (user.getAuthorities().contains(0))
            return true;
        return new HashSet<>(user.getAuthorities()).containsAll(authorities.stream().map(Long::intValue).toList());
    }

    @Override
    public boolean solve(HumanDto user, Form<Column, Table> form) throws EoaException {
        if (user.getAuthorities().contains(0))
            return true;
        for (Long authority:
             authorities) {
            Set<Integer> asked = new HashSet<>();
            AtomicBoolean isFound = new AtomicBoolean(false);
            form.getGroups().forEach(
                group -> group.getColumns().forEach((column, o) -> {
                    if (column.getColumnId().equals(authority)) {
                        asked.add(Integer.valueOf(o.toString()));
                        isFound.set(true);
                    }
                })
            );
            if (!isFound.get()) {
                throw new DataException(form.getTable().getTableDataName(),form.getDataId().toString(),"authority",authorities.toString(),
                        String.format("%d不在该表单的字段中",authority));
            }
            if (!user.getAuthorities().containsAll(asked))
                return false;
        }
        return true;
    }

    public List<Long> getAuthorities() {
        return authorities;
    }

    public AuthorityConstraint setAuthorities(List<Long> authorities) {
        this.authorities = authorities;
        return this;
    }
}
