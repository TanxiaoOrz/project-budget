package org.eoa.projectbudget.service.workflow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.RequestBacklogView;
import org.eoa.projectbudget.entity.RequestDoneView;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.exception.EoaException;

import java.util.List;
import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/12/27 11:31
 * @PackageName: org.eoa.projectbudget.service.workflow
 * @ClassName: RequestService
 * @Description: 流程模块前端操作接口
 * @Version: 1.3
 **/
public interface RequestService {

    /**
     * 获取所有请求
     * @param wrapper 条件构造器
     * @param userId 操作人
     * @return 请求数组
     */
    List<Request> getRequests(QueryWrapper<Request> wrapper, Long userId);

//    /**
//     * 获取请求监控页面
//     * @param requestId 请求编号
//     * @param userId 操作人
//     * @return 请求展示数据
//     */
//    RequestDto getRequestControl(Long requestId, Long userId);

    /**
     * 删除请求和相关数据
     * @param requestId 请求编号
     * @param userId 操作人
     * @return 结果数字
     */
    Map<Long,Long> dropRequest(Long requestId, Long userId);

    /**
     * 请求流转
     * @param requestDto 请求数据
     * @param nodeId 目标节点编号
     * @param receivers 强制指定接收人,为空数组则为节点默认接收人
     * @param userId 操作人
     * @return 结果数字
     */
    Integer transferRequest(RequestDto requestDto, Long nodeId, List<Long> receivers, Long userId);


//    /**
//     * 获取可创建工作流清单列表
//     * @param wrapper 条件构造器
//     * @param user 操作人
//     * @return 工作流数组
//     */
//    List<Workflow> getCreateAbleList(QueryWrapper<Workflow> wrapper, Long user);

    /**
     * 获取待办请求
     * @param wrapper 条件构造器
     * @param user 操作人
     * @return 待办请求数组
     */
    List<RequestBacklogView> getBackLogRequest(QueryWrapper<RequestBacklogView> wrapper, Long user);

    /**
     * 获取已办请求
     * @param wrapper 条件构造器
     * @param user 操作人
     * @return 已办请求数组
     */
    List<RequestDoneView> getHaveDone(QueryWrapper<RequestDoneView> wrapper, Long user);

    /**
     * 获取自己发起的请求数组
     * @param wrapper 条件构造器
     * @param user 操作人
     * @return 请求数组
     */
    List<Request> getRequestsSelf(QueryWrapper<Request> wrapper, Long user);


    /**
     * 获取流程数据
     * @param requestId 流程编号
     * @param user 操作人
     * @return 流程数据
     */
    RequestDto getRequest(Long requestId, Long user);

    /**
     * 创建请求
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 流程编号
     */
    Long createRequest(RequestDto request, Long user);

    /**
     * 退回请求
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 剩余未操作人员
     */
    List<Long> refuseRequest(RequestDto request, Long user);

    /**
     * 提交流程
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 剩余未操作人员
     */
    List<Long>  submitRequest(RequestDto request, Long user);

    /**
     * 批准流程
     * @param request 请求传入结构体
     * @param user 操作人
     * @return 剩余未操作人员
     */
    List<Long>  admitRequest(RequestDto request, Long user);


    /**
     * 检查查看权限
     * @param requestId 请求
     * @param user 操作人
     * @return 展示对应的nodeId布局,空代表没有权限
     */
    Long getViewNode(Long requestId, Long user);

    /**
     * 检查并组装requestDto
     * @param requestDto 待检查的流程请求
     * @param user 操作人
     */
    RequestDto checkAndConsist(RequestDto requestDto, Long user) throws EoaException;

    WorkflowNode getCreateNode(Long workflowId);
}
