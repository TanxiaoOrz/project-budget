package org.eoa.projectbudget.vo.in;

import org.eoa.projectbudget.entity.Menu;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.utils.DataProcessUtils;

/**
 * @Author: 张骏山
 * @Date: 2024/1/16 23:02
 * @PackageName: org.eoa.projectbudget.vo.in
 * @ClassName: MenuIn
 * @Description: 页面菜单输入类
 * @Version: 1.0
 */
public class MenuIn implements CheckParameter<Menu> {

    String contentName;
    Long belongContent;
    String contentUrl;
    Integer viewNo;
    String shareAuthority;


    @Override
    public void checkSelf() throws ParameterException {
        if (DataProcessUtils.isEmpty(contentName)) {
            throw new ParameterException("contentName","","缺少菜单名称");
        }
    }

    @Override
    public Menu toEntity(Long dataId) throws ParameterException {
        checkSelf();
        return new Menu(dataId, contentName, belongContent, contentUrl, viewNo, 0 , shareAuthority);
    }

    public String getContentName() {
        return contentName;
    }

    public MenuIn setContentName(String contentName) {
        this.contentName = contentName;
        return this;
    }

    public Long getBelongContent() {
        return belongContent;
    }

    public MenuIn setBelongContent(Long belongContent) {
        this.belongContent = belongContent;
        return this;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public MenuIn setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
        return this;
    }

    public Integer getViewNo() {
        return viewNo;
    }

    public MenuIn setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
        return this;
    }

    public String getShareAuthority() {
        return shareAuthority;
    }

    public MenuIn setShareAuthority(String shareAuthority) {
        this.shareAuthority = shareAuthority;
        return this;
    }
}
