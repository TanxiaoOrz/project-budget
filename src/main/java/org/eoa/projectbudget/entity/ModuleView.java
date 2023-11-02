package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/17 17:28
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: ModuleView
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("module_view")
public class ModuleView {

    @TableId(type = IdType.AUTO)
    private Long moduleTypeId;
    private String moduleTypeName;
    private String workflowRemark;
    private Long creator;
    private Date createTime;
    private Integer searchCounts;
    private Integer flowCounts;
    private Integer tableCounts;
    private Integer chartsCounts;
}
