package kr.ac.jejunu.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean//spring이 new주는 instance
    public UserDao getUserDao() {
        return new UserDao(ConnectionMaker());
    }
    @Bean
    public JejuConnectionMaker ConnectionMaker() {
        return new JejuConnectionMaker();
    }
}

