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
    Integer isDeprecated;

    public HumanResourceView(HumanResourceView humanResourceView) {
        this.dataId = humanResourceView.dataId;
        this.loginName = humanResourceView.loginName;
        this.password = humanResourceView.password;
        this.name = humanResourceView.name;
        this.sex = humanResourceView.sex;
        this.birth = humanResourceView.birth;
        this.telephone = humanResourceView.telephone;
        this.mail = humanResourceView.mail;
        this.phone = humanResourceView.phone;
        this.fax = humanResourceView.fax;
        this.workCode = humanResourceView.workCode;
        this.section = humanResourceView.section;
        this.depart = humanResourceView.depart;
        this.job = humanResourceView.job;
        this.directorLeader = humanResourceView.directorLeader;
        this.supporter = humanResourceView.supporter;
        this.photo = humanResourceView.photo;
        this.signature = humanResourceView.signature;
        this.lastLogin = humanResourceView.lastLogin;
        this.safety = humanResourceView.safety;
        this.age = humanResourceView.age;
        this.isDeprecated = humanResourceView.isDeprecated;
    }
}
