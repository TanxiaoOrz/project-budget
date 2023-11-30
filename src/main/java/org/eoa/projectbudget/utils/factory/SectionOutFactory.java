package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.Section;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.SectionMapper;
import org.eoa.projectbudget.vo.out.SectionOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/29 20:57
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: SectionOutFactory
 * @Description: 分部输出类工厂
 * @Version: 1.0
 */

@Component
public class SectionOutFactory implements OutFactory<Section, SectionOut> {
    @Autowired
    HumanMapper humanMapper;

    @Autowired
    SectionMapper sectionMapper;
    @Override
    public SectionOut out(Section section) {
        if (section == null) {
            return null;
        }
        HumanResource humanResource = humanMapper.selectById(section.getSectionManager());
        Section section1 = sectionMapper.selectById(section.getBelongSection());
        return new SectionOut(section)
                .setManagerName(humanResource==null?null:humanResource.getName())
                .setBelongSectionName(section1==null?null:section1.getSectionName());
    }

    @Override
    public List<SectionOut> outs(List<? extends Section> sections) {
        if (sections == null) {
            return null;
        }
        return sections.stream().map(this::out).toList();
    }
}
