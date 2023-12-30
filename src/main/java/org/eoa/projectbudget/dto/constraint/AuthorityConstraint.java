package org.eoa.projectbudget.dto.constraint;

import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.utils.ContextUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 张骏山
 * @Date: 2023/10/31 11:38
 * @PackageName: org.eoa.projectbudget.utils.authority
 * @ClassName: TableColumn
 * @Description: 根据表单字段进行字段限定
 * @Version 1.0
 **/
public class AuthorityConstraint implements AuthoritySolve, FormSolve {
    List<Long> authorities;

    @Override
    public boolean solve(HumanDto user, HumanDto creator) {
        if (user.getAuthorities().contains(1))
            return true;
        return new HashSet<>(user.getAuthorities()).containsAll(authorities.stream().map(Long::intValue).toList());
    }

    @Override
    public List<Long> get(HumanDto creator) {
        HumanMapper humanMapper = ContextUtils.getInstance().getHumanMapper();
        ArrayList<Long> longs = new ArrayList<>();
        authorities.forEach(authorityId->longs.addAll(humanMapper.getFromAuthorityIds(authorityId)));
        return longs.stream().distinct().toList();
    }

    @Override
    public boolean solve(HumanDto user, FormOutDto formOutDto) throws EoaException {
        if (user.getAuthorities().contains(1))
            return true;
        for (Long authority:
             authorities) {
            Set<Integer> asked = new HashSet<>();

            // 判断列是否在表单中
            inputValues(formOutDto, asked, authority);


            for (Integer ask:
                 asked) {
                if (user.getAuthorities().contains(ask))
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<Long> get(FormOutDto formOutDto) {
        HumanMapper humanMapper = ContextUtils.getInstance().getHumanMapper();
        ArrayList<Long> longs = new ArrayList<>(humanMapper.getFromAuthorityIds(1L));
        Set<Integer> askeds = new HashSet<>();
        for (Long authority:
                authorities) {
            inputValues(formOutDto, askeds, authority);
        }
        askeds.forEach(asked->longs.addAll(humanMapper.getFromAuthorityIds(Long.valueOf(asked))));
        return longs.stream().distinct().toList();
    }

    private void inputValues(FormOutDto formOutDto, Set<Integer> askeds, Long authority) {
        if (formOutDto.getColumn(authority) == null) {
            throw new DataException(formOutDto.getTable().getTableDataName(), formOutDto.getDataId().toString(),"authority",authorities.toString(),
                    String.format("%d不在该表单的字段中",authority));
        }
        boolean isFound = false;
        Object mainValue = formOutDto.getMainValue(authority);

        if (mainValue != null) {
            askeds.add((Integer) mainValue);
            isFound = true;
        }

        if (!isFound) {
            List<Object> detailsValues = formOutDto.getDetailsValues(authority);
            if (detailsValues != null) {
                askeds.addAll(detailsValues.stream().map(o -> (Integer) o).toList());
                isFound = true;
            }
        }
    }

    public List<Long> getAuthorities() {
        return authorities;
    }

    public AuthorityConstraint setAuthorities(List<Long> authorities) {
        this.authorities = authorities;
        return this;
    }
}
