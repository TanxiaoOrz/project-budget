package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.exception.ParameterException;

/**
 * @Author: 张骏山
 * @Date: 2023/12/29 21:13
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: RequestIn
 * @Description: 请求操作传入类
 * @Version: 1.0
 */
public class RequestIn implements CheckParameter<RequestDto> {
    Long requestId;
    FormIn form;

    @Override
    public void checkSelf() throws ParameterException {
        if (requestId == null) {
            throw new ParameterException("requestId","","缺少请求编号");
        }
        if (form == null) {
            throw new ParameterException("form","","缺少表单数据");
        }
        form.checkSelf();
    }

    /**
     *
     * @param dataId 无意义传入null即可
     * @return 待组装的requestDto对象
     * @throws ParameterException check出错
     */
    @Override
    public RequestDto toEntity(Long dataId) throws ParameterException {
        checkSelf();
        RequestDto requestDto = new RequestDto();
        return requestDto.setRequestId(requestId);
    }
}
