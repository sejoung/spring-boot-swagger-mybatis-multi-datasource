package com.github.sejoung.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.sejoung.api.domain.Test;
import com.github.sejoung.api.product.dao.ProductTestDao;
import com.github.sejoung.api.service.TestService;
import com.github.sejoung.api.statistics.dao.StatisticsTestDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("TestService")
public class TestServiceImpl implements TestService {

	@Resource(name = "ProductTestDao")
	private ProductTestDao ProductTestDao;
	
	@Resource(name = "statisticsTestDao")
	private StatisticsTestDao statisticsTestDao;
	
	
	@Override
	public Test selectMasterTest() {
		log.debug("selectMasterTest DAO");
		Test test = ProductTestDao.selectMasterTest();
		return test;
	}

	@Override
	public Test selectSlaveTest() {
		log.debug("selectSlaveTest DAO");
		Test test = ProductTestDao.selectSlaveTest();
		return test;
	}

	@Override
	public Test selectTest() {
		log.debug("selectTest DAO");
		Test test = statisticsTestDao.selectTest();
		return test;
	}

}
