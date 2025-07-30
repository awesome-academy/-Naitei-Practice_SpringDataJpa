package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory; // Dùng jakarta.persistence cho JPA 3+
import jakarta.persistence.EntityManager; // Dùng jakarta.persistence cho JPA 3+
import javax.sql.DataSource; // Vẫn dùng javax.sql nếu bạn dùng JPA 2.x hoặc cũ hơn, tùy vào pom.xml

import java.util.Properties;

@Configuration
@EnableTransactionManagement
// ComponentScan để Spring tìm các @Service, @Repository, @Configuration và cả @Entity nếu Entity không được quét bởi packagesToScan
@ComponentScan(basePackages = {"org.example.service", "org.example.repository", "org.example.config", "org.example.entity"})
@EnableJpaRepositories(basePackages = "org.example.repository") // Quét các Repository của Spring Data JPA
@PropertySource("classpath:db_example.properties") // Chỉ định file properties để Spring đọc
public class AppConfig {

    private final Environment env; // Biến Environment để truy cập các thuộc tính từ file .properties

    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        // Chỉ định gói chứa các Entity của bạn để Hibernate/JPA quét và ánh xạ
        em.setPackagesToScan("org.example.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        // Lấy giá trị từ properties, nếu không có thì dùng giá trị mặc định để tránh NPE
        vendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("spring.jpa.show-sql", "false")));

        String ddlAuto = env.getProperty("spring.jpa.hibernate.ddl-auto", "none");
        vendorAdapter.setGenerateDdl(ddlAuto.equals("update") || ddlAuto.equals("create") || ddlAuto.equals("create-drop"));

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // Lấy giá trị từ properties, nếu không có thì dùng giá trị mặc định
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto", "none"));
        properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.NoSQLDialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql", "false"));
        properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql", "false"));
        return properties;
    }
}