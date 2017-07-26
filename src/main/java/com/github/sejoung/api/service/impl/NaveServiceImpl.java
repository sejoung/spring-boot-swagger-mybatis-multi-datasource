package com.github.sejoung.api.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.sejoung.api.parameter.HearinaParameter;
import com.github.sejoung.api.service.NaverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("HearinaService")
public class NaveServiceImpl implements NaverService {

	@Value("${naver.shopid.url}")
	private String shopIdDataUrl;

	private RestTemplate restTemplate;

	public NaveServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		restTemplateBuilder.setConnectTimeout(50000);
		restTemplateBuilder.setReadTimeout(50000);
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public void putShopIdData(String shopId) {
		this.getShopIdData(shopId);
	}

	@Override
	public void getShopIdData(String shopId) {
		log.debug("HearinaService getShopIdData start shopId = {}", shopId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(httpHeaders);

		ResponseEntity<List<HearinaParameter>> hearinaResponse = restTemplate.exchange(shopIdDataUrl + shopId, HttpMethod.GET, entity, new ParameterizedTypeReference<List<HearinaParameter>>() {
		});

		List<HearinaParameter> hearinaList = hearinaResponse.getBody();

		for (int i = 0; i < 1; i++) {
			HearinaParameter hearinaParameter = hearinaList.get(i);
			log.info("test " + hearinaParameter.get_id());
		}
	}

	@Scheduled(cron = "50 4 * * * *")
	private void putShopIdData() {
		this.getShopIdData("dabagirl");
	}

}
