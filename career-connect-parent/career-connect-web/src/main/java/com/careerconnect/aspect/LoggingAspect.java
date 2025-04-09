package com.careerconnect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.careerconnect.controller.*.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", 
            joinPoint.getSignature().getName(),
            joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.careerconnect.controller.*.*(..))", 
                   returning = "result")
    public void logAfterControllerMethod(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", 
            joinPoint.getSignature().getName(),
            result);
    }

    @AfterThrowing(pointcut = "execution(* com.careerconnect.controller.*.*(..))", 
                  throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: {} with cause: {}", 
            joinPoint.getSignature().getName(),
            exception.getMessage());
    }
} 