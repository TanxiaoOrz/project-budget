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
 * @Date 2023/10/9 19:23
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Column
 * @Description: 字段索引抽象声明类
 * @Version 1.0
 */

@Data
@Accessors(chain = true)
public abstract class Column {
    public final static String typeText = "0";


    @TableId(type = IdType.AUTO)
    protected Long columnId;
    protected String columnViewName;
    protected String columnDataName;
    protected String columnType;
    protected String columnTypeDescription;
    protected Long tableNo;
    protected Integer columnGroupNo;
    protected Integer columnViewNo;
    protected Long creator;
    protected Date createTime;

}
