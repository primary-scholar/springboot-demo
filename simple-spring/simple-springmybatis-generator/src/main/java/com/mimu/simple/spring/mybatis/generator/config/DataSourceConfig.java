package com.mimu.simple.spring.mybatis.generator.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mimu.simple.spring.mybatis.generator.mapper.UserInfoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;

/**
 * author: mimu
 * date: 2019/10/28
 */
@Configuration
@PropertySource(value = {"classpath:db-connect.properties"})
@MapperScan(basePackageClasses = UserInfoMapper.class, sqlSessionFactoryRef = "userSqlSessionFactory")
@EnableTransactionManagement
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value(value = "${db.user.url}")
    private String dbUrl;
    @Value(value = "${db.user.username}")
    private String dbUser;
    @Value(value = "${db.user.password}")
    private String dbPassword;

    @Bean
    public DataSource userDataSource() {
        return getDataSource(dbUrl, dbUser, dbPassword, true);
    }


    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(DataSource userDataSource) {
        return new DataSourceTransactionManager(userDataSource);
    }

    @Bean
    @Resource
    public SqlSessionFactory userSqlSessionFactory(DataSource userDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(userDataSource);
        ClassPathResource resource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory.getObject();
    }

    private DataSource getDataSource(String url, String user, String password, boolean useUtf8Mb4) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        String jdbcUrl = String.format("%s"
                , url);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        dataSource.setInitialSize(5);
        dataSource.setMaxWait(60000);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setValidationQuery("select 1");
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeoutMillis(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setBreakAfterAcquireFailure(false);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        if (useUtf8Mb4) {
            dataSource.setConnectionInitSqls(Collections.singletonList("set names utf8mb4;"));
        }
        logger.debug(" mybatis generator getDataSource info url={}", jdbcUrl);
        return dataSource;
    }
}