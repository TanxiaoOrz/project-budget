package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2024/3/6 10:34
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: BudgetColumnLink
 * @Description: 预算编制流程字段联动
 * @Version: 1.0
 **/
public class BudgetColumnLink implements WorkflowAction{
    @Override
    public void action(RequestDto requestDto, JdbcTemplate jdbcTemplate) throws EoaException {
        Long dataId = requestDto.getDataId();
        // 添加销售金额
        jdbcTemplate.update("update form_table_2 set xsje = (select xsje from form_table_1 where form_table_1.dataId = form_table_2.htbh) where dataId = ?",dataId);
        // 计算预算金额
        jdbcTemplate.update("update form_table_2 set ysje = (select sum(jdje) from form_table_2_dt_1 where detailMainId = ?) where dataId = ?", dataId, dataId);
        // 添加预估利润
        jdbcTemplate.update("update form_table_2 set yglr = (xsje - ysje), sfyl = if(xsje>ysje,1,0)  where dataId = ?", dataId);
    }
}
