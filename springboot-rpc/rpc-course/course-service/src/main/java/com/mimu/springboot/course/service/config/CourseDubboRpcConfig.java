package com.mimu.springboot.course.service.config;

import com.mimu.springboot.course.service.rpc.CourseRemoteServiceImpl;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubbo(scanBasePackageClasses = {CourseRemoteServiceImpl.class})
public class CourseDubboRpcConfig {
}
