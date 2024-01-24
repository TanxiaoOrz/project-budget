package org.eoa.projectbudget.vo.out;


import io.swagger.v3.oas.annotations.media.Schema;
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

@Schema(name = "FormOut", description = "表单数据输出类,浏览框对象id为columnId")
public class FormOut implements VoOut{

    @Schema(description = "数据id")
    Long dataId;
    @Schema(description = "审批流程id")
    Long requestId;
    @Schema(description = "表单id")
    Long tableId;

    @Schema(description = "表单名称")
    String tableName;
    @Schema(description = "流程状态")
    Integer requestStatus;
    @Schema(description = "创建者")
    Long creator;
    @Schema(description = "创建时间")
    Date createTime;
    @Schema(description = "查看权限")
    String viewAuthority;
    @Schema(description = "创建者名称")
    String creatorName;
    @Schema(description = "最后修改时间")
    Date latestEditTime;
    @Schema(description = "是否虚拟图表数据")
    Boolean virtual;
    @Schema(description = "主表分组列表")
    List<Group> groups;
    @Schema(description = "明细表单列表")
    List<Detail> details;
    @Schema(description = "显示浏览框标题")
    String title;

    public FormOut() {
    }

    public void toBrowser(Long columnId) {
        for (Group group:
             groups) {
            Optional<ColumnSimple> entry = group.columns.stream().filter(set -> set.columnId.equals(columnId)).findFirst();
            if (entry.isPresent()) {
                Object o = group.values.get(entry.get().columnDataName);
                title = o==null?dataId.toString():o.toString();
                break;
            }
        }
        title = DataProcessUtils.nullToString(title);

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

    public FormOut addGroup(Integer groupId, String groupName, List<ColumnSimple> columns, Map<String, Object> values) {
        groups.add(new Group(groupId, groupName, columns, values));
        return this;
    }

    public FormOut addDetail(Integer detailId, String detailName, List<FormOut.ColumnSimple> columns, List<Map<String, Object>> values) {
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

        String columnDataName;

        public ColumnSimple() {
        }

        public ColumnSimple(String columnType, String columnTypeDescription, String columnViewName, Long columnId, String columnDataName) {
            this.columnType = columnType;
            this.columnTypeDescription = columnTypeDescription;
            this.columnViewName = columnViewName;
            this.columnId = columnId;
            this.columnDataName = columnDataName;
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

        public String getColumnDataName() {
            return columnDataName;
        }

        public ColumnSimple setColumnDataName(String columnDataName) {
            this.columnDataName = columnDataName;
            return this;
        }
    }

    public static class Group {
        Integer groupId;
        String groupName;
        List<FormOut.ColumnSimple> columns;
        Map<String,Object> values;

        public Group() {
        }

        public Group(Integer groupId, String groupName, List<FormOut.ColumnSimple> columns, Map<String, Object> values) {
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

        public List<FormOut.ColumnSimple> getColumns() {
            return columns;
        }

        public Group setColumns(List<FormOut.ColumnSimple> columns) {
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
        List<FormOut.ColumnSimple> columns;
        List<Map<String, Object>> values;

        public Detail() {
        }

        public Detail(Integer detailId, String detailName, List<FormOut.ColumnSimple> columns, List<Map<String, Object>> values) {
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

        public List<FormOut.ColumnSimple> getColumns() {
            return columns;
        }

        public Detail setColumns(List<FormOut.ColumnSimple> columns) {
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




