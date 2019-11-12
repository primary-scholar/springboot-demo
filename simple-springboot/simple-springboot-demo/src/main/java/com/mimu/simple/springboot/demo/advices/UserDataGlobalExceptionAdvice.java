package com.mimu.simple.springboot.demo.advices;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * author: mimu
 * date: 2019/10/10
 */

/**
 * 总的来说 spring 中的异常主要有两种
 * 1. 404(异常) 用户访问的 url 不存在
 * 2. controller 异常(参考 SBApplication 说明)
 * 2.1 参数转化异常
 * 2.2 method invoke 异常
 * 对于 1 异常 该 全局异常处理并不支持，
 */
@ControllerAdvice
public class UserDataGlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> errorInfo(Exception exception) {
        Map<String, Object> result = new HashMap<>();
        result.put("cause", exception.getCause());
        result.put("msg", exception.getMessage());
        return result;
    }
}
