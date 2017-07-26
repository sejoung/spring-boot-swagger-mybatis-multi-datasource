package com.github.sejoung.api.service;

public interface NaverService {
	
	/**
	 * shopId 기준 추천상품정보등록
	 * 
	 * @param shopId 광고주아이디
	 * 
	 */
	public void putShopIdData(String shopId); 
	
	
	/**
	 * Nave 추천정보 요청(shopid기준)
	 * 
	 * @param shopId 광고주아이디
	 */
	public void getShopIdData(String shopId);
	
}
