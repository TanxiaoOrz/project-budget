package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/8 15:38
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Table
 * @Description: table实体类
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@TableName("table_index")
@Accessors(chain = true)
public class Table {
    @TableId(type = IdType.AUTO)
    private Long tableId;
    private String tableViewName;
    private String tableDataName;
    private Long moduleNo;
    private String workFlowNo;
    private Integer detailCount;
    private String detailName;
    private Integer groupCount;
    private String groupName;
    private String remark;
    private String defaultEdit;
    private String defaultCreate;
    private String defaultDelete;
    private String defaultShare;
    private Long creator;
    private Date createTime;

    public Table() {

    }
}
