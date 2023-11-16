package org.eoa.projectbudget.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.vo.constraint.AuthorityConstraint;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/16 19:20
 * @PackageName: IntelliJ IDEA
 * @ClassName: BackInterceptor
 * @Description: TODO
 * @Version: 1.0
 */
public class BackInterceptor implements HandlerInterceptor {

    private final AuthorityConstraint constraint;

    public BackInterceptor(Long[] authorityAsked) {
        constraint = new AuthorityConstraint().setAuthorities(List.of(authorityAsked));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HumanDto humanDto =(HumanDto) request.getAttribute("HumanDto");
        return constraint.solve(humanDto, humanDto);
    }

}
