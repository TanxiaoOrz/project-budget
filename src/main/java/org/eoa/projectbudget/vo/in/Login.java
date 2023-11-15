package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.ibatis.annotations.Property;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2023/11/11 22:47
 * @PackageName: IntelliJ IDEA
 * @ClassName: Login
 * @Description: 登录结构VO
 * @Version: 1.0
 */

@Schema(name = "Login" , description = "登录传入结构体")
public class Login implements CheckParameter<Login>{
    @Schema(name = "loginName",description = "用户名",type = "string")
    String loginName;
    @Schema(name = "password",description = "密码",type = "password")
    String password;

    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(loginName)) {
            throw new ParameterException("loginName","","没有loginName");
        }

        if (DataProcessUtils.isEmpty(password)) {
            throw new ParameterException("password","","没有password");
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public Login setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Login setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public Login toEntity(Long dataId) throws ParameterException {
        return this;
    }
}
