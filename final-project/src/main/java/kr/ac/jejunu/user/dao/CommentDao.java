package kr.ac.jejunu.user.dao;

import kr.ac.jejunu.user.data.Comment;
import kr.ac.jejunu.user.data.Gallery;
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
public class CommentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //use viewpost
    public Comment get(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, galleryid, name,comment ,commentdate from comment where id = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            Comment comment = null;
            if (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setName(rs.getString("name"));
                comment.setGalleryid(rs.getInt("galleryid"));
                comment.setComment(rs.getString("comment"));
                SimpleDateFormat format1=new SimpleDateFormat("MM-dd");
                SimpleDateFormat format2=new SimpleDateFormat("HH:mm");
                String datestring=format1.format(rs.getDate("commentdate"));
                String timestring=format2.format(rs.getTime("commentdate"));
                String datetime=datestring+" "+timestring;
                comment.setOutdate(datetime);
            }
            return comment;
        });
    }
    //use lobby
    public ArrayList<Comment> getAll() {
        ArrayList<Comment> commentList = new ArrayList<>();
        Object[] params = new Object[]{};
        String sql = "select id, galleryid, name,comment ,commentdate from comment";
        return jdbcTemplate.query(sql, params, rs -> {
            Comment comment = null;
            while (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setName(rs.getString("name"));
                comment.setGalleryid(rs.getInt("galleryid"));
                comment.setComment(rs.getString("comment"));
                SimpleDateFormat format1=new SimpleDateFormat("MM-dd");
                SimpleDateFormat format2=new SimpleDateFormat("HH:mm");
                String datestring=format1.format(rs.getDate("commentdate"));
                String timestring=format2.format(rs.getTime("commentdate"));
                String datetime=datestring+" "+timestring;
                comment.setOutdate(datetime);
                commentList.add(comment);
            }
            return commentList;
        });
    }

    public void insert(Comment comment) {
        //mysql
        //driver 로딩
        Object[] params = new Object[]{comment.getName(), comment.getGalleryid(),comment.getComment(),comment.getCommentdate()};
        String sql = "Insert into comment(name, galleryid,comment,commentdate) values(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        comment.setId(keyHolder.getKey().intValue());
    }

    public void delete(Integer id) {
        String sql = "delete from comment where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
