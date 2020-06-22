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
public class Comment {
    private Integer id;
    private Integer galleryid;
    private String name;
    private String comment;
    private Date commentdate;
}
