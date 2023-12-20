package org.eoa.projectbudget.vo.out;

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
 * @Description: 应用模块显示类
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ModuleOut", description = "应用模块显示类")
public class ModuleOut implements VoOut{

    @Schema(description = "唯一id")
    private Long moduleTypeId;
    @Schema(description = "应用名称")
    private String moduleTypeName;
    @Schema(description = "应用备注")
    private String workflowRemark;
    @Schema(description = "创建者编号")
    private Long creatorId;
    @Schema(description = "创建者姓名")
    private String creatorName;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "查询数量")
    private Integer searchCounts;
    @Schema(description = "流程数量")
    private Integer flowCounts;
    @Schema(description = "表单数量")
    private Integer tableCounts;
    @Schema(description = "图标数量")
    private Integer chartsCounts;

    @Override
    public void toBrowser(Long browserId) {

        this.moduleTypeName = browserId!=0?null:moduleTypeName;
        this.workflowRemark = browserId!=1?null:workflowRemark;
        this.creatorId = null;
        this.creatorName = null;
        this.createTime = null;
        this.searchCounts = null;
        this.flowCounts = null;
        this.tableCounts = null;
        this.chartsCounts = null;
    }
}
