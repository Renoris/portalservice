package kr.ac.jejunu.user.dao;

import kr.ac.jejunu.user.data.Gallery;
import kr.ac.jejunu.user.data.GameScore;
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
public class ScoreDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //use viewpost
    public GameScore get(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, name, score, scoredate from gamescore where id = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            GameScore score = null;
            if (rs.next()) {
                score = new GameScore();
                score.setId(rs.getInt("id"));
                score.setName(rs.getString("name"));
                score.setScore(rs.getInt("score"));
                score.setDate(rs.getDate("scoredate"));
            }
            return score;
        });
    }

    public GameScore get_name(String name) {
        Object[] params = new Object[]{name};
        String sql = "select id, name, score, scoredate from gamescore where name = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            GameScore score = null;
            if (rs.next()) {
                score = new GameScore();
                score.setId(rs.getInt("id"));
                score.setName(rs.getString("name"));
                score.setScore(rs.getInt("score"));
                score.setDate(rs.getDate("scoredate"));
            }
            return score;
        });
    }
    //use lobby
    public ArrayList<GameScore> getAll() {
        ArrayList<GameScore> scoreList = new ArrayList<>();
        Object[] params = new Object[]{};
        String sql = "select id, name, score, scoredate from gamescore ORDER BY score DESC";
        return jdbcTemplate.query(sql, params, rs -> {
            GameScore score = null;
            while (rs.next()) {
                score = new GameScore();
                score.setId(rs.getInt("id"));
                score.setName(rs.getString("name"));
                score.setScore(rs.getInt("score"));
                score.setDate(rs.getDate("scoredate"));
                scoreList.add(score);
            }
            return scoreList;
        });
    }

    public void insert(GameScore score) {
        //mysql
        //driver 로딩
        Object[] params = new Object[]{score.getName(),score.getScore(),score.getDate()};
        String sql = "Insert into gamescore(name, score, scoredate) values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        score.setId(keyHolder.getKey().intValue());
    }

    public void update(GameScore score) {
        String sql = "update gamescore set name = ?, score = ?, scoredate = ? where name = ?";
        Object[] params = new Object[]{score.getName(), score.getScore(), score.getDate(), score.getName()};
        jdbcTemplate.update(sql, params);
    }



    public void delete(Integer id) {
        String sql = "delete from gamescore where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
