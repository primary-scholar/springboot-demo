package com.mimu.simple.sa.repository;

import com.mimu.simple.sa.model.TermData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

}
