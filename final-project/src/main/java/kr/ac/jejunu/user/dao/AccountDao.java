package kr.ac.jejunu.user.dao;

import kr.ac.jejunu.user.data.User;
import kr.ac.jejunu.user.data.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;


@Component
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //change
    public UserAccount get(String name, String password) {
        Object[] params = new Object[]{name, password};
        String sql = "select id, name, password from useraccount where name = ? and password = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            UserAccount userAccount = null;
            if (rs.next()) {
                userAccount = new UserAccount();
                userAccount.setId(rs.getInt("id"));
                userAccount.setName(rs.getString("name"));
                userAccount.setPassword(rs.getString("password"));
                System.out.println(userAccount.toString());
            }
            return userAccount;
        });
    }

    public UserAccount getmatch(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, name, password from useraccount where id = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            UserAccount userAccount = null;
            if (rs.next()) {
                userAccount = new UserAccount();
                userAccount.setId(rs.getInt("id"));
                userAccount.setName(rs.getString("name"));
                userAccount.setPassword(rs.getString("password"));
                System.out.println(userAccount.toString());
            }
            return userAccount;
        });
    }

    public ArrayList<UserAccount> getUserAll() { //관리자용
        ArrayList<UserAccount> userList = new ArrayList<>();
        Object[] params = new Object[]{};
        String sql = "select id, name, password from account";
        return jdbcTemplate.query(sql, params, rs -> {
            UserAccount userAccount = null;
            while (rs.next()) {
                userAccount = new UserAccount();
                userAccount.setId(rs.getInt("id"));
                userAccount.setName(rs.getString("name"));
                userAccount.setPassword(rs.getString("password"));
                userList.add(userAccount);
            }

            return userList;
        });
    }

    public void insert(UserAccount userAccount) {
        //mysql
        //driver 로딩
        Object[] params = new Object[]{userAccount.getName(), userAccount.getPassword()};
        String sql = "insert into useraccount (name, password) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        userAccount.setId(keyHolder.getKey().intValue());
    }

    public void update(UserAccount userAccount) {
        String sql = "update useraccount set name = ?, password = ? where id =?";
        Object[] params = new Object[]{userAccount.getName(), userAccount.getPassword(), userAccount.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) { //관리자용
        String sql = "delete from useraccount where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
