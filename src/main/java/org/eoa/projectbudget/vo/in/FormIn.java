package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;
import java.util.Map;

/**
 * @Author: 张骏山
 * @Date: 2023/12/8 16:18
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: FormIn
 * @Description: 表单数据传入实体
 * @Version: 1.0
 **/
public class FormIn implements CheckParameter<FormInDto> {
    Boolean virtual;
    Long tableId;
    Map<String,Object> mains;
    List<Map<String,Object>> details;

    @Override
    public void checkSelf() throws ParameterException {
        if (virtual == null) {
            throw new ParameterException("virtual","","缺少是否虚拟视图");
        }
    }

    @Override
    public FormInDto toEntity(Long dataId) throws ParameterException {
        return null;
    }
}
