package com.mimu.simple.sa.core;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2018/11/17
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class SimpleLoggerAroundAspectBean {
    private static final Logger logger = LoggerFactory.getLogger(SimpleLoggerAroundAspectBean.class);

    @Pointcut(value = "@annotation(SimpleLogger)")
    public void executeInfo() {
    }

    @Around(value = "executeInfo()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        logger.info("logInfo target:{}.{}(),parameter({}),result:{},cost:{}ms", joinPoint.getTarget().getClass().getSimpleName(), getMethod(joinPoint),
                getMethodInfo(joinPoint), JSONObject.toJSONString(object), System.currentTimeMillis() - startTime);
        return object;
    }

    private String getMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            return target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes()).getName();
        }
        return null;
    }

    private String getMethodInfo(ProceedingJoinPoint joinPoint) {
        String[] parameters = getParameter(joinPoint);
        Object[] parameterValue = getParameterValue(joinPoint);
        if (parameters != null) {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < parameters.length; i++) {
                Object object = null;
                try {
                    object = parameterValue[i];
                } catch (Exception e) {
                }
                String value = JSONObject.toJSONString(object);
                buffer.append(parameters[i]).append("=").append(value).append(",");
            }
            String result = buffer.toString();
            return result.length() > 0 ? result.substring(0, result.length() - 1) : result;
        }
        return null;
    }

    private String[] getParameter(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            return methodSignature.getParameterNames();
        }
        return null;
    }

    private Object[] getParameterValue(ProceedingJoinPoint joinPoint) {
        return joinPoint.getArgs();
    }


}
