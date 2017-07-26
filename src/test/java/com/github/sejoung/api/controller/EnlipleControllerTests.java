package com.github.sejoung.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.sejoung.api.parameter.AdvertisementRequestParameter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EnlipleControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetRecommendAD() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		HttpEntity entity = new HttpEntity("{\"img\": \"test\",\"point\": 1, \"purl\": \"test\"}", headers);
		ResponseEntity<AdvertisementRequestParameter> response = restTemplate.postForEntity("/google/getRecommendAD", entity, AdvertisementRequestParameter.class);
		AdvertisementRequestParameter responseData = response.getBody();
		System.out.println(response.getBody().getPurl());
		assertEquals(responseData.getPurl(), "test");
		assertEquals(responseData.getPoint(), 1);
		assertNotEquals(responseData.getImg(), "a");
	}

}
