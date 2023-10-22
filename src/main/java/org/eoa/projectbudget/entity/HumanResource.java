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
 * @Author: 张骏山
 * @Date: 2023/10/22 19:47
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: HumanResource
 * @Description: 人力资源
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("human_resource")
public class HumanResource {
    @TableId(type = IdType.AUTO)
    Long dataId;
    String loginName;
    String password;
    String name;
    Integer sex;
    Date birth;
    String telephone;
    String mail;
    String phone;
    String fax;
    String workCode;
    Long section;
    Long depart;
    String job;
    Long directorLeader;
    Long supporter;
    Long photo;
    String signature;
    Date lastLogin;

}
