package com.mimu.simple.springboot.sbd2.dao;

import com.mimu.simple.springboot.sbd2.model.TermData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * author: mimu
 * date: 2019/7/7
 */
@Repository
public class TermRepository {
    private JdbcTemplate termJdbc;

    @Autowired
    public void setTermJdbc(JdbcTemplate termJdbcTemplate) {
        this.termJdbc = termJdbcTemplate;
    }

    public TermData getTermData(long pid) {
        String sql = "select * from term_info where person_id=?";
        List<TermData> termDataList = termJdbc.query(sql, (resultSet, i) -> {
            TermData termData = new TermData();
            termData.setPersonId(resultSet.getLong("person_id"));
            termData.setTermId(resultSet.getInt("term_id"));
            return termData;
        }, pid);
        return termDataList.isEmpty() ? new TermData() : termDataList.get(0);
    }

    public boolean addTermData(long pid, int termId) {
        String sql = "insert into term_info (`term_id`, `person_id`) values (?,?)";
        return termJdbc.update(sql, termId, pid) > 0;
    }
}
