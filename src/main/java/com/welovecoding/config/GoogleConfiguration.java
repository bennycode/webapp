package com.welovecoding.config;

import com.welovecoding.api.v1.base.Logged;
import com.welovecoding.gauth.GLoginCallbackServlet;
import com.welovecoding.gauth.GLoginServlet;
import com.welovecoding.gauth.GoogleConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;

import javax.inject.Inject;
import org.springframework.boot.context.embedded.ServletRegistrationBean;

@Configuration
public class GoogleConfiguration {

    private final Logger LOG = LoggerFactory.getLogger(GoogleConfiguration.class);
    
    public static final String GOOGLE_PLUS_LOGIN_CALLBACK = "/google-plus-login-callback";
    public static final String GOOGLE_PLUS_LOGIN = "/google-plus-login";

    @Inject
    private WLCProperties wlcProperties;

    @Logged
    @Bean
    public GoogleConnector getGoogleConnector() {
        return new GoogleConnector(
                wlcProperties.getGoogle().getProjectid(),
                wlcProperties.getGoogle().getClientid(),
                wlcProperties.getGoogle().getClientsecret(),
                wlcProperties.getGoogle().getRedirecturi());
    }

    @Logged
    @Bean
    public ServletRegistrationBean googleCallbackServletRegistrationBean() {
        return new ServletRegistrationBean(new GLoginCallbackServlet(), GOOGLE_PLUS_LOGIN_CALLBACK);
    }
    
    @Logged
    @Bean
    public ServletRegistrationBean googleServletRegistrationBean() {
        return new ServletRegistrationBean(new GLoginServlet(), GOOGLE_PLUS_LOGIN);
    }
}
