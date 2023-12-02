package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo.out.Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author 张骏山
 * @Date 2023/10/23 16:49
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: AuthorityException
 * @Description: 权限异常类
 * @Version 1.0
 */
public class AuthorityException extends EoaException{

    private final Logger log = LoggerFactory.getLogger("AuthorityModule");


    public AuthorityException(Long userId, String action, String reason) {
        super();
        code = Vo.NO_AUTHORITY;
        description = String.format("用户编号=>%d,尝试进行操作=>%s,拦截原因=>%s",userId,action,reason);
        log.warn(description);
    }

    public AuthorityException(Long userId, String objectName, Long id, String action) {
        super();
        code = Vo.NO_AUTHORITY;
        description = String.format("没有对%s-%d的%s权限",objectName,id,action);
        log.warn("用户编号=>{},{}",userId,description);
    }
}
