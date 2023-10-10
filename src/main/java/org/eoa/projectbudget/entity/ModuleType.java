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
 * @Date 2023/10/7 17:09
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: ModuleType
 * @Description: 实体类:模块类型
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("module_type")
@Accessors(chain = true)
public class ModuleType {
    /**
     * 基本表字段属性
     */
    @TableId(type = IdType.AUTO)
    private Long moduleTypeId;
    private String moduleTypeName;
    private String workflowRemark;
    private Long creator;
    private Date createTime;

    public ModuleType(String moduleTypeName, String workflowRemark) {
        this.moduleTypeName = moduleTypeName;
        this.workflowRemark = workflowRemark;
    }

    @Override
    public String toString() {
        return "ModuleType{" +
                "moduleTypeId=" + moduleTypeId +
                ", moduleTypeName='" + moduleTypeName + '\'' +
                ", workflowRemark='" + workflowRemark + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                '}';
    }
}
