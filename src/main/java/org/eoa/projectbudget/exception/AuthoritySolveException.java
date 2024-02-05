package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo.out.Vo;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 16:45
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: AuthoritySolveException
 * @Description: 权限解析异常类
 * @Version 1.0
 **/
public class AuthoritySolveException extends EoaException{

    public AuthoritySolveException(String authorityString, String description) {
        super();
        this.code = Vo.STORE_DATA_ERROR;
        this.description =authorityString + "-" + description;
    }
}
