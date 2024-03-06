package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2024/2/11 20:40
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: TestCheck
 * @Description: check测试实验
 * @Version: 1.0
 */

@SuppressWarnings("unused")
public class TestCheck implements WorkflowCheck{
    @Override
    public boolean check(RequestDto requestDto, JdbcTemplate jdbcTemplate) {
        System.out.println("Test Check Start");
        return false;
    }

    @Override
    public String getFailureReason() {
        return "";
    }
}
