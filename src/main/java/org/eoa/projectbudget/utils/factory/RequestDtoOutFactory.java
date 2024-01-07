package org.eoa.projectbudget.utils.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.dto.RequestDto;
import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.WorkflowNode;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.vo.out.RequestDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    ColumnEntityMapper columnEntityMapper;


    @Override
    public RequestDtoOut out(RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        RequestDtoOut requestDtoOut = new RequestDtoOut();

        WorkflowNode currentNode = requestDto.getCurrentNode();
        String tableModifyAuthority = currentNode.getTableModifyAuthority();
        try {

            if (tableModifyAuthority != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<Long, Boolean> map = objectMapper.readValue(tableModifyAuthority, new TypeReference<Map<Long, Boolean>>() {
                });
                List<ColumnEntity> columnEntities = columnEntityMapper.selectList(new QueryWrapper<ColumnEntity>().eq("tableNo", requestDto.getWorkflow().getTableId()));
                Map<String, Boolean> stringMap = new HashMap<>();
                map.keySet().forEach(key -> {
                    Optional<ColumnEntity> column = columnEntities.stream().filter(columnEntity -> columnEntity.getColumnId().equals(key)).findFirst();
                    column.ifPresent(columnEntity -> stringMap.put(columnEntity.getColumnDataName(), map.get(key)));
                });
                currentNode.setTableModifyAuthority(objectMapper.writeValueAsString(stringMap));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataException("node", currentNode.getDataId().toString(), "TableModifyAuthority", tableModifyAuthority, "JSON转换错误");

        }

        return requestDtoOut.setFormOut(formOutFactory.out(requestDto.getFormOutDto()))
                .setCurrentNode(currentNode)
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
