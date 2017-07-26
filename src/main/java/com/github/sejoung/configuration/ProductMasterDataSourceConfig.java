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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ProductMasterDataSourceConfig {
	
	@Bean(name="sqlSessionProductMasterFactory")
	public SqlSessionFactory sqlSessionProductMasterFactory(@Qualifier("productMasterJdbcDataSource") DataSource productMasterJdbcDataSource ,ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(productMasterJdbcDataSource);
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/product/*.xml"));
		return factoryBean.getObject();
	}

	@Bean(name = "sqlSessionProductMaster")
	public SqlSessionTemplate sqlSessionProductMaster(@Qualifier("sqlSessionProductMasterFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate sqlSessionTemplate= new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}

	@Bean(name = "productMasterTX")
	public PlatformTransactionManager productMasterTransactionManager(@Qualifier("productMasterJdbcDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name="productMasterPoolProperties")
	@ConfigurationProperties(prefix = "productMaster")
	public PoolProperties getProductMasterPoolProperties() {
		return new PoolProperties();
	}

	@Primary
	@Bean(name="productMasterJdbcDataSource")
	public DataSource productMasterJdbcDataSource(@Qualifier("productMasterPoolProperties") PoolProperties poolProperties) {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
		return dataSource;
	}
}
