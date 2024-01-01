package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.vo.out.RequestDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/1 22:23
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: RequestDtoOutFactory
 * @Description: 具体流程输出类构造工厂
 * @Version: 1.0
 */

@Component
public class RequestDtoOutFactory implements OutFactory<RequestDto, RequestDtoOut> {

    @Autowired
    RequestOutFactory requestOutFactory;
    @Autowired
    FormOutFactory formOutFactory;


    @Override
    public RequestDtoOut out(RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        RequestDtoOut requestDtoOut = new RequestDtoOut();
        return requestDtoOut.setFormOut(formOutFactory.out(requestDto.getFormOutDto()))
                .setCurrentNode(requestDto.getCurrentNode())
                .setWorkflow(requestDto.getWorkflow())
                .setRequestOut(requestOutFactory.out(requestDto.getRequest()));
    }

    @Override
    public List<RequestDtoOut> outs(List<? extends RequestDto> requestDtos) {
        if (requestDtos == null) {
            return null;
        }
        return requestDtos.stream().map(this::out).toList();
    }
}
