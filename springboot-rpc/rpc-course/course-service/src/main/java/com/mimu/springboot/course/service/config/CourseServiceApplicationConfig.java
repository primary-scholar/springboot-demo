package com.mimu.springboot.course.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CourseDubboRpcConfig.class})
public class CourseServiceApplicationConfig {
}
