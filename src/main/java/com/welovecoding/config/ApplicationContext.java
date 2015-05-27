package com.welovecoding.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {
  "com.welovecoding.data.category.service",
  "com.welovecoding.data.tutorial.service",
  "com.welovecoding.data.user.service",
  "com.welovecoding.data.post.service"
})
@Import({WebContext.class, PersistenceContext.class, /*SecurityContext.class,*/ SocialContext.class})
public class ApplicationContext {

  private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/en_GB";

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
    messageSource.setUseCodeAsDefaultMessage(true);

    return messageSource;
  }

  @Bean
  public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
