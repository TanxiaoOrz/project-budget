package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/10 16:48
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: TableOut
 * @Description: TODO
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("table_view_index")
@Accessors(chain = true)
public class TableView extends Table{
    @TableId(type = IdType.AUTO)
    private Long tableId;
    private String tableViewName;
    private String tableDataName;
    private Long moduleNo;
    private Integer groupCount;
    private String groupName;
    private String remark;
    private Long creator;
    private Date createTime;

}
