package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

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
    public final static int SINGLE_TEXT = 0;
    public final static int TEXT = 1;
    public final static int NUMBER = 2;
    public final static int BROWSER_BOX = 3;
    public final static int SELECT_ITEM = 4;
    public final static int DATETIME = 5;
    public final static int FILE = 6;



    public final static List<String> DATATYPE_GROUP_VIEW =List.of(new String[]{
            "SINGLE_TEXT",
            "TEXT",
            "NUMBER",
            "BROWSER_BOX",
            "SELECT_ITEM",
            "DATETIME",
            "FILE"
    });

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
