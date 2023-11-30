package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.entity.Section;
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
        HumanResourceView leader = humanViewMapper.selectById(humanResourceView.getDirectorLeader());
        Depart depart = departMapper.selectById(humanResourceView.getDepart());
        Section section = sectionMapper.selectById(humanResourceView.getSection());
        return new HumanOut(humanResourceView)
                .setLeaderName(leader==null?null:leader.getName())
                .setDepartName(depart==null?null:depart.getDepartName())
                .setSectionName(section==null?null:section.getSectionName());
    }

    @Override
    public List<HumanOut> outs(List<? extends HumanResourceView> humanResourceViews) {
        if (humanResourceViews == null) {
            return null;
        }
        return humanResourceViews.stream().map(this::out).toList();
    }
}
