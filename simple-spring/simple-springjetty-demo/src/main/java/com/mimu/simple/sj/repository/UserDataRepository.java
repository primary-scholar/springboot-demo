package com.mimu.simple.sj.repository;

import com.mimu.simple.jdbcsupport.BaseRepository;
import com.mimu.simple.sj.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * author: mimu
 * date: 2019/8/5
 */
@Repository
public class UserDataRepository extends BaseRepository<UserData> {
    @Override
    @Autowired
    public void init(DataSource db1Write, DataSource db1Read) {
        setWriteDataSource(db1Write);
        setReadDataSource(db1Read);
    }

    public boolean save(UserData userData) {
        String sql = "INSERT INTO `user_info` (`person_name`, `person_id`) values (?,?)";
        int result = getWriteJdbcTemplate().update(sql, userData.getNickName(), userData.getPid());
        return result > 0;
    }

    public boolean update(UserData userData) {
        String sql = "UPDATE `user_info` SET `person_name`=? WHERE `person_id`=?";
        int result = getWriteJdbcTemplate().update(sql, userData.getNickName(), userData.getPid());
        return result > 0;
    }

    public UserData getData(int pid) {
        String sql = "select * from `user_info` where person_id=?";
        List<UserData> userDataList = getReadJdbcTemplate().query(sql, this::mapRow, pid);
        return userDataList.size() > 0 ? userDataList.get(0) : new UserData();
    }


    @Override
    public UserData mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserData.builder()
                .pid(resultSet.getInt("person_id"))
                .nickName(resultSet.getString("person_name"))
                .build();
    }
}
