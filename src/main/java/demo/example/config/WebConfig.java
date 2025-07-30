package demo.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer; 

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "demo.example.controller")
public class WebConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") 
            .allowedOrigins("*") 
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
            .allowedHeaders("*")
            .allowCredentials(false);
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}