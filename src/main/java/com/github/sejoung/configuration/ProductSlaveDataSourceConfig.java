package com.github.sejoung.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ProductSlaveDataSourceConfig {

	@Bean(name="sqlSessionProductSlaveFactory")
	public SqlSessionFactory sqlSessionProductSlaveFactory(@Qualifier("productSlaveJdbcDataSource") DataSource productSlaveJdbcDataSource ,ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(productSlaveJdbcDataSource);
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/product/*.xml"));
		return factoryBean.getObject();
	}

	@Bean(name = "sqlSessionProductSlave")
	public SqlSessionTemplate sqlSessionProductSlave(@Qualifier("sqlSessionProductSlaveFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate sqlSessionTemplate= new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}

	@Bean(name = "productSlaveTX")
	public PlatformTransactionManager productSlaveTransactionManager(@Qualifier("productSlaveJdbcDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name="productSlavePoolProperties")
	@ConfigurationProperties(prefix = "productSlave")
	public PoolProperties getProductSlavePoolProperties() {
		return new PoolProperties();
	}

	@Bean(name="productSlaveJdbcDataSource")
	public DataSource productSlaveJdbcDataSource(@Qualifier("productSlavePoolProperties") PoolProperties poolProperties) {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
		return dataSource;
	}
}
