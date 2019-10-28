package com.mimu.simple.spring.dubbo.consumer;

import com.mimu.simple.spring.dubbo.consumer.config.ApplicationConsumerConfig;
import com.mimu.simple.sd.server.SimpleJettyServer;
import lombok.extern.slf4j.Slf4j;

/**
 * author: mimu
 * date: 2019/9/18
 */
@Slf4j
public class AppSpringDubboConsumer extends SimpleJettyServer {

    private AppSpringDubboConsumer(int port, Class<?> clazz) {
        super(port, clazz);
    }

    public static void main(String args[]) {
        try {
            int port = 9091;
            new AppSpringDubboConsumer(port, ApplicationConsumerConfig.class).run();
        } catch (Exception e) {
            log.error("server start failure");
        }
    }
}
