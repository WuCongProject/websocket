package com.wucong.client.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @author luhoo
 * @date 2020/11/3  19:43
 */
@Aspect
@Component
public class RequestAop {

    private final Logger logger = LoggerFactory.getLogger(RequestAop.class);

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    /**
     * 切入点描述 这个是controller包的切入点
     */
    @Pointcut("execution(public * com.wucong.client.controller..*.*(..))")
    public void controllerLog(){}//签名，可以理解成这个切入点的一个名称


    /**
     * 在切入点的方法run之前要干的
     */
    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        //这个RequestContextHolder是SpringMvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("THE ARGS OF THE CONTROLLER : " + Arrays.toString(joinPoint.getArgs()));

        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

    }

    /**
     * 在切入点的方法run完要干的
     */
    @AfterReturning(value = "controllerLog()", returning = "response")
    public void logAfterController(Object response) {
        logger.info("response : {}",response.toString());
        logger.info("This request use {} ms",System.currentTimeMillis() - startTime.get());

    }

}
