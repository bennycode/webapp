package com.welovecoding.config;

import com.welovecoding.data.user.repository.UserRepository;
import com.welovecoding.data.user.service.ExampleUserDetailsService;
import com.welovecoding.data.user.service.SimpleSocialUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      //Spring Security ignores request to static resources such as CSS or JS files.
      .ignoring()
      .antMatchers("/resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      //Configures form login
      .formLogin()
      .loginPage("/login")
      .loginProcessingUrl("/login/authenticate")
      .failureUrl("/login?error=bad_credentials")
        //Configures the logout function
      .and()
      .logout()
      .deleteCookies("JSESSIONID")
      .logoutUrl("/logout")
      .logoutSuccessUrl("/login")
        //Configures url based authorization
      .and()
      .authorizeRequests()
        //Anyone can access the urls
      .antMatchers(
        "/**",
        "/api/v1/service/ping",
        "/auth/**",
        "/login",
        "/signup/**",
        "/user/register/**"
      ).permitAll()
      //The rest of the our application is protected.
//      .antMatchers("/**").hasRole("USER")
      .and()
      .rememberMe()
        //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
      .and()
      .apply(new SpringSocialConfigurer());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService())
      .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  @Bean
  public SocialUserDetailsService socialUserDetailsService() {
    return new SimpleSocialUserDetailsService(userDetailsService());
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return new ExampleUserDetailsService(userRepository);
  }
}
