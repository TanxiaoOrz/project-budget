package org.eoa.projectbudget.vo.constraint;

import org.eoa.projectbudget.dto.Form;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;

/**
 * @Author: 张骏山
 * @Date: 2023/11/1 16:40
 * @PackageName: org.eoa.projectbudget.vo.constraint
 * @ClassName: AllConstraint
 * @Description: 所有人安全等级限制
 * @Version: 1.0
 **/
public class AllConstraint implements FormSolve, AuthoritySolve {

    Long start;
    Long end;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        return (user.getSafety()>=start && user.getSafety() <= end);
    }

    @Override
    public boolean solve(HumanDto user, Form<Column, Table> form) throws EoaException {
        Integer start =(Integer) form.getMainValue(this.start);
        Integer end = (Integer) form.getMainValue(this.end);
        if (start == null) {
            throw new DataException(form.getTable().getTableDataName(),form.getDataId().toString(),"authority",this.start.toString(),
                    String.format("%d不在该表单的字段中",this.start));
        }
        if (end == null) {
            throw new DataException(form.getTable().getTableDataName(),form.getDataId().toString(),"authority",this.end.toString(),
                    String.format("%d不在该表单的字段中",this.end));
        }
        return (user.getSafety()>=start && user.getSafety() <= end);
    }

    public Long getStart() {
        return start;
    }

    public AllConstraint setStart(Long start) {
        this.start = start;
        return this;
    }

    public Long getEnd() {
        return end;
    }

    public AllConstraint setEnd(Long end) {
        this.end = end;
        return this;
    }
}
