package org.eoa.projectbudget.vo.constraint;

import org.eoa.projectbudget.dto.Form;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:29
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Matrix
 * @Description: TODO
 * @Version 1.0
 **/
public class MatrixConstraint implements AuthoritySolve, FormSolve {
    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        return false;
    }

    @Override
    public boolean solve(HumanDto user, Form<Column, Table> form) throws EoaException {
        return false;
    }
}
