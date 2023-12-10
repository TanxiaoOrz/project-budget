package org.eoa.projectbudget.dto.constraint;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;

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
    public boolean solve(HumanDto user, FormOutDto formOutDto) throws EoaException {
        Float start =(Float) formOutDto.getMainValue(this.start);
        Float end = (Float) formOutDto.getMainValue(this.end);
        if (start == null) {
            throw new DataException(formOutDto.getTable().getTableDataName(), formOutDto.getDataId().toString(),"authority",this.start.toString(),
                    String.format("%d不在该表单的字段中",this.start));
        }
        if (end == null) {
            throw new DataException(formOutDto.getTable().getTableDataName(), formOutDto.getDataId().toString(),"authority",this.end.toString(),
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
