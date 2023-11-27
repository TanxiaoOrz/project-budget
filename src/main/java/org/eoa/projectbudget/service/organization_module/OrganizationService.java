package org.eoa.projectbudget.service.organization_module;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.entity.Section;
import org.eoa.projectbudget.exception.ParameterException;

import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/23 16:26
 * @PackageName: org.eoa.projectbudget.service.organization_module
 * @ClassName: OrganizationService
 * @Description: 组织架构业务层声明
 * @Version 1.0
 */
public interface OrganizationService {

    /**
     * 新建人力资源
     * @param humanResource 人力资源
     * @param userId 操作人员编号
     * @return 结果数字
     */
    Integer newHuman(HumanResource humanResource, Long userId);

    /**
     * 更新人力资源
     * @param humanResource 人力资源
     * @param userId 操作人员编号
     * @return 结果数字
     */
    Integer updateHuman(HumanResource humanResource, Long userId);

    /**
     * 获取人力资源
     * @param humanId 人力资源标号
     * @param userId 操作人员编号
     * @return 结果
     */
    HumanResourceView getHuman(Long humanId, Long userId);

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    HumanDto getHumanDto(String userName, String password) throws ParameterException;

    /**
     * 缓存过期时获取用户数据
     * @param humanId 想要获取的人员id
     * @param userId 操作人
     * @return 人员
     * @throws ParameterException 人员id不存在
     */
    HumanDto getHumanDto(Long humanId, Long userId) throws ParameterException;

    /**
     * 删除人力资源
     * @param humanId 人力资源标号
     * @param userId 操作人员编号
     * @return 给过
     */
    Integer dropHuman(Long humanId, Long userId);

    Integer newDepart(Depart depart,Long userId);

    Integer updateDepart(Depart depart, Long userId);

    Depart getDepart(Long departId, Long userId);

    Integer dropDepart(Long departId, Long userId, boolean recursion);

    Integer newSection(Section Section,Long userId);

    Integer updateSection(Section Section, Long userId);

    Section getSection(Long SectionId, Long userId);

    Integer dropSection(Long SectionId, Long userId, boolean recursion);

    List<HumanResourceView> getHumanList(QueryWrapper<HumanResourceView> wrapper,Long userId);

    List<Depart> getDepartList(QueryWrapper<Depart> wrapper,Long userId);

    List<Section> getSectionList(QueryWrapper<Section> wrapper,Long userId);

}
