package org.eoa.projectbudget.service.table_module.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.FormInDto;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.constraint.Constraint;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.exception.ServerException;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.FormDMLMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.service.table_module.FormService;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.utils.FilterFormUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author 张骏山
 * @Date 2023/10/23 16:15
 * @PackageName: org.eoa.projectbudget.service.table_module
 * @ClassName: FromEntityServiceImpl
 * @Description: 表单模块自定义表单前端操作处理业务类
 * @Version 1.1
 */
@Service
public class FromEntityServiceImpl implements FormService {
    @Autowired
    TableEntityMapper tableMapper;
    @Autowired
    ColumnEntityMapper columnMapper;
    @Autowired
    FormDMLMapper formDMLMapper;
    @Autowired
    OrganizationService organizationService;

    public final String DETAIL_LAG = "_dt_";

    private final Logger log = LoggerFactory.getLogger("TableModule");

    @Override
    public FormOutDto getFormOne(Long tableId, Long dataId, Long userId) throws ParameterException {
        log.info("用户=>{}读取实体表单,表单编号=>{},数据编号=>{}",userId,tableId,dataId);
        TableEntity tableEntity = tableMapper.selectById(tableId);
        if (tableEntity == null) {
            log.error("不存在该实体表单");
            throw new ParameterException("tableId",tableId.toString(),"不能存在该编号视图");
        }
        List<ColumnEntity> columns = columnMapper.selectList(new QueryWrapper<ColumnEntity>().eq("tableNo", tableId)
                .orderByAsc("columnViewNo"));
        FormOutDto form = consistForm(dataId, tableEntity, columns);
        if (form == null) {
            throw new ParameterException("dataId",dataId.toString(),"在"+tableEntity.getTableDataName()+"不能存在该编号数据");
        }
        return form;
    }

    private FormOutDto consistForm(Long dataId, TableEntity table, List<ColumnEntity> columns) {
        FormOutDto form = new FormOutDto();
        form.setTable(table);
        form.setTableId(table.getTableId());

        Integer groupCount = table.getGroupCount();
        Map<String,Object> mainValues;
        if (dataId == null) {
            mainValues = new HashMap<>();
        } else {
            mainValues = formDMLMapper.getMainFormById(dataId, table.getTableDataName());
        }

        if (mainValues == null) {
            return null;
        }

        form.setCreator((Long) mainValues.get("creator"))
                .setDataId(dataId==null?0L:dataId)
                .setRequestId((Long) mainValues.get("requestId"))
                .setRequestStatus((Integer) mainValues.get("requestStatus"))
                .setCreateTime((Date) mainValues.get("createTime"))
                .setDeleteAuthority((String) mainValues.get("deleteAuthority"))
                .setViewAuthority((String) mainValues.get("viewAuthority"))
                .setEditAuthority((String) mainValues.get("editAuthority"))
                .setLatestEditTime((Date) mainValues.get("lastEditTime"))
                .setVirtual(false);

        String[] groupNames = DataProcessUtils.splitStringArray(table.getGroupName());
        List<ColumnEntity> mainColumns = columns.stream().filter(columnEntity -> columnEntity.getColumnDetailNo() == -1).toList();
        for (int i = 0; i < groupCount; i++) {
            int groupNo = i + 1;

            HashMap<Column, Object> groupValue = new HashMap<>();
            mainColumns.stream().filter(columnEntity -> columnEntity.getColumnGroupNo().equals(groupNo)).
                    forEach(column->groupValue.put(column,mainValues.get(column.getColumnDataName())));
            form.addGroup(groupNo, groupNames[i],
                    groupValue);
        }
        String[] detailNames = DataProcessUtils.splitStringArray(table.getDetailName());
        List<ColumnEntity> detailColumns = columns.stream().filter(columnEntity -> !columnEntity.getColumnDetailNo().equals(-1)).toList();
        for (int i = 0; i < table.getDetailCount(); i++) {
            int detailNo = i + 1;
            List<Map<String, Object>> detailValues = formDMLMapper.getDetailFormByDataId(dataId,table.getTableDataName()+DETAIL_LAG+ detailNo);
            form.addDetail(detailNo,detailNames[i],detailColumns.stream().filter(c -> c.getColumnDetailNo().equals(detailNo)).toList(),detailValues);
        }
        return form;
    }

    @Override
    @Transactional
    public Long createForm(FormInDto form, Long userId) {
        log.info("用户->{}尝试创建实体表单数据form->{}",userId,form);
        TableEntity table = form.getTable();
        String tableDataName = table.getTableDataName();
        Map<String, Object> mains = form.getMainMap();
        mains.put("creator",userId);
        mains.put("createTime", new Date());
        mains.put("deleteAuthority",table.getDefaultDelete());
        mains.put("viewAuthority",table.getDefaultShare());
        mains.put("editAuthority",table.getDefaultEdit());

        formDMLMapper.insertMainForm(mains, tableDataName);
        Long dataId = (Long) mains.get("dataId");

        List<List<Map<String, Object>>> detailMapLists = form.getDetailMapLists();
        for (int i = 0; i < table.getDetailCount(); i++) {  // 遍历每个明细表
            int detailNo = i + 1;
            List<Map<String, Object>> detailMapList = detailMapLists.get(i);
            for (Map<String, Object> detailMap
                    :detailMapList) {   //遍历一个明细表每一行
                detailMap.remove("detailDataId"); //获取并去除明细id
                String formDetailTableName = tableDataName + DETAIL_LAG + detailNo;
                if (detailMap.size()>0)
                    formDMLMapper.insertDetailForm(detailMap, formDetailTableName);
            }
        }
        return dataId;
    }

