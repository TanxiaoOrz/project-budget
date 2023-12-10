package org.eoa.projectbudget.vo.out;


import org.eoa.projectbudget.dto.FormOutDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    public FormOut() {
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
    }

    public FormOut addGroup(Integer groupId, String groupName, Map<String, ColumnSimple> columns, Map<String, Object> values) {
        groups.add(new Group(groupId, groupName, columns, values));
        return this;
    }

    public FormOut addDetail(Integer detailId, String detailName, Map<String, FormOut.ColumnSimple> columns, List<Map<String, Object>> values) {
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

        String columnName;
        String type;
        String typeDescription;

        public ColumnSimple() {
        }

        public ColumnSimple(String type, String typeDescription,String columnName) {
            this.type = type;
            this.typeDescription = typeDescription;
            this.columnName = columnName;
        }

        public String getType() {
            return type;
        }

        public ColumnSimple setType(String type) {
            this.type = type;
            return this;
        }

        public String getTypeDescription() {
            return typeDescription;
        }

        public ColumnSimple setTypeDescription(String typeDescription) {
            this.typeDescription = typeDescription;
            return this;
        }

        public String getColumnName() {
            return columnName;
        }

        public ColumnSimple setColumnName(String columnName) {
            this.columnName = columnName;
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




