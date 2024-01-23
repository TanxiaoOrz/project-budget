package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/10 17:16
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Table
 * @Description: 表单类的抽象声明
 * @Version 1.0
 */
@Data
public abstract class Table {
    @TableId(type = IdType.AUTO)
    protected Long tableId;
    protected String tableViewName;
    protected String tableDataName;
    protected Long moduleNo;
    protected Integer groupCount;
    protected String groupName;
    protected String remark;
    protected Long creator;
    protected Date createTime;

    public Long getTableId() {
        return tableId;
    }

    public Table setTableId(Long tableId) {
        this.tableId = tableId;
        return this;
    }

    public String getTableViewName() {
        return tableViewName;
    }

    public Table setTableViewName(String tableViewName) {
        this.tableViewName = tableViewName;
        return this;
    }

    public String getTableDataName() {
        return tableDataName;
    }

    public Table setTableDataName(String tableDataName) {
        this.tableDataName = tableDataName;
        return this;
    }

    public Long getModuleNo() {
        return moduleNo;
    }

    public Table setModuleNo(Long moduleNo) {
        this.moduleNo = moduleNo;
        return this;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public Table setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public Table setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Table setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public Table setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Table setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableId=" + tableId +
                ", tableViewName='" + tableViewName + '\'' +
                ", tableDataName='" + tableDataName + '\'' +
                ", moduleNo=" + moduleNo +
                ", groupCount=" + groupCount +
                ", groupName='" + groupName + '\'' +
                ", remark='" + remark + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                '}';
    }
}
