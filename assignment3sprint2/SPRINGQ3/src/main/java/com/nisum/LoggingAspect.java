package com.nisum;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.nisum.MyService.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("LoggingAspect: Starting execution of method: " + joinPoint.getSignature().getName());
    }
}
