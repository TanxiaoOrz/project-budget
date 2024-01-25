package org.eoa.projectbudget.service.organization_module.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.entity.Section;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.*;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.DataProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:58
 * @PackageName: org.eoa.projectbudget.service.organization_module.impl
 * @ClassName: OrganizationServiceImpl
 * @Description: 组织结构业务是实现类
 * @Version: 1.4
 **/

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    HumanMapper humanMapper;
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
    public Long newHuman(HumanResource humanResource, Long userId) {
        humanResource.setIsDeprecated(0);
        if (DataProcessUtils.isEmpty(humanResource.getPassword()))
            throw new ParameterException("password","","密码为空");

        Depart depart = departMapper.selectById(humanResource.getDepart());

        if (depart == null) {
            log.error("用户=>{}创建人员=>{},部门=>{}不存在",userId,humanResource,humanResource.getDepart());
            throw new ParameterException("depart",humanResource.getDepart().toString(),"部门不存在");
        }
        if (DataProcessUtils.translateIntegerToBoolean(depart.getIsDeprecated()))
            throw new ParameterException("depart",humanResource.getDepart().toString(),"部门已废弃");

        humanResource.setSection(depart.getBelongSection());
        log.info("用户=>{}创建人员=>{}",userId,humanResource);
        humanMapper.insert(humanResource);
        return humanResource.getDataId();
    }

    @Override
    @CacheEvict(cacheNames = "humanDto",key = "#humanResource.dataId")
    public Integer updateHuman(HumanResource humanResource, Long userId) {
        humanResource.setIsDeprecated(0);

        if (DataProcessUtils.isEmpty(humanResource.getPassword()))
            humanResource.setPassword(humanMapper.selectById(humanResource.getDataId()).getPassword());

        Depart depart = departMapper.selectById(humanResource.getDepart());

        if (depart == null) {
            log.error("用户=>{}创建人员=>{},部门=>{}不存在",userId,humanResource,humanResource.getDepart());
            throw new ParameterException("depart",humanResource.getDepart().toString(),"部门不存在");
        }
        if (DataProcessUtils.translateIntegerToBoolean(depart.getIsDeprecated()))
            throw new ParameterException("depart",humanResource.getDepart().toString(),"部门已废弃");
        humanResource.setSection(depart.getBelongSection());


        int update = humanMapper.updateById(humanResource);
        log.info("用户=>{}修改人员=>{},修改数量=>{}",userId,humanResource,update);
        return update;
    }

    @Override
    public HumanResourceView getHuman(Long humanId, Long userId) {
        log.info("用户=>{}获取人员=>{}",userId,humanId);
        HumanResourceView humanResourceView = humanViewMapper.selectById(humanId);
        if (humanResourceView == null) {
            log.error("人员=>{}不存在",humanId);
            throw new ParameterException("dataId", humanId.toString(), "不存在该编号");
        }
        return humanResourceView;
    }

    @Override
    public Long checkLogin(String loginName, String password) throws ParameterException {
        log.info("用户名=>{}尝试登录",loginName);
        HumanResourceView humanResourceView = humanViewMapper.selectOne(new QueryWrapper<HumanResourceView>().eq("loginName", loginName).eq("password", password));
        if (humanResourceView == null) {
            log.info("未检索到符合输入用户名与密码的人员");
            throw new ParameterException("longinName",loginName,"用户名或密码不正确");
        }
        return humanResourceView.getDataId();
    }

    private HumanDto getHumanDto(HumanResourceView humanResourceView) {
        Long humanId = humanResourceView.getDataId();
        HashMap<Long, Integer> characters = new HashMap<>();
        List<CharacterMapper.Grade> characterIdFromHuman = characterMapper.getCharacterIdFromHuman(humanId);
        characterIdFromHuman.forEach(grade -> characters.put(grade.getCharacterId(), grade.getGrade()));

        HashSet<Integer> authorities = new HashSet<>(humanMapper.getHumansAuthority(humanId));
        HashSet<Long> leaderRecursion = new HashSet<>();
        Long nextLeader = humanResourceView.getDirectorLeader();
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
    @Cacheable(cacheNames = "humanDto",key = "#humanId")
    public HumanDto getHumanDto(Long humanId, Long userId) throws ParameterException {
        if (userId == null) {
            HumanResourceView humanResourceView = humanViewMapper.selectById(humanId);
            if (humanResourceView == null) {
                return null;
            }
            return getHumanDto(humanResourceView);
        }

        log.info("用户=>{}获取人员数据=>{}",userId,humanId);
        HumanResourceView humanResourceView = humanViewMapper.selectById(humanId);
        if (humanResourceView == null) {
            log.info("无该编号成员");
            throw new ParameterException("humanId",humanId.toString(),"无该编号成员");
        }
        log.info("获取成功");
        return getHumanDto(humanResourceView);
    }

    @Override
    @CacheEvict(cacheNames = "humanDto",key = "#humanId")
    public Integer dropHuman(Long humanId, Long userId) {
        HumanResource humanResource = humanMapper.selectById(humanId);
        humanResource.setIsDeprecated(1);
        int update = humanMapper.updateById(humanResource);
        log.info("用户=>{}废弃人员=>{},废弃数量=>{}",userId,humanId,update);
        return update;
    }

    @Override
    public Long newDepart(Depart depart, Long userId) {
        if (depart.getBelongDepart() != null) {
            Depart leadDepart = departMapper.selectById(depart.getBelongDepart());
            if (leadDepart == null) {
                log.error("用户=>{}创建部门=>{},部门=>{}不存在",userId,depart,depart.getBelongDepart());
                throw new ParameterException("depart",depart.getBelongDepart().toString(),"部门不存在");
            }
            if (DataProcessUtils.translateIntegerToBoolean(leadDepart.getIsDeprecated())) {
                log.error("用户=>{}创建部门=>{},部门=>{}不存在",userId,depart,depart.getBelongDepart());
                throw new ParameterException("depart",depart.getBelongDepart().toString(),"部门已废弃");
            }
            depart.setBelongSection(leadDepart.getBelongSection());
        }
        depart.setIsDeprecated(0);
        if (depart.getCreateTime() == null) {
            depart.setCreateTime(new Date());

        }
        Section section = sectionMapper.selectById(depart.getBelongSection());
        if (section == null||DataProcessUtils.translateIntegerToBoolean(section.getIsDeprecated())) {
            log.error("用户=>{}创建部门=>{},分部=>{}不存在",userId,depart,depart.getBelongSection());
            throw new ParameterException("depart",depart.getBelongSection().toString(),"分部不存在或已废弃");
        }
        log.info("用户=>{}创建部门=>{}",userId,depart);
        departMapper.insert(depart);
        return depart.getDataId();
    }

    @Override
    public Integer updateDepart(Depart depart, Long userId) {
        if (depart.getBelongDepart() != null) {
            Depart leadDepart = departMapper.selectById(depart.getBelongDepart());
            if (leadDepart == null) {
                log.error("用户=>{}修改部门=>{},部门=>{}不存在",userId,depart,depart.getBelongDepart());
                throw new ParameterException("depart",depart.getBelongDepart().toString(),"部门不存在");
            }
            if (DataProcessUtils.translateIntegerToBoolean(leadDepart.getIsDeprecated())) {
                log.error("用户=>{}创建部门=>{},部门=>{}不存在",userId,depart,depart.getBelongDepart());
                throw new ParameterException("depart",depart.getBelongDepart().toString(),"部门已废弃");
            }
            depart.setBelongSection(leadDepart.getBelongSection());
        }
        depart.setIsDeprecated(0);
        if (depart.getCreateTime() == null) {
            depart.setCreateTime(departMapper.selectById(depart.getDataId()).getCreateTime());
        }
        Section section = sectionMapper.selectById(depart.getBelongSection());
        if (section == null||DataProcessUtils.translateIntegerToBoolean(section.getIsDeprecated())) {
            log.error("用户=>{}创建部门=>{},分部=>{}不存在",userId,depart,depart.getBelongSection());
            throw new ParameterException("depart",depart.getBelongSection().toString(),"分部不存在或已废弃");
        }
        int update = departMapper.updateById(depart);
        log.info("用户=>{}修改部门=>{},修改数量=>{}",userId,depart,update);
        return update;
    }

    @Override
    public Depart getDepart(Long departId, Long userId) {
        log.info("用户=>{}获取部门=>{}",userId,departId);
        Depart depart = departMapper.selectById(departId);
        if (depart == null) {
            log.error("部门=>{}不存在",departId);
            throw new ParameterException("dataId", departId.toString(), "不存在该编号");
        }
        return depart;
    }

    @Override
    public Integer dropDepart(Long departId, Long userId, boolean recursion) {
        Depart depart = departMapper.selectById(departId);
        depart.setIsDeprecated(1);
        int update = departMapper.updateById(depart);
        log.info("用户=>{}废弃部门=>{},修改数量=>{}",userId,departId,update);
        if (recursion) {
            dropDepartRecursion(departId);
        }
        return update;
    }

    private void dropDepartRecursion(Long belongId) {
        List<Long> belongs = departMapper.selectList(new QueryWrapper<Depart>()
                .eq("belongDepart",belongId)
                .eq("isDeprecated",0)).stream().map(Depart::getDataId).toList();
        Integer dropped = departMapper.dropBelongDepart(belongId);
        log.info("废弃部门=>{},连带废弃子部门=>{},废弃数量=>{}",belongId,belongs,dropped);
        belongs.forEach(this::dropDepartRecursion);
    }


    @Override
    public Long newSection(Section section, Long userId) {
        section.setIsDeprecated(0);
        if (section.getCreateTime() == null)
            section.setCreateTime(new Date());

        Long belongSection = section.getBelongSection();
        Section sectionLead = sectionMapper.selectById(belongSection);
        if (belongSection!= 0 && (sectionLead == null||DataProcessUtils.translateIntegerToBoolean(sectionLead.getIsDeprecated()))) {
            log.error("用户=>{}创建分部=>{},分部=>{}不存在",userId,section, belongSection);
            throw new ParameterException("section", belongSection.toString(),"分部不存在或已废弃");
        }

        log.info("用户=>{}创建分部=>{}",userId,section);
        sectionMapper.insert(section);
        return section.getDataId();
    }

    @Override
    public Integer updateSection(Section section, Long userId) {
        section.setIsDeprecated(0);
        if (section.getCreateTime() == null)
            section.setCreateTime(sectionMapper.selectById(section.getDataId()).getCreateTime());

        Long belongSection = section.getBelongSection();
        Section sectionLead = sectionMapper.selectById(belongSection);
        if (belongSection!= 0 && (sectionLead == null||DataProcessUtils.translateIntegerToBoolean(sectionLead.getIsDeprecated()))) {
            log.error("用户=>{}修改分部=>{},分部=>{}不存在",userId,section, belongSection);
            throw new ParameterException("section", belongSection.toString(),"分部不存在或已废弃");
        }

        int update = sectionMapper.updateById(section);
        log.info("用户=>{}修改部门=>{},修改数量=>{}",userId,section,update);
        return update;
    }

    @Override
    public Section getSection(Long sectionId, Long userId) {
        log.info("用户=>{}获取分部=>{}",userId,sectionId);
        Section section = sectionMapper.selectById(sectionId);
        if (section == null) {
            log.error("分部=>{}不存在",sectionId);
            throw new ParameterException("dataId", sectionId.toString(), "不存在该编号");
        }
        return section;
    }

    @Override
    public Integer dropSection(Long sectionId, Long userId, boolean recursion) {
        Section section = sectionMapper.selectById(sectionId);
        section.setIsDeprecated(1);
        int update = sectionMapper.updateById(section);
        log.info("用户=>{}废弃分部=>{},修改数量=>{}",userId,sectionId,update);
        if (recursion) {
            dropSectionRecursion(sectionId);
        }
        return update;
    }

    private void dropSectionRecursion(Long belongId) {

        List<Long> belongDepart = departMapper.selectList(new QueryWrapper<Depart>()
                .eq("belongSection",belongId)
                .eq("isDeprecated",0)).stream().map(Depart::getDataId).toList();
        Integer droppedDepartCount = departMapper.dropBelongDepart(belongId);
        log.info("废弃分部=>{},连带废弃子部门=>{},废弃数量=>{}",belongId,belongDepart,droppedDepartCount);
        belongDepart.forEach(this::dropDepartRecursion);


        List<Long> belongSection = sectionMapper.selectList(new QueryWrapper<Section>()
                .eq("belongSection",belongId)
                .eq("isDeprecated",0)).stream().map(Section::getDataId).toList();
        Integer droppedSectionCount = sectionMapper.dropBelongDepart(belongId);
        log.info("废弃分部=>{},连带废弃子分部=>{},废弃数量=>{}",belongId,belongSection,droppedSectionCount);
        belongSection.forEach(this::dropDepartRecursion);
    }

    @Override
    public List<HumanResourceView> getHumanList(QueryWrapper<HumanResourceView> wrapper, Long userId) {
        List<HumanResourceView> humanResourceViews = humanViewMapper.selectList(wrapper);
        log.info("用户=>{}获取人员表单,获取数量=>{}",userId,humanResourceViews.size());
        return humanResourceViews;
    }

    @Override
    public List<Depart> getDepartList(QueryWrapper<Depart> wrapper, Long userId) {
        List<Depart> departs = departMapper.selectList(wrapper);
        log.info("用户=>{}获取部门表单,获取数量=>{}",userId,departs.size());
        return departs;
    }

    @Override
    public List<Section> getSectionList(QueryWrapper<Section> wrapper, Long userId) {
        List<Section> sections = sectionMapper.selectList(wrapper);
        log.info("用户=>{}获取分部表单,获取数量=>{}",userId,sections.size());
        return sections;
    }
}
