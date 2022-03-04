package com.indir.async.app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("@annotation(com.indir.async.app.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        Object proceed;
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String  methodName = joinPoint.getSignature().getName();
        try {
            proceed = joinPoint.proceed();
            log.info("[{}].[{}] execution success in {}ms", className, methodName, System.currentTimeMillis() - start);

        } catch (Throwable throwable) {
            log.info("[{}].[{}] execution failed", className, methodName);
            throw throwable;
        }
        return proceed;
    }
}
