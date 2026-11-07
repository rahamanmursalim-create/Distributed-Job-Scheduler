package io.distributedjobscheduler.server.admin.autoconfigure;

import io.distributedjobscheduler.server.admin.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Distributed Job Scheduler
 */
@Configuration
public class WebAdminConfigurer implements WebMvcConfigurer {

    private final AccessInterceptor accessInterceptor;

    @Autowired()
    public WebAdminConfigurer(AccessInterceptor accessInterceptor) {
        this.accessInterceptor = accessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns("/admin/**");
    }
}
