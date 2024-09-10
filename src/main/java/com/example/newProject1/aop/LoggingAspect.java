package com.example.newProject1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    public static final Logger Logger=  LoggerFactory.getLogger(LoggingAspect.class);

    // return type , class name(fully classified name) , method name,

    @Before("execution(* com.example.newProject1.service.CourseService.getCourses(..)) || execution(* com.example.newProject1.service.CourseService.getCourseById(..))")
    public void BeforeMethodCalled(JoinPoint jp){
        Logger.info("Before Method called "+jp.getSignature().getName());
    }

    @After("execution(* com.example.newProject1.service.CourseService.getCourses(..)) || execution(* com.example.newProject1.service.CourseService.getCourseById(..))")
    public void AfterMethod(JoinPoint jp){
        Logger.info("After Method called "+jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.example.newProject1.service.CourseService.getCourses(..)) || execution(* com.example.newProject1.service.CourseService.getCourseById(..))")
    public void AfterThrowingMethod(JoinPoint jp){
        Logger.info("AfterThrowing Method has some issue "+jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.newProject1.service.CourseService.getCourses(..)) || execution(* com.example.newProject1.service.CourseService.getCourseById(..))")
    public void AfterReturningMethod(JoinPoint jp){
        Logger.info("Method executed successfully  "+jp.getSignature().getName());
    }
}
