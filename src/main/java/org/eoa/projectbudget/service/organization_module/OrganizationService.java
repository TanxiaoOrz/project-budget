package org.eoa.projectbudget.service.organization_module;

import org.eoa.projectbudget.entity.Depart;
import org.eoa.projectbudget.entity.HumanResource;
import org.eoa.projectbudget.entity.HumanResourceView;
import org.eoa.projectbudget.entity.Section;

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
     * @param isDto 是否dto包装
     * @return 结果
     */
    HumanResourceView getHuman(Long humanId, Long userId, boolean isDto);

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

}
