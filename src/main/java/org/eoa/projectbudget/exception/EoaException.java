package org.eoa.projectbudget.exception;

/**
 * @Author 张骏山
 * @Date 2023/10/8 14:48
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: EoaException
 * @Description: 自定义异常基础类
 * @Version 1.0
 */
public class EoaException extends RuntimeException{
    public int code;
    public String description;

    public EoaException() {
        super();
    }
}
