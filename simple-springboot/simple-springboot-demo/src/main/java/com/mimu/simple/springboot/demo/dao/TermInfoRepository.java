package com.mimu.simple.springboot.demo.dao;

import com.mimu.simple.springboot.demo.model.TermInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * author: mimu
 * date: 2019/7/7
 */
@Repository
public class TermInfoRepository {
    private JdbcTemplate termInfoJdbcTemplate;

    @Autowired
    public void setTermInfoJdbcTemplate(JdbcTemplate termInfoJdbcTemplate) {
        this.termInfoJdbcTemplate = termInfoJdbcTemplate;
    }

    public TermInfo getTermInfo(long pid) {
        String sql = "select * from term_info where person_id=?";
        List<TermInfo> termInfoList = termInfoJdbcTemplate.query(sql, (resultSet, i) -> {
            TermInfo termInfo = TermInfo.builder().personId(resultSet.getInt("person_id")).
                    termId(resultSet.getInt("term_id")).build();
            return termInfo;
        }, pid);
        return termInfoList.isEmpty() ? null : termInfoList.get(0);
    }

    public boolean addTermInfo(long pid, int termId) {
        String sql = "insert into term_info (`term_id`, `person_id`) values (?,?)";
        return termInfoJdbcTemplate.update(sql, termId, pid) > 0;
    }
}
