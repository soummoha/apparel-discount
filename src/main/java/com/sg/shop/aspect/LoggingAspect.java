package com.sg.shop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * LoggingAspect logs for all services
 * 
 */
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class LoggingAspect {

	private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.sg.shop.service..*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.debug("start executing " + joinPoint.getTarget().getClass() +" : "+joinPoint.getSignature().getName());
	}

	@After("execution(* com.sg.shop.service..*(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.debug("end executing " + joinPoint.getTarget().getClass() +" : "+joinPoint.getSignature().getName());
	}

}
