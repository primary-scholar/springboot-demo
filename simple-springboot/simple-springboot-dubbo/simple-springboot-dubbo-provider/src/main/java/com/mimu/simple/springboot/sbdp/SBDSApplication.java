package com.mimu.simple.springboot.sbdp;

import com.mimu.simple.springboot.sbdp.config.DataSourceConfig;
import com.mimu.simple.springboot.sbdp.config.ServiceProviderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2019/8/18
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
@Import(value = {DataSourceConfig.class, ServiceProviderConfig.class})
public class SBDSApplication {
    public static void main(String[] args) {
        SpringApplication.run(SBDSApplication.class);
    }
}
