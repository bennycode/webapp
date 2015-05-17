package com.welovecoding.config;

import com.welovecoding.data.author.Author;
import com.welovecoding.data.category.Category;
import com.welovecoding.data.playlist.Playlist;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
  "com.welovecoding.data.author",
  "com.welovecoding.data.category",
  "com.welovecoding.data.playlist",
  "com.welovecoding.data.user",
  "com.welovecoding.data.video"
})
@PropertySources({
  @PropertySource("classpath:database.properties")
})
public class PersistenceConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("wlc.database.driver.classname"));
    dataSource.setUrl(env.getProperty("wlc.database.url"));
    dataSource.setUsername(env.getProperty("wlc.database.username"));
    dataSource.setPassword(env.getProperty("wlc.database.password"));
    return dataSource;
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

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    // scan for entities
    em.setPackagesToScan(
      Category.class.getPackage().getName(),
      Playlist.class.getPackage().getName(),
      Video.class.getPackage().getName(),
      Author.class.getPackage().getName(),
      User.class.getPackage().getName()
    );

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    em.setJpaProperties(new Properties() {
      {
        setProperty("hibernate.hbm2ddl.auto", "create-drop");
        setProperty("hibernate.vendor", "MYSQL");
        setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        setProperty("hibernate.globally_quoted_identifiers", "true");
      }
    });

    return em;
  }
}



