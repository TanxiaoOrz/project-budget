package org.eoa.projectbudget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: 张骏山
 * @Date: 2023/11/3 16:37
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: Authority
 * @Description: 权限实体类
 * @Version: 1.0
 **/

@TableName("authority")
public class Authority {

    @TableId(type = IdType.AUTO)
    Long dataId;
    String authorityName;
    String authorityDescription;
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
}
