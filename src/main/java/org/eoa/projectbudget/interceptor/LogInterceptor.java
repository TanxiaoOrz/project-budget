package org.eoa.projectbudget.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.eoa.projectbudget.dto.HumanDto;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: 张骏山
 * @Date: 2024/1/23 22:38
 * @PackageName: org.eoa.projectbudget.interceptor
 * @ClassName: LogInterceptor
 * @Description: 日志记录过滤器接口
 * @Version: 1.0
 */

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("OPTIONS"))
            return true;
        HumanDto humanDto =(HumanDto) request.getAttribute("HumanDto");
        log.info("用户=>{}访问url=>{}",humanDto.getDataId(),request.getPathInfo());
        return true;
    }
}
