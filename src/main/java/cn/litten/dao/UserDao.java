package cn.litten.dao;

import cn.litten.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //根据用户名和密码查询数据库中匹配的个数
    public Integer getMatchCount(String username,String password){
        String sqlStr = "SELECT count(*) FROM t_user WHERE user_name = ? AND password = ?";

        Integer userCount = jdbcTemplate.queryForObject(sqlStr, new Object[]{username, password}, Integer.class);
        return userCount;
    }
    //根据用户名查询出用户的相关信息
    public User findUserByUsername(String username){
        final User user = new User();
        String sqlStr = "select * from t_user where user_name = ? ";
        jdbcTemplate.query(sqlStr, new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(username);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    //修改用户的登录信息
    public void updateLoginInfo(User user){
        String updateSql = "UPDATE t_user SET last_ip = ? , last_visit = ? , credits = ? WHERE user_id = ? ";
        jdbcTemplate.update(updateSql,new Object[]{user.getLastIp(),user.getLastVisit(),user.getCredits(),user.getUserId()});
    }
}
