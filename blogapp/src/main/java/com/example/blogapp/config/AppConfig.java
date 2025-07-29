package com.example.blogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import com.example.blogapp.repository.repositories.PostRepository;
import com.example.blogapp.repository.repositories.UserRepository;
import com.example.blogapp.repository.repositoriesImpl.PostRepositoryImpl;
import com.example.blogapp.repository.repositoriesImpl.UserRepositoryImpl;
import com.example.blogapp.service.services.PostService;
import com.example.blogapp.service.services.UserService;
import com.example.blogapp.service.servicesImpl.PostServiceImpl;
import com.example.blogapp.service.servicesImpl.UserServiceImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;
import javax.persistence.EntityManagerFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.example.blogapp")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class AppConfig {

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
       DriverManagerDataSource ds = new DriverManagerDataSource();
       ds.setDriverClassName(dbDriver);
       ds.setUrl(dbUrl);
       ds.setUsername(dbUsername);
       ds.setPassword(dbPassword);
       return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.example.blogapp.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        emf.setJpaProperties(props);
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepositoryImpl();
    }

    @Bean
    public PostService postService(PostRepository postRepository) {
        return new PostServiceImpl(postRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

}
