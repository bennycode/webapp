package com.welovecoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring Data Demos:	https://github.com/gordonad/spring-data-demos Spring
 * Profiles:
 * http://gordondickens.com/wordpress/2013/02/28/database-config-spring-3-2-environment-profiles/comment-page-1/
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.welovecoding.data.news", "com.welovecoding.data.account", "com.welovecoding.data.author", "com.welovecoding.data.category", "com.welovecoding.data.playlist", "com.welovecoding.data.user", "com.welovecoding.data.video"})
public class PersistenceConfig {

//  @Bean
//  public DataSource dataSource() {
//    DataSource dataSource = new EmbeddedDatabaseBuilder()
//      .setType(EmbeddedDatabaseType.H2)
//      .setScriptEncoding("UTF-8")
//      .ignoreFailedDrops(true)
//      .continueOnError(true)
//      .build();
//
//    return dataSource;
//  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/welovecoding");
    dataSource.setUsername("root");
    dataSource.setPassword("");

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    // scan for entities
    em.setPackagesToScan(new String[]{"com.welovecoding.data.news", "com.welovecoding.data.account", "com.welovecoding.data.author", "com.welovecoding.data.category", "com.welovecoding.data.playlist", "com.welovecoding.data.user", "com.welovecoding.data.video"});

//    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
    em.setLoadTimeWeaver(new SimpleLoadTimeWeaver());
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());

    return em;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "create");
//	properties.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
    return properties;
  }
}
