package com.mimu.simple.sa.repository;

import com.mimu.simple.sa.model.People;
import com.mimu.simple.sa.repository.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * author: mimu
 * date: 2019/1/11
 */
@Repository
public class PeopleRepository extends BaseRepository<People> {

    @Override
    @Autowired
    public void init(DataSource db1Write, DataSource db1Read) {
        setWriteData(db1Write);
        setReadData(db1Read);
    }

    public boolean save(People people) {
        String sql = "INSERT INTO `person_info` (`person_name`, `person_id`) values (?,?)";
        int result = getWriteTemplate().update(sql, people.getName(), people.getPid());
        return result > 0;
    }

    public boolean update(People people) {
        String sql = "UPDATE `person_info` SET `person_name`=? WHERE `person_id`=?";
        int result = getWriteTemplate().update(sql, people.getName(), people.getPid());
        return result > 0;
    }

    public People getPeople(int pid) {
        String sql = "select * from `person_info` where person_id=?";
        List<People> peopleList = getReadTemplate().query(sql, this::mapRow, pid);
        return peopleList.size() > 0 ? peopleList.get(0) : new People();
    }


    @Override
    public People mapRow(ResultSet resultSet, int i) throws SQLException {
        return People.builder()
                .pid(resultSet.getInt("person_id"))
                .name(resultSet.getString("person_name"))
                .build();
    }
}
