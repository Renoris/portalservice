package kr.ac.jejunu.user.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mypicture {
    private Integer id;
    private String name;
    private String picturetitle;
    private String pictureurl;
    private Date picturedate;
    private String outdate;
}
