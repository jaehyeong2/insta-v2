package com.example.kingsta.handler.aop;


import com.example.kingsta.handler.ex.CustomValidationApiException;
import com.example.kingsta.handler.ex.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class ValidationAdvice {

    @Around("execution(* com.example.kingsta.controller.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("api 컨트롤러 호출");

        Object[] args = joinPoint.getArgs();
        for(Object arg: args){
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage()); }
                    throw new CustomValidationApiException("유효성 검사 실패", errorMap);
                }
            }
        }
        return joinPoint.proceed();
    }

    @Around("execution(* com.example.kingsta.controller.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("컨트롤러 호출");

        Object[] args = joinPoint.getArgs();
        for(Object arg: args) {
            BindingResult bindingResult = (BindingResult) arg;

            if (bindingResult.hasErrors()) {
                Map<String, String> errorMap = new HashMap<>();

                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }
                throw new CustomValidationException("유효성 검사 실패", errorMap);
            }
        }
        return joinPoint.proceed();
    }
}