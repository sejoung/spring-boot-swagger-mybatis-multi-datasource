package com.github.sejoung.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class HeaderAspect {

	@Pointcut("execution(* com.github.sejoung.controller.*Controller.*(..))")
	public void rest() {
	}

	@Before("rest()")
	public void setRestHeaderInfo(JoinPoint joinPoint) throws Exception {
		log.debug("### Rest Before Advice Start !! ###");

		log.debug("### Rest Before Advice End !! ###");
	}

}
