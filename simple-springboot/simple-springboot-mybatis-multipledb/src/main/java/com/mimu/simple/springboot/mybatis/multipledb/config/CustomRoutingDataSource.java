package com.mimu.simple.springboot.mybatis.multipledb.config;

import com.mimu.simple.springboot.mybatis.multipledb.utils.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 author: mimu
 date: 2019/12/18
 */
public class CustomRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
