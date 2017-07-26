package com.github.sejoung.api.product.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.sejoung.api.product.dao.ProductTestDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDaoTests {

	@Autowired
	private ProductTestDao testDao;

	@Test
	public void testGetRecommendAD() throws Exception {
		
		com.github.sejoung.api.domain.Test test = testDao.selectMasterTest();
		  assertThat(test.getId(), is(1L));
	}

}
