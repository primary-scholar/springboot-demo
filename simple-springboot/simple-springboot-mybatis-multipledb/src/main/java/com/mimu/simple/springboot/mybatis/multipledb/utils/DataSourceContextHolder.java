package com.mimu.simple.springboot.mybatis.multipledb.utils;

import com.mimu.simple.springboot.mybatis.multipledb.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 author: mimu
 date: 2019/12/18
 */
@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void setDataSourceType(DataSourceType type) {
        contextHolder.set(type);
    }

    public static DataSourceType getDataSourceType() {
        return contextHolder.get();
    }

    public static void master() {
        log.info("master datasource");
        setDataSourceType(DataSourceType.master);
    }

    /**
     * here if you have multiple slave datasource you can use AtomicInteger to
     * set which slave was set the slave
     * for example:
     * public static void slave() {
     *         int index = counter.getAndIncrement() % 2;
     *         if (counter.get() > 9999) {
     *             counter.set(-1);
     *         }
     *         if (index == 0) {
     *             set(DBTypeEnum.SLAVE1);
     *         }else {
     *             set(DBTypeEnum.SLAVE2);
     *         }
     *     }
     */
    public static void slave() {
        log.info("slave datasource");
        setDataSourceType(DataSourceType.slave);
    }
}
