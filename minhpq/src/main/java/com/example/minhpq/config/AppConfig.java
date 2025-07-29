package com.example.minhpq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.example.minhpq")
@EnableJpaRepositories(basePackages = "com.example.minhpq.repository")
public class AppConfig {
}
