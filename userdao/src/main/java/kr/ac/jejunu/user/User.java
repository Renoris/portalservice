package kr.ac.jejunu.user;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "userinfo")
public class User {
    @Id//id값이 이 오브젝트의 키값이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //그리고 id는 자동증가하는 녀석입니다. 그리고 이 데이터베이스에서 지원하는 자동생성모델을 따르겟습니다.
    private Integer id;

    //@Column(name="name2") 만약 필드의 이름이 다를경우 처리
    private String name;
    private String password;
}
