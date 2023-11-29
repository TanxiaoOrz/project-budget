package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.mapper.DepartMapper;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.SectionMapper;
import org.eoa.projectbudget.vo.out.DepartOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/29 20:51
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: DepartOutFactory
 * @Description: 部门输出构造结构体
 * @Version: 1.0
 */

@Component
public class DepartOutFactory implements OutFactory<Depart, DepartOut> {
    @Autowired
    HumanMapper humanMapper;
    @Autowired
    DepartMapper departMapper;
    @Autowired
    SectionMapper sectionMapper;

    @Override
    public DepartOut out(Depart depart) {
        if (depart == null) {
            return null;
        }
        return new DepartOut(depart)
                .setManagerName(humanMapper.selectById(depart.getDepartManager()).getName())
                .setBelongDepartName(departMapper.selectById(depart.getBelongDepart()).getDepartName())
                .setBelongSectionName(sectionMapper.selectById(depart.getBelongSection()).getSectionName());
    }

    @Override
    public List<DepartOut> outs(List<? extends Depart> departs) {
        if (departs == null) {
            return null;
        }
        return departs.stream().map(this::out).toList();
    }
}
