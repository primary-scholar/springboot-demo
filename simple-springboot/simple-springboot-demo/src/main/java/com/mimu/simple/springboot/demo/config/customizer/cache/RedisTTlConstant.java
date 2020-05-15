package com.mimu.simple.springboot.demo.config.customizer.cache;

/**
 author: mimu
 date: 2020/5/14
 */
public class RedisTTlConstant {
    public static final String minute_1_info = "mo";
    public static final long minute_1_ttl = 60 * 1000;
    public static final String minute_5_info = "mf";
    public static final long minute_5_ttl = 5 * 60 * 1000;
    public static final String minute_10_info = "mt";
    public static final long minute_10_ttl = 10 * 60 * 1000;
}
