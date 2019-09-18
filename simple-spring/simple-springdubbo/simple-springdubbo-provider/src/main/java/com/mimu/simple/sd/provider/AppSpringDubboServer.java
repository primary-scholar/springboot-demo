package com.mimu.simple.sd.provider;

import com.mimu.simple.sd.provider.config.ApplicationServerConfig;
import com.mimu.simple.sd.provider.server.SimpleJettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2019/7/15
 */
public class AppSpringDubboServer extends SimpleJettyServer {
    private static final Logger logger = LoggerFactory.getLogger(AppSpringDubboServer.class);

    private static int port = 9091;

    private AppSpringDubboServer(int port, Class<?> clazz) {
        super(port, clazz);
    }

    public static void main(String args[]) {
        try {
            logger.info("system params initial complete.");
            new AppSpringDubboServer(port, ApplicationServerConfig.class).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
