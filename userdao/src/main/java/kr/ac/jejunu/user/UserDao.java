package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
    //change
    public User get(Integer id) throws  SQLException {
        Object[] params = new Object[] {id};
        String sql ="select id, name, password from userinfo where id = ? ";
        return jdbcContext.get(params, sql);
    }

    public void insert(User user) throws  SQLException {
        //mysql
        //driver 로딩
        Object[] params = new Object[] {user.getName(), user.getPassword()};
        String sql ="insert into userinfo (name, password) values(?,?)";
        jdbcContext.insert(user, params, sql);
    }
    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id =?";
        Object[] params = new Object[] {user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[] {id};
        jdbcContext.update(sql, params);
    }


}
