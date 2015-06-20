package com.welovecoding.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
  "com.welovecoding.data.category.service",
  "com.welovecoding.data.tutorial.service",
  "com.welovecoding.data.user.service",
  "com.welovecoding.data.post.service"
})
@Import({PersistenceContext.class, APIContext.class})
public class ApplicationContext extends SpringBootServletInitializer {

  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationContext.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ApplicationContext.class);
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
