package org.eoa.projectbudget.vo_out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/10/21 20:56
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: ModuleOut
 * @Description: TODO
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ModuleOut", description = "应用模块显示类")
public class ModuleOut {

    private Long moduleTypeId;
    private String moduleTypeName;
    private String workflowRemark;
    private Long creatorId;
    private String creatorName;
    private Date createTime;
    private Integer searchCounts;
    private Integer flowCounts;
    private Integer tableCounts;
    private Integer chartsCounts;

}
