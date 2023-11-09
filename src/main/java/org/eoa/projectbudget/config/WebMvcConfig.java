package org.eoa.projectbudget.config;

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
    }
}