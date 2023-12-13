package org.eoa.projectbudget.vo.out;


import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.utils.DataProcessUtils;

import java.util.*;

/**
 * @Author 张骏山
 * @Date 2023/10/23 17:24
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: FormOut
 * @Description: 表单数据输出类
 * @Version 1.1
 */

public class FormOut {

    Long dataId;
    Long requestId;
    Long tableId;

    String tableName;
    Integer requestStatus;
    Long creator;
    Date createTime;
    String viewAuthority;
    String creatorName;
    Date latestEditTime;
    Boolean virtual;
    List<Group> groups;
    List<Detail> details;
    String title;

    public FormOut() {
    }

    public void toBrowser(Long columnId) {
        for (Group group:
             groups) {
            Optional<Map.Entry<String, ColumnSimple>> entry = group.columns.entrySet().stream().filter(set -> set.getValue().columnId.equals(columnId)).findFirst();
            if (entry.isPresent()) {
                title = group.values.get(entry.get().getKey()).toString();
                break;
            }
        }
        title = DataProcessUtils.nullToString(title);

        this.dataId = null;
        this.requestId = null;
        this.tableId = null;
        this.requestStatus = null;
        this.creator = null;
        this.createTime = null;
        this.latestEditTime = null;
        this.virtual = null;
        this.viewAuthority = null;
        this.tableName = null;
        groups = null;
        details = null;
    }

    public FormOut(FormOutDto form) {
        this.dataId = form.getDataId();
        this.requestId = form.getRequestId();
        this.tableId = form.getTableId();
        this.requestStatus = form.getRequestStatus();
        this.creator = form.getCreator();
        this.createTime = form.getCreateTime();
        this.latestEditTime = form.getLatestEditTime();
        this.virtual = form.getVirtual();
        this.viewAuthority = form.getViewAuthority();
        this.tableName = form.getTable().getTableViewName();
        groups = new ArrayList<>();
        details = new ArrayList<>();
        title = null;
    }

    public String getTableName() {
        return tableName;
    }

    public FormOut setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FormOut setTitle(String title) {
        this.title = title;
        return this;
    }

    public FormOut addGroup(Integer groupId, String groupName, Map<String, ColumnSimple> columns, Map<String, Object> values) {
        groups.add(new Group(groupId, groupName, columns, values));
        return this;
    }

    public FormOut addDetail(Integer detailId, String detailName, Map<String, FormOut.ColumnSimple> columns, List<Map<String, Object>> values) {
        details.add(new Detail(detailId, detailName, columns, values));
        return this;
    }

    public Long getDataId() {
        return dataId;
    }

    public FormOut setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public Long getRequestId() {
        return requestId;
    }

    public FormOut setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public Long getTableId() {
        return tableId;
    }

    public FormOut setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public FormOut setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public FormOut setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public FormOut setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return this;
    }

    public FormOut setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getLatestEditTime() {
        return latestEditTime;
    }

    public FormOut setLatestEditTime(Date latestEditTime) {
        this.latestEditTime = latestEditTime;
        return this;
    }

    public String getViewAuthority() {
        return viewAuthority;
    }

    public FormOut setViewAuthority(String viewAuthority) {
        this.viewAuthority = viewAuthority;
        return this;
    }

    public Boolean getVirtual() {
        return virtual;
    }

    public FormOut setVirtual(Boolean virtual) {
        this.virtual = virtual;
        return this;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public FormOut setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public FormOut setDetails(List<Detail> details) {
        this.details = details;
        return this;
    }

    public static class ColumnSimple {

        Long columnId;
        String columnViewName;
        String columnType;
        String columnTypeDescription;

        public ColumnSimple() {
        }

        public ColumnSimple(String columnType, String columnTypeDescription, String columnViewName, Long columnId) {
            this.columnType = columnType;
            this.columnTypeDescription = columnTypeDescription;
            this.columnViewName = columnViewName;
            this.columnId = columnId;
        }

        public Long getColumnId() {
            return columnId;
        }

        public ColumnSimple setColumnId(Long columnId) {
            this.columnId = columnId;
            return this;
        }

        public String getColumnType() {
            return columnType;
        }

        public ColumnSimple setColumnType(String columnType) {
            this.columnType = columnType;
            return this;
        }

        public String getColumnTypeDescription() {
            return columnTypeDescription;
        }

        public ColumnSimple setColumnTypeDescription(String columnTypeDescription) {
            this.columnTypeDescription = columnTypeDescription;
            return this;
        }

        public String getColumnViewName() {
            return columnViewName;
        }

        public ColumnSimple setColumnViewName(String columnViewName) {
            this.columnViewName = columnViewName;
            return this;
        }
    }

    public static class Group {
        Integer groupId;
        String groupName;
        Map<String, FormOut.ColumnSimple> columns;
        Map<String,Object> values;

        public Group() {
        }

        public Group(Integer groupId, String groupName, Map<String, FormOut.ColumnSimple> columns, Map<String, Object> values) {
            this.groupId = groupId;
            this.groupName = groupName;
            this.columns = columns;
            this.values = values;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public Group setGroupId(Integer groupId) {
            this.groupId = groupId;
            return this;
        }

        public String getGroupName() {
            return groupName;
        }

        public Group setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Map<String, FormOut.ColumnSimple> getColumns() {
            return columns;
        }

        public Group setColumns(Map<String, FormOut.ColumnSimple> columns) {
            this.columns = columns;
            return this;
        }

        public Map<String, Object> getValues() {
            return values;
        }

        public Group setValues(Map<String, Object> values) {
            this.values = values;
            return this;
        }
    }

    public static class Detail {
        Integer detailId;
        String detailName;
        Map<String, FormOut.ColumnSimple> columns;
        List<Map<String, Object>> values;

        public Detail() {
        }

        public Detail(Integer detailId, String detailName, Map<String, FormOut.ColumnSimple> columns, List<Map<String, Object>> values) {
            this.detailId = detailId;
            this.detailName = detailName;
            this.columns = columns;
            this.values = values;
        }

        public Integer getDetailId() {
            return detailId;
        }

        public Detail setDetailId(Integer detailId) {
            this.detailId = detailId;
            return this;
        }

        public String getDetailName() {
            return detailName;
        }

        public Detail setDetailName(String detailName) {
            this.detailName = detailName;
            return this;
        }

        public Map<String, FormOut.ColumnSimple> getColumns() {
            return columns;
        }

        public Detail setColumns(Map<String, FormOut.ColumnSimple> columns) {
            this.columns = columns;
            return this;
        }

        public List<Map<String, Object>> getValues() {
            return values;
        }

        public Detail setValues(List<Map<String, Object>> values) {
            this.values = values;
            return this;
        }
    }

}




