package com.example.blogapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class }; // Spring config chính
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null; // Không có servlet-specific config
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // Map toàn bộ request
    }
}
