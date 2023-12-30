package org.eoa.projectbudget.extension;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.EoaException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: 张骏山
 * @Date: 2023/12/30 19:22
 * @PackageName: org.eoa.projectbudget.extension
 * @ClassName: WorkflowAction
 * @Description: 流程开发执行接口
 * @Version: 1.0
 */
public interface WorkflowAction {

    /**
     * 流程action自定义开发实现方法,流转过程中数据操作
     * @param requestDto 提交时的传入数据和流程涉及到的其它数据,注意获取数据时可能会在之前的操作中已修改未及时更新,表单数据均视为提交时数据,获取最新数据请从数据库中重新寻找
     * @param jdbcTemplate 数据库操作对象
     * @throws EoaException 若想要阻止流程提交请抛出异常
     */
    void action(RequestDto requestDto, JdbcTemplate jdbcTemplate) throws EoaException;

}
