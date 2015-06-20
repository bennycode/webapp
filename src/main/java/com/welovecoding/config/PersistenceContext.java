package com.welovecoding.config;

import com.jolbox.bonecp.BoneCPDataSource;
import com.welovecoding.data.category.entity.Category;
import com.welovecoding.data.post.entity.Post;
import com.welovecoding.data.tutorial.entity.Tutorial;
import com.welovecoding.data.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
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
@EnableJpaRepositories(basePackages = {
  "com.welovecoding.data.category.repository",
  "com.welovecoding.data.tutorial.repository",
  "com.welovecoding.data.user.repository",
  "com.welovecoding.data.post.repository"
})
@PropertySources({
  @PropertySource("classpath:database.properties")
})
public class PersistenceContext {

  @Autowired
  private Environment env;

  @Bean
  public static DataSource dataSource() {

    BoneCPDataSource dataSource = new BoneCPDataSource();
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/webapp");
    dataSource.setUsername("root");
    dataSource.setPassword("");

    dataSource.setIdleConnectionTestPeriodInMinutes(240);
    dataSource.setIdleMaxAgeInMinutes(60);
    dataSource.setMaxConnectionsPerPartition(10);
    dataSource.setMinConnectionsPerPartition(1);
    dataSource.setPartitionCount(2);
    dataSource.setAcquireIncrement(5);
    dataSource.setStatementsCacheSize(100);
    return dataSource;
  }

  @Bean
  public static PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }

  @Bean
  public static PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public static LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    // scan for entities
    em.setPackagesToScan(
      Category.class.getPackage().getName(),
      Tutorial.class.getPackage().getName(),
      User.class.getPackage().getName(),
      Post.class.getPackage().getName()
    );

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    em.setJpaProperties(new Properties() {
      {
        setProperty("hibernate.hbm2ddl.auto", "update");
        setProperty("hibernate.vendor", "MYSQL");
        setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        setProperty("hibernate.format_sql", "true");
        setProperty("hibernate.show_sql", "true");
        setProperty("hibernate.globally_quoted_identifiers", "true");
      }
    });

    return em;
  }
}



