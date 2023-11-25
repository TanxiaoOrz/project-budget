package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.entity.TableView;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/11/20 11:20
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: TableIn
 * @Description: 表单传入类
 * @Version: 1.0
 **/
@Schema(name = "TableIn",description = "表单传入类")
public class TableIn implements CheckParameter<Table>{
    @Schema(description = "表单显示名称")
    String tableViewName;
    @Schema(description = "表单数据库名称`")
    String tableDataName;
    @Schema(description = "表单所属模块")
    Long moduleNo;
    @Schema(description = "主表分组名称数组")
    String[] groupName;
    @Schema(description = "表单描述")
    String remark;
    @Schema(description = "流程编号",deprecated = true)
    String workFlowNo;
    @Schema(description = "明细表名称数组")
    String[] detailName;
    @Schema(description = "默认编辑权限")
    String defaultEdit;
    @Schema(description = "默认创建权限")
    String defaultCreate;
    @Schema(description = "默认删除权限")
    String defaultDelete;
    @Schema(description = "默认共享权限")
    String defaultShare;
    @Schema(description = "是否是虚拟表单")
    Boolean virtual;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(tableViewName)) {
            throw new ParameterException("tableViewName","","没有表单名称");
        }
        if (moduleNo==null) {
            throw new ParameterException("moduleNo","","没有所属模块编号");
        }
        if (virtual == null) {
            throw new ParameterException("isVirtual","","没有是否虚拟表单");
        }
        if (!virtual) {
            if (DataProcessUtils.isEmpty(defaultCreate))
                throw new ParameterException("defaultCreate","","没有默认创建权限");
            if (DataProcessUtils.isEmpty(defaultDelete))
                throw new ParameterException("defaultDelete","","没有默认删除权限");
            if (DataProcessUtils.isEmpty(defaultShare))
                throw new ParameterException("defaultShare","","没有默认共享权限");
            if (DataProcessUtils.isEmpty(defaultEdit))
                throw new ParameterException("defaultEdit","","没有默认删除权限");
        } else {
            if (DataProcessUtils.isEmpty(tableDataName))
                throw new ParameterException("tableDataName","","没有数据库表名");
        }
    }

    @Override
    public Table toEntity(Long dataId) throws ParameterException {
        checkSelf();
        Table table;
        table = virtual ?new TableView():new TableEntity();
        table.setTableId(dataId);
        table.setTableViewName(tableViewName)
                .setTableDataName(tableDataName)
                .setModuleNo(moduleNo)
                .setGroupCount(groupName==null?0:groupName.length)
                .setGroupName(DataProcessUtils.contactStringArray(groupName))
                .setRemark(remark);
        if (!virtual) {
            assert table instanceof TableEntity;
            ((TableEntity) table).setDetailCount(detailName==null?0:detailName.length)
                    .setDetailName(DataProcessUtils.contactStringArray(detailName))
                    .setWorkFlowNo(workFlowNo)
                    .setDefaultCreate(defaultCreate)
                    .setDefaultEdit(defaultEdit)
                    .setDefaultShare(defaultShare)
                    .setDefaultDelete(defaultDelete);
        }
        return table;
    }

    public String getTableViewName() {
        return tableViewName;
    }

    public TableIn setTableViewName(String tableViewName) {
        this.tableViewName = tableViewName;
        return this;
    }

    public String getTableDataName() {
        return tableDataName;
    }

    public TableIn setTableDataName(String tableDataName) {
        this.tableDataName = tableDataName;
        return this;
    }

    public Long getModuleNo() {
        return moduleNo;
    }

    public TableIn setModuleNo(Long moduleNo) {
        this.moduleNo = moduleNo;
        return this;
    }

    public String[] getGroupName() {
        return groupName;
    }

    public TableIn setGroupName(String[] groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public TableIn setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getWorkFlowNo() {
        return workFlowNo;
    }

    public TableIn setWorkFlowNo(String workFlowNo) {
        this.workFlowNo = workFlowNo;
        return this;
    }

    public String[] getDetailName() {
        return detailName;
    }

    public TableIn setDetailName(String[] detailName) {
        this.detailName = detailName;
        return this;
    }

    public String getDefaultEdit() {
        return defaultEdit;
    }

    public TableIn setDefaultEdit(String defaultEdit) {
        this.defaultEdit = defaultEdit;
        return this;
    }

    public String getDefaultCreate() {
        return defaultCreate;
    }

    public TableIn setDefaultCreate(String defaultCreate) {
        this.defaultCreate = defaultCreate;
        return this;
    }

    public String getDefaultDelete() {
        return defaultDelete;
    }

    public TableIn setDefaultDelete(String defaultDelete) {
        this.defaultDelete = defaultDelete;
        return this;
    }

    public String getDefaultShare() {
        return defaultShare;
    }

    public TableIn setDefaultShare(String defaultShare) {
        this.defaultShare = defaultShare;
        return this;
    }

    public Boolean getVirtual() {
        return virtual;
    }

    public TableIn setVirtual(Boolean virtual) {
        this.virtual = virtual;
        return this;
    }
}
