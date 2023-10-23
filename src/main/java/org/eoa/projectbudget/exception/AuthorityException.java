package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo.Vo;

/**
 * @Author 张骏山
 * @Date 2023/10/23 16:49
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: AuthorityException
 * @Description: TODO
 * @Version 1.0
 */
public class AuthorityException extends EoaException{



    public AuthorityException(Long userId,String action,String reason) {
        code = Vo.NO_AUTHORITY;
        description = String.format("用户编号=>%d,尝试进行操作=>%s,拦截原因=>%s",userId,action,reason);
    }
}
