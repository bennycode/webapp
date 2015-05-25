package com.welovecoding.config;

import com.welovecoding.data.user.facebook.SimpleSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
@PropertySources({
  @PropertySource("classpath:social.properties")
})
public class SocialContext implements SocialConfigurer {

  @Autowired
  private Environment env;

  @Autowired
  private DataSource dataSource;

  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
    cfConfig.addConnectionFactory(new TwitterConnectionFactory(
      this.env.getProperty("twitter.consumer.key", "no_key"),
      this.env.getProperty("twitter.consumer.secret", "no_secret")
    ));
    cfConfig.addConnectionFactory(new FacebookConnectionFactory(
      this.env.getProperty("facebook.app.id", "no_id"),
      this.env.getProperty("facebook.app.secret", "no_secret")
    ));
  }

  @Override
  public UserIdSource getUserIdSource() {
    return new AuthenticationNameUserIdSource();
  }

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    return new JdbcUsersConnectionRepository(
      dataSource,
      connectionFactoryLocator,
      Encryptors.noOpText()
    );
  }

  @Bean
  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
  public Facebook facebook(ConnectionRepository repository) {
    Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
    return connection != null ? connection.getApi() : null;
  }

  @Bean
  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
  public Twitter twitter(ConnectionRepository repository) {
    Connection<Twitter> connection = repository.findPrimaryConnection(Twitter.class);
    return connection != null ? connection.getApi() : null;
  }

  //
  // Web Controller and Filter Beans
  //
  @Bean
  public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
    ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
    return connectController;
  }

  @Bean
  public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
    return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(new HttpSessionRequestCache()));
  }

  @Bean
  public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
    return new ReconnectFilter(usersConnectionRepository, userIdSource);
  }
}
