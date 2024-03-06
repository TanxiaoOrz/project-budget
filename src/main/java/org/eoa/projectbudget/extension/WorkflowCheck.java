package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2023/12/30 19:21
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: Action
 * @Description: 流程开发检查接口
 * @Version: 1.0
 */
public interface WorkflowCheck {

    /**
     * 流程action自定义开发实现方法,检查是否允许通过
     * requestDto 的leaveNode/arriveNode/passRoute禁止调用,可能会造成无法结束的循环调用
     * @param requestDto 提交时的传入数据和流程涉及到的其它数据,注意获取数据时可能会在之前的操作中已修改未及时更新,表单数据均视为提交时数据,获取最新数据请从数据库中重新寻找
     * @param jdbcTemplate 数据库操作对象
     * @throws EoaException 若想要阻止流程提交请抛出异常
     */
    boolean check(RequestDto requestDto, JdbcTemplate jdbcTemplate);

    /**
     * 获取失败原因
     * @return 失败原因字符串
     */
    String getFailureReason();
}
