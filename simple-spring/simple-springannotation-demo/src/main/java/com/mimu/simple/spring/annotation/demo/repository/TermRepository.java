package com.mimu.simple.spring.annotation.demo.repository;

import com.mimu.simple.spring.annotation.demo.model.TermData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * author: mimu
 * date: 2019/1/11
 */
@Repository
public class TermRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save(TermData termData) {
        String sql = "INSERT INTO `term_info` (`term_id`, `person_id`) values (?,?)";
        int result = jdbcTemplate.update(sql, termData.getTid(), termData.getPid());
        return result > 0;
    }

    public boolean update(TermData termData) {
        String sql = "UPDATE `term_info` SET `term_id`=? WHERE `person_id`=? ";
        int result = jdbcTemplate.update(sql, termData.getTid(), termData.getPid());
        return result > 0;
    }

    public List<TermData> list(String termIdSet) {
        String sql = "SELECT * FROM term_info WHERE find_in_set (term_id,?)";
        return jdbcTemplate.query(sql, new TermDataMapper(), termIdSet);
    }

    static class TermDataMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            TermData record = new TermData();
            record.setTid(rs.getInt("term_id"));
            record.setPid(rs.getInt("person_id"));
            return record;
        }
    }

}
