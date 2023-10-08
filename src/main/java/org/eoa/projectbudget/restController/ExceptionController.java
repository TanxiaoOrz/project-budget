package org.eoa.projectbudget.restController;

import lombok.extern.slf4j.Slf4j;
import org.eoa.projectbudget.exception.EoaException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.eoa.projectbudget.vo.Vo;

/**
 * @Author 张骏山
 * @Date 2023/9/28 9:43
 * @PackageName: org.eoa.projectbudget.restController
 * @ClassName: ExceptionController
 * @Description: 全局异常统一处理控制器
 * @Version 1.0
 */

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(EoaException.class)
    public Vo<String> handleEoaException(EoaException e) {
        return new Vo<>(e);
    }

    @ExceptionHandler(Exception.class)
    public Vo<Exception> handleException(Exception e) {
        log.error("系统运行异常,error_message:{}",e.getMessage());
        return new Vo<>(Vo.SERVER_ERROR,e.getMessage());
    }
}
