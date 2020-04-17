package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {//-메소드가 abstract뿐-인터페이스로 만듬
    public Connection getConnection() throws ClassNotFoundException, SQLException ;
       /* //추상화 기법-특정 메소드에 뭐가 들어갈지 모를때 씀-abstract 메소드

        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=Asia/Seoul", "jeju", "jejupw");
        //추상화과정-abstract로 만들고 각각 따로*/

}