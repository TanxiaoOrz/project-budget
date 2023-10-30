package org.eoa.projectbudget.vo_out;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 张骏山
 * @Date 2023/10/23 17:24
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: FormOut
 * @Description: TODO
 * @Version 1.0
 */
public class FormOut {

    Long dataId;
    Long requestId;
    Integer requestStatus;
    Long creator;
    Date createTime;
    Date latestEditTime;
    String editAuthority;
    String viewAuthority;
    String deleteAuthority;
    List<Group> groups;
    List<Detail> details;
    Table table;

    static class Table {

    }

    static class Column {

    }


    static class Group {
        Integer groupId;
        String groupName;
        Map<String,Column> columns;

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

        public Map<String, Column> getColumns() {
            return columns;
        }

        public Group setColumns(Map<String, Column> columns) {
            this.columns = columns;
            return this;
        }
    }

    static  class Detail {
        Integer detailId;
        String detailName;
        Map<String, Column> columns;

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

        public Map<String, Column> getColumns() {
            return columns;
        }

        public Detail setColumns(Map<String, Column> columns) {
            this.columns = columns;
            return this;
        }
    }
}
