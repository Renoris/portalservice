package kr.ac.jejunu.user;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Integer id) throws SQLException {
        Connection connection =null;
        PreparedStatement preparedStatement = null;//statement와 preparestatement의 차이점 preparestatement로 구성하게되면 ? 파라미터값을 재활용0 할수 있음
        ResultSet resultSet = null;
        User user = null;
        try {
            //mysql
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select id , name, password from userinfo where id =? ");
            //실행
            preparedStatement.setInt(1, id);
            //실행
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                //결과매핑
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setName(resultSet.getString("password"));
                //자원해지
            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //자원해지

        //결과리턴
        return user;


    }

    public void insert(User user) throws SQLException {
        //mysql
        //driver 로딩
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet= null;
        try {
            connection = dataSource.getConnection();
            //query
            preparedStatement = connection.prepareStatement("insert into userinfo(name, password) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            //실행
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    public void update(User user) throws SQLException {
        //mysql
        //driver 로딩
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("update userinfo set name=? password =? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void delete(Integer id) throws SQLException {
        //mysql
        //driver 로딩
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(
                    "delete from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
