package org.eoa.projectbudget.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.ColumnEntity;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.mapper.ColumnEntityMapper;
import org.eoa.projectbudget.mapper.TableEntityMapper;

import java.util.*;

/**
 * @Author: 张骏山
 * @Date: 2023/12/8 16:16
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: FormInDto
 * @Description: 表单数据传入工具类
 * @Version: 1.0
 **/
public class FormInDto {

    Long dataId;
    Long tableId;
    Map<String,Object> mains;
    List<List<Map<String,Object>>> detailValueMapLists;

    TableEntity table;
    List<ColumnEntity> mainColumns;
    List<List<ColumnEntity>> detailColumns;

    public FormInDto() {
    }

    public FormInDto consist(TableEntityMapper tableMapper, ColumnEntityMapper columnMapper) {
        table = tableMapper.selectById(tableId);
        List<ColumnEntity> columns = columnMapper.selectList(new QueryWrapper<ColumnEntity>().eq("tableNo", tableId));
        mainColumns = columns.stream().filter(columnEntity -> columnEntity.getColumnDetailNo()==-1).toList();

        int detailCounts = table.getDetailCount();
        detailColumns = new ArrayList<>(detailCounts);
        for (int i = 1; i <= detailCounts; i++) {
            detailColumns.add(columns.stream().filter(columnEntity -> columnEntity.getColumnDetailNo()==detailCounts).toList());
        }
        return this;
    }

    public Map<String,Object> getMainMap() {
        Map<String, Object> collect = new HashMap<>();
        mainColumns.forEach(column->collect.put(column.getColumnDataName(), mains.get(column.getColumnDataName())));
        collect.put("lastEditTime",new Date());
        return collect;
    }

    public List<List<Map<String,Object>>> getDetailMapLists() {
        ArrayList<List<Map<String, Object>>> maps = new ArrayList<>();
        Integer detailCount = table.getDetailCount();
        for (int i = 0; i < detailCount; i++) {
            ArrayList<Map<String, Object>> detailMapList = new ArrayList<>();
            List<Map<String,Object>> details = detailValueMapLists.get(i);
            for (Map<String,Object> value:
                    details) {
                Map<String, Object> detailMap = new HashMap<>();
                detailColumns.get(i).forEach(column->detailMap.put(column.getColumnDataName(),value.get(column.getColumnDataName())));
                Object detailDataId = details.get(i).get("detailDataId");
                if (detailDataId != null) {
                    detailMap.put("detailDataId",detailDataId);
                }
                detailMapList.add(detailMap);
            }

            maps.add(detailMapList);
        }
        return maps;
    }

    public Long getDataId() {
        return dataId;
    }

    public FormInDto setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public FormInDto setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public Map<String, Object> getMains() {
        return mains;
    }

    public FormInDto setMains(Map<String, Object> mains) {
        this.mains = mains;
        return this;
    }

    public List<List<Map<String, Object>>> getDetailValueMapLists() {
        return detailValueMapLists;
    }

    public FormInDto setDetailValueMapLists(List<List<Map<String, Object>>> detailValueMapLists) {
        this.detailValueMapLists = detailValueMapLists;
        return this;
    }

    public TableEntity getTable() {
        return table;
    }

    public FormInDto setTable(TableEntity table) {
        this.table = table;
        return this;
    }

    public List<ColumnEntity> getMainColumns() {
        return mainColumns;
    }

    public FormInDto setMainColumns(List<ColumnEntity> mainColumns) {
        this.mainColumns = mainColumns;
        return this;
    }

    public List<List<ColumnEntity>> getDetailColumns() {
        return detailColumns;
    }

    public FormInDto setDetailColumns(List<List<ColumnEntity>> detailColumns) {
        this.detailColumns = detailColumns;
        return this;
    }
}
