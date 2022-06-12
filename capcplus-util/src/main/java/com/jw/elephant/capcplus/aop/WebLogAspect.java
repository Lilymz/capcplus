package com.jw.elephant.capcplus.aop;

import com.jw.elephant.capcplus.annotation.ControllerLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 日志切面
 * @author zhangjie
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    public static final Logger logger= LoggerFactory.getLogger(ControllerLog.class);
    /**
     *  日志切人点
     * @author zhangjie
     * @date 2022/5/20
     * @return void
     */
    @Pointcut("@annotation(com.jw.elephant.capcplus.annotation.ControllerLog)")
    public void controllerLog(){}

    @Around("controllerLog()")
    public void invokeBefore(ProceedingJoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            Object target = joinPoint.getTarget();
            StringBuilder logAppend = new StringBuilder();
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest){
                    HttpServletRequest request = (HttpServletRequest)arg;
                    logAppend.append(request.getMethod());
                    logAppend.append("\t"+request.getServletPath());
                    break;
                }
            }
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ControllerLog annotation = method.getAnnotation(ControllerLog.class);
            logger.info(logAppend.toString()+"\t"+annotation.value());
            method.invoke(target,args);
        }catch (Exception e){
            log.error("aop日志出错：",e.getMessage());
        }
    }
}
