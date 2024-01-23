package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 张骏山
 * @Date 2023/10/10 16:48
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: TableView
 * @Description: 虚拟视图实体类
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("table_view_index")
public class TableView extends Table{


    @Override
    public String toString() {
        return "TableView{" +
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
