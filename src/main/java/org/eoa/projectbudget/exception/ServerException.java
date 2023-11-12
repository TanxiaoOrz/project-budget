package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo.out.Vo;

/**
 * @Author: 张骏山
 * @Date: 2023/10/30 14:49
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: ServerException
 * @Description: 系统运行异常类
 * @Version 1.0
 **/
public class ServerException extends EoaException{
    public ServerException(String description,Exception e) {

        e.printStackTrace();
        code=Vo.SERVER_ERROR;
        this.description = description+"\n"+e.getMessage();
    }
}
