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
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/11 14:09
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: ColumnEntity
 * @Description: 字段索引实体类
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("table_column_index")
public class ColumnEntity extends Column{

    private final static List<String> DATATYPE_GROUP_DATA = List.of(new String[]{
            "varchar(100)",
            "varchar(1000)",
            "DECIMAL(38,2)",
            "BIGINT(64) UNSIGNED",
            "int",
            "datetime",
            "varchar(10000)"
    });

    private Integer columnDetailNo;

    /**
     *
     * @return 该字段实际在数据库中存储的类型
     */
    public String createDateType() {
        return DATATYPE_GROUP_DATA.get(DATATYPE_GROUP_VIEW.indexOf(columnType));
    }
}
