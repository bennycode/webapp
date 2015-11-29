package com.welovecoding.config.persistence;

import com.codahale.metrics.MetricRegistry;
import com.welovecoding.config.Constants;
import com.welovecoding.config.WLCProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.Arrays;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {
	"com.welovecoding.data.*"
})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
	"com.welovecoding.data.*"
})
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class PersistenceConfiguration {

	private final Logger log = LoggerFactory.getLogger(PersistenceConfiguration.class);

	@Inject
	private Environment env;

	@Autowired(required = false)
	private MetricRegistry metricRegistry;

	@Bean(destroyMethod = "close")
	public DataSource dataSource(DataSourceProperties dataSourceProperties, WLCProperties wlcProperties) {
		log.debug("Configuring Datasource");
		if (dataSourceProperties.getUrl() == null) {
			log.error("Your database connection pool configuration is incorrect! The application"
				+ " cannot start. Please check your Spring profile, current profiles are: {}",
				Arrays.toString(env.getActiveProfiles()));

			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(dataSourceProperties.getDriverClassName());
		config.addDataSourceProperty("url", dataSourceProperties.getUrl());
		if (dataSourceProperties.getUsername() != null) {
			config.addDataSourceProperty("user", dataSourceProperties.getUsername());
		} else {
			config.addDataSourceProperty("user", ""); // HikariCP doesn't allow null user
		}
		if (dataSourceProperties.getPassword() != null) {
			config.addDataSourceProperty("password", dataSourceProperties.getPassword());
		} else {
			config.addDataSourceProperty("password", ""); // HikariCP doesn't allow null password
		}

		//MySQL optimizations, see https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
		if ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource".equals(dataSourceProperties.getDriverClassName())) {
			config.addDataSourceProperty("cachePrepStmts", wlcProperties.getDatasource().isCachePrepStmts());
			config.addDataSourceProperty("prepStmtCacheSize", wlcProperties.getDatasource().getPrepStmtCacheSize());
			config.addDataSourceProperty("prepStmtCacheSqlLimit", wlcProperties.getDatasource().getPrepStmtCacheSqlLimit());
		}
		if (metricRegistry != null) {
			config.setMetricRegistry(metricRegistry);
		}
		return new HikariDataSource(config);
	}

	/**
	 * Open the TCP port for the H2 database, so it is available remotely.
	 */
	@Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2TCPServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers");
	}

}
