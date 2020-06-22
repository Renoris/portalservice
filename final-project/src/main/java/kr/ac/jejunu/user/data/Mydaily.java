package kr.ac.jejunu.user.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date dailydate;
    private String oudate;
    private boolean dateimportant;
}
