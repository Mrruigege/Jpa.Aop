package com.example.demo000.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
/**
 * aop拦截器
 */
public class HttpAspect {
    private static final Logger loger = LoggerFactory.getLogger(HttpAspect.class);
    //避免代码重复，要拦截路径的地址的重复
    @Pointcut("execution(public * com.example.demo000.controller.GirlController.*(..))")
    public void log0(){

    }
    //在方法执行之前进行拦截,其中*(..)拦截controller下所有的请求
    // @Before("execution(public * com.example.demo000.controller.GirlController.*(..))")
    @Before("log0()")
    public void log(JoinPoint joinPoint){
        //得到request请求
       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        loger.info("url={}",request.getRequestURI());
        //method
        loger.info("method={}",request.getMethod());
        //ip
        loger.info("ip={}",request.getRemoteAddr());
        //类方法，要获取要传入参数JoinPoint
        loger.info("class_method={}",joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName());
        //获取参数
        loger.info("args={}",joinPoint.getArgs());
        //System.out.println(111111);
        //用日志输出
        loger.info("11111");
    }
    //在请求执行之后要调用的方法
    //@After("execution(public * com.example.demo000.controller.GirlController.*(..))")
    @After("log0()")
    public void after(){
      //  System.out.println(222222);
        // 用日志输出
        loger.info("2222222");
    }

    /**
     * 接收返回的参数，装入对象中
     * @param object
     */
    @AfterReturning(returning = "object",pointcut = "log0()")
    public void doAfterReturning(Object object){
        loger.info("object={}",object);
    }
}
