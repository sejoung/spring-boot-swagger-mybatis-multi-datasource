
package com.github.sejoung.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sejoung.api.parameter.AdvertisementRequestParameter;
import com.github.sejoung.api.parameter.ClickParameter;
import com.github.sejoung.api.parameter.ConversionParameter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "구글 API")
@RequestMapping(value = "/google")
public class GoogleController {

	@ApiOperation(value = "추천광고요청", notes = "추천광고요청을 위한 API 입니다.")
	@RequestMapping(value = "/recommendAD", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertisementRequestParameter> advertisementRequest(@ApiParam(name="광고요청",value="광고요청을 위한 파라미터") @RequestBody AdvertisementRequestParameter payload) {
		log.info("advertisementRequest start AdvertisementRequestParameter = {}", payload);
		
		return ResponseEntity.ok(payload);
	}
	
	@ApiOperation(value = "클릭정보 등록", notes = "클릭정보 등록을 위한 API 입니다.")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/click", method = RequestMethod.PUT, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public void click(@ApiParam(name="클릭정보",value="클릭정보 등록을 위한 파라미터") @RequestBody ClickParameter payload) {
		log.info("click start ClickParameter = {}", payload);
		
	}
	
	@ApiOperation(value = "구매정보 등록", notes = "구매정보 등록을 위한 API 입니다.")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/conversion", method = RequestMethod.PUT, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public void conversion(@ApiParam(name="구매정보",value="구매정보 등록을 위한 파라미터") @RequestBody ConversionParameter payload) {
		log.info("conversion start ConversionParameter = {}", payload);
		
	}

}
