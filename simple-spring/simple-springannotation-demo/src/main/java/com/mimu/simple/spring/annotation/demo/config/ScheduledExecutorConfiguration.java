package com.mimu.simple.spring.annotation.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


@Configuration
public class ScheduledExecutorConfiguration {

    /**
     * spring enableScheduling 会引入一个 单线程的 ThreadPoolTaskScheduler
     * 如果 任务较多且耗时，最好自定义 TaskScheduler
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.initialize();
        return scheduler;
    }
}
