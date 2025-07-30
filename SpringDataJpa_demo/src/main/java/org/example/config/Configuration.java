package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;



@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.example.Repository")
@ComponentScan(basePackages = "org.example")
@ImportResource("classpath:beans.xml")
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Configuration {


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.example.Entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "none");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");

        emf.setJpaProperties(props);

        return emf;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}