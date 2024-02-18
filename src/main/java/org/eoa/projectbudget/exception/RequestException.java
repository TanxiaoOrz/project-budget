package org.eoa.projectbudget.exception;

import org.eoa.projectbudget.vo.out.Vo;

/**
 * @Author: 张骏山
 * @Date: 2024/2/18 14:21
 * @PackageName: org.eoa.projectbudget.exception
 * @ClassName: RequestException
 * @Description: 流程专用异常
 * @Version: 1.0
 **/
public class RequestException extends EoaException{
    Long requestId;

    public Exception e;


    public RequestException(Long requestId,Exception e) {
        super();
        this.e = e;
        this.requestId = requestId;
        if (e instanceof EoaException) {
            description = ((EoaException) e).description;
            code = ((EoaException) e).code;
        }
        else {
            code = Vo.SERVER_ERROR;
            description = e.getMessage();
        }
    }
}
