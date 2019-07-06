package com.mimu.simple.sa.repository.base;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * author: mimu
 * date: 2019/1/11
 */
public abstract class BaseRepository<T> implements RowMapper<T>, InitializingBean {

    protected DataSource writeData;
    protected DataSource readData;
    protected JdbcTemplate writeTemplate;
    protected JdbcTemplate readTemplate;
    protected NamedParameterJdbcTemplate writeNamedParameterJdbcTemplate;
    protected NamedParameterJdbcTemplate readNamedParameterJdbcTemplate;


    @Override
    public void afterPropertiesSet() throws Exception {
        init(writeData, readData);
    }

    public abstract void init(DataSource write, DataSource read);

    public void setWriteData(DataSource dataSource) {
        this.writeData = dataSource;
        this.writeTemplate = new JdbcTemplate(dataSource);
        this.writeNamedParameterJdbcTemplate = new NamedParameterJdbcTemplate(writeTemplate);
    }

    public void setReadData(DataSource dataSource) {
        this.readData = dataSource;
        this.readTemplate = new JdbcTemplate(dataSource);
        this.readNamedParameterJdbcTemplate = new NamedParameterJdbcTemplate(readTemplate);
    }

    public JdbcTemplate getWriteTemplate() {
        return writeTemplate;
    }

    public JdbcTemplate getReadTemplate() {
        return readTemplate;
    }

    public NamedParameterJdbcTemplate getWriteNamedParameterJdbcTemplate() {
        return writeNamedParameterJdbcTemplate;
    }

    public NamedParameterJdbcTemplate getReadNamedParameterJdbcTemplate() {
        return readNamedParameterJdbcTemplate;
    }
}
