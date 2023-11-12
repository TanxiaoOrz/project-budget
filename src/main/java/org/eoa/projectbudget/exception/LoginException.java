package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo.out.Vo;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 17:08
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: LoginException
 * @Description: TODO
 * @Version: 1.0
 **/
public class LoginException extends EoaException{

    public LoginException(boolean isOut) {
        super();
        code = Vo.TOKEN_ERROR;
        description = isOut?"token过期,重新登录":"token异常,重新登录";
    }

}
