package com.naiteipractice.springdatajpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "com.naiteipractice.springdatajpa")
@ImportResource("classpath:applicationContext.xml")
@Import(WebConfig.class)
public class AppConfig {
    // Main application configuration
}
