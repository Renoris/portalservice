package kr.ac.jejunu.user.dao;

import kr.ac.jejunu.user.data.Mydaily;
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
public class MydailyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //change
    public Mydaily get(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, name, dailytitle, dailycontent, dailydate,dailytime from mydaily where id = ? ";
        return jdbcTemplate.query(sql, params, rs -> {
            Mydaily mydaily = null;
            if (rs.next()) {
                mydaily = new Mydaily();
                mydaily.setId(rs.getInt("id"));
                SimpleDateFormat format1=new SimpleDateFormat("MM-dd");
                SimpleDateFormat format2=new SimpleDateFormat("HH:mm");
                String datestring=format1.format(rs.getDate("dailydate"));
                String timestring=format2.format(rs.getTime("dailytime"));
                String datetime=datestring+" "+timestring;
                mydaily.setOutdate(datetime);
                mydaily.setName(rs.getString("name"));
                mydaily.setDailytitle(rs.getString("dailytitle"));
                mydaily.setDailycontent(rs.getString("dailycontent"));
                System.out.println(mydaily.toString());
            }
            return mydaily;
        });
    }

    public ArrayList<Mydaily> getMydailyAll(String name) {
        ArrayList<Mydaily> mydailyList = new ArrayList<>();
        Object[] params = new Object[]{name};
        String sql = "select id, name, dailytitle, dailycontent, dailydate,dailytime from mydaily where name = ?";
        return jdbcTemplate.query(sql, params, rs -> {
            Mydaily mydaily = null;
            while (rs.next()) {
                mydaily = new Mydaily();
                mydaily.setId(rs.getInt("id"));
                SimpleDateFormat format1=new SimpleDateFormat("MM-dd");
                SimpleDateFormat format2=new SimpleDateFormat("HH:mm");
                String datestring=format1.format(rs.getDate("dailydate"));
                String timestring=format2.format(rs.getTime("dailytime"));
                String datetime=datestring+" "+timestring;
                mydaily.setOutdate(datetime);
                mydaily.setName(rs.getString("name"));
                mydaily.setDailytitle(rs.getString("dailytitle"));
                mydaily.setDailycontent(rs.getString("dailycontent"));
                mydailyList.add(mydaily);
            }

            return mydailyList;
        });
    }

    public void insert(Mydaily mydaily) {
        //mysql
        //driver 로딩
        Object[] params = new Object[]{mydaily.getName(), mydaily.getDailytitle(), mydaily.getDailycontent(),mydaily.getDailydate(),mydaily.getDailytime()};
        String sql = "insert into mydaily (name, dailytitle, dailycontent, dailydate,dailytime) values(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        mydaily.setId(keyHolder.getKey().intValue());
    }

    public void update(Mydaily mydaily) {
        String sql = "update mydaily set name = ?, dailytitle = ?, dailycontent, dailydate,dailytime where id =?";
        Object[] params = new Object[]{mydaily.getName(), mydaily.getDailytitle(), mydaily.getDailycontent(),mydaily.getDailydate()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
        String sql = "delete from mydaily where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
