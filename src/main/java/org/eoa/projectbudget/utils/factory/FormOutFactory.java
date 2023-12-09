package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.mapper.HumanViewMapper;
import org.eoa.projectbudget.vo.out.FormOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/10 0:57
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: FormOutFactory
 * @Description: 表单数据输出类构造工厂
 * @Version: 1.0
 */

@Component
public class FormOutFactory implements OutFactory<FormOutDto,FormOut>{

    @Autowired
    HumanViewMapper humanViewMapper;

    @Override
    public FormOut out(FormOutDto dto) {
        if (dto == null) {
            return null;
        }
        FormOut formOut = new FormOut(dto)
                .setCreatorName(humanViewMapper.selectById(dto.getCreator()).getName());

        dto.getGroups().forEach(group -> {
            HashMap<String, FormOut.ColumnSimple> columns = new HashMap<>();
            HashMap<String, Object> values = new HashMap<>();
            group.getColumns().forEach((column, value) -> {
                String columnDataName = column.getColumnDataName();
                columns.put(columnDataName, new FormOut.ColumnSimple(column.getColumnType(),column.getColumnTypeDescription()));
                values.put(columnDataName,value);
            });

            formOut.addGroup(group.getGroupId(),
                    group.getGroupName(),
                    columns,
                    values);
        });

        dto.getDetails().forEach(detail -> {
            HashMap<String, FormOut.ColumnSimple> columns = new HashMap<>();
            detail.getDetailColumns().forEach(column ->
                    columns.put(column.getColumnDataName(),new FormOut.ColumnSimple(column.getColumnType(), column.getColumnTypeDescription())));
            formOut.addDetail(detail.getDetailId(),detail.getDetailName(), columns,detail.getDetailValuesList());
        });
        return formOut;
    }


    @Override
    public List<FormOut> outs(List<? extends FormOutDto> formOutDtos) {
        if (formOutDtos == null) {
            return null;
        }
        return formOutDtos.stream().map(this::out).toList();
    }
}
