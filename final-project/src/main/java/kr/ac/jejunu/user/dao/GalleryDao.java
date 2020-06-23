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
import java.text.SimpleDateFormat;
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
                gallery.setPostcontent(rs.getString("postcontent"));
                gallery.setPosttitle(rs.getString("posttitle"));
                gallery.setPostdate(rs.getDate("postdate"));
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
                SimpleDateFormat format1=new SimpleDateFormat("MM-dd");
                SimpleDateFormat format2=new SimpleDateFormat("HH:mm");
                String datestring=format1.format(rs.getDate("postdate"));
                String timestring=format2.format(rs.getTime("postdate"));
                String datetime=datestring+" "+timestring;
                gallery.setOutdate(datetime);
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
        String sql = "update gallery set posttitle = ? , postcontent = ? ,postdate = ? where id =?";
        Object[] params = new Object[]{gallery.getPosttitle(), gallery.getPostdate()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
        String sql = "delete from gallery where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
