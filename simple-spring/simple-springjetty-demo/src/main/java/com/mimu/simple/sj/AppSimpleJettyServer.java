package com.mimu.simple.sj;

import com.mimu.simple.sj.config.ApplicationConfig;
import com.mimu.simple.sj.server.SimpleJettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2019/7/15
 */
public class AppSimpleJettyServer extends SimpleJettyServer {
    private static final Logger logger = LoggerFactory.getLogger(AppSimpleJettyServer.class);

    private static int port = 9091;

    private AppSimpleJettyServer(int port, Class<?> clazz) {
        super(port, clazz);
    }

    public static void main(String args[]) {
        try {
            logger.info("system params initial complete.");
            new AppSimpleJettyServer(port, ApplicationConfig.class).run();
        } catch (Exception e) {
            logger.error("start server error", e);
        }
    }
}
