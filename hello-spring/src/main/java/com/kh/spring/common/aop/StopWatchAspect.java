package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class StopWatchAspect {
	
	@Pointcut("execution(* com.kh.spring.memo.controller.MemoController.insertMemo(..))")
	public void pointcut() {}
	
	@Around("pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		//JoinPoint 수행 전 
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//JoinPoint 수행 
		Object retObj = joinPoint.proceed();
		
		//JoinPoint 수행 후 
		stopWatch.stop();
		long duration = stopWatch.getTotalTimeMillis();
		
		Signature sign = joinPoint.getSignature();
		String methodName = sign.getName();
		log.debug("{} 소요 시간 {}ms", methodName, duration);
		
		return retObj;
	}
}
