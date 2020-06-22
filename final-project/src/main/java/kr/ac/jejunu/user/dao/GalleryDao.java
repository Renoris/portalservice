package kr.ac.jejunu.user.dao;

import kr.ac.jejunu.user.data.Gallery;
import kr.ac.jejunu.user.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;


@Component
public class GalleryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //use viewpost
    public Gallery get(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, name, posttitle,postcontent,postdate from gallery where id = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            Gallery gallery = null;
            if (rs.next()) {
                gallery = new Gallery();
                gallery.setId(rs.getInt("id"));
                gallery.setName(rs.getString("name"));
                gallery.setPosttitle(rs.getString("posttitle"));
                gallery.setPostdate(rs.getDate("posttitle"));
            }
            return gallery;
        });
    }
    //use lobby
    public ArrayList<Gallery> getAll() {
        ArrayList<Gallery> galleryList = new ArrayList<>();
        Object[] params = new Object[]{};
        String sql = "select id, name, posttitle, postdate from gallery";
        return jdbcTemplate.query(sql, params, rs -> {
            Gallery gallery = null;
            while (rs.next()) {
                gallery = new Gallery();
                gallery.setId(rs.getInt("id"));
                gallery.setName(rs.getString("name"));
                gallery.setPosttitle(rs.getString("posttitle"));
                gallery.setPostdate(rs.getDate("postdate"));
                galleryList.add(gallery);
            }
            return galleryList;
        });
    }

    public void insert(Gallery gallery) {
        //mysql
        //driver 로딩
        Object[] params = new Object[]{gallery.getName(), gallery.getPosttitle(),gallery.getPostcontent(),gallery.getPostdate()};
        String sql = "Insert into gallery(name, posttitle,postcontent,postdate) values(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        gallery.setId(keyHolder.getKey().intValue());
    }

    public void update(Gallery gallery) {
        String sql = "update gallery set name = ?, posttitle = ? , postcontent = ? ,where id =?";
        Object[] params = new Object[]{gallery.getName(), gallery.getPosttitle(), gallery.getPostcontent()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
        String sql = "delete from gallery where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
