package com.welovecoding.config.security;

import com.welovecoding.config.WLCProperties;
import com.welovecoding.config.security.util.AjaxLogoutSuccessHandler;
import com.welovecoding.config.security.util.AuthoritiesConstants;
import com.welovecoding.config.security.util.Http401UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
public class OAuth2ServerConfiguration {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Inject
		private Http401UnauthorizedEntryPoint authenticationEntryPoint;

		@Inject
		private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.logout()
				.logoutUrl("/api/v1/logout")
				.logoutSuccessHandler(ajaxLogoutSuccessHandler)
				.and()
				.csrf()
				.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
				.disable()
				.headers()
				.frameOptions().disable()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/api/v1/account/authenticate").permitAll()
				.antMatchers("/api/v1/account/register").permitAll()
				.antMatchers("/api/v1/account/activate").permitAll()
				.antMatchers("/api/v1/ping").permitAll()
        .antMatchers("/health/**").permitAll()
				.antMatchers("/api/v1/**").authenticated()
				.antMatchers("/logs/**").hasAnyAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/metrics/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/dump/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/shutdown/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/beans/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/configprops/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/info/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/autoconfig/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/env/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/liquibase/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/api-docs/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/protected/**").authenticated();

		}
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Inject
		private DataSource dataSource;

		@Inject
		private WLCProperties wlcProperties;

		@Bean
		public TokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}

		@Inject
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {

			endpoints
				.tokenStore(tokenStore())
				.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients
				.inMemory()
				.withClient(wlcProperties.getSecurity().getAuthentication().getOauth().getClientid())
				.scopes("read", "write")
				.authorities(AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER)
				.authorizedGrantTypes("password", "refresh_token")
				.secret(wlcProperties.getSecurity().getAuthentication().getOauth().getSecret())
				.accessTokenValiditySeconds(wlcProperties.getSecurity().getAuthentication().getOauth().getTokenValidityInSeconds());
		}
	}
}
