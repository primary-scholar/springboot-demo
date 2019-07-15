package com.mimu.simple.sa.repository;

import com.mimu.simple.sa.model.TermData;
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
public class TermRepository extends BaseRepository<TermData> {

    @Override
    @Autowired
    public void init(DataSource db1Write, DataSource db1Read) {
        setWriteData(db1Write);
        setReadData(db1Read);
    }

    public boolean save(TermData termData) {
        String sql = "INSERT INTO `term_info` (`term_id`, `person_id`) values (?,?)";
        int result = getWriteTemplate().update(sql, termData.getTid(), termData.getPid());
        return result > 0;
    }

    public boolean update(TermData termData) {
        String sql = "UPDATE `term_info` SET `term_id`=? WHERE `person_id`=? ";
        int result = getWriteTemplate().update(sql, termData.getTid(), termData.getPid());
        return result > 0;
    }

    @Override
    public TermData mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
