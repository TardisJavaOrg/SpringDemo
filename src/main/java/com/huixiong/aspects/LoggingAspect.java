package com.huixiong.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(public * com.huixiong.aspects.UserService.getStr(..))")
    public  void doAccessCheck(){
        System.out.println("[Before] do access check");
    }
}
