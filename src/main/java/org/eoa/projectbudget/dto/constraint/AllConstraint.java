package org.eoa.projectbudget.dto.constraint;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.utils.ContextUtils;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/1 16:40
 * @PackageName: org.eoa.projectbudget.vo.constraint
 * @ClassName: AllConstraint
 * @Description: 所有人安全等级限制
 * @Version: 1.0
 **/
public class AllConstraint implements FormSolve, AuthoritySolve {

    Long start;
    Long end;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        return (user.getSafety()>=start && user.getSafety() <= end);
    }

    @Override
    public List<Long> get(HumanDto creator) {
        HumanMapper humanMapper = ContextUtils.getInstance().getHumanMapper();
        return humanMapper.selectList(new QueryWrapper<HumanResource>().between("safety",start,end)).stream().map(HumanResource::getDataId).toList();
    }

    @Override
    public boolean solve(HumanDto user, FormOutDto formOutDto) throws EoaException {
        Float start =getaFloat(formOutDto,this.start);
        Float end = getaFloat(formOutDto, this.end);

        return (user.getSafety()>=start && user.getSafety() <= end);
    }

    @Override
    public List<Long> get(FormOutDto formOutDto) {
        HumanMapper humanMapper = ContextUtils.getInstance().getHumanMapper();
        Float start = getaFloat(formOutDto, this.start);
        Float end = getaFloat(formOutDto, this.end);
        return humanMapper.selectList(new QueryWrapper<HumanResource>().between("safety",start,end)).stream().map(HumanResource::getDataId).toList();
    }

    private Float getaFloat(FormOutDto formOutDto, Long id) {
        Float value = (Float) formOutDto.getMainValue(id);
        if (value == null) {
            value = 0.0F;
        }
        if (formOutDto.getColumn(id) == null) {
            throw new DataException(formOutDto.getTable().getTableDataName(), formOutDto.getDataId().toString(), "authority", id.toString(),
                    String.format("%d不在该表单的字段中", id));
        }
        return value;
    }

    public Long getStart() {
        return start;
    }

    public AllConstraint setStart(Long start) {
        this.start = start;
        return this;
    }

    public Long getEnd() {
        return end;
    }

    public AllConstraint setEnd(Long end) {
        this.end = end;
        return this;
    }
}
