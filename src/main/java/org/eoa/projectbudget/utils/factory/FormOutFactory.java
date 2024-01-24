package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.mapper.HumanViewMapper;
import org.eoa.projectbudget.vo.out.FormOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/10 0:57
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: FormOutFactory
 * @Description: 表单数据输出类构造工厂
 * @Version: 1.1
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
        HumanResourceView humanResourceView = humanViewMapper.selectById(dto.getCreator());
        FormOut formOut = new FormOut(dto)
                .setCreatorName(humanResourceView==null?"":humanResourceView.getName());

        dto.getGroups().forEach(group -> {
            List< FormOut.ColumnSimple> columns = new ArrayList<>();
            HashMap<String, Object> values = new HashMap<>();
            group.getColumns().forEach((column, value) -> {
                String columnDataName = column.getColumnDataName();
                columns.add(new FormOut.ColumnSimple(column.getColumnType(),column.getColumnTypeDescription(),column.getColumnViewName(), column.getColumnId(),columnDataName));
                values.put(columnDataName,value);
            });

            formOut.addGroup(group.getGroupId(),
                    group.getGroupName(),
                    columns,
                    values);
        });
        dto.getGroups().sort(Comparator.comparingInt(FormOutDto.Group::getGroupId));
        if (!dto.getVirtual())
            dto.getDetails().forEach(detail -> {
                List< FormOut.ColumnSimple> columns = new ArrayList<>();
                detail.getDetailColumns().forEach(column ->
                        columns.add(new FormOut.ColumnSimple(column.getColumnType(), column.getColumnTypeDescription(), column.getColumnViewName(), column.getColumnId(),column.getColumnDataName())));
                formOut.addDetail(detail.getDetailId(),detail.getDetailName(), columns,detail.getDetailValuesList());
            });

        formOut.getDetails().sort(Comparator.comparingInt(FormOut.Detail::getDetailId));
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
