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
    Long tableId;
    String tableViewName;
    String tableDataName;
    Long moduleNo;
    String workFlowNo;
    String[] detailNames;
    List<DropSelect> detailSelect;
    String[] groupNames;
    List<DropSelect> groupSelect;
    String remark;
    Boolean virtual;
    Long creator;
    String createName;
    Date createTime;
    String moduleName;

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

        remark = table.getRemark();
    }

    public TableOut generateSelect() {
        int detailCount = detailNames.length;
        if (detailCount >0){
            detailSelect = new ArrayList<>(detailCount);
            for (int i = 0; i < detailCount; i++) {
                detailSelect.add(new DropSelect(i,detailNames[i],true,null));
            }
        }
        int groupCount = groupNames.length;
        if (groupCount >0){
            groupSelect = new ArrayList<>(groupCount);
            for (int i = 0; i < groupCount; i++) {
                groupSelect.add(new DropSelect(i,groupNames[i],true,null));
            }
        }
        return this;
    }
}
