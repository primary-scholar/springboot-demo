package com.mimu.simple.spring.dubbo.provider;

import com.mimu.simple.spring.dubbo.provider.config.ApplicationServerConfig;
import com.mimu.simple.spring.dubbo.server.SimpleJettyServer;
import lombok.extern.slf4j.Slf4j;

/**
 * author: mimu
 * date: 2019/7/15
 */
@Slf4j
public class AppSpringDubboServer extends SimpleJettyServer {

    private AppSpringDubboServer(int port, Class<?> clazz) {
        super(port, clazz);
    }

    public static void main(String args[]) {
        try {
            int port = 9090;
            new AppSpringDubboServer(port, ApplicationServerConfig.class).run();
        } catch (Exception e) {
            log.error("server start failure");
        }
    }
}
