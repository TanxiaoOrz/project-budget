package org.eoa.projectbudget.service.table_module.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.Form;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.AuthorityException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.ColumnViewMapper;
import org.eoa.projectbudget.mapper.FormDMLMapper;
import org.eoa.projectbudget.mapper.TableViewMapper;
import org.eoa.projectbudget.service.table_module.FormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:23
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: FormViewServiceImpl
 * @Description: 表单模块自定义视图前端操作处理业务类
 * @Version 1.0
 */

@Service
public class FormViewServiceImpl implements FormService<ColumnView, TableView> {
    @Autowired
    TableViewMapper tableMapper;
    @Autowired
    ColumnViewMapper columnMapper;
    @Autowired
    FormDMLMapper formDMLMapper;

    private final Logger log = LoggerFactory.getLogger("TableModule");

    @Override
    public Form<ColumnView, TableView> getFormOne(Long tableId, Long dataId, Long userId) throws EoaException {
        log.info("用户=>{}读取视图,表单编号=>{},数据编号=>{}",userId,tableId,dataId);
        TableView table = tableMapper.selectById(tableId);
        if (table == null) {
            throw new ParameterException("tableId",tableId.toString(),"不能存在该编号视图");
        }
        Form<ColumnView, TableView> form = consistForm(tableId, dataId, table);
        if (form == null) {
            throw new ParameterException("dataId",dataId.toString(),"在"+table.getTableDataName()+"不能存在该编号数据");
        }
        log.info("查找到一条数据,数据编号=>{}",dataId);
        return form;
    }

    private Form<ColumnView, TableView> consistForm(Long tableId, Long dataId, TableView table) {
        Form<ColumnView, TableView> form = new Form<ColumnView, TableView>().setTable(table);

        List<ColumnView> mainColumns = columnMapper.selectList(
                new QueryWrapper<ColumnView>().eq("tableNo", tableId)
                        .orderByAsc("columnViewNo"));
        //视图不存在明细表单,直接使用,实体表需要过滤

        Integer groupCount = table.getGroupCount();
        Map<String, Object> mainValues = formDMLMapper.getMainFormById(dataId, table.getTableDataName());
        if (mainValues == null) {
            return null;
        }
        String[] groupNames = table.getGroupName().split(",");
        for (int i = 0; i < groupCount; i++) {
            int groupNo = i;
            Map<ColumnView, Object> columnMap = mainColumns.stream().filter(column -> column.getColumnGroupNo().equals(groupNo))
                    .collect(Collectors.toMap(column -> column, column -> mainValues.get(column.getColumnDataName())));
            if (columnMap.isEmpty()) {
                continue;
            }
            form.addGroup(groupNo,groupNames[groupNo],columnMap);
        }
        return form;
    }

    @Override
    public Integer updateForm(Form<ColumnView, TableView> form, Long userId) throws EoaException {
        log.error("用户=>{}违规尝试修改视图form=>{}",userId,form);
        throw new AuthorityException(userId,"修改视图form","系统禁止行为");
    }

    @Override
    public Integer createForm(Form<ColumnView, TableView> form, Long userId) throws EoaException {
        log.error("用户=>{}违规尝试创建视图form=>{}",userId,form);
        throw new AuthorityException(userId,"创建视图form","系统禁止行为");
    }

    @Override
    public Integer deleteForm(Long tableId, Long dataId, Long userId) throws EoaException {
        log.error("用户=>{}违规尝试删除视图formDataId=>{}",userId,dataId);
        throw new AuthorityException(userId,"删除视图form","系统禁止行为");
    }

    @Override
    public List<Form<ColumnView, TableView>> getFormSort(Long tableId, Map<Long, Object> orders, Long userId) {
        return null;
    }
}
