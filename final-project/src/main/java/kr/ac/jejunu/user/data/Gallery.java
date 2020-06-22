package kr.ac.jejunu.user.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {
    private Integer id;
    private String name;
    private String posttitle;
    private String postcontent;
    private Date postdate;

}
