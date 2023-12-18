package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.vo.out.VoOut;

/**
 * @Author: 张骏山
 * @Date: 2023/11/3 16:37
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Authority
 * @Description: 权限实体类
 * @Version: 1.0
 **/

@TableName("authority")
public class Authority implements VoOut {

    @Schema(description = "唯一编号")
    @TableId(type = IdType.AUTO)
    Long dataId;
    @Schema(description = "权限名称,browserId=0")
    String authorityName;
    @Schema(description = "权限描述,browserId=1")
    String authorityDescription;
    @Schema(description = "权限备注,browserId=2")
    String authorityRemark;

    public Long getDataId() {
        return dataId;
    }

    public Authority setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public Authority setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public String getAuthorityDescription() {
        return authorityDescription;
    }

    public Authority setAuthorityDescription(String authorityDescription) {
        this.authorityDescription = authorityDescription;
        return this;
    }

    public String getAuthorityRemark() {
        return authorityRemark;
    }

    public Authority setAuthorityRemark(String authorityRemark) {
        this.authorityRemark = authorityRemark;
        return this;
    }

    public Authority(Long dataId, String authorityName, String authorityDescription, String authorityRemark) {
        this.dataId = dataId;
        this.authorityName = authorityName;
        this.authorityDescription = authorityDescription;
        this.authorityRemark = authorityRemark;
    }

    @Override
    public void toBrowser(Long browserId) {
        VoOut.super.toBrowser(browserId);

        this.authorityName = browserId!=0?null:authorityName;
        this.authorityDescription = browserId!=1?null:authorityDescription;
        this.authorityRemark = browserId!=2?null:authorityRemark;
    }
}
