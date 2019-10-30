package com.mimu.simple.springboot.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2019/10/30
 */
@Configuration
@Import(value = {TermInfoDataSourceConfig.class, UserInfoDataSourceConfig.class})
public class ApplicationConfig {
}
