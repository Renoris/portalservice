package kr.ac.jejunu.user.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mydaily {
    private Integer id;
    private String name;
    private String dailytitle;
    private String dailycontent;
    private String dailydate;
    private String dailytime;
    private String outdate;
}
