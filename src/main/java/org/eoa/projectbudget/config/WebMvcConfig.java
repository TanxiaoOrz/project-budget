package org.eoa.projectbudget.config;

import org.eoa.projectbudget.interceptor.SleepInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SleepInterceptor()).addPathPatterns("/api/v1/test/**");
    }
}