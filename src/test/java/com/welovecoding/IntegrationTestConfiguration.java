package com.welovecoding;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import com.welovecoding.data.category.entity.Category;
import com.welovecoding.data.post.entity.Post;
import com.welovecoding.data.tutorial.entity.Tutorial;
import com.welovecoding.data.user.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(value = "com.welovecoding",
  excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Configuration.class}))
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
  "com.welovecoding.data.category",
  "com.welovecoding.data.tutorial",
  "com.welovecoding.data.user",
  "com.welovecoding.data.post"
})
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class IntegrationTestConfiguration extends WebMvcConfigurerAdapter {

  @Bean
  public static DataSource dataSource() {
    DataSource dataSource = new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2)
      .setScriptEncoding("UTF-8")
      .ignoreFailedDrops(true)
      .continueOnError(true)
      .build();

    return dataSource;
  }

  /**
   * Prevents: WARN [org.dbunit.dataset.AbstractTableMetaData]
   * AbstractTableMetaData - Potential problem found: The configured data type
   * factory 'class org.dbunit.dataset.datatype.DefaultDataTypeFactory' might
   * cause problems with the current database 'H2' (e.g. some datatypes may
   * not be supported properly). In rare cases you might see this message
   * because the list of supported database products is incomplete
   * (list=[derby]). If so please request a java-class update via the
   * forums.If you are using your own IDataTypeFactory extending
   * DefaultDataTypeFactory, ensure that you override getValidDbProducts() to
   * specify the supported database products.
   *
   * @return
   */
  @Bean
  public static DatabaseConfigBean dbUnitDatabaseConfig() {
    DatabaseConfigBean dbConfig = new com.github.springtestdbunit.bean.DatabaseConfigBean();
    dbConfig.setDatatypeFactory(new org.dbunit.ext.h2.H2DataTypeFactory());
    return dbConfig;
  }

  @Bean
  public static DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
    DatabaseDataSourceConnectionFactoryBean dbConnection = new com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean(dataSource());
    dbConnection.setDatabaseConfig(dbUnitDatabaseConfig());
    return dbConnection;
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
        setProperty("hibernate.hbm2ddl.auto", "create-drop");
        setProperty("database.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        setProperty("hibernate.format_sql", "true");
        setProperty("hibernate.show_sql", "false");
        setProperty("hibernate.format_sql", "true");
      }
    });

    return em;
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

//  @Bean
//  public static PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder(10);
//  }

//  http://stackoverflow.com/questions/26384930/how-to-add-n-before-each-spring-json-response-to-prevent-common-vulnerab
//  @Override
//  public static void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//    converter.setJsonPrefix(")]}',\n");
//    converters.add(converter);
//  }
}
