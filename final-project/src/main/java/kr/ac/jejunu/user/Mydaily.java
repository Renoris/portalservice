package kr.ac.jejunu.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
