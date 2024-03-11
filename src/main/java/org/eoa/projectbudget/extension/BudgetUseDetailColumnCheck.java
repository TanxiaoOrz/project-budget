package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/3/6 11:37
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: BudgetUseDetailColumnCheck
 * @Description: 预算使用流程预算细项检查
 * @Version: 1.0
 **/
public class BudgetUseDetailColumnCheck implements WorkflowCheck {

    Long tableId;
    Object xmmc;

    @Override
    public boolean check(RequestDto requestDto, JdbcTemplate jdbcTemplate) {
        tableId = requestDto.getFormOutDto().getTableId();
        if (checkYsxxIds(requestDto, jdbcTemplate)) {
            columnLink(requestDto, jdbcTemplate);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getFailureReason() {
        return "存在预算细项不属于改项目,请检查";
    }

    private void columnLink(RequestDto requestDto, JdbcTemplate jdbcTemplate) {
        Long dataId = requestDto.getDataId();
        String tableName;
        switch (tableId.intValue()) {
            case 3 -> tableName = "form_table_3_dt_2";
            case 4 -> tableName =  "form_table_4_dt_2";
            case 5 -> tableName =  "form_table_5_dt_1";
            case 6 -> tableName =  "form_table_6_dt_1";
            default -> {
                EoaException eoaException = new EoaException();
                eoaException.code = Vo.SERVER_ERROR;
                eoaException.description = "错误的流程接口配置,流程编号=>"+requestDto.getWorkflowId()+"接口=>BudgetUseDetailColumnCheck";
                throw eoaException;
            }
        }
        if (tableId != 5)
            jdbcTemplate.update("update " + tableName +" used left join form_table_2_dt_1 ys on used.ysx = ys.detailDataId set used.jdje = ys.jdje, used.yyje = ys.yyje where used.detailMainId = ?",dataId);
        else
            jdbcTemplate.update("update form_table_5_dt_1 used left join form_table_2_dt_1 ys on used.ysx = ys.detailDataId set used.yjdje = ys.jdje where used.detailMainId = ?",dataId);
    }

    private boolean checkYsxxIds(RequestDto requestDto, JdbcTemplate jdbcTemplate) {
        long ysxxColumnId;
        switch (tableId.intValue()) {
            case 3 -> {
                ysxxColumnId = 65L;
                xmmc = requestDto.getFormOutDto().getMainValue(53L);
            }
            case 4 -> {
                ysxxColumnId = 78L;
                xmmc = requestDto.getFormOutDto().getMainValue(71L);
            }
            case 5 -> {
                ysxxColumnId = 86L;
                xmmc = requestDto.getFormOutDto().getMainValue(82L);
            }
            case 6 -> {
                ysxxColumnId = 93L;
                xmmc = requestDto.getFormOutDto().getMainValue(89L);
            }
            default -> {
                EoaException eoaException = new EoaException();
                eoaException.code = Vo.SERVER_ERROR;
                eoaException.description = "错误的流程接口配置,流程编号=>"+requestDto.getWorkflowId()+"接口=>BudgetUseDetailColumnCheck";
                throw eoaException;
            }
        }
        List<Integer> ysxxIds = requestDto.getFormOutDto().getDetailsValues(ysxxColumnId).stream().map(o -> Integer.parseInt(o.toString())).toList();
        List<Integer> includes = jdbcTemplate.queryForList("select detailDataId from form_table_2_dt_1 where detailMainId = " + xmmc, Integer.class);
        return new HashSet<>(includes).containsAll(ysxxIds);
    }
}
