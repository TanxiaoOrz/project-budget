package org.eoa.projectbudget.vo.constriant;

import org.eoa.projectbudget.dto.Form;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:14
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Proposed
 * @Description: TODO
 * @Version 1.0
 **/


public class ProposedConstraint implements AuthoritySolve, FormSolve {
    List<Long> humans;
    List<Long> departs;
    List<Long> sections;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        if (humans.contains(user.getDataId()))
            return true;
        if (departs.contains(user.getDepart()))
            return true;
        return sections.contains(user.getSection());
    }

    @Override
    public boolean solve(HumanDto user, Form<Column, Table> form) throws EoaException {
        for (Long humanColumn:
             humans) {
            Set<Long> asked = getAsked(form, humanColumn);
            if (asked.contains(user.getDataId()))
                return true;
        }

        for (Long departColumnId:
                departs) {
            if (getAsked(form,departColumnId).contains(user.getDepart()))
                return true;
        }

        for (Long sectionColumnId:
                sections) {
            if (getAsked(form,sectionColumnId).contains(user.getSection()))
                return true;
        }

        return false;
    }

    private Set<Long> getAsked(Form<Column, Table> form, Long columnId) throws DataException {
        Set<Long> asked = new HashSet<>();
        boolean isFound = false;
        Object mainValue = form.getMainValue(columnId);

        if (mainValue != null) {
            asked.add((Long) mainValue);
            isFound = true;
        }

        if (!isFound) {
            List<Object> detailsValues = form.getDetailsValues(columnId);
            if (detailsValues != null) {
                asked.addAll(detailsValues.stream().map(o -> (Long) o).toList());
                isFound = true;
            }
        }

        if (!isFound) {
            throw new DataException(form.getTable().getTableDataName(), form.getDataId().toString(),"authority", columnId.toString(),
                    String.format("%d不在该表单的字段中", columnId));
        }
        return asked;
    }

    public List<Long> getHumans() {
        return humans;
    }

    public ProposedConstraint setHumans(List<Long> humans) {
        this.humans = humans;
        return this;
    }

    public List<Long> getDeparts() {
        return departs;
    }

    public ProposedConstraint setDeparts(List<Long> departs) {
        this.departs = departs;
        return this;
    }

    public List<Long> getSections() {
        return sections;
    }

    public ProposedConstraint setSections(List<Long> sections) {
        this.sections = sections;
        return this;
    }

}
