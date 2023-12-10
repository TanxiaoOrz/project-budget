package org.eoa.projectbudget.dto.constraint;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.EoaException;

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
    public boolean solve(HumanDto user, FormOutDto formOutDto) throws EoaException {
        return false;
    }
}
