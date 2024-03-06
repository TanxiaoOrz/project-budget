package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2024/3/6 11:13
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: BudgetUseMainColumnLink
 * @Description: 预算分配流程主表字段联动
 * @Version: 1.0
 **/
public class BudgetUseMainColumnLink implements WorkflowAction{
    @Override
    public void action(RequestDto requestDto, JdbcTemplate jdbcTemplate) throws EoaException {
        Long dataId = requestDto.getDataId();
        String tableName = requestDto.getFormOutDto().getTable().getTableDataName();
        // 添加项目经理
        jdbcTemplate.update("update "+ tableName+ " set xmjl = (select xmjl from form_table_2 where dataId = " + tableName + ".xmmc) where dataId = " + dataId);
    }
}
