package org.dashia18.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
Custom Aspect
Essentially, this is the class where we will implement the logic
that we want our custom annotation to inject.
https://www.baeldung.com/spring-aop-annotation
https://www.baeldung.com/spring-aop-pointcut-tutorial
https://www.baeldung.com/spring-aop-advice-tutorial
 */
@Aspect
@Slf4j
@Component
public class LoggingAspect {
    /*
    * @Around advice means we are adding extra code both before and after method execution
    *
    * "@annotation(LogExecutionTime)" pointcut just says,
       ‘Apply this advice any method which is annotated with @LogExecutionTime.’

     * method logExecutionTime() itself is our advice

     * calling proceed() is the just calling the original annotated method

     * @LogExecutionTime - use this annotation on any method
     */
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
