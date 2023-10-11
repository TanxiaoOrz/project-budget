package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/10 16:48
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: ColumnView
 * @Description: 视图列对象索引
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("table_view_column_index")
public class ColumnView {

    private String columnViewName;
    private String columnDataName;
    private String columnType;
    private String columnTypeDescription;
    private Long tableNo;
    private Integer columnGroupNo;
    private Integer columnViewNo;
    private Long creator;
    private Date createTime;
    private String columnViewDisplay;

}
