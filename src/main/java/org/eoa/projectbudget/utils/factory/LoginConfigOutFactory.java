package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.LoginConfig;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.vo.out.LoginConfigOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2024/1/17 16:01
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: LoginConfigOutFactory
 * @Description: 登陆配置输出工厂
 * @Version: 1.0
 **/
@Component
public class LoginConfigOutFactory implements OutFactory<LoginConfig, LoginConfigOut> {
    @Autowired
    HumanMapper humanMapper;

    @Override
    public LoginConfigOut out(LoginConfig loginConfig) {
        if (loginConfig == null) {
            return null;
        }
        LoginConfigOut loginConfigOut = new LoginConfigOut(loginConfig);
        HumanResource humanResource = humanMapper.selectById(loginConfig.getCreator());
        return loginConfigOut.setCreatorName(humanResource==null?"":humanResource.getName());
    }

    @Override
    public List<LoginConfigOut> outs(List<? extends LoginConfig> loginConfigs) {
        if (loginConfigs == null) {
            return null;
        }
        return loginConfigs.stream().map(this::out).collect(Collectors.toList());
    }
}
