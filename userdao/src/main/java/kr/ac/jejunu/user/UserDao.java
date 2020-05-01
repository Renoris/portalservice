package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
    //change
    public User get(Integer id) throws  SQLException {
        StatementStrategy statementStrategy =new GetStatementStrategy(id);;
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public void insert(User user) throws  SQLException {
        //mysql
        //driver 로딩
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
