package org.eoa.projectbudget.service.workflow.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.entity.Request;
import org.eoa.projectbudget.entity.RequestBacklogView;
import org.eoa.projectbudget.entity.RequestDoneView;
import org.eoa.projectbudget.entity.Workflow;
import org.eoa.projectbudget.service.workflow.WorkflowFrontService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/28 9:09
 * @PackageName: org.eoa.projectbudget.service.workflow.impl
 * @ClassName: WorkflowFrontServiceImpl
 * @Description: 工作流前端业务类
 * @Version: 1.0
 **/

@Service
public class WorkflowFrontServiceImpl implements WorkflowFrontService {

    private final Logger log = LoggerFactory.getLogger("WorkflowModule");

    @Override
    public List<Workflow> getCreateAbleList(QueryWrapper<RequestBacklogView> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public List<RequestBacklogView> getBackLogRequest(QueryWrapper<RequestBacklogView> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public List<RequestDoneView> getHaveDone(QueryWrapper<RequestDoneView> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public List<Request> getRequestsSelf(QueryWrapper<Request> wrapper, HumanDto user) {
        return null;
    }

    @Override
    public RequestDto getRequest(Long requestId, HumanDto user) {
        return null;
    }

    @Override
    public Long createRequest(RequestDto request, HumanDto user) {
        return null;
    }

    @Override
    public Integer refuseRequest(RequestDto request, HumanDto user) {
        return null;
    }

    @Override
    public Integer submitRequest(RequestDto request, HumanDto user) {
        return null;
    }

    @Override
    public Integer admitRequest(RequestDto request, HumanDto user) {
        return null;
    }

    @Override
    public Boolean checkAuthority(RequestDto request, Integer method, HumanDto user) {
        return null;
    }

    @Override
    public Boolean checkViewAuthority(Request requestId, HumanDto user) {
        return null;
    }
}
