package com.github.sejoung.api.product.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.github.sejoung.api.domain.Test;

@Repository("ProductTestDao")
public class ProductTestDao {
	
	@Resource(name="sqlSessionProductMaster")
	private SqlSessionTemplate sqlSessionProductMaster;
	
	
	@Resource(name="sqlSessionProductSlave")
	private SqlSessionTemplate sqlSessionProductSlave;

	public Test selectMasterTest() {
		return this.sqlSessionProductMaster.selectOne("selectTest");
	}
	
	public Test selectSlaveTest() {
		return this.sqlSessionProductSlave.selectOne("selectTest");
	}

}
