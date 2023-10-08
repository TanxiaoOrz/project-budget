package org.eoa.projectbudget.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.vo.ViewData.DropSelect;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 16:28
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: TableView
 * @Description: 表单的视图传出类
 * @Version 1.0
 */
@Schema(name = "TableView",title = "表单视图格式")
@Data
@NoArgsConstructor
public class TableView {
    private Long tableId;
    private String tableViewName;
    private String tableDataName;
    private Long moduleNo;
    private String workFlowNo;
    private String[] detailNames;
    private List<DropSelect> detailSelect;
    private String[] groupNames;
    private List<DropSelect> groupSelect;
    private String remark;


    public TableView(Table table) {
        tableId=table.getTableId();
        tableViewName=table.getTableViewName();
        tableDataName=table.getTableDataName();
        moduleNo=table.getModuleNo();
        workFlowNo=table.getWorkFlowNo();
        try {
            detailNames=new ObjectMapper().readValue(table.getDetailName(),String[].class);
            groupNames=new ObjectMapper().readValue(table.getGroupName(),String[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (table.getDetailCount()>=0){
            detailSelect = new ArrayList<>(table.getDetailCount());
            for (int i = 0; i < table.getDetailCount(); i++) {
                detailSelect.add(new DropSelect(i,detailNames[i],true,null));
            }
        }
        if (table.getGroupCount()>=0){
            groupSelect = new ArrayList<>(table.getGroupCount());
            for (int i = 0; i < table.getGroupCount(); i++) {
                groupSelect.add(new DropSelect(i,groupNames[i],true,null));
            }
        }
        remark = table.getRemark();
    }
}
