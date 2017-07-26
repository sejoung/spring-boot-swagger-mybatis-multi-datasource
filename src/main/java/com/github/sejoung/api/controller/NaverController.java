package com.github.sejoung.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sejoung.api.service.NaverService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "Naver 추천 API 요청")
@RequestMapping(value = "/hearina")
public class NaverController {

	@Autowired
	private NaverService hearinaService;

	@ApiOperation(value = "SHOP_ID 기준 추천 정보 등록", notes = "SHOP_ID 기준 추천 정보 등록")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/putShopIdData", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void putShopIdData(@ApiParam(name = "shopId", value = "Naver 추천정보 등록을 위한 파라미터") @RequestParam(value = "shopId", defaultValue = "dabagirl") String shopId) {
		log.info("param " + shopId);
		hearinaService.putShopIdData(shopId);
	}

}
