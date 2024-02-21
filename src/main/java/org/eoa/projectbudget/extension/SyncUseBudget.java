package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2024/2/19 10:52
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: SyncUseBudget
 * @Description: 预算使用结算后同步相关预算使用数据
 * @Version: 1.0
 **/
@SuppressWarnings("unused")
public class SyncUseBudget implements WorkflowAction{

    private final Logger log = LoggerFactory.getLogger("BudgetModule");

    /**
     *
     * @param requestDto 提交时的传入数据和流程涉及到的其它数据,注意获取数据时可能会在之前的操作中已修改未及时更新,表单数据均视为提交时数据,获取最新数据请从数据库中重新寻找
     * @param jdbcTemplate 数据库操作对象
     * @throws EoaException jdbc失败
     */
    @Override
    public void action(RequestDto requestDto, JdbcTemplate jdbcTemplate) throws EoaException {
        int update = jdbcTemplate.update("update form_table_2_dt_1 set yyje = (select je from (select ysyje as je,dataId from ysxx_use_statistics) as temp  where dataId = form_table_2_dt_1.detailDataId)");
        log.info("request请求更新使用预算,requestId=>{},更新数量=>{}",requestDto.getRequestId(),update);
    }
}
