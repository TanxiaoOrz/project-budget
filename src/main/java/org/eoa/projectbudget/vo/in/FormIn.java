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
 * @Version: 1.1
 **/
public class FormIn implements CheckParameter<FormInDto> {
    Long tableId;
    Map<String,Object> mains;
    List<List<Map<String,Object>>> detailValueMapLists;

    @Override
    public void checkSelf() throws ParameterException {
        if (tableId == null) {
            throw new ParameterException("tableId","","缺少表单id");
        }
        if (mains == null) {
            throw new ParameterException("mains","","缺少主表数据键值对");
        }
        if (detailValueMapLists == null) {
            throw new ParameterException("details","","缺少明细数据键值对数组");
        }
    }

    @Override
    public FormInDto toEntity(Long dataId) throws ParameterException {
        FormInDto formInDto = new FormInDto();
        formInDto.setDataId(dataId)
                .setMains(mains)
                .setDetailValueMapLists(detailValueMapLists)
                .setTableId(tableId);
        return formInDto;
    }

    public Long getTableId() {
        return tableId;
    }

    public FormIn setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public Map<String, Object> getMains() {
        return mains;
    }

    public FormIn setMains(Map<String, Object> mains) {
        this.mains = mains;
        return this;
    }

    public List<List<Map<String, Object>>> getDetailValueMapLists() {
        return detailValueMapLists;
    }

    public FormIn setDetailValueMapLists(List<List<Map<String, Object>>> detailValueMapLists) {
        this.detailValueMapLists = detailValueMapLists;
        return this;
    }
}
