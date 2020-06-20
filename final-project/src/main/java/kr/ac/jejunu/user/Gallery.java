package kr.ac.jejunu.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {
    private Integer id;
    private String name;
    private String posttitle;
    private String postcontent;
    private String postdate;

}
