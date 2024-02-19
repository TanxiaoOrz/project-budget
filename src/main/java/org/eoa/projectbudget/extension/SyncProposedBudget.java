package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 张骏山
 * @Date: 2024/2/19 11:14
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: SyncProposedBudget
 * @Description: 修改既定预算
 * @Version: 1.0
 **/
@SuppressWarnings("unused")
public class SyncProposedBudget implements WorkflowAction{

    private final Logger log = LoggerFactory.getLogger("BudgetModule");

    private static final Long YSXX_Id = 86L;
    @Override
    public void action(RequestDto requestDto, JdbcTemplate jdbcTemplate) throws EoaException {
        FormOutDto formOutDto = requestDto.getFormOutDto();
        Long dataId = formOutDto.getDataId();
        List<Object> ysxxIds = formOutDto.getDetailsValues(YSXX_Id);
        AtomicInteger count = new AtomicInteger();
        ysxxIds.forEach(ysxxId->{
            int update = jdbcTemplate.update("update form_table_2_dt_1 set jdje = (select xjdje from form_table_5_dt_1 as new where new.detailMainId = ? and new.ysx = form_table_2_dt_1.detailDataId) where detailDataId = ?", dataId, ysxxId);
            count.getAndIncrement();
        });
        log.info("流程=>{}修改既定预算=>{},共计修改条数=>{}",requestDto.getRequestId(),ysxxIds,count.get());
    }
}
