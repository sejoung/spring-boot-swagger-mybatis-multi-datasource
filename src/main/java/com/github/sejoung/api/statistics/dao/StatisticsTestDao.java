package com.github.sejoung.api.statistics.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.github.sejoung.api.domain.Test;

@Repository("statisticsTestDao")
public class StatisticsTestDao {
	
	@Resource(name="sqlSessionStatistics")
	private SqlSessionTemplate sqlSessionStatistics;
	

	public Test selectTest() {
		return this.sqlSessionStatistics.selectOne("selectTest");
	}

}
