package com.github.sejoung.api.service;

import com.github.sejoung.api.domain.Test;

public interface TestService {
	
	public Test selectMasterTest();
	
	public Test selectSlaveTest();
	
	public Test selectTest();
}
