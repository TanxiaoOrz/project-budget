package org.eoa.projectbudget.rest_controller;


import lombok.extern.slf4j.Slf4j;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.eoa.projectbudget.vo.out.Vo;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;

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
    @Value("${eoa.release}")
    private boolean isRelease;

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Vo<String> handleViolateUniqueConstraint(SQLIntegrityConstraintViolationException e) {
        String[] strings = (String[]) Arrays.stream(
                e.getMessage().split(" "))
                .filter(s -> s.startsWith("'")).toArray();
        String attribute = strings[0];
        String value = strings[1];
        return new Vo<>(new ParameterException(attribute,value,e.getMessage()));
    }

    @ExceptionHandler(EoaException.class)
    public Vo<String> handleEoaException(EoaException e) {
        return new Vo<>(e);
    }

    @ExceptionHandler(Exception.class)
    public Vo<Exception> handleException(Exception e) {
        if (!isRelease)
            e.printStackTrace();
        log.error("系统运行异常,error_message:{}",e.getMessage());
        return new Vo<>(Vo.SERVER_ERROR,e.getMessage());
    }
}
