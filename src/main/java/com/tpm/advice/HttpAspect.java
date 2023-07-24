package com.tpm.advice;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	@Pointcut("execution(public * com.tpm.controller.*.*(..))")
	public void log() {}
	
	@Before("log()")
	public void logBefore(JoinPoint point) {
		ServletRequestAttributes attributes =
				(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		assert attributes != null;
		HttpServletRequest request = attributes.getRequest();
		//url
		logger.info("url={}",request.getRequestURI());
		//method
		logger.info("method={}",request.getMethod());
		//IP:Port
		logger.info("IP:PORT={}",request.getRemoteAddr());
		//调用的服务
		logger.info("Class_Method={}",point.getSignature().getDeclaringTypeName() +
				"." + point.getSignature().getName());
		//传递的参数
		Object[] args = point.getArgs();
		StringBuilder argsStr = new StringBuilder();
		for(Object arg : args) {
			argsStr.append(arg.toString());
		}
		logger.info("args={}", argsStr);
	}
	

	@AfterReturning(pointcut = "log()", returning = "ret")
	public void logAfterRet(Object ret) {
		logger.info("response={}",ret.toString());
	}
	
}
