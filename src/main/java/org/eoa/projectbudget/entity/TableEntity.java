package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:38
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: TableEntity
 * @Description: table实体类
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@TableName("table_index")
@Accessors(chain = true)
public class TableEntity extends Table{

    private String workFlowNo;
    private Integer detailCount;
    private String detailName;

    private String defaultEdit;
    private String defaultCreate;
    private String defaultDelete;
    private String defaultShare;


    public TableEntity() {

    }

    public String getWorkFlowNo() {
        return workFlowNo;
    }

    public TableEntity setWorkFlowNo(String workFlowNo) {
        this.workFlowNo = workFlowNo;
        return this;
    }

    public Integer getDetailCount() {
        return detailCount;
    }

    public TableEntity setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
        return this;
    }

    public String getDetailName() {
        return detailName;
    }

    public TableEntity setDetailName(String detailName) {
        this.detailName = detailName;
        return this;
    }

    public String getDefaultEdit() {
        return defaultEdit;
    }

    public TableEntity setDefaultEdit(String defaultEdit) {
        this.defaultEdit = defaultEdit;
        return this;
    }

    public String getDefaultCreate() {
        return defaultCreate;
    }

    public TableEntity setDefaultCreate(String defaultCreate) {
        this.defaultCreate = defaultCreate;
        return this;
    }

    public String getDefaultDelete() {
        return defaultDelete;
    }

    public TableEntity setDefaultDelete(String defaultDelete) {
        this.defaultDelete = defaultDelete;
        return this;
    }

    public String getDefaultShare() {
        return defaultShare;
    }

    public TableEntity setDefaultShare(String defaultShare) {
        this.defaultShare = defaultShare;
        return this;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "workFlowNo='" + workFlowNo + '\'' +
                ", detailCount=" + detailCount +
                ", detailName='" + detailName + '\'' +
                ", defaultEdit='" + defaultEdit + '\'' +
                ", defaultCreate='" + defaultCreate + '\'' +
                ", defaultDelete='" + defaultDelete + '\'' +
                ", defaultShare='" + defaultShare + '\'' +
                ", tableId=" + tableId +
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
