package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    String name = "Byung_jun";
    String password="1234";

    private static UserDao userDao;

    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class); //dependency lookup?
    }



    @Test
    public void get() throws SQLException {
        Integer id = 1;
        /*
        ConnectionMaker connectionMaker =new JejuConnectionMaker();
        userDao = new UserDao(connectionMaker);*/
        DaoFactory daoFactory =new DaoFactory();
        UserDao userDao= daoFactory.getUserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void insert() throws SQLException {
        User user =new User();
        user.setName(name);
        user.setPassword(password);
        /*ConnectionMaker connectionMaker =new JejuConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);*/

        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));
        User insertUser = userDao.get(user.getId());
        assertThat(insertUser.getName(), is(name));
        assertThat(insertUser.getPassword(),is(password));
    }
   /* @Test

    public void getHalla() throws SQLException, ClassNotFoundException {
        Integer id = 1;

        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));

        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void insertHalla() throws SQLException, ClassNotFoundException {
        User user =new User();
        user.setName(name);
        user.setPassword(password);
        ConnectionMaker connectionMaker=new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));
        User insertUser = userDao.get(user.getId());
        assertThat(insertUser.getName(), is(name));
        assertThat(insertUser.getPassword(),is(password));
    }*/

    public void update() throws SQLException {
        User user =new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        String updatedName = "김병준";
        user.setName(updatedName);
        String updatedPassword= "1111";
        user.setPassword(updatedPassword);

        userDao.update(user);

        User updatedUser = userDao.get(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    public void delete() throws SQLException {
        User user =new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser=userDao.get(user.getId());
        assertThat(deletedUser, IsNull.nullValue());
    }
}