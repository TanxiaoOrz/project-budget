package org.eoa.projectbudget.dto.constraint;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.EoaException;

import java.util.List;

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
     * @param formOutDto 表单内容
     * @return 用户是否有权限
     */
    boolean solve(HumanDto user, FormOutDto formOutDto) throws EoaException;


    /**
     * 获取范围内对象
     * @param formOutDto 表单内容
     * @return 范围内人力资源数组
     */
    List<Long> get(FormOutDto formOutDto);
}
