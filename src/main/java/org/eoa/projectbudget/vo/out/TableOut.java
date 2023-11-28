package org.eoa.projectbudget.vo.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.entity.TableView;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.eoa.projectbudget.vo.out.ViewData.DropSelect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 16:28
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: TableOut
 * @Description: 表单的视图传出类
 * @Version 1.2
 */
@Schema(name = "TableOut",title = "表单视图格式")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TableOut {
    @Schema(description = "表单编号")
    Long tableId;
    @Schema(description = "表单显示名称")
    String tableViewName;
    @Schema(description = "表单数据库名称`")
    String tableDataName;
    @Schema(description = "表单所属模块")
    Long moduleNo;
    @Schema(description = "流程编号",deprecated = true)
    String workFlowNo;
    @Schema(description = "明细表名称数组")
    String[] detailNames;
    @Schema(description = "明细选择框列表")
    List<DropSelect> detailSelect;
    @Schema(description = "主表分组名称数组")
    String[] groupNames;
    @Schema(description = "主表选择框列表")
    List<DropSelect> groupSelect;
    @Schema(description = "表单描述")
    String remark;
    @Schema(description = "是否是虚拟表单")
    Boolean virtual;
    @Schema(description = "创建人id")
    Long creator;
    @Schema(description = "创建名称")
    String createName;
    @Schema(description = "创建时间")
    Date createTime;
    @Schema(description = "模块名称")
    String moduleName;
    @Schema(description = "默认编辑权限")
    String defaultEdit;
    @Schema(description = "默认创建权限")
    String defaultCreate;
    @Schema(description = "默认删除权限")
    String defaultDelete;
    @Schema(description = "默认共享权限")
    String defaultShare;

    public TableOut(Table table){
        if (table.getClass() == TableEntity.class) {
            entityCreate((TableEntity) table);
            virtual = false;
        }
        if (table.getClass() == TableView.class) {
            viewCreate((TableView) table);
            virtual = true;
        } else {
            tableId=table.getTableId();
            tableViewName=table.getTableViewName();
            tableDataName=table.getTableDataName();
            moduleNo=table.getModuleNo();
            groupNames=DataProcessUtils.splitStringArray(table.getGroupName());
            remark = table.getRemark();
        }
        creator = table.getCreator();
        generateSelect();
    }

    private void viewCreate(TableView table) {
        tableId=table.getTableId();
        tableViewName=table.getTableViewName();
        tableDataName=table.getTableDataName();
        moduleNo=table.getModuleNo();
        groupNames = table.getGroupName().split(",");
        remark = table.getRemark();
    }


    private void entityCreate(TableEntity table) {
        tableId=table.getTableId();
        tableViewName=table.getTableViewName();
        tableDataName=table.getTableDataName();
        moduleNo=table.getModuleNo();
        workFlowNo=table.getWorkFlowNo();
        groupNames = DataProcessUtils.splitStringArray(table.getGroupName());
        detailNames = DataProcessUtils.splitStringArray(table.getDetailName());
        defaultCreate = table.getDefaultCreate();
        defaultDelete = table.getDefaultDelete();
        defaultEdit = table.getDefaultEdit();
        defaultShare = table.getDefaultShare();
        remark = table.getRemark();
    }

    private void generateSelect() {
        if (!virtual) {
            int detailCount = detailNames.length;
            if (detailCount > 0) {
                detailSelect = new ArrayList<>(detailCount);
                for (int i = 0; i < detailCount; i++) {
                    detailSelect.add(new DropSelect(i, detailNames[i], true, null));
                }
            }
        }
        int groupCount = groupNames.length;
        if (groupCount >0){
            groupSelect = new ArrayList<>(groupCount);
            for (int i = 0; i < groupCount; i++) {
                groupSelect.add(new DropSelect(i,groupNames[i],true,null));
            }
        }
    }
}
