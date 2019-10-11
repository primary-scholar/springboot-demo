package com.mimu.simple.sa.repository;

import com.mimu.simple.sa.model.PersonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * author: mimu
 * date: 2019/1/11
 */
@Repository
public class PeopleRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean save(PersonData personData) {
        String sql = "INSERT INTO `user_info` (`person_name`, `person_id`) values (?,?)";
        int result = jdbcTemplate.update(sql, personData.getNickName(), personData.getPid());
        return result > 0;
    }

    public boolean update(PersonData personData) {
        String sql = "UPDATE `user_info` SET `person_name`=? WHERE `person_id`=?";
        int result = jdbcTemplate.update(sql, personData.getNickName(), personData.getPid());
        return result > 0;
    }

    public PersonData getPeople(int pid) {
        String sql = "select * from `user_info` where person_id=?";
        List<PersonData> personDataList = jdbcTemplate.query(sql, this::mapRow, pid);
        return personDataList.size() > 0 ? personDataList.get(0) : new PersonData();
    }


    private PersonData mapRow(ResultSet resultSet, int i) throws SQLException {
        return PersonData.builder()
                .pid(resultSet.getInt("person_id"))
                .nickName(resultSet.getString("person_name"))
                .build();
    }
}
