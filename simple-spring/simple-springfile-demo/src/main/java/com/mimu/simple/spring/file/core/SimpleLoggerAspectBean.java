package com.mimu.simple.spring.file.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: mimu
 * date: 2019/1/25
 */
public class SimpleLoggerAspectBean {
    private static final Logger logger = LoggerFactory.getLogger(SimpleLoggerAspectBean.class);

    public void executeInfo() {
        logger.info(" advise info");
    }
}
