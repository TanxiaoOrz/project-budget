package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/3/6 14:12
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: CheckOverUsed
 * @Description: 超支检查
 * @Version: 1.0
 **/
public class CheckOverUsed implements WorkflowCheck{
    /**
     * 判断使用的预算项中是否存在超支
     * @param requestDto 提交时的传入数据和流程涉及到的其它数据,注意获取数据时可能会在之前的操作中已修改未及时更新,表单数据均视为提交时数据,获取最新数据请从数据库中重新寻找
     * @param jdbcTemplate 数据库操作对象
     * @return 是否存在超值项
     */
    @Override
    public boolean check(RequestDto requestDto, JdbcTemplate jdbcTemplate) {
        long ysxxColumnId;
        switch (requestDto.getFormOutDto().getTableId().intValue()) {
            case 3 -> ysxxColumnId = 65L;
            case 4 -> ysxxColumnId = 78L;
            case 5 -> ysxxColumnId = 86L;
            case 6 -> ysxxColumnId = 93L;
            default -> {
                EoaException eoaException = new EoaException();
                eoaException.code = Vo.SERVER_ERROR;
                eoaException.description = "错误的流程接口配置,流程编号=>"+requestDto.getWorkflowId()+"接口=>CheckOverUsed";
                throw eoaException;
            }
        }
        List<Integer> ysxxIds = requestDto.getFormOutDto().getDetailsValues(ysxxColumnId).stream().map(o -> Integer.parseInt(o.toString())).toList();
        StringBuilder in = new StringBuilder("(").append(ysxxIds.get(0));
        for (int i = 1; i < ysxxIds.size(); i++) {
            in.append(",").append(ysxxIds.get(i));
        }
        in.append(")");
        List<Integer> sfczs = jdbcTemplate.queryForList("select sfcz from form_table_2_dt_1 where detailDataId in " + in, Integer.class);
        return sfczs.contains(0);
    }

    @Override
    public String getFailureReason() {
        return "预算使用未超支";
    }
}
