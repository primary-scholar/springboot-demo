package com.mimu.simple.springboot.ssb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * author: mimu
 * date: 2019/10/9
 */
@Configuration
@Import(DataSourceConfig.class)
public class UserDataApplicationConfig {
}
