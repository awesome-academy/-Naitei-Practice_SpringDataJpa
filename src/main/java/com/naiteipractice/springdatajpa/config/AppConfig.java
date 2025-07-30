package com.naiteipractice.springdatajpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {
    "com.naiteipractice.springdatajpa.service", 
    "com.naiteipractice.springdatajpa.repository"
})
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {
    // Main application configuration
}
