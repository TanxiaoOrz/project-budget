package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo_out.Vo;

/**
 * @Author 张骏山
 * @Date 2023/10/9 14:02
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: ParameterException
 * @Description: 传入参数错误异常
 * @Version 1.0
 */
public class ParameterException extends EoaException{
    /**
     *
     * @param attribute 错误的属性
     * @param value 属性值
     * @param description 错误描述
     */
     public ParameterException(String attribute, String value, String description) {
        code = Vo.WRONG_PARAMETER;
        this.description = String.format("传入参数 %s = %s 不符合要求,错误原因: %s",attribute,value,description);
    }
}
