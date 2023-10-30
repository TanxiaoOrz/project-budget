package org.eoa.projectbudget.exception;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 16:45
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: AuthoritySolveException
 * @Description: TODO
 * @Version 1.0
 **/
public class AuthoritySolveException extends EoaException{
    String authorityString;
    String description;

    public AuthoritySolveException(String authorityString, String description) {
        this.authorityString = authorityString;
        this.description = description;
    }
}
