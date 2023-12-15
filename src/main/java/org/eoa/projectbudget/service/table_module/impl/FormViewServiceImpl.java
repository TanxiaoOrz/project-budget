package org.eoa.projectbudget.service.table_module.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.ColumnView;
import org.eoa.projectbudget.entity.TableView;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ColumnViewMapper;
import org.eoa.projectbudget.mapper.FormDMLMapper;
import org.eoa.projectbudget.mapper.TableViewMapper;
import org.eoa.projectbudget.service.table_module.FormService;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.utils.FilterFormUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:23
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: FormViewServiceImpl
 * @Description: 表单模块自定义视图前端操作处理业务类
 * @Version 1.0
 */

@Service
public class FormViewServiceImpl implements FormService {
    @Autowired
    TableViewMapper tableMapper;
    @Autowired
    ColumnViewMapper columnMapper;
    @Autowired
    FormDMLMapper formDMLMapper;

    private final Logger log = LoggerFactory.getLogger("TableModule");

    @Override
    public FormOutDto getFormOne(Long tableId, Long dataId, Long userId) throws EoaException {
        log.info("用户=>{}读取虚拟视图数据,表单编号=>{},数据编号=>{}",userId,tableId,dataId);
        TableView table = tableMapper.selectById(tableId);
        if (table == null) {
            log.error("不存在该虚拟视图");
            throw new ParameterException("tableId",tableId.toString(),"不能存在该编号视图");
        }
        List<ColumnView> mainColumns = columnMapper.selectList(
                new QueryWrapper<ColumnView>().eq("tableNo", tableId)
                        .orderByAsc("columnViewNo"));
        //视图不存在明细表单,直接使用,实体表需要过滤
        FormOutDto formOutDto = consistForm(dataId, table,mainColumns);
        if (formOutDto == null) {
            throw new ParameterException("dataId",dataId.toString(),"在"+table.getTableDataName()+"不能存在该编号数据");
        }
        log.info("查找到一条数据,数据编号=>{}",dataId);
        return formOutDto;
    }

    @Override
    public List<FormOutDto> getFormSort(Long tableId, FilterFormUtils filter, Long userId) throws ParameterException {
        log.info("用户=>{}读取视图,表单编号=>{}",userId,tableId);
        TableView table = tableMapper.selectById(tableId);
        if (table == null) {
            throw new ParameterException("tableId",tableId.toString(),"不能存在该编号视图");
        }
        List<ColumnView> mainColumns = columnMapper.selectList(
                new QueryWrapper<ColumnView>().eq("tableNo", tableId)
                        .orderByAsc("columnViewNo"));
        //视图不存在明细表单,且不存在权限控制部分,直接使用,实体表需要过滤
        List<Long> ids = filter.getIds(formDMLMapper,mainColumns,table.getTableDataName());
        List<FormOutDto> formOutDtos = ids.stream().map(id->consistForm(id,table,mainColumns)).toList();
        log.info("查找到{}条数据,数据编号=>{}",ids.size(),ids);
        return formOutDtos;
    }

    private FormOutDto consistForm(Long dataId, TableView table, List<ColumnView> mainColumns) {
        FormOutDto formOutDto = new FormOutDto().setTable(table);
        formOutDto.setDataId(dataId)
                .setTableId(table.getTableId())
                .setVirtual(true);

        Integer groupCount = table.getGroupCount();
        Map<String, Object> mainValues = formDMLMapper.getMainFormById(dataId, table.getTableDataName());
        if (mainValues == null) {
            return null;
        }
        String[] groupNames = DataProcessUtils.splitStringArray(table.getGroupName());
        for (int i = 0; i < groupCount; i++) {
            int groupNo = i + 1;
            HashMap<Column, Object> groupValue = new HashMap<>();
            mainColumns.stream().filter(columnEntity -> columnEntity.getColumnGroupNo().equals(groupNo)).
                    forEach(column->groupValue.put(column,mainValues.get(column.getColumnDataName())));
            formOutDto.addGroup(groupNo, groupNames[i],
                    groupValue);
        }
        return formOutDto;
    }

    @Override
    public Integer updateForm(FormInDto form, Long userId) throws EoaException {
        log.error("用户=>{}违规尝试修改视图form=>{}",userId, form);
        throw new AuthorityException(userId,"修改视图form","系统禁止行为");
    }

    @Override
    public Long createForm(FormInDto form, Long userId) throws EoaException {
        log.error("用户=>{}违规尝试创建视图form=>{}",userId, form);
        throw new AuthorityException(userId,"创建视图form","系统禁止行为");
    }

    @Override
    public Integer deleteForm(Long tableId,Long dataId, Long userId) throws EoaException {
        log.error("用户=>{}违规尝试删除视图formDataId=>{}",userId,tableId);
        throw new AuthorityException(userId,"删除视图form","系统禁止行为");
    }

    @Override
    public Boolean checkAuthority(Long tableId, Long dataId, int type, HumanDto user) throws EoaException {
        return false;
    }
}
