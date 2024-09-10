package com.example.newProject1.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    public static final org.slf4j.Logger Logger=  LoggerFactory.getLogger(ValidationAspect.class);


    @Around("execution(* com.example.newProject1.service.CourseService.getCourseById(..)) && args(postId)")
    public Object valid(ProceedingJoinPoint jp,int postId) throws Throwable {

        if(postId<0){
            postId=-postId;
        }
        Object obj=jp.proceed(new Object[]{postId});

        return obj;
    }

}
