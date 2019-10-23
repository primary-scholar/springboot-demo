package com.mimu.simple.sj.repository;

import com.mimu.simple.sj.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * author: mimu
 * date: 2019/8/5
 */
@Repository
public class UserDataRepository {
    private JdbcTemplate userDataJdbcTemplate;

    @Autowired
    public void setUserDataJdbcTemplate(JdbcTemplate userDataJdbcTemplate) {
        this.userDataJdbcTemplate = userDataJdbcTemplate;
    }

    public boolean save(UserData userData) {
        String sql = "INSERT INTO `user_info` (`person_name`, `person_id`) values (?,?)";
        int result = userDataJdbcTemplate.update(sql, ps -> {
            ps.setString(1,userData.getNickName());
            ps.setLong(2,userData.getPid());
        });
        return result > 0;
    }

    public boolean update(UserData userData) {
        String sql = "UPDATE `user_info` SET `person_name`=? WHERE `person_id`=?";
        int result = userDataJdbcTemplate.update(sql, userData.getNickName(), userData.getPid());
        return result > 0;
    }

    public UserData getData(long pid) {
        String sql = "select * from `user_info` where person_id=?";
        List<UserData> userDataList = userDataJdbcTemplate.query(sql, this::mapRow, pid);
        return userDataList.size() > 0 ? userDataList.get(0) : new UserData();
    }


    private UserData mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserData.builder()
                .pid(resultSet.getInt("person_id"))
                .nickName(resultSet.getString("person_name"))
                .build();
    }
}
