package org.eoa.projectbudget.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.AuthorityUtils;
import org.eoa.projectbudget.vo.constraint.Constraint;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author 张骏山
 * @Date 2023/10/23 13:50
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Form
 * @Description: 自定义表单传输公共类
 * @Version 1.1
 */

@Data
@Accessors(chain = true)
public class FormOutDto {
    Long dataId;
    Long requestId;
    Integer requestStatus;
    Long creator;
    Date createTime;
    Date latestEditTime;
    String editAuthority;
    String viewAuthority;
    String deleteAuthority;
    Boolean virtual;
    Long tableId;
    List<Group> groups;
    List<Detail> details;
    Table table;

    public boolean checkView(HumanDto creator,HumanDto user) throws EoaException {
        if (virtual)
            return true;
        Constraint constraint = AuthorityUtils.getConstraint(viewAuthority);
        return AuthorityUtils.checkAuthority(user,creator,constraint) || AuthorityUtils.checkTable(user,this,constraint);
    }


    public void addGroup(Integer groupId, String groupName, Map<? extends Column, Object> columns) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(new Group(groupId, groupName, columns));
    }

    public void addDetail(Integer detailId, String detailName, List<? extends Column> detailColumns, List<Map<String, Object>> detailValuesList) {
        if (details == null) {
            details = new ArrayList<>();
        }
        details.add(new Detail(detailId, detailName, detailColumns, detailValuesList));
    }

    public Object getMainValue(Long columnId) {

        AtomicBoolean isFound = new AtomicBoolean(false);
        AtomicReference<Object> value = new AtomicReference<>();
        groups.forEach(group -> group.getColumns().forEach((column, object) -> {
            if (isFound.get())
                return;
            if (column.getColumnId().equals(columnId)) {
                value.set(object);
                isFound.set(true);
            }
        }));

        return isFound.get()?value.get():null;
    }

    public List<Object> getDetailsValues(Long columnId) {

        AtomicBoolean isFound = new AtomicBoolean(false);
        List<Object> values = new ArrayList<>();

        details.forEach((detail -> {
            if (isFound.get())
                return;
            Optional<? extends Column> first = detail.detailColumns.stream().filter(column -> column.getColumnId().equals(columnId)).findFirst();
            if (first.isPresent()) {
                String columnDataName = first.get().getColumnDataName();
                values.addAll(detail.getDetailValuesList().stream().map(map -> map.get(columnDataName)).filter(Objects::nonNull).toList());
                isFound.set(true);
            }
        }));

        return isFound.get()?values:null;
    }


    public static class Group {
        Integer groupId;
        String groupName;
        Map<? extends Column,Object> columns;

        public Group(Integer groupId, String groupName, Map<? extends Column, Object> columns) {
            this.groupId = groupId;
            this.groupName = groupName;
            this.columns = columns;
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

        public Map<? extends Column, Object> getColumns() {
            return columns;
        }

        public Group setColumns(Map<Column, Object> columns) {
            this.columns = columns;
            return this;
        }
    }

    public static class Detail {
        Integer detailId;
        String detailName;

        List<? extends Column> detailColumns;
        List<Map<String, Object>> detailValuesList;

        public Detail(Integer detailId, String detailName, List<? extends Column> detailColumns, List<Map<String, Object>> detailValuesList) {
            this.detailId = detailId;
            this.detailName = detailName;
            this.detailColumns = detailColumns;
            this.detailValuesList = detailValuesList;
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

        public List<? extends Column> getDetailColumns() {
            return detailColumns;
        }

        public Detail setDetailColumns(List<Column> detailColumns) {
            this.detailColumns = detailColumns;
            return this;
        }

        public List<Map<String, Object>> getDetailValuesList() {
            return detailValuesList;
        }

        public Detail setDetailValuesList(List<Map<String, Object>> detailValuesList) {
            this.detailValuesList = detailValuesList;
            return this;
        }
    }

}
