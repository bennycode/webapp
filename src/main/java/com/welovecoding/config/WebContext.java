package com.welovecoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {
  "com.welovecoding.view",
  "com.welovecoding.api"
})
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class WebContext extends WebMvcConfigurerAdapter {

  @Bean
  public InternalResourceViewResolver setupViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }

  @Bean
  public SimpleMappingExceptionResolver exceptionResolver() {
    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

    Properties exceptionMappings = new Properties();

    exceptionMappings.put("java.lang.Exception", "error/error");
    exceptionMappings.put("java.lang.RuntimeException", "error/error");

    exceptionResolver.setExceptionMappings(exceptionMappings);

    Properties statusCodes = new Properties();

    statusCodes.put("error/404", "404");
    statusCodes.put("error/error", "500");

    exceptionResolver.setStatusCodes(statusCodes);

    return exceptionResolver;
  }
//  http://stackoverflow.com/questions/26384930/how-to-add-n-before-each-spring-json-response-to-prevent-common-vulnerab
//  @Override
//  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//    converter.setJsonPrefix(")]}',\n");
//    converters.add(converter);
//  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

}
