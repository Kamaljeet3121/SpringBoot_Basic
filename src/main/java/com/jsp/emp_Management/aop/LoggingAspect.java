package com.jsp.emp_Management.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

//    @Before("execution(* com.jsp.emp_Management.*.*.*(..))")
//    public void loggingMethod1(JoinPoint joinPoint) {
////        System.out.println("Logging Before Method..");
//            log.info("Entering logging method 1"+joinPoint.getSignature().getName()+"and Arguments "+ Arrays.toString(joinPoint.getArgs()));
//    }

    //after is also same

    @Around("execution(* com.jsp.emp_Management.*.*.*(..))")
    public Object loggingMethod2(ProceedingJoinPoint joinPoint) {
        log.info("Entering logging method 2"+joinPoint.getSignature().getName());
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        log.info("Exiting logging method 2"+joinPoint.getSignature().getName());
        long end = System.currentTimeMillis();
        return result;
    }

}
