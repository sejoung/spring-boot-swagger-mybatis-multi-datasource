package com.github.sejoung.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.sejoung.api.domain.Test;
import com.github.sejoung.api.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "테스트 API")
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestService testService;

	@ApiOperation(value = "DAO TEST", notes = "DAO 테스트")
	@RequestMapping(value = "/selectMasterTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test> selectMasterTest() {
		log.debug("selectMasterTest");
		return ResponseEntity.ok(testService.selectMasterTest());
	}

	@ApiOperation(value = "DAO TEST", notes = "DAO 테스트")
	@RequestMapping(value = "/selectSlaveTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Test> selectSlaveTest() {
		log.debug("selectSlaveTest");
		return ResponseEntity.ok(testService.selectSlaveTest());
	}

}
