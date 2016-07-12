package com.welovecoding.config;

import com.welovecoding.api.v1.base.Logged;
import com.welovecoding.gauth.GoogleConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;

import javax.inject.Inject;

@Configuration
public class GoogleConfiguration {

    private final Logger LOG = LoggerFactory.getLogger(GoogleConfiguration.class);

    @Inject
    private WLCProperties wlcProperties;

    @Logged
    @Bean
    public GoogleConnector getGoogleConnector() {
        System.out.println(wlcProperties.getGoogle().getProjectid()+ 
                wlcProperties.getGoogle().getClientid()+
                wlcProperties.getGoogle().getClientsecret()+
                wlcProperties.getGoogle().getRedirecturi());
        return new GoogleConnector(
                wlcProperties.getGoogle().getProjectid(), 
                wlcProperties.getGoogle().getClientid(), 
                wlcProperties.getGoogle().getClientsecret(), 
                wlcProperties.getGoogle().getRedirecturi());
    }
}
