package com.github.sejoung.api.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HearinaParameter {
	
	/**
	 * 분석된 키값
	 */
	private String _id;

	/**
	 * 광고주아이디
	 */
	private String shop_id;

	/**
	 * 분석된 상품 코드별 가중치
	 */
	private String [] pid_values;

	/**
	 * 분석된 상품 코드
	 */
	private String [] pids;
	
	/**
	 * 유저 유니크키
	 */
	private String gid;
	
	/**
	 * 광고주 도메인
	 */
	private String domain;
	
	
}
