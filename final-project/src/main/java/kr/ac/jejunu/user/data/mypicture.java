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
public class mypicture {
    private Integer id;
    private String name;
    private String picturetitle;
    private String pictureurl;
    private Date picturedate;
}
