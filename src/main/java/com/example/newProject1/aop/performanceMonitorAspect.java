package com.example.newProject1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class performanceMonitorAspect {

    public static final Logger Logger=  LoggerFactory.getLogger(performanceMonitorAspect.class);

    @Around("execution(* com.example.newProject1.service.CourseService.getCourses(..)) || execution(* com.example.newProject1.service.CourseService.getCourseById(..))")
    public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {

        long start=System.currentTimeMillis();
        Object obj=jp.proceed();
        long end=System.currentTimeMillis();

        Logger.info("time taken by " + (end-start)+"ms " + jp.getSignature().getName()  );

        return obj;
    }


}
