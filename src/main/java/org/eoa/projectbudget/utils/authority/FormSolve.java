package org.eoa.projectbudget.utils.authority;

import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Form;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.EoaException;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:14
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: FormSolve
 * @Description: 自定义表单解析需要完成的接口
 * @Version 1.0
 **/
public interface FormSolve {

    /**
     *
     * @param user 需要判断的用户
     * @param form 表单内容
     * @return 用户是否有权限
     */
    boolean solve(HumanDto user, Form<Column,Table> form) throws EoaException;

}
