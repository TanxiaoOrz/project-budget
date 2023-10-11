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
 * @Date 2023/10/11 14:09
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: ColumnEntity
 * @Description: 字段索引实体类
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("table_column_index")
public class ColumnEntity {

    private Integer columnDetailNo;

}
