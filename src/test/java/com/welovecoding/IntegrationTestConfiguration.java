package com.welovecoding;

import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class IntegrationTestConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(IntegrationTestConfiguration.class.getName());

//	@Inject
//	private DataSource datasource;
//
//	/**
//	 * Prevents: WARN [org.dbunit.dataset.AbstractTableMetaData]
//	 * AbstractTableMetaData - Potential problem found: The configured data type
//	 * factory 'class org.dbunit.dataset.datatype.DefaultDataTypeFactory' might
//	 * cause problems with the current database 'H2' (e.g. some datatypes may
//	 * not be supported properly). In rare cases you might see this message
//	 * because the list of supported database products is incomplete
//	 * (list=[derby]). If so please request a java-class update via the
//	 * forums.If you are using your own IDataTypeFactory extending
//	 * DefaultDataTypeFactory, ensure that you override getValidDbProducts() to
//	 * specify the supported database products.
//	 *
//	 */
//	@Bean
//	public DatabaseConfigBean dbUnitDatabaseConfig() {
//		LOG.debug("dbUnitDatabaseConfig()");
//		DatabaseConfigBean dbConfig = new com.github.springtestdbunit.bean.DatabaseConfigBean();
//		dbConfig.setDatatypeFactory(new org.dbunit.ext.h2.H2DataTypeFactory());
//		return dbConfig;
//	}
//
//	@Bean
//	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
//		LOG.debug("dbUnitDatabaseConnection()");
//		DatabaseDataSourceConnectionFactoryBean dbConnection = new com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean(datasource);
//		dbConnection.setDatabaseConfig(dbUnitDatabaseConfig());
//		return dbConnection;
//	}
}
