package org.eoa.projectbudget.service.organization_module.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.*;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.*;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:58
 * @PackageName: org.eoa.projectbudget.service.organization_module.impl
 * @ClassName: OrganizationServiceImpl
 * @Description: TODO
 * @Version: 1.0
 **/

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    HumanViewMapper humanViewMapper;
    @Autowired
    AuthorityMapper authorityMapper;
    @Autowired
    CharacterMapper characterMapper;
    @Autowired
    DepartMapper departMapper;
    @Autowired
    SectionMapper sectionMapper;


    private final Logger log = LoggerFactory.getLogger("AuthorityModule");

    @Override
    public Integer newHuman(HumanResource humanResource, Long userId) {
        return null;
    }

    @Override
    public Integer updateHuman(HumanResource humanResource, Long userId) {
        return null;
    }

    @Override
    public HumanResourceView getHuman(Long humanId, Long userId) {
        return null;
    }

    @Override
    public HumanDto getHumanDto(String loginName, String password) throws ParameterException {
        log.info("用户名=>{}尝试登录",loginName);
        HumanResourceView humanResourceView = humanViewMapper.selectOne(new QueryWrapper<HumanResourceView>().eq("loginName", loginName).eq("password", password));

        if (humanResourceView == null) {
            log.info("未检索到符合输入用户名与密码的人员");
            throw new ParameterException("longinName + password",loginName,"用户名或密码不正确");
        }

        Long humanId = humanResourceView.getDataId();
        log.info("检索到对应人员dataId=>{}", humanId);

        return getHumanDto(humanResourceView);
    }

    private HumanDto getHumanDto(HumanResourceView humanResourceView) {
        Long humanId = humanResourceView.getDataId();
        HashMap<Long, Integer> characters = new HashMap<>();
        List<CharacterMapper.Grade> characterIdFromHuman = characterMapper.getCharacterIdFromHuman(humanId);
        characterIdFromHuman.forEach(grade -> {
            characters.put(grade.getCharacterId(), grade.getGrade());
        });

        HashSet<Integer> authorities = new HashSet<>(authorityMapper.getIdsFormHuman(humanId));
        characterIdFromHuman.forEach(grade -> authorities.addAll(authorityMapper.getIdsFormCharacter(grade.getCharacterId())));
        HashSet<Long> leaderRecursion = new HashSet<>();
        Long nextLeader = humanResourceView.getDepart();
        while (nextLeader!=0) {
            leaderRecursion.add(nextLeader);
            nextLeader = humanViewMapper.getLeader(nextLeader);
        }

        HashSet<Long> sectionRecursion = new HashSet<>();
        Long nextSection = humanResourceView.getSection();
        while (nextSection!=0) {
            sectionRecursion.add(nextSection);
            nextSection = sectionMapper.getBelong(nextSection);
        }

        return new HumanDto(humanResourceView, characters, authorities, leaderRecursion, sectionRecursion);
    }

    @Override
    public HumanDto getHumanDto(Long humanId, Long userId) throws ParameterException {
        log.info("用户=>{}重新获取人员数据=>{}",userId,humanId);
        HumanResourceView humanResourceView = humanViewMapper.selectById(humanId);
        if (humanResourceView == null) {
            log.info("无该编号成员");
            throw new ParameterException("humanId",humanId.toString(),"用户名或密码不正确");
        }
        log.info("获取成功");
        return getHumanDto(humanResourceView);
    }

    @Override
    public Integer dropHuman(Long humanId, Long userId) {
        return null;
    }

    @Override
    public Integer newDepart(Depart depart, Long userId) {
        return null;
    }

    @Override
    public Integer updateDepart(Depart depart, Long userId) {
        return null;
    }

    @Override
    public Depart getDepart(Long departId, Long userId) {
        return null;
    }

    @Override
    public Integer dropDepart(Long departId, Long userId, boolean recursion) {
        return null;
    }

    @Override
    public Integer newSection(Section Section, Long userId) {
        return null;
    }

    @Override
    public Integer updateSection(Section Section, Long userId) {
        return null;
    }

    @Override
    public Section getSection(Long SectionId, Long userId) {
        return null;
    }

    @Override
    public Integer dropSection(Long SectionId, Long userId, boolean recursion) {
        return null;
    }
}
