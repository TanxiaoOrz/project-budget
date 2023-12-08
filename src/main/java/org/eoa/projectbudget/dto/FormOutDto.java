package org.eoa.projectbudget.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class FormOutDto<C extends Column, T extends Table> {
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
    T table;

    public FormOutDto<C,T> addGroup(Integer groupId, String groupName, Map<C, Object> columns) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(new Group(groupId,groupName,columns));
        return this;
    }

    public FormOutDto<C,T> addDetail(Integer detailId, String detailName, Map<C,Map<Integer,Object>> columns) {
        // TODO



        return this;
    }

    public Object getMainValue(Long columnId) {

        AtomicBoolean isFound = new AtomicBoolean(false);
        AtomicReference<Object> value = new AtomicReference<>();
        groups.forEach(group -> {
            group.getColumns().forEach((column,object) -> {
                if (isFound.get())
                    return;
                if (column.getColumnId().equals(columnId)) {
                    value.set(object);
                    isFound.set(true);
                }
            });
        });

        return isFound.get()?value.get():null;
    }

    public List<Object> getDetailsValues(Long columnId) {
        //TODO
        AtomicBoolean isFound = new AtomicBoolean(false);
        List<Object> values = new ArrayList<>();

        details.forEach((detail -> {
            detail.getColumns().forEach((column, integerObjectMap) -> {
                if (isFound.get())
                    return;
                if (column.getColumnId().equals(columnId)) {
                    isFound.set(true);
                    values.addAll(integerObjectMap.values());
                }
            });
        }));

        return isFound.get()?values:null;
    }


    public class Group {
        Integer groupId;
        String groupName;
        Map<C,Object> columns;

        public Group(Integer groupId, String groupName, Map<C, Object> columns) {
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

        public Map<C, Object> getColumns() {
            return columns;
        }

        public Group setColumns(Map<C, Object> columns) {
            this.columns = columns;
            return this;
        }
    }

    public class Detail {
        Integer detailId;
        String detailName;
        Map<C,Map<Integer,Object>> columns;

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

        public Map<C,Map<Integer,Object>> getColumns() {
            return columns;
        }

        public Detail setColumns(Map<C,Map<Integer,Object>> columns) {
            this.columns = columns;
            return this;
        }
    }

}
