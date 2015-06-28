package com.welovecoding.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = {
  "com.welovecoding.data.category.entity",
  "com.welovecoding.data.tutorial.entity",
  "com.welovecoding.data.user.entity",
  "com.welovecoding.data.post.entity"
})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
  "com.welovecoding.data.category.repository",
  "com.welovecoding.data.tutorial.repository",
  "com.welovecoding.data.user.repository",
  "com.welovecoding.data.post.repository"
})
public class PersistenceContext {

}



