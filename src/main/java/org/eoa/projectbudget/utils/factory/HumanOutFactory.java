package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.mapper.DepartMapper;
import org.eoa.projectbudget.mapper.HumanViewMapper;
import org.eoa.projectbudget.mapper.SectionMapper;
import org.eoa.projectbudget.vo.out.HumanOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/29 20:42
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: HumanOutFactory
 * @Description: HumanOut构造工厂
 * @Version: 1.0
 */

@Component
public class HumanOutFactory implements OutFactory<HumanResourceView, HumanOut>{

    @Autowired
    HumanViewMapper humanViewMapper;
    @Autowired
    DepartMapper departMapper;
    @Autowired
    SectionMapper sectionMapper;

    @Override
    public HumanOut out(HumanResourceView humanResourceView) {
        if (humanResourceView == null) {
            return null;
        }
        return new HumanOut(humanResourceView)
                .setLeaderName(humanViewMapper.selectById(humanResourceView.getDirectorLeader()).getName())
                .setDepartName(departMapper.selectById(humanResourceView.getDepart()).getDepartName())
                .setSectionName(sectionMapper.selectById(humanResourceView.getSection()).getSectionName());
    }

    @Override
    public List<HumanOut> outs(List<? extends HumanResourceView> humanResourceViews) {
        if (humanResourceViews == null) {
            return null;
        }
        return humanResourceViews.stream().map(this::out).toList();
    }
}
