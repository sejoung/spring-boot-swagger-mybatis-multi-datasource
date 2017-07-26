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
public class StatisticsDataSourceConfig {

	@Bean(name="sqlSessionStatisticsFactory")
	public SqlSessionFactory sqlSessionStatisticsFactory(@Qualifier("statisticsJdbcDataSource") DataSource StatisticsJdbcDataSource ,ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(StatisticsJdbcDataSource);
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/statistics/*.xml"));
		return factoryBean.getObject();
	}

	@Bean(name = "sqlSessionStatistics")
	public SqlSessionTemplate sqlSessionStatistics(@Qualifier("sqlSessionStatisticsFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate sqlSessionTemplate= new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}

	@Bean(name = "statisticsTX")
	public PlatformTransactionManager StatisticsTransactionManager(@Qualifier("statisticsJdbcDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name="statisticsPoolProperties")
	@ConfigurationProperties(prefix = "statistics")
	public PoolProperties getStatisticsPoolProperties() {
		return new PoolProperties();
	}

	@Bean(name="statisticsJdbcDataSource")
	public DataSource StatisticsJdbcDataSource(@Qualifier("statisticsPoolProperties") PoolProperties poolProperties) {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
		return dataSource;
	}
}
