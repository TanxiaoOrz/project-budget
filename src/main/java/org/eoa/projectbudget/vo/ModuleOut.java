package org.eoa.projectbudget.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 张骏山
 * @Date 2023/10/17 17:28
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: ModuleOut
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ModuleOut {
    private Long moduleTypeId;
    private String moduleTypeName;
    private String workflowRemark;
    private Long creator;
    private Date createTime;
    private Integer 
}