    @Override
    public Integer updateForm(FormInDto form, Long userId) {
        log.info("用户->{}尝试修改实体表单数据form->{}",userId,form);
        TableEntity table = form.getTable();
        Long dataId = form.getDataId();
        String tableDataName = table.getTableDataName();
        Integer integer = formDMLMapper.updateMainForm(form.getMains(), dataId, tableDataName);
        List<List<Map<String, Object>>> detailMapLists = form.getDetailMapLists();
        for (int i = 0; i < table.getDetailCount(); i++) {
            int detailNo = i + 1;
            String formDetailTableName = tableDataName + DETAIL_LAG + detailNo;
            List<Long> oldExist = formDMLMapper.getExistDetail(dataId, formDetailTableName);
            List<Map<String, Object>> detailMapList = detailMapLists.get(i);
            List<Long> newExist = new ArrayList<>(detailMapList.size());
            for (Map<String, Object> detailMap
                    :detailMapList) {
                Long detailDataId = (Long) detailMap.remove("detailDataId");    //获取并去除明细id
                if (detailDataId == null || oldExist.contains(detailDataId)) {
                    formDMLMapper.insertDetailForm(detailMap, formDetailTableName);
                } else {
                    formDMLMapper.updateDetailForm(detailMap, detailDataId, formDetailTableName);
                    newExist.add(detailDataId);
                }
            }
            oldExist.removeAll(newExist);
            oldExist.forEach(deleted -> formDMLMapper.deleteDetailForm(deleted,formDetailTableName));
        }

        return integer;
    }

    @Override
    public Integer deleteForm(Long tableId,Long dataId, Long userId) {
        log.info("用户->{}尝试修改删除表单数据tableId->{},dataId->{}",userId,tableId,dataId);
        TableEntity tableEntity = tableMapper.selectById(tableId);
        String tableDataName = tableEntity.getTableDataName();
        Integer delete = formDMLMapper.deleteMainForm(dataId, tableDataName);
        for (int i = 0; i < tableEntity.getDetailCount(); i++) {
            int detailNo = i + 1;
            String formDetailTableName = tableDataName + DETAIL_LAG + detailNo;
            formDMLMapper.deleteDetailFormByDataId(dataId,formDetailTableName);
        }
        return delete;
    }

    @Override
    public List<FormOutDto> getFormSort(Long tableId, FilterFormUtils filter, Long userId) throws ParameterException {

        log.info("用户=>{}读取视图,表单编号=>{}",userId,tableId);
        TableEntity table = tableMapper.selectById(tableId);
        if (table == null) {
            throw new ParameterException("tableId",tableId.toString(),"不能存在该编号视图");
        }
        List<ColumnEntity> columns = columnMapper.selectList(new QueryWrapper<ColumnEntity>().eq("tableNo", tableId)
                .orderByAsc("columnViewNo"));
        List<Long> ids = filter.getIds(formDMLMapper,columns.stream().filter(columnEntity -> columnEntity.getColumnDetailNo() == -1).toList(),table.getTableDataName());
        ArrayList<FormOutDto> formOutDtos = new ArrayList<>();
        ids.forEach(id ->consistForm(id,table,columns));
        log.info("查找到{}条数据,数据编号=>{}",ids.size(),ids);
        return formOutDtos;
    }

    @Override
    public Boolean checkAuthority(Long tableId, Long dataId, int type, HumanDto user) throws EoaException {
        switch (type) {
            case CREATE -> {
                TableEntity tableEntity = tableMapper.selectById(tableId);
                String defaultCreate = tableEntity.getDefaultCreate();
                Long creator = tableEntity.getCreator();
                return AuthorityUtils.checkAuthority(user,
                        organizationService.getHumanDto(creator,null),
                        AuthorityUtils.getConstraint(defaultCreate));
            }
            case EDIT -> {
                TableEntity tableEntity = tableMapper.selectById(tableId);
                Map<String, Object> mainFormById = formDMLMapper.getMainFormById(dataId, tableEntity.getTableDataName());
                String editAuthority = (String) mainFormById.get("editAuthority");
                Long creator = (Long) mainFormById.get("creator");
                Constraint constraint = AuthorityUtils.getConstraint(editAuthority);
                return AuthorityUtils.checkAuthority(user,
                        organizationService.getHumanDto(creator,null),
                        constraint)
                        ||
                        AuthorityUtils.checkTable(user,getFormOne(tableId,dataId,user.getDataId()), constraint);
            }
            case DELETE -> {
                TableEntity tableEntity = tableMapper.selectById(tableId);
                Map<String, Object> mainFormById = formDMLMapper.getMainFormById(dataId, tableEntity.getTableDataName());
                String deleteAuthority = (String) mainFormById.get("deleteAuthority");
                Long creator = (Long) mainFormById.get("creator");
                Constraint constraint = AuthorityUtils.getConstraint(deleteAuthority);
                return AuthorityUtils.checkAuthority(user,
                        organizationService.getHumanDto(creator,null),
                        constraint)
                        ||
                        AuthorityUtils.checkTable(user,getFormOne(tableId,dataId,user.getDataId()), constraint);
            }
            default ->
                throw new ServerException("错误的鉴权调用"+type);
        }
    }
}
