package kr.ac.jejunu.user.dao;

import kr.ac.jejunu.user.data.Comment;
import kr.ac.jejunu.user.data.Mypicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@Component
public class MypictureDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //use viewpost
    public Mypicture get(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, name, picturetitle, pictureurl,picturedate from mypicture where id = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            Mypicture mypicture = null;
            if (rs.next()) {
                mypicture = new Mypicture();
                mypicture.setId(rs.getInt("id"));
                mypicture.setName(rs.getString("name"));
                mypicture.setPicturetitle(rs.getString("picturetitle"));
                mypicture.setPictureurl(rs.getString("pictureurl"));
                SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
                String datestring=format1.format(rs.getDate("picturedate"));
                mypicture.setOutdate(datestring);
            }
            return mypicture;
        });
    }
    //use lobby
    public ArrayList<Mypicture> getAll() {
        ArrayList<Mypicture> mypictureList = new ArrayList<>();
        Object[] params = new Object[]{};
        String sql = "select id, name, picturetitle, pictureurl, picturedate from mypicture";
        return jdbcTemplate.query(sql, params, rs -> {
            Mypicture mypicture = null;
            while (rs.next()) {
                mypicture = new Mypicture();
                mypicture.setId(rs.getInt("id"));
                mypicture.setName(rs.getString("name"));
                mypicture.setPicturetitle(rs.getString("picturetitle"));
                mypicture.setPictureurl(rs.getString("pictureurl"));
                SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
                String datestring=format1.format(rs.getDate("picturedate"));
                mypicture.setOutdate(datestring);
                mypictureList.add(mypicture);
            }
            return mypictureList;
        });
    }

    public void insert(Mypicture mypicture) {
        //mysql
        //driver 로딩
        Object[] params = new Object[]{mypicture.getName(),mypicture.getPicturetitle(),mypicture.getPictureurl(),mypicture.getPicturedate()};
        String sql = "Insert into mypicture(name, picturetitle, pictureurl, picturedate) values(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        mypicture.setId(keyHolder.getKey().intValue());
    }

    public void delete(Integer id) {
        String sql = "delete from mypicture where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
