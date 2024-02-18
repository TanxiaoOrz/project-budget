package org.eoa.projectbudget.rest_controller;


import lombok.extern.slf4j.Slf4j;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.exception.RequestException;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(RequestException.class)
    public Vo<String> handleRequestException(RequestException e) {
        if (!isRelease)
            e.e.printStackTrace();
        return new Vo<>(e);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Vo<String> handleViolateUniqueConstraint(DuplicateKeyException e) {
        if (!isRelease) {
            e.printStackTrace();
        }
        Object[] strings = Arrays.stream(
                e.getMessage().toString().split(" "))
                .filter(s -> s.startsWith("'"))
                .toArray();
        String attribute =((String) strings[1]).split("_")[0].replace('\'',' ');
        String value =(String) strings[0];
        ParameterException e1 = new ParameterException(attribute, value, "违反唯一键约束");
        log.error(e1.description);
        return new Vo<>(e1);
    }

    @ExceptionHandler(EoaException.class)
    public Vo<String> handleEoaException(EoaException e) {
        if (!isRelease) {
            e.printStackTrace();
        }
        log.error(e.description);
        return new Vo<>(e);
    }

    @ExceptionHandler(Exception.class)
    public Vo<Exception> handleException(Exception e) {
        if (!isRelease)
            e.printStackTrace();
        log.error("系统运行异常,error_message:{}",e.getMessage());
        return new Vo<>(Vo.SERVER_ERROR,"系统运行异常,error_message:{}" + e.getMessage());
    }
}
