package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2024/2/11 20:39
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: TestAction
 * @Description: action测试实验
 * @Version: 1.0
 */

@SuppressWarnings("unused")
public class TestAction implements WorkflowAction{
    @Override
    public void action(RequestDto requestDto, JdbcTemplate jdbcTemplate) throws EoaException {
        System.out.println("Test Action Start");
    }
}
