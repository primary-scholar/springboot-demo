package com.mimu.simple.sa.repository;

import com.mimu.simple.sa.model.Term;
import com.mimu.simple.sa.repository.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author: mimu
 * date: 2019/1/11
 */
@Repository
public class TermRepository extends BaseRepository<Term> {

    @Override
    @Autowired
    public void init(DataSource db1Write, DataSource db1Read) {
        setWriteData(db1Write);
        setReadData(db1Read);
    }

    public boolean save(Term term) {
        String sql = "INSERT INTO `term_info` (`term_id`, `person_id`) values (?,?)";
        int result = getWriteTemplate().update(sql, term.getTid(), term.getPid());
        return result > 0;
    }

    public boolean update(Term term) {
        String sql = "UPDATE `term_info` SET `term_id`=? WHERE `person_id`=? ";
        int result = getWriteTemplate().update(sql, term.getTid(), term.getPid());
        return result > 0;
    }

    @Override
    public Term mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
