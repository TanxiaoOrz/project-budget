package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.PageConfig;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.vo.out.PageConfigOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2024/1/17 16:09
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: PageConfigOutFactory
 * @Description: 页面配置输出工厂
 * @Version: 1.0
 **/
@Component
public class PageConfigOutFactory implements OutFactory<PageConfig, PageConfigOut> {
    @Autowired
    HumanMapper humanMapper;

    @Override
    public PageConfigOut out(PageConfig pageConfig) {
        if (pageConfig == null) {
            return null;
        }
        PageConfigOut pageConfigOut = new PageConfigOut(pageConfig);
        HumanResource humanResource = humanMapper.selectById(pageConfig.getCreator());
        return pageConfigOut.setCreatorName(humanResource==null?"":humanResource.getName());
    }

    @Override
    public List<PageConfigOut> outs(List<? extends PageConfig> pageConfigs) {
        if (pageConfigs == null) {
            return null;
        }
        return pageConfigs.stream().map(this::out).collect(Collectors.toList());
    }
}