package org.eoa.projectbudget.utils.authority;

import org.eoa.projectbudget.dto.HumanDto;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:29
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Matrix
 * @Description: TODO
 * @Version 1.0
 **/
public class Matrix implements AuthoritySolve{
    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        return false;
    }
}
