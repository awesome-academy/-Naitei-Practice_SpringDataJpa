package com.example.phucnh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.example.phucnh")
@EnableJpaRepositories(basePackages = "com.example.phucnh.repository")
public class AppConfig {
}
