package org.eoa.projectbudget.vo_out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.eoa.projectbudget.entity.TableEntity;
import org.eoa.projectbudget.vo_out.ViewData.DropSelect;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/8 16:28
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: TableOut
 * @Description: 表单的视图传出类
 * @Version 1.0
 */
@Schema(name = "TableOut",title = "表单视图格式")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TableOut {
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


    public TableOut(TableEntity table) {
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
