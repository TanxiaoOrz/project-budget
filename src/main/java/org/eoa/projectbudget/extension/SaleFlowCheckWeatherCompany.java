package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2024/2/18 16:34
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: SaleFlowCheckWeatherCompany
 * @Description: 销售合同审批是否公司领导审批
 * @Version: 1.0
 **/

@SuppressWarnings("unused")
public class SaleFlowCheckWeatherCompany implements WorkflowCheck{

    private static final long JE_ID = 15L;

    private static final long BZ_ID = 16L;

    private static final int ALLOWED_BZ = 0; //CNY
    private static final int ALLOWED_JE = 100*1000;

    private final Logger log = LoggerFactory.getLogger("BudgetModule");

    /**
     * 若金额小于要求金额并且币种属于规定币种则无需公司领导审批
     * @param requestDto 提交时的传入数据和流程涉及到的其它数据,注意获取数据时可能会在之前的操作中已修改未及时更新,表单数据均视为提交时数据,获取最新数据请从数据库中重新寻找
     * @param jdbcTemplate 数据库操作对象
     * @return 判读结果
     */
    @Override
    public boolean check(RequestDto requestDto, JdbcTemplate jdbcTemplate) {
        Integer bz = (Integer) requestDto.getFormOutDto().getMainValue(BZ_ID);
        Integer je = (Integer) requestDto.getFormOutDto().getMainValue(JE_ID);
        boolean con =  (je <= ALLOWED_JE && bz == ALLOWED_BZ);
        log.info("流程=>{}判断是否公司领导审批,币种=>{},金额=>{},结果=>{}",requestDto.getRequestId(),bz,je,con);
        return con;
    }
}
