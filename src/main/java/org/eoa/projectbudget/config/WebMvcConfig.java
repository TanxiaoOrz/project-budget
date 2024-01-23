package org.eoa.projectbudget.config;

import org.eoa.projectbudget.interceptor.BackInterceptor;
import org.eoa.projectbudget.interceptor.LogInterceptor;
import org.eoa.projectbudget.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 所有标准阿皮操作都需要进行token检查
     */
    @Autowired
    TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/v1/**")
                .excludePathPatterns("/api/v1/token")
                .excludePathPatterns("/api/v1/token/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/api/v1/**")
                .excludePathPatterns("/api/v1/token")
                .excludePathPatterns("/api/v1/token/**");
        registry.addInterceptor(new BackInterceptor(new Long[]{2L}, "操作表单模块","需要表单模块操作权限,权限id=>2,请联系相关管理员添加"))
                .addPathPatterns("/api/v1/table/back/**");
        registry.addInterceptor(new BackInterceptor(new Long[]{4L},"操作目录模块","需要目录模块操作权限,权限id=>4,请联系相关管理员添加"))
                .addPathPatterns("/api/v1/content/back/**");
        registry.addInterceptor(new BackInterceptor(new Long[]{5L},"操作角色模块","需要权限模块操作权限,权限id=>5,请联系相关管理员添加"))
                .addPathPatterns("/api/v1/authority/back/**");
        registry.addInterceptor(new BackInterceptor(new  Long[]{7L},"操作流程模块","需要流程模块操作权限,权限id=>7,请联系相关管理员添加"))
                .addPathPatterns("/api/v1/workflow/back");
        registry.addInterceptor(new BackInterceptor(new  Long[]{3L},"操作组织模块","需要组织模块操作权限,权限id=>3,请联系相关管理员添加"))
                .addPathPatterns("/api/v1/organization/back");
        registry.addInterceptor(new BackInterceptor(new  Long[]{9L},"操作展示模块","需要展示模块操作权限,权限id=>9,请联系相关管理员添加"))
                .addPathPatterns("/api/v1/display/back");
        registry.addInterceptor(new BackInterceptor(new  Long[]{10L},"操作菜单模块","需要菜单模块操作权限,权限id=>10,请联系相关管理员添加"))
                .addPathPatterns("/api/v1/menu/back");

    }
}