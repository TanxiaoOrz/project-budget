package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/10 17:16
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Table
 * @Description: 表单类的抽象声明
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public abstract class Table {
    @TableId(type = IdType.AUTO)
    protected Long tableId;
    protected String tableViewName;
    protected String tableDataName;
    protected Long moduleNo;
    protected Integer groupCount;
    protected String groupName;
    protected String remark;
    protected Long creator;
    protected Date createTime;
}
