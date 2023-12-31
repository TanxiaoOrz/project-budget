package org.eoa.projectbudget.service.workflow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.RequestBacklogView;
import org.eoa.projectbudget.entity.RequestDoneView;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.exception.EoaException;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:31
 * @PackageName: org.eoa.projectbudget.service.workflow
 * @ClassName: WorkflowFrontService
 * @Description: 流程模块前端操作接口
 * @Version: 1.3
 **/
public interface WorkflowFrontService {


    /**
     * 获取可创建工作流清单列表
     * @param wrapper 条件构造器
     * @param user 操作人
     * @return 工作流数组
     */
    List<Workflow> getCreateAbleList(QueryWrapper<RequestBacklogView> wrapper, HumanDto user);

    /**
     * 获取待办请求
     * @param wrapper 条件构造器
     * @param user 操作人
     * @return 待办请求数组
     */
    List<RequestBacklogView> getBackLogRequest(QueryWrapper<RequestBacklogView> wrapper, HumanDto user);

    /**
     * 获取已办请求
     * @param wrapper 条件构造器
     * @param user 操作人
     * @return 已办请求数组
     */
    List<RequestDoneView> getHaveDone(QueryWrapper<RequestDoneView> wrapper, HumanDto user);

    /**
     * 获取自己发起的请求数组
     * @param wrapper 条件构造器
     * @param user 操作人
     * @return 请求数组
     */
    List<Request> getRequestsSelf(QueryWrapper<Request> wrapper, HumanDto user);


    /**
     * 获取流程数据
     * @param requestId 流程编号
     * @param user 操作人
     * @return 流程数据
     */
    RequestDto getRequest(Long requestId, HumanDto user);

    /**
     * 创建请求
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 流程编号
     */
    Long createRequest(RequestDto request, HumanDto user);

    /**
     * 退回请求
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 剩余未操作人员
     */
    List<Long> refuseRequest(RequestDto request, HumanDto user);

    /**
     * 提交流程
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 剩余未操作人员
     */
    List<Long>  submitRequest(RequestDto request, HumanDto user);

    /**
     * 批准流程
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 剩余未操作人员
     */
    List<Long>  admitRequest(RequestDto request, HumanDto user);


    /**
     * 检查查看权限
     * @param requestId 请求
     * @param user 操作人
     * @return 展示对应的nodeId布局,空代表没有权限
     */
    Long getViewNode(Long requestId, HumanDto user);

    /**
     * 检查并组装requestDto
     * @param requestDto 待检查的流程请求
     * @param user 操作人
     */
    RequestDto checkAndConsist(RequestDto requestDto, HumanDto user) throws EoaException;
}
