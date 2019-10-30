package com.mimu.simple.springboot.mybatis.generator;

import com.mimu.simple.springboot.mybatis.generator.mapper.TermInfoMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author: mimu
 * date: 2019/10/30
 */
@MapperScan(basePackageClasses = TermInfoMapper.class)
@SpringBootApplication
public class SBMGApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SBMGApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run();
    }
}
