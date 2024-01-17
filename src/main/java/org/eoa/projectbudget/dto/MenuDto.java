package org.eoa.projectbudget.dto;

import org.eoa.projectbudget.dto.constraint.Constraint;
import org.eoa.projectbudget.entity.Menu;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.utils.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 22:53
 * @PackageName: org.eoa.projectbudget.dto
 * @ClassName: MenuDto
 * @Description: 菜单运算类
 * @Version: 1.0
 */
public class MenuDto {

    Long dataId;
    String contentName;
    Long belongContent;
    String contentUrl;
    Integer viewNo;
    Integer isDeprecated;
    String shareAuthority;
    Long creator;
    Date createTime;

    List<MenuDto> children;
    
    

    public MenuDto(Menu menu) {
        this.dataId = menu.getDataId();
        this.contentName = menu.getContentName();
        this.belongContent = menu.getBelongContent();
        this.contentUrl = menu.getContentUrl();
        this.viewNo = menu.getViewNo();
        this.isDeprecated = menu.getIsDeprecated();
        this.shareAuthority = menu.getShareAuthority();
        this.creator = menu.getCreator();
        this.createTime = menu.getCreateTime();
    }

    public MenuDto filter(HumanDto humanDto,OrganizationService organizationService) {
        this.children = children.stream().filter(menuDto -> {
            Constraint constraint = AuthorityUtils.getConstraint(menuDto.getShareAuthority());
            return AuthorityUtils.checkAuthority(humanDto,organizationService.getHumanDto(menuDto.getCreator(),null),constraint);
        }).collect(Collectors.toList());
        children.forEach(menuDto -> menuDto.filter(humanDto, organizationService));
        return this;
    }
    
    public Long getDataId() {
        return dataId;
    }


    public MenuDto setDataId(Long dataId) {
        this.dataId = dataId;
        return this;
    }

    
    public String getContentName() {
        return contentName;
    }

    
    public MenuDto setContentName(String contentName) {
        this.contentName = contentName;
        return this;
    }

    
    public Long getBelongContent() {
        return belongContent;
    }

    
    public MenuDto setBelongContent(Long belongContent) {
        this.belongContent = belongContent;
        return this;
    }

    
    public String getContentUrl() {
        return contentUrl;
    }

    
    public MenuDto setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
        return this;
    }

    
    public Integer getViewNo() {
        return viewNo;
    }

    
    public MenuDto setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    
    public Integer getIsDeprecated() {
        return isDeprecated;
    }

    
    public MenuDto setIsDeprecated(Integer isDeprecated) {
        this.isDeprecated = isDeprecated;
        return this;
    }

    
    public String getShareAuthority() {
        return shareAuthority;
    }

    
    public MenuDto setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }

    
    public Long getCreator() {
        return creator;
    }

    
    public MenuDto setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    
    public Date getCreateTime() {
        return createTime;
    }

    
    public MenuDto setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<MenuDto> getChildren() {
        return children;
    }

    public MenuDto setChildren(List<MenuDto> children) {
        this.children = children;
        return this;
    }
}
