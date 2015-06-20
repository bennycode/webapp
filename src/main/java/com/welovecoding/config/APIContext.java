package com.welovecoding.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {
  "com.welovecoding.api"
})
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class APIContext extends WebMvcConfigurerAdapter {


//  @Override
//  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//    configurer.enable();
//  }

}
