package kr.ac.jejunu.user;
import java.sql.*;

public abstract class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        //mysql
        Connection connection = getConnection();
        PreparedStatement preparedStatement=
                connection.prepareStatement("select id , name, password from userinfo where id =? ");//statement와 preparestatement의 차이점 preparestatement로 구성하게되면 ? 파라미터값을 재활용0 할수 있음
        //실행
        preparedStatement.setInt(1,id);
        //실행
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        //결과매핑
        User user =new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setName(resultSet.getString("password"));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과리턴
        return user;


    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        //mysql
        //driver 로딩
        Connection connection = getConnection();
        //query
        PreparedStatement preparedStatement=
                connection.prepareStatement("insert into userinfo(name, password) values(?, ?)",Statement.RETURN_GENERATED_KEYS);
        //실행
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.executeUpdate();
        ResultSet resultSet =preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    abstract public Connection getConnection() throws ClassNotFoundException, SQLException ;//추상화 기법-특정 메소드에 뭐가 들어갈지 모를때 씀-abstract 메소드
       /* //driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection

        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=Asia/Seoul", "jeju", "jejupw");*/
        //추상화과정-abstract로 만들고 각각 따로

}
