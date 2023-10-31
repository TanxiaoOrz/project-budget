package org.eoa.projectbudget.utils.authority;

import org.eoa.projectbudget.dto.HumanDto;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:14
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: Proposed
 * @Description: TODO
 * @Version 1.0
 **/


public class Proposed implements AuthoritySolve {
    List<Long> humans;
    List<Long> departs;
    List<Long> sections;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        if (humans.contains(user.getDataId()))
            return true;
        if (departs.contains(user.getDepart()))
            return true;
        return sections.contains(user.getSection());
    }

    public List<Long> getHumans() {
        return humans;
    }

    public Proposed setHumans(List<Long> humans) {
        this.humans = humans;
        return this;
    }

    public List<Long> getDeparts() {
        return departs;
    }

    public Proposed setDeparts(List<Long> departs) {
        this.departs = departs;
        return this;
    }

    public List<Long> getSections() {
        return sections;
    }

    public Proposed setSections(List<Long> sections) {
        this.sections = sections;
        return this;
    }

}
