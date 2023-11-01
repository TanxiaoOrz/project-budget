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
 * @Date: 2023/10/22 20:00
 * @PackageName: org.eoa.projectbudget.entity
 * @ClassName: HumanResourceView
 * @Description: 人力资源视图类
 * @Version 1.0
 **/

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("human_view")
public class HumanResourceView {
    @TableId(type = IdType.AUTO)
    Long dataId;
    String loginName;
    String password;
    String name;
    Integer sex;
    Date birth;
    Integer age;    //视图计算添加项
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
    Integer safety;
}
