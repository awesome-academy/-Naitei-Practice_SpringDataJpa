package org.example.config;

import org.example.service.CampaignService;
import org.example.service.SkillService;
import org.example.service.VolunteerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "org.example")
@EnableJpaRepositories(basePackages = "org.example.repository")
@Import({DatabaseConfig.class, JpaConfig.class})
public class AppConfig {

    // Service Beans
    @Bean
    public VolunteerService volunteerService() {
        return new VolunteerService();
    }

    @Bean
    public CampaignService campaignService() {
        return new CampaignService();
    }

    @Bean
    public SkillService skillService() {
        return new SkillService();
    }
}