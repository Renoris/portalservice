# Spring_Study

**Language**:java

**framework**:spring

**IDE**:intelliJ IDEA-ultimate

## 사전설정

Build,Execution.Deployment-> Compiler->Annotation Processors -> enable annotation processing

build-buildTool.gradle로 가서 gradle을 인텔리제이로 바꿔주면됨

build.gradle 추가사항-라이브러리들

```groovy
plugins {
    id 'java'
    id 'war'
}

group 'kr.ac.jejunu'
version '0.0.1-SNAPSHOT'
sourceCompatibility = '12'


repositories {
    mavenCentral()
}
test{
    useJUnitPlatform()
}

ext{
    springVersion ='5.2.5.RELEASE'
}

dependencies {
    implementation 'javax.servlet:javax.servlet-api:4.0.1'
    
    compileOnly 'org.projectlombok:lombok:1.18.16'
    annotationProcessor 'org.projectlombok:lombok:1.18.16'
    testCompileOnly 'org.projectlombok:lombok:1.18.16'
    testAnnotationProcessor 'org.projectlombok:lombok:1.18.16'
    
    implementation 'org.codehaus.groovy:groovy:3.0.3'
    
    implementation "org.springframework:spring-jdbc:${springVersion}"
    
    implementation "org.springframework:spring-core:${springVersion}"
    implementation "org.springframework:spring-context:${springVersion}"
    
    implementation "org.springframework:spring-webmvc:${springVersion}"
    
    runtimeOnly 'mysql:mysql-connector-java:8.0.19'
    testImplementation 'org.hamcrest:hamcrest:2.1'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.5.2'
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.0'
    implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.11.0'

}
```



implementation 'org.codehaus.groovy:groovy:3.0.3' 는 GenericGroovyApplicationContext를 사용하기 위한 라이브러리





lombok라이브러리-annotation processing 활성화 필요

```groovy
compileOnly 'org.projectlombok:lombok:1.18.16'
annotationProcessor 'org.projectlombok:lombok:1.18.16'
testCompileOnly 'org.projectlombok:lombok:1.18.16'
testAnnotationProcessor 'org.projectlombok:lombok:1.18.16'
```

servlet api 라이브러리-servlet을 쓰기위한 인터페이스가 정의되어 있음 또한 플러그인으로 war를 요구

```groovy
implementation 'javax.servlet:javax.servlet-api:4.0.1'
```

spring jdbc 라이브러리

```groovy
implementation "org.springframework:spring-jdbc:${springVersion}"
```

spring mvc모델 라이브러리

```groovy
implementation "org.springframework:spring-webmvc:${springVersion}"
```

ContentNegotiatingViewResolver 라이브러리

```groovy
implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.0' -object를 json 데이터로 mapping
implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.11.0'-object를 xml 로 mapping
```

Spring jpa data 라이브러리

```groovy
implementation 'org.springframework.data:spring-data-jpa:2.3.1.RELEASE' //spring data jpa를 랩핑한것에 불가
implementation 'org.hibernate:hibernate-entitymanager:5.4.17.Final' //실제 jpa
```





## 인텔리제이 단축키

Ctrl+space      code insite

Ctrl+F6            change signiture  -리팩토링시 인자값을 바꿀때 사용 

Ctrl+E              최근 실행 파일 열기

Ctrl+B              해당 필드의 선언으로 이동

Ctrl+W             해당줄 선택

Ctrl+d               해당줄 복제

Ctrl+y               해당줄 삭제

Ctrl+/                해당줄 주석처리

Ctrl+I                 implement method

Ctrl+O               override Method

Ctrl+Alt+M       EXTRACT Method

Ctrl+shift+↑↓   구문안에서 라인이동

Ctrl+왼오          단어별 이동

Ctrl+shift+←→  단어별 선택

Ctrl+Alt+shift+←→ - 엘리먼트 단위 이동



Alt+Enter         show context Action

Alt+1                프로젝트창열기

Alt+shift+↑↓    문법관계없이 라인이동



shift+↑↓            그 커서에서 선택하면서 라인이동

shift+Ctrl+Enter  현재 구문완성-for문입력도중 사용시 중괄호 완성하고 안에 커서이동 

shift+Ctrl+Alt+T   리펙토링 단축키





### 프레임워크란?

개발자들이 쌓아온 노하우들을 하나씩 모아서 정립시킨 여러가지 **디자인 패턴**들을 중복해서 개발하지 않게 잘 뭉쳐 구현체로 만든것



### Object Oriented(객체 지향)

-Encapsulation 캡슐화

-Abstarction 추상화

-Inheritance 상속성

-Polymorphism 다형성



### Object Oriented Design Principle(객체지향 디자인 원칙)

객체지향을 사용해서 어떻게 하면 잘 사용할수 있을지에 대한 개발자의 노하우를 담은 원칙들

-**SRP(Single Responsibility Principle)**-단일 책임의 법칙-클래스와 메소드는 한가지의 책임을 가져야만 한다

-특정메소드가 사용자 수정/삭제/생성을 모두하지않는다

-특정클래스가 상품관리와 상품 조회를 모두 하지않는다



-**OCP(Open/Close Principle)**-변경에는 닫혀있고 확장에는 열려있어야 한다.



-**LSP(Liskov Substitution Principle)**-리스코프 치환의 법칙-자식오브젝트는 부모오브젝트처럼 활용이 가능하다.(치환이 가능하다)

ex-String a="1"

Object a=(Object) a;



-**ISP(Interface Segregation Principle)**-인터페이스를 다양한 기능으로 분리해서 인터페이스를 만들것-구현해야되는 기능이 3개가있는데 셋중하나를 구현할 필요가없으면 아예 인터페이스를 분리를 하라-나중에 세가지 기능을 갖는 클래스를 구현을 할때 그 분리된 인터페이스들을 모두 끌고오면 될일.



-**DIP(Dependency Inversion Principle)**-스프링을 설명하면서 강의할때 나옴



### KentBeck의 TDD

-큰 설계를 통한 개발은 agile한 방법론에는 맞지않음

-Known To UnKnown-아는것에서 모르는것으로



# DI(IoC)

Inversion Of Control

Dependency Injection

Dependency Lookup





## Gradle

Build-자바의 빌드라는 과정은

-clean-기존의 컴파일이 되어있던 올드한 클래스들을 깔끔하게 지우고

-javac-자바로된 소스코드를 컴파일

-test-테스트케이스가 있으면 테스트케이스가 잘돌아가는지 검증

-packaging(jar,war,ear)-자바로된 라이브러리는 jar로 웹어플리케이션에대한 파일은 war로, 엔터프라이즈 어플리케이션에 대한것들은 .ear로 패키징 

-deploy-최종적으로 만들어진 파일을 전달하는과정, 혹은 서버에 올리는 과정



위 과정을 리눅스에서는 shell로 만들어서 지정을 해주고 돌렷음

그러나 매우 번거롭고 나중에 복잡해질수록 shell 더러워졋음 그래서 Ant 라는 툴을 만듬

Ant는 하나의 프로젝트를 두가지 개념으로 나눔 target과 task

특정 단계별로 task들을 묶어놓은것을 target이라고함

그리고 차례대로 target을 실행하는것이 Ant                Ant과정은 Xml파일로 정의

그러나 라이브러리 끼리 참조하거나 디펜던시 관리를 하지못해서 힘든상황에 획기적인 maven이 나옴

maven은 pom.xml에 디펜던시를 다 정해놓아짐. pom.xml만있으면 라이브러리들을 자동으로 가져옴 . 라이브러리는 공개된 소스들에서 다 가져옴

maven의 deploy는 maven repository에 대한 deploy

또한 maven의 goal은 Ant의 task와 비슷하고 phase는 ant의 target과 비슷함

maven은 표준 디렉토리를 만들어서 관리해 줘야함-main과 test를 분리하고 각기 java와 resources 폴더로 나눠주어야함- 또한 인텔리제이의 경우 테스트와 메인, 테스트소스와 메인소스를 지정해줘야함-인텔리제이 module탭에서 가능

root

​	-lib

​	-src

​	-main

​		-java

​		-resources

​	-test

​		-java

​		-resources

maven을 실행시 lifecycle이 보이는데 그것을 실행하면 정의되어 있는 라이프 사이클을 따라서 실행이 됨

-plugin 실행시 그 과정이 바로 실행되는 장점이 있음



**gradle**-groovy 기반의 language

-쉬운확장

-maven의 dependency를 그대로 가지고가고 maven의 대부분이 지원이됨, Ant와 Ivy라는것도 지원이 된다고 함

-개발되는 환경과 실제 수행하고 배포되는 환경이 다르거나 여러가지 환경에서 지원되는데 XML로 실행되는 maven은 플러그인들의 도움을 받아야 되거나 받지못하는경우가 있어서 gradle이 탄생함 

groovy language 입력시 그대로 수행가능

gradle은 단지 task를 입력하는것 뿐이엿지만 java plugin을 통해 많은 행동을 할 수 있었으며 다른 language 또한 plugin으로 gradle을 활용할수 있어 많은 쓰임을 받게 되었다.

-gradle에서 jar파일을 만드는 plugin은 jar 이다

-또한 gradle은 배포한 서버쪽의 gradle이 install 되지 않아도 gradlew를 실행하게 되면 이 빌드에 활용되는 버전을 기반으로 gradle이 실행이됨.-버전의 하위 호환성이 유지되고 gradle이 깔려있지 않아도 사용할수 있음







### 단어들

인텔리제이에서 어노테이션을 쓰려면 어노테이션 프로세싱을 활성화 해줘야함

**mock**:가짜오브젝트임에도 진짜처럼 보이는것...?





## 라이브러리

**Spring**

-Controller

@RestController-이 클래스는 Rest형식의 컨트롤러임을 병시

@RequestMapping()-인자값으로 패스 설정-클래스나 메소드에 설정

-@RequestMapping(path="/save", method={RequestMethod.POST, RequestMehod.PUT}) 처럼 여러개의 방식을 받을수도있음-메소드에 설정

@Repository-이클래스가 레포지토리임을 선언.



@Autowired-bean에 생명력을 불어넣는 어노테이션



@SpringBootTest:이 클래스는 테스트 클래스임을 명시 -test





**Mock**:테스트를 진행하기 위한 가짜 오브젝트를 만드는 라이브러리-프론트엔드의 역할을 대신 할 수 있음

-MockMvc:웹에 get이나 post를 날리기 위한 mockmvc 객체. 메소드로 perform을 가지고 있고 이 perform안에 get이나 post를 요청하게된다. mvc.perform(MockMvcRequestBuilders.get("/api/list")) 이런식 보통 MockMvcRequestBuilders 는 자주쓰니까 static으로 요청하면 get만써도된다

mvc.perform의 하위 메소드

.andExpect() 메소드를 붙여주게되면 이것을 기대 한다는 뜻임. 즉 호출값과 andExpect안의 값이 동일하면 테스트 성공...?

.contentType(MediaType.APPLICATION_JSON)-컨텐트 타입이 json이라고 알림

.content(jsonString)-컨텐트

-@MockBean:가짜 오브젝트라는것을 알려주는 것

-@AutoConfigureMockMvc:이클래스가 MockMvc임을 명시 클래스위에 설정하는것 -Test



**org.mockito.BDDMOckito.given**

-ex) given(userController.create(users).willReturn(user));

즉 given이라는 객체는 테스트를 위해 현재 작성하고있는 메소드를 넣으면 .willReturn() 즉 저 매개변수 안에있는 것이 리턴이 된다고 가정하는 메소드임.



**objectMapper**-객체를 json String으로 바꿔주는 클래스

-ex)objectMapper.writeValueAsString(users)-하면 json String 형식으로 나옴



**lombok:**자바에서 Model(DTO, VO, Domain) Object 를 만들때, 멤버필드(프로퍼티)에 대한 Getter/Setter, ToString과 멤버필드에 주입하는 생성자를 만드는 코드 등 불필요하게 반복적으로 만드는 코드를 어노테이션을 통해 줄여 주는 라이브러리

@Data :getter, setter를 생성할 필요없게 만들어준다.

@Builder:생성자를 일일이 만드는 대신 객체 생성당시에 하나하나 직접 추가가 가능. ex)User user= User.Builder().id(1004).username("god").build(); 이런식

-@Singular:필드에 넣는 어노테이션. collection으로 된 필드면 모든원소를 한번에 보내지않고 원소 하나씩 추가가능.ex) User.Builder().score(70).score(80).build();

@NonNull:자동으로 널체크를 해줌, 해당 변수로 Null이 넘어오면 NullPointerException예외가 발생하게 된다

@NoArgsCostructor

@AllArgsConstructor:빌더를 쓰려면 모든 필드 혹은 Argument를 가진 생성자 혹은 아무것도없는(기본) 생성자를 선언해줘야하기 때문에 빌더를 쓸때에는 이것 2개를 넣어준다.

@RequiredArgsConstructor-final 객체에 대한 생명력을 자동으로 잡아주는 것-Contoller에 설정



**JPA**

인터페이스를 만들고 거기에 JpaRepository를 상속하게 하면 자동으로 JpaRespostiory를 쓸수잇음 

-public interface UserRepository extends JpaRepository<User, Interger>-꺽쇄 사이에는데이터베이스에 저장할 객체와 integer를 넣어주면됨

-userRepository.findById(id).get(); -findBy -필드이름을 주고 값을 넣은다음 get하면 레코드에 해당된 정보가 저장된 객체를 불러올수 있음

-userRepository.delete();-매개변수로 받은 객체를 지움

-userRepository.save();-매개변수로 받은 객체를 저장함





@DataJpaTest: jpa test를 하는 어노테이션

@Entity:JPA를 쓰기위해서는 일반적으로 DTO에 선언해줘야하는 것 선언해줄때 @Entity(name ="userinfo"); 처럼 테이블이름을 지정해줘야함

-@Id:엔티티에는 ID가 반드시필요-DTO내부에 가장맨위에 설정

-@generateValue(Startergy = GerationType.Identity) -그리고 ID바로 밑에 그 ID를 autoincrement 하게 설정해준다는뜻. 이렇게되면 테스트할때마다 자동적으로 id를 생성해줌



**그외**

is():equalTo와 비슷한 의미

assertThat(1,2):보통 두번째 인자에 is(객체) 로 주게된다.-앞 값과 뒤값을 비교하는 메서드. 보통 static으로 사용해서 편하게 쓴다



**mysql에서 autoincreament id 를 쓸경우**

preparedStatement에서 보통 insert할때 id를 제외한 값을 주게되는데 왜냐하면 id는 mysql에선 autogenerated 로 설정해놧기 때문이다.

그러나 id를 ResultSet에 담기위해서는 따로 특별하게 설정해줘야하는데 Statement.RETURN_GENERATED_KEYS라는 상수인자를 넘겨주어야 한다.

connection.prepareStatement("insert into userinfo(name ,password)" values(?,?)" , Statement.RETURN_GENERATED_KEYS);

그리고 ResultSet을 결과값으로 받지않고 단독 업데이트를 한이후 

ResultSet 생성할때 ResultSet rs=preparedStatement.getGeneratedKeys(); 메소드로 Resultset을 생성하고 

next()메소드로 데이터를 가져와서 bean에 담아야 한다.



# DI

제공하는쪽에서는 항상 완성된 상태로 jar 파일을 납품해야함. 

그렇지만 납품받는쪽이 무슨 데이터베이스를 쓰는지도 알수없고 뭐가 들어갈지도 모름. 데이터베이스 테이블 내용이라던가 데이터베이스 아이디 비밀번호 등등

그런경우 만약 커넥션을 얻어오는 메소드를 Extract Method로 분리해서 그것을 추상화 시킨다음 그 메소드를 구현한 상속 클래스들을 요구클래스에서 만들어서 정해주고 쪽으로 넘기면 제공 클래스 쪽에서는 정하지않고 요구클래스가 시키는대로 처리가 가능하게됨. 이게 DI

혹은 상속한 메소드들을 만들어주는 팩토리 클래스를 만들어서 요구클래스 쪽으로 넘겨주면 됨



#### 템플릿 메소드 패턴

상속기반의 추상화 패턴

클래스중 dependency가 있는것을 추상화 시키고 그 클래스를 상속시켜 만들어서 (템플릿으로 찍어내서) 여러클래스들을 만들어 대신 넘겨주는 패턴

클래스가 Extract 되고 그 클래스에 대한 행위를 자식이 해주는 패턴



#### 팩토리 메소드 패턴

상속기반의 추상화 패턴

템플릿 메소드 패턴과 유사한대 템플릿 메소드 패턴은 특정 "행위" 를 자식 오브젝트가 결정한다면

팩토리 메소드 패턴은 어떤 행위를 하는 디펜던시에 대한 "객체" 를 자식 오브젝트가 결정함

즉 템플릿은 메소드를 추상화했다면 팩토리는 더나아가 그것을 클래스 확장 시킴

인텔리제이의 extract delegate를 사용해서 간단하게 밖으로 뺄수 잇으며 또한 마찬가지로 추상화 하는데

클래스 내에 있는 메소드가 모두 추상메소드면 간단히 클래스를 인터페이스로 만들어서 사용할 수 있음.

또한 받는클래스내의 dependency가 발생하는 부분을 생성자를 통해 요구클래스로 받게해서 받는클래스 내의 dependency를 제거하여 

요구클래스로 DI가 가능하다



#### Strategy 패턴

필요에 따라 전략을 바꾸는 패턴...?

요구클래스에서도 dependency를 갖지않게 따로 Factory 클래스들을 만들어 객체를 전달해주는 패턴임

즉 Factory에서 의존성을 모두 갖고 각 객체들을 주입함. 그리고 이 Factory의 역할을 하는것이 spring 임



## Spring

스프링의 di =dependency를 모두 담아 new를 해줘서 인스턴스를 반환해주는 역할 

@Configuration-이 클래스가 bean을 정의한다고 하는 역할이라는걸 알리는 annotation

@Bean-spring이 new해주는 인스턴스-**또한 Spring이 관리하는 bean들은 Singleton으로 관리가 됨**



클래스를를 @Configuration 어노테이션과 @Bean 어노테이션으로 지정해주면 spring으로 설정된 클래스가 되고 

또한 이렇게 만들어진 클래스를 꺼내쓰기 위해서는

메소드 내에있는 객체 생성자를 지우고 필드에 static으로 선언한뒤 @Before 어노테이션과 setup 메소드를 통해서 설정한다

```java
private static UserDao userDao;

@BeforeAll
public static void setup(){
    ApplicationContext applicationContext=new AnnotationConfigApplicationContext(DaoFactory.class);
    userDao=applicationContext.getBean("userDao", UserDao.class);
}
```

이를 dependency look up이라고 하는데 어플리케이션 컨텍스트로 어떤 방법으로 컨텍스트를 관리할 것인지 정하고 인자로 그 클래스를 준뒤

그 클래스의 인스턴스를 어플리케이션 컨텍스트의 getBean 메소드로 가져와 bean을 배정하는것을 dependency look up이라고함



##### DataSource

spring jdbc 라이브러리에 들어있는 인터페이스

이것을 심플하게 구현한것이 바로 SimpleDriverDataSource 인데

setUrl, setUsername, setPassword, setDriverClass 네가지의 메소드만 설정해주면 간단하게 드라이버를 사용 가능하게 만든다

```java
SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
try {
    dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
dataSource.setUrl(url);
dataSource.setUsername(username);
dataSource.setPassword(password);
```

이렇게되면 요구클래스 측에서 bean이 담긴 받는클래스를 spring에게 요청하게되고 

spring은 dependency들이 담긴 정보를 포함하여 받는클래스를 요청

받는 클래스는 datasource의 정보를 참조하여 커넥션을 호출하여 데이터베이스 작업을 할 수 있다.



또한 dataSource의 저 set인자들은 필드를 통해서 빼서 설정하게되는데 

@Value 어노테이션으로 환경설정에 있는 값들을 삽입이 가능하게 만들수 있다-edit configuration으로 가능-run 한번하면 가능

---



### 리팩토링기법

tip:intelliJ 에서 Extract method를 쓰기위해서는 사용되는 변수가 선언된 부분이 try 밖에있으면 안으로 끄집어 온다음 사용해야 한다.

리팩토링도 ExtractMethod로 추출해서 가능하지만 서로 비슷하지만 인자가 약간씩 다를경우 그 방법을 쓰기힘들다.

그럴때는 strategy pattern을 이용해서 인터페이스로 추상화 한뒤에 사용을 해서 리팩토링을 하게 되면 보기 깔끔한 코드가 될수 있다.

또한 인터페이스로 추상화 할때 오브젝트가 서로 다르다면 인자값을 Object로 바꿔서 cast 해서 쓰게되면 모두를 아우르는 추상화된 인터페이스를 쓸수있다.

또 만약 바뀌지않을 메소드를 가지고 있다면 이것을 외부 클래스로 뺀다음 최종의존성을 spring에게 주입하여 리팩토링을 완성할 수 도 있음

ex)JDBC 클래스들 리팩토링

```java
public void update(User user) throws SQLException {
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        pstmt =conn.prepareStatement("update userinfo set name=?, password=? where id=?");
        pstmt.setString(1,user.getName());
        pstmt.setString(2,user.getPassword());
        pstmt.setInt(3,user.getId());
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

public void delete(Integer id) throws SQLException {
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        pstmt =conn.prepareStatement("delete from userinfo where id=?");
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
```

---

유사한부분이 있어 Extractmethod로 빼려고했으나 각기 다른것들이 있어서 빼기가 힘듬.

```java
public void update(User user) throws SQLException {
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        pstmt =conn.prepareStatement("update userinfo set name=?, password=? where id=?");
        pstmt.setString(1,user.getName());
        pstmt.setString(2,user.getPassword());
        pstmt.setInt(3,user.getId());
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

public void delete(Integer id) throws SQLException {
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        pstmt = makeStatement(id, conn);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

private PreparedStatement makeStatement(Integer id, Connection conn) throws SQLException {
    PreparedStatement pstmt;
    pstmt =conn.prepareStatement("delete from userinfo where id=?");
    pstmt.setInt(1,id);
    return pstmt;
}
```

---



makeStatement를 인터페이스로 추상화하고 맞는 클래스 구현화 한뒤에 업데이트도 바꿈.

```java
public void update(User user) throws SQLException {
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        StatementStrategy statementStrategy=new UpdateStatementStrategy();
        pstmt=statementStrategy.makeStatement(user, conn);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

public void delete(Integer id) throws SQLException {
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        StatementStrategy statementStrategy=new DeleteStatementStrategy();
        pstmt = statementStrategy.makeStatement(id, conn);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
```

```java
public interface StatementStrategy {
    PreparedStatement makeStatement(Object obj, Connection conn) throws SQLException;
}
```

```java
public class UpdateStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object obj, Connection conn) throws SQLException {
        User user=(User)obj;
        PreparedStatement pstmt =conn.prepareStatement("update userinfo set name=?, password=? where id=?");
        pstmt.setString(1,user.getName());
        pstmt.setString(2,user.getPassword());
        pstmt.setInt(3,user.getId());
        return pstmt;
    }
}
```

```java
public class DeleteStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object obj, Connection conn) throws SQLException {
        Integer id=(Integer) obj;
        PreparedStatement pstmt=conn.prepareStatement("delete from userinfo where id=?");
        pstmt.setInt(1, id);
        return pstmt;
    }
}
```

---

여전히 맞지않음 이제 여기서 선언부를 위로 올려주고 인자만 다르니 인자를 통합화 하기위해서 인자를 생성자로 넘기기



```java
public void update(User user) throws SQLException {
    StatementStrategy statementStrategy=new UpdateStatementStrategy(user);
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        pstmt=statementStrategy.makeStatement(conn);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

public void delete(Integer id) throws SQLException {
    StatementStrategy statementStrategy=new DeleteStatementStrategy(id);
    Connection conn=null;
    PreparedStatement pstmt=null;
    try {
        conn = dataSource.getConnection();
        pstmt = statementStrategy.makeStatement(conn);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
```

```java
public interface StatementStrategy {
    //각각 맞는 생성자가 필요
    PreparedStatement makeStatement(Connection conn) throws SQLException;
}
```

```java
public class UpdateStatementStrategy implements StatementStrategy {
    private User user;
    public UpdateStatementStrategy(User user){
        this.user=user;
    }
    @Override
    public PreparedStatement makeStatement(Connection conn) throws SQLException {
        PreparedStatement pstmt =conn.prepareStatement("update userinfo set name=?, password=? where id=?");
        pstmt.setString(1,user.getName());
        pstmt.setString(2,user.getPassword());
        pstmt.setInt(3,user.getId());
        return pstmt;
    }
}
```

```java
public class DeleteStatementStrategy implements StatementStrategy {
    private Integer id;
    public DeleteStatementStrategy(Integer id){
        this.id=id;
    }

    @Override
    public PreparedStatement makeStatement(Connection conn) throws SQLException {
        PreparedStatement pstmt=conn.prepareStatement("delete from userinfo where id=?");
        pstmt.setInt(1, id);
        return pstmt;
    }
}
```

---

통합화

```java
public void update(User user) throws SQLException {
    StatementStrategy statementStrategy=new UpdateStatementStrategy(user);
    jdbcContextForUpdate(statementStrategy);
}

public void delete(Integer id) throws SQLException {
    StatementStrategy statementStrategy=new DeleteStatementStrategy(id);
    jdbcContextForUpdate(statementStrategy);
}

private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
        conn = dataSource.getConnection();
        pstmt = statementStrategy.makeStatement(conn);
        pstmt.executeUpdate();
    } finally {
        try {
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
```

---

마지막으로 jdbcContext는 변하지 않는 녀석이니 클래스로 밖으로 빼주면 됨. 밖으로 빼주는 과정에서 여러 의존성이 차이가나면 DI를 반복해서 Spring에 던져주면 됨

-변하지않는 부분을 JDBC Context라는 클래스를 만들어 빼고 dataSource는 userDao에서 관리를 안할거니 jdbcContext만 받아와서 씀

```java
public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext=jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        StatementStrategy statementStrategy=new GetStatementStrategy(id);
        User user = jdbcContext.jdbcContextForGet(statementStrategy);
        return user;
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy=new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy=new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy=new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

}
```

-jdbcContext에서는 DataSource 에 대한 의존성을 Spring에게 투척

```java
public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    User jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {...}
    void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {...}
    
    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            pstmt = statementStrategy.makeStatement(conn);
            pstmt.executeUpdate();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
```

-spring에서는 userDao에 대한 생성자를  이미 생성된 DataSource() 를 이용해서 다시 만들어줌



-before

```java
@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

 	@Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
```



-after

```java
@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcContext());
    }

    @Bean
    public JdbcContext jdbcContext() {
        return new JdbcContext(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
```

이렇게해서 의존성 주입까지 완료해서 리팩토링을 끝냄



#### **Template callback 패턴**

Strategy나 다른 팩토리 메소드 패턴중에 인터페이스를 따로 구현하지않고 그 라인상에 그대로 구현하는것이 이 패턴임

before- 각기 다른 StatementStrategy 인터페이스를 차용한 클래스들을 쓰고있음

```java
public User get(Integer id) throws SQLException {
    StatementStrategy statementStrategy=new GetStatementStrategy(id);
    User user = jdbcContext.jdbcContextForGet(statementStrategy);
    return user;
}

```

```java
public class GetStatementStrategy implements StatementStrategy {
    private Integer id;
    public GetStatementStrategy(Integer id){
        this.id=id;
    }
    @Override
    public PreparedStatement makeStatement(Connection conn) throws SQLException {
        PreparedStatement pstmt =conn.prepareStatement("select id, name, password from userinfo where id=?");
        pstmt.setInt(1,id);
        return pstmt;
    }
}
```



after-다만 인터페이스를 그대로 구현하지않고 보기좋게 하기위해 람다를 활용할경우 메소드가 하나인 인터페이스만 가능하다

```java
public User get(Integer id) throws SQLException {
    StatementStrategy statementStrategy= conn -> {
        PreparedStatement pstmt =conn.prepareStatement("select id, name, password from userinfo where id=?");
        pstmt.setInt(1,id);
        return pstmt;
    };
    User user = jdbcContext.jdbcContextForGet(statementStrategy);
    return user;
}

```

-----

PreParedStatement set형식 바꾸기-template callback pattern 사용이후

모든 객체는 결국 object의 하위 개체임으로 setObject로 바꾸고 for문을 돌리면서 sql문도 지역변수로 빼면 형식이 같아지므로 리팩토링 가능

```java
public User get(Integer id) throws SQLException {
    Object[] params= new Object[]{id};
    String sql="select id, name, password from userinfo where id=?";
    return get(sql, params);
}
```

```java
private User get(String sql, Object[] params) throws SQLException {
    StatementStrategy statementStrategy= conn -> {
        PreparedStatement pstmt =conn.prepareStatement(sql);
        for(int i=0; i<params.length;i++){
            pstmt.setObject(i+1, params[i]);
        }
        return pstmt;
    };
    User user = jdbcContext.jdbcContextForGet(statementStrategy);
    return user;
}
```

이렇게하고 이 변하지않는 메소드를 이전에 만든 jdbcContext로 옮기면 코드가 깔끔해짐-refactor-move instance로 옮기면 편함

```java
public User get(Integer id) throws SQLException {
    Object[] params= new Object[]{id};
    String sql="select id, name, password from userinfo where id=?";
    return jdbcContext.get(sql, params);
}
```





#### JDBC Template

위에서 진행한 리팩토링 과정을 통째로 진행해서 라이브러리화 시켜놓은 jdbc 라이브러리

jdbc template 역시 delete는 update로 취급하는데 insert 역시 update 취급함 

get은 query()메소드를 insert, delete,update는 update() 메소드를 활용함

get 과 insert 쓸때 우리는 보통 DAO를 만들때 DO를 이용해서 커스텀해서 쓰는데 이것은 jdbc Template과는 다름

이것을 맞춰주기위해 RowCallbackHandler 라는 인터페이스를 제공함- callback pattern을 쓰거나 구현하면됨

- get은 람다를 활용한것은 rs를 쓰면됨

- insert는 con 을 쓰는데 insert 할때 보통 autogenerate를 쓰기때문에 그에 맞는 객체가 필요함- keyholder

```java
public User get(Integer id) throws SQLException {
    Object[] params= new Object[]{id};
    String sql="select id, name, password from userinfo where id=?";
    return jdbcTemplate.query(sql, params, rs -> {
        User user=null;
        if(rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    });
}

public void insert(User user) throws SQLException {
    Object[] params= new Object[]{user.getName(),user.getPassword()};
    String sql="insert into userinfo (name, password) values(? , ?)";
    KeyHolder keyHolder=new GeneratedKeyHolder();
    jdbcTemplate.update(con -> {
        PreparedStatement pstmt=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for(int i=0; i<params.length;i++){
            pstmt.setObject(i+1, params[i]);
        }
        return pstmt;
    },keyHolder);
    user.setId(keyHolder.getKey().intValue());
}
```

GeneratedkeyHolder() 로 keyHolder를 생성해주고 update() 메소드의 두번째 인자로 꽂아넣은다음 update를 끝내고 id값이 필요할때는 keyholder를 이용하여 id값을 getKey().intValue() 메소드로 추출해 오면됨





### Application Context

Test를 하기전 setup단계에서 정해줄때 applicaition context를 정하게 되는데 대부분 현재와서는 대부분 AnnotationConfigApplicationContext를 쓰지만 이것외에도 많은 것들이 있다.



**StaticApplicationContext**-코드를 통해서 수작업으로 bean을 배치-실제로 거의 사용안함-어플리케이션 특정bean들만 테스트하기위해서 사용하는 경우는 있음

```java
//StaticApplicationContext
StaticApplicationContext applicationContext=new StaticApplicationContext();

BeanDefinition dataSourceBeanDefinition=new RootBeanDefinition(JdbcTemplate.class);
dataSourceBeanDefinition.getPropertyValues().addPropertyValue("driverClass", Class.forName(System.getenv("DB_CLASSNAME")));
dataSourceBeanDefinition.getPropertyValues().addPropertyValue("url", Class.forName(System.getenv("DB_URL")));
dataSourceBeanDefinition.getPropertyValues().addPropertyValue("username", Class.forName(System.getenv("DB_USERNAME")));
dataSourceBeanDefinition.getPropertyValues().addPropertyValue("password", Class.forName(System.getenv("DB_PASSWORD")));
applicationContext.registerBeanDefinition("dataSource",dataSourceBeanDefinition);

BeanDefinition jdbcContextBeanDefinition=new RootBeanDefinition(JdbcTemplate.class);
jdbcContextBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("dataSource"));
applicationContext.registerBeanDefinition("jdbcContext",jdbcContextBeanDefinition);

BeanDefinition beanDefinition=new RootBeanDefinition(UserDao.class);
beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("jdbcContext"));
applicationContext.registerBeanDefinition("userDao",beanDefinition);

userDao=applicationContext.getBean("userDao", UserDao.class);
```



GenericXmlApplicationContext

**ClassPathXmlApplicationContext**-xml로 bean을 관리-옛날버전에서 사용-다만 property_placeholder 라던가 여러가지 bean이 없으면 bean들을 관리하기 까다로움, 유틸적인 부분을 꼽아야되는것도 있고 그래서 xml이 복잡해짐

```java
ApplicationContext applicationContext=new ClassPathXmlApplicationContext("daoFactory.xml");
userDao=applicationContext.getBean("userDao", UserDao.class);
```

xml은 메인의 resources 폴더에서 관리

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

<!--    환경변수로 dataSource의 프로퍼티value를 넣기위해서 이것을 선언 -->
    <context:property-placeholder/>
    <bean name="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
        <property name="driverClass" value="${db.classname}"/>
        <property name="url" value="${db.url}"/>
        <property name="username" value="${db.username}"/>
        <property name="password" value="${db.password}"/>
    </bean>
    <bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <constructor-arg ref="dataSource"/>
    </bean>
    <bean name="userDao" class="kr.ac.jejunu.user.UserDao">
        <constructor-arg ref="jdbcTemplate"/>
    </bean>
</beans>
```



**GenericGroovyApplicationContext**-gradle에서 dependency 하나를 꼽아야 사용가능-위에 build.gradle부분에 서술-groovy 파일역시 resources 폴더에 위치

```java
ApplicationContext applicationContext= new GenericGroovyApplicationContext("daoFactory.groovy");
userDao=applicationContext.getBean("userDao", UserDao.class);
```

```groovy
import kr.ac.jejunu.user.UserDao
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource


beans {
    dataSource(SimpleDriverDataSource){
        driverClass = System.getenv("DB_CLASSNAME")
        url=System.getenv("DB_URL")
        username=System.getenv("DB_USERNAME")
        password=System.getenv("DB_PASSWORD")
    }

    jdbcTemplate(JdbcTemplate, dataSource){

    }

    userDao(UserDao, jdbcTemplate){

    }
}
```

**AnnotationConfigApplicationContext**-이전까지 썻던거. 

```java
ApplicationContext applicationContext=new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext applicationContext2=new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        userDao=applicationContext.getBean("userDao", UserDao.class);
```

클래스 대신 패키지를 넣게되면 베이스 패키지 하위의 모든패키지를 뒤져서 @Configration과 @Bean을 모두 뒤져서 로드하게됨

또한 클래스 네임위에 @Component를 넣게되면 이 클래스는 DaoFactory 즉 spring이 관리하는 bean이 됨

또한 필드에 @Autowired 를 쓰게되면 자동적으로 DI까지 해줌

```java
@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

//    @Bean
//    public UserDao userDao() {
//        return new UserDao(jdbcContext());
//    }

    @Bean
    public JdbcTemplate jdbcContext() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
```

```java
@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate =jdbcTemplate;
    }
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
```

**하지만 jdbc template을 생성자로 받아와야 동작하는데 어떻게 이렇게 될수 가 있냐?-spring의 약속**

특정 생성자를 통해서 받아오는 녀석이 있으면 bean을 통해서 자동으로 DI를 해줌-생성자가 없을때는?

필드에 @Autowired를 넣게되면 자동적으로 주입을 해줌 

```java
@Component
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
```

**물론 이것을 하려면 ApplicationContext 등록을할때 패키지 단위로 해줘야 함. class로 등록을 하면 Component인식을 안하니 쓸대없다**



**결론**

bean을 등록하는 방법은 2가지

1.daoFactory에 실제 bean을 등록을 해서 쓰거나 아니면 다양한(xml,groovy) application context로 bean을 등록을 해서 쓴다.

2.bean을 등록할 클래스로 가서 @component를 써줘서 자동적으로 bean을 등록 하고 DI가 필요한 객체가 있으면 @Autowired 까지 써준다-이럴경우 AnnotationConfigApplcationContext 는 필수고 패키지 단위로 묶어줘야함



그외

각각의 WebApplicationContext-StaticWeblApplicationContext, XmlWeblApplicationContext, AnnotationConfigWebApplicationContext



### BeanScope

singleton-default

prototype-호출할때마다 new

request

session

application

websocket

-----------------------------------------------------------

헐크 기준으로 기타 singleton외에 쓰지 않는다고함



### Lombok

@Setter-자동 세터

@Getter-자동 게터

@Data-자동 둘다+데이터 기반 equals 설정



@Builder-생성자 만들때 편하게 정의가능

@NoArgsConstructor-기본생성자 만들어줌

@AllargsConstructor-모든 필드를 가진 생성자를 만들어줌

@RequiredArgsConstructor-final로 정의된 필드에 대해서만 생성자를 만들어줌

# Java Web Application

war

구조

기본적인 구조

webContent

​		-META-INF

​		-WEB-INF

​				-lib

​				-views

​				-xml파일들...

​		-jsp+css파일들...



maven or gradle 구조

상위폴더들

​	main

​		-java

​		-resources

​		-webapp-루트가 될거임

​			-WEB-INF

​				-web.xml

​		

​	test

​		-java

​		-resources

---

**Tomcat 설치**

edit configuration- +tomcatServer- tomcat 설정-deployment-artifacts 추가- exploded로 - applicationContext root 설정(/)

artifacts는 war플러그인을 꼽지않으면 안보임

exploded로 설정한 이유:개발하면서 서버를 리스타트를 하지않고 바로 반영되는 핫 스와핑을 하기 위해(디버그모드 만 가능)-풀어 헤쳐야함-안풀어 헤치고 사용하기 위해서는

build-buildTool.gradle로 가서 gradle을 인텔리제이로 바꿔주면됨-지금까지는 gradle 기반으로 했는데 gradle은 풀어 헤치는 개념이 없다고해서 intellij로 바꿔주는것

그리고 tomcat도 환경변수 셋팅해주기

### Web.xml

웹 어플리케이션에 들어가는 설정

기본

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" version="4.0">

    
</web-app>
```

서블릿정의

```xml
<servlet>
    <servlet-name>userServlet</servlet-name>
    <servlet-class>kr.ac.jejunu.user.UserSevlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>userServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

위는 서블릿클래스 지정, 밑은 어떤url 이 나왓을때 servlet이 실행될건지 정의

필터정의

```xml
<filter>
    <filter-name>userFilter</filter-name>
    <filter-class>kr.ac.jejunu.user.UserFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>userFilter</filter-name>
    <url-pattern>*</url-pattern>
</filter-mapping>
```

리스너 정의

```xml
<listener>
    <listener-class>kr.ac.jejunu.user.UserContextListener</listener-class>
</listener>
```





### Servlet interface의 기본적인 구조

public void init(ServletConfig config) throws ServletException;

public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;

public void destroy();



init-한번만 딱 요청

service-요청될때마다

destroy-끝날때 딱한번



**GenericServlet**

앵간한거 다 구현된 servlet-service만 하면됨- init, destroy 설정하고싶으면 override





### Filter

```
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
public void init(FilterConfig filterConfig) throws ServletException;
public void destroy();
```

```
@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    //서블릿이 실행되기전
    chain.doFilter(request,response);
    //서블릿이 실행된후
}

@Override
public void init(FilterConfig filterConfig) throws ServletException {

}

@Override
public void destroy() {

}
```

서블릿이 실행되기전과 실행된후(chain.doFilter() 메소드 사용전후)에 로직을 적용하는 메소드



### Listener

ServletContextListener:컨텍스트 초기화, 마무리-서버의 시작과 끝을 듣고있는 리스너

ServletContextAttributeListner:컨텍스트에 속성을 추가,제거,수정했는지 알고 싶을때

HttpSessionListner:얼마나 많은 동시 사용자가 물려있는지

ServletRequestListner:요청이 들어올때마다 알고 싶을때 혹은 로그를 남기고 싶을때-이거-필터-서블릿-필터-이거 순

ServletRequestAttributeListner:reuqest 속성이 추가 제거 수정되었는지 알고 싶을때

HttpSessionBindingListner:속성개체가 하나있는데 이객체가 세션에 바인딩 되었는지 아니면 제거 되었는지 알고 싶을때

HttpSessionAttributeListner:세션 속성이 추가,제거 수정되었는지 알고 싶을때

HttpSessionActivationListner:세션 활성화 비활성화



### Intercepter

preHandle:RequestMapping이 되었을때 handler 처리가능

postHandle:로직이 끝나고 modelAndView가 전달될때 처리가능

afterCompletion:모든게 종료가 되었을때 예외가 떨어진다 하더라도 처리가능





# Spring MVC

M:Model-Java Bean

V:View- Jsp, etc...

C:Controller -Servlet



**SpringMVC 구조**

![image-20210112135549995](C:\Users\celbe\AppData\Roaming\Typora\typora-user-images\image-20210112135549995.png)



**Spring MVC Life Cycle**

![image-20210112135635808](C:\Users\celbe\AppData\Roaming\Typora\typora-user-images\image-20210112135635808.png)

1.DispatcherServlet이 모든 요청을받음

2.요청된 URL 에대해서 Handler Mapping이 규약에 맞는 HandlerAdapter(Controller)에게 맵핑

3.맵핑된이후 비즈니스로직을 수행한다음 잘 감싼것을 ViewResolver로 보내서 

4.ViewResolver가 jsp라던가 그런 기타 탬플릿을 통해 화면에 뿌려줌

---

#### DispatcherServlet 정의

##### XML 기반의 web application Context

dispatcherServlet.xml과

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
             xmlns:beans="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xsi:schemaLocation="
             http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
             http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
             http://www.springframework.org/schema/context http://www.springframe.org/schema/context/spring-context.xsd">
</beans:beans>
```

root-context.xml 을 만들어주고



**web.xml**에 추가해줘야함

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/root-context.xml</param-value>
</context-param>
```

context-param-web application context가 로딩이 되면서 특정한 파라미터를 받는다는뜻

그리고 listener 추가

```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

ContextLoaderListener는 ServletContextListener를 구현한 것중 하나

ServletContext가 로딩하면서 root-context.xml에 있는 bean의 정의를 보고 applicationContext로 로딩을함



또한 Servlet을 지정-userServlet을 dispatcherServlet으로 바꿔주자. package는 org.springframework.web.servlet.DispatcherServlet

url-mapping또한 모든 요청을 DisaptcherServlet이 받을거니까 /user를 /로 바꾸어 주자-이렇게 하면 모든 url을 이 서블릿이 받음.

*도 할수있는데 tomcat에서 이미지파일등 다른것을 로딩할때도 문제가 생길수 있으므로 /



그리고 내부에 아까 정의한 contextConfigLocation을 받을수 있도록 init param을 정의

```xml
 <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>WEB-INF/dispatcher-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```

servlet은 요청할때 init이 되서 bean들을 정의해야하기때문에 load-on-startup을 박고 1을 넣어줌으로써 처음에 로딩할때 1번으로 로딩이 된다고 정의



이렇게 하면 하이어라키로 구성된 다수의 application Context를 가질수 있다-

-다수의 dispatcher-Servlet이 가질수 있는 공통된 설정-root-context.xml

-각각의 dispatcher-Servlet이 가지는 설정-disaptcherServlet.xml을 정의하여 각각 배치



---

**Controller 작성**

org.springframework.web.servlet.mvc.Controller 라이브러리의 Controller 인터페이스를 구현하면 spring의 controller 역할을 수행할 수 있음

controller-실제 비즈니스 로직을 담당해줌



**기본**

```java
@RequiredArgsConstructor// final로 생성된 필드에 대해서 생성자를 생성해줌
public class SimpleController implements Controller {
    private final UserDao userDao;//생성자를 만들어줫으니 spring이 자동으로 di해줄거임

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user =userDao.get(Integer.valueOf(request.getParameter("id")));
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
```

ModelAndView-spring에서 제공하는 model을 담는 그릇, view 또한 정의 할수 있음



**HttpRequestHandler adapte**r- handleRequest 리턴값이 void - original servlet 처리만을 활용하는 adpater -거의 안씀

**simpleServletHandler adapter** -서블릿 자체를  bean로만 정의하고 그냥 그대로 갖다 쓰겟다-기존의 servlet으로 개발된것을 mvc 로 전환하기 위한 용도 

-기존 servlet에 그냥 @Controller 만 붙이면된다.



```xml
<!--    simple Servlet Handler adapter-->
<beans:bean class="org.springframework.web.servlet.handler.SimpleServletHandlerAdapter"/>
```

```java
@Controller("/userServlet")
public class UserSevlet extends GenericServlet {
    @Autowired
    private UserDao userDao;
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        this.userDao=applicationContext.getBean("userDao",UserDao.class);
        super.init(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        User user=userDao.get(1);
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<html>");
        stringBuffer.append("<h1>");
        stringBuffer.append(String.format("Hello %s!!!!!", user.getName()));
        stringBuffer.append("</h1>");
        stringBuffer.append("</html>");
        res.getWriter().println(stringBuffer);
        res.setContentType("text/html;charset=UTF-8");
    }
}

```

단! simpleServletHandler adapter는 기존의 servlet에서 service 메소드는 찾아주지만 init과 destroy 메소드는 찾아주지 않는다. 그러므로 위코드는 

userDao가 null값이 되어서 작동을 안할것임

-그럼 UserDao에 @Autowired를 맺어주면 다시 작동을 할거임-spring이 알아서 생명력을 넣어줄거니까

-그리고 위코드가 servlet에서 모든게 끝나기때문에 view Resolver 가 작동을 안할거임-필요가없으니까



**RequestMappingHandler Adapter**- RequestMethodHandlerMapping과 같이 쓰임

컨트롤러를 만들고 @Controller와 주고난뒤에 

@RequestMapping()을 클래스와 메소드 모두에게 주는 방법

인자로 패스 값을 주면 그 패스값대로 간 최종목적지에 있는 메소드가 실행이 되는 형식

```java
@Controller
@RequestMapping
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {
    private final UserDao userDao;

//    @RequestMapping
    @RequestMapping("/user")
    public User getUser(@RequestParam("id") Integer id){
        return userDao.get(id);
    }
}
```

@RequestParam("id")-request인자중 이름이 id 인 parameter를 찾아 맵핑해줌



---



**Annotation 종류**

**RequestMapping**

@RequestMapping(path= "/user", method=RequestMethod.Get)-요청 형식에 따라 바꿀 mapping을 줄수 있음 

@RequestMapping(path= "/user", params="id=2")- param의 값에 따라 mapping을 줄수있음

@RequestMapping(path= "/user", headers="content-type=text/*")- 컨텐트 타입에 따라 mapping 가능 -content-type=\*

@RequestMapping(path= "/user", consumes="application/json") 

@RequestMapping(path= "/user", produces="application/json")-response를 제공할때 application/json이라고 했다고 가정하면 이 method를 줄거임

@GetMapping("/user")=@RequestMapping(path= "/user", method=RequestMethod.Get)-요청 형식에 따라 바꿀 mapping을 줄수 있음 

@PostMapping

@PutMapping

@DeleteMapping




**Method Param**-컨트롤러의 메소드에 들어올수 있는 파라미터

HttpServletRequest

HttpServletResponse

HttpSession

```java
@RequestMapping("/test")
public void testing(HttpServletRequest request, HttpServletResponse response, HttpSession session){
}
```

Map, Model ,ModelMap - ModelAndView 대신 쓸수 있는 것

```java
@RequestMapping("/test")
public void testing5(@RequestParam("id")int id, Map map){
    map.put("user",userDao.get(id));
}
@RequestMapping("/test")
public void testing6(@RequestParam("id")int id, ModelMap modelMap){
    modelMap.addAttribute("user",userDao.get(id));// addAttribute는 Model의 메소드, ModelMap은 둘다지원
}
```



@PathVariable- @RequestMapping(path ="/user/{id)") 으로 들어왔을때 @PathVariable("id) int id

-단 path로 integer가 들어왔기 때문에 void로는 안되고 return값을 ModelAndView로 바꾼뒤에 생성자에 view name 주기

```java
@RequestMapping(path = "/user/{id}")
public ModelAndView testing2(@PathVariable("id") int id){
    return new ModelAndView("user");
}
```



@RequestParam -@RequestParam("id") int id 혹은 @RequestParam(value="id", required=false, defaultValue="I" int id)

```java
@RequestMapping("/test")
public void testing3(@RequestParam("id")int id, @RequestParam(value = "pw", required = false, defaultValue = "1234") String pw){
    
}
```

@CookieValue- @CookieValue("id")String id- 쿠키값을 받아올수 있음

```java
@RequestMapping("/test")
public void testing4(@CookieValue("id")String id){
}
```

@ModelAttribute-request에 해당되는정보나 response에 해당되는 정보 모두 받아올수 있음-가장많이쓰임 심지어 생략도 가능함

```java
@RequestMapping("/test")
public void testing7(@ModelAttribute User user){
    int id=user.getId();
    User user2= userDao.get(id);
    user.setName(user2.getName());
}

@RequestMapping("/test")
public void testing7(User user){
    int id=user.getId();
    User user2= userDao.get(id);
    user.setName(user2.getName());
}
//단 이렇게는 안된다- 인스턴스가 달라지니까
@RequestMapping("/test")
public void testing7(User user){
    int id=user.getId();
   	user= userDao.get(id);
}
```











**리턴값 종류**

void

String-이것은 ViewName 혹은 Redirect 혹은 Forward

ModelAndView

Map,Model,ModelMap

View- return new RedirectView("/path")              

@ModelAttribute-마찬가지로 생략가능



---

**HandlerMapping 작성**

**bean name url handler mapping(default)**

그리고 Controller를 작성햇으면 spring이 인식할 수 있게 dispatcherServlet에 추가 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
             xmlns:beans="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xsi:schemaLocation="
             http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
             http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
             http://www.springframework.org/schema/context http://www.springframe.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="kr.ac.jejunu.user"/>
    <beans:bean name="/user" class="kr.ac.jejunu.user.SimpleController">
        <beans:constructor-arg name="userDao" ref="userDao"/>
    </beans:bean>
</beans:beans>
```

<context:component-scan base-package="kr.ac.jejunu.user"/>:annotation 기반으로 bean을 정의한걸 basepackage에서 읽어오겟다

<beans:bean name="/user" class="kr.ac.jejunu.user.SimpleController"> bean의 이름이 패스가 됨-즉 이 패스로 bean을 요청이 가능함

<beans:constructor-arg name="userDao" ref="userDao"/>-spring에서 관리할 생성자를 추가-userDao



**simple url handler mapping**

```xml
<beans:bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
    <beans:property name="mappings">
        <beans:props>
            <beans:prop key="/getuser">/user</beans:prop>
        </beans:props>
    </beans:property>
</beans:bean>
```

이런식으로 다른 루트를 타도 키를 /user를 맵핑해주면 /user(bean name url handler 맵핑당시 정의해놓은 bean에게 가도록 할 수 있음

그리고 요청 url이 달라졋기때문에 jsp파일 네임을 수정 or jsp 파일을 복사하거나 Controller를 수정해야함-model and view 생성할 당시 생성자 인자를 bean 이름을 꽂아주면됨



```java
@RequiredArgsConstructor// final로 생성된 필드에 대해서 생성자를 생성해줌
public class SimpleController implements Controller {
    private final UserDao userDao;//생성자를 만들어줫으니 spring이 자동으로 di해줄거임

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user =userDao.get(Integer.valueOf(request.getParameter("id")));
//        ModelAndView modelAndView= new ModelAndView(); 
        ModelAndView modelAndView= new ModelAndView("user"); //bean 이름 정의
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
```

단! 이렇게 되면 새로운 핸들러 맵핑을 꽂았기 때문에 default인 bean name url mapping은 disable 됨



**SimpleServlet Handler Mapping** -위에서 정의한 SimpleServletHandler adpater와 같이 쓰임-거의 안쓰임 legecy 

```
<!--    simple Servlet Handler adapter-->
<beans:bean class="org.springframework.web.servlet.handler.SimpleServletHandlerAdapter"/>
```



**Request Method Handler Mapping(Default)-3.1 -가장 많이 쓰임 -RequestMappingHandlerAdapter와 같이 쓰임**

어노테이션 기반이라 위에서 다 설명되있음.

@Controller를 붙이고 @RequestMapping

DispatcherServlet에서만 bean을 찾을게 아니라 RequestMapping 이라고 어노테이션 을 붙인 컨트롤러도 찾기때문에 xml에 정의를 하지않아도!  Controller와 DispatcherServlet간에 통신이 가! 능! 하다







---

#### **view resolver 추가**



**InternalResourceViewResolver**-jsp파일에 view를 연결시켜주는 view resolver

마찬가지로 dispatcherServlet.xml에 정의

```xml
<beans:bean name="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <beans:property name="prefix" value="/WEB-INF/views"/>
    <beans:property name="suffix" value=".jsp"/>
</beans:bean>
```

prefix에는 파일위치

suffix에는 확장자를 넣어주자.



**ThymeleafViewResolver**-타임리프 템플릿 엔진과 연결하는 view resolver

**VelocityViewResolver**-벨로시티 템플릿 엔진과 연결하는 view resolver

**FreemarketViewResolver**-프리마켓 템플릿 엔진과 연결하는 view resolver



ResourceBundleViewResolver-view와 수행되는 컨트롤러간의 정보를 property에 담는 view resolver...?-활용잘안함

XmlViewResolver-view와 연결해주는 controller mapping을 xml안에 정의하는 view Resolver-활용잘안함



**ContentNegotiatingViewResolver**-요청이 왔을때 협상을 하는 ViewResolver-특정 요청이 왔을때 어떨땐 json, xml 로 변환해서 보여주는 resolver

```xml
<beans:bean class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
    <beans:property name="contentNegotiationManager">  <!--협상력이 필요한 녀석-->
        <beans:bean class="org.springframework.web.accept.ContentNegotiationManagerFactoryBean">
            <beans:property name="mediaTypes">
                <beans:props>
                    <beans:prop key="js">application/json</beans:prop>
                    <!-- key=확장자 , .json이라고 확장자를 가진 녀석으로 요청을 달라하면(request) json 타입으로 리턴할거임-->
                    <beans:prop key="x">application/xml</beans:prop>
                    <!-- key=확장자 , .x라고 확장자를 가진 녀석으로 요청을 달라하면 xml 타입으로 리턴할거임-->
                </beans:props>
            </beans:property>
        </beans:bean>
    </beans:property>
    <beans:property name="defaultViews">
        <beans:list>
            <beans:bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
            <!--json 형태가 오면 얘랑 연결-->
            <beans:bean class="org.springframework.web.servlet.view.xml.MappingJackson2XmlView"/>
            <!--xml 형태가 오면 얘랑 연결-->
        </beans:list>
    </beans:property>
    <beans:property name="viewResolvers"> <!--위와 다른 일반적인 요청이 오면 이 view resolver 실행-->
        <beans:bean name="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <beans:property name="prefix" value="/WEB-INF/views"/>
            <beans:property name="suffix" value=".jsp"/>
        </beans:bean>
    </beans:property>
</beans:bean>
```

**HandlerExceptionResolver**-어플리케이션이 오류가 났을때 혹은 Exception이 떨어졌을때 어떤 view를 보여줄건지 정하는 resolver

```java
@ExceptionHandler(Exception.class) //exception이 떨어졌을때 자체가 이쪽으로 핸들링 하는것이 HandlerExceptionResolver
public ModelAndView error(Exception e) {
    ModelAndView modelAndView=new ModelAndView("error");
    modelAndView.addObject("e",e);
    return modelAndView;
}
```

xml로 정의하는것이 아닌 어노테이션 기반 코드로 메소드로 작성



**MultipartResolver**

**Resource 추가** 파일을 업로드할때 받을 공간

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
             xmlns:beans="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xsi:schemaLocation="
             http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
             http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
             http://www.springframework.org/schema/context http://www.springframe.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="kr.ac.jejunu.user"/>

    <!--spring mvc를 활용하려면 이것을 정의해야함.-->
    <!-- 버전이 업그레이드 되면서 request mapping이라던가 그런것만 할땐 필요없어졋지만 resources 같은 기능들을 하기위해서는 무조건 설정해줘야함-->
    <annotation-driven/>
```

```xml
<!--images 루트를 타고 들어오면 static 폴더를 바라보게 해라-->
<!--다만 이 resources를 활용을 하려면 위에 annotation driven을 정의해야함-->
<resources mapping="/images/**" location="/WEB-INF/static/"/>
```

원래는 멀티파트 리졸버를 꽂아줘야했지만 spring 3.0이상에서는 기능이 지원만 된다면 자동적으로 해당되는 multipart resolver를 spring이 알아서 꽂아줌

multipart resolver 종류

-CommonsMultipartResolver

-StandardServletMultipartResolver



```java
@RequestMapping(path="/upload",method = RequestMethod.POST)
public ModelAndView upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
    //request.getServletContext에 getRealPath로 실제 서버의 물리적인 위치를 받을수 있음.
    File path=new File(request.getServletContext().getRealPath("/")+"WEB-INF/static/"+file.getOriginalFilename());
    FileOutputStream fileOutputStream=new FileOutputStream(path);
    //파일업로드의 경우 바로 파일열고 스트림으로 바이트 전송하면 성능이 안나옴
    BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
    bufferedOutputStream.write(file.getBytes());
    bufferedOutputStream.close();
    //----------------------파일 저장끝

    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("url","/images"+file.getOriginalFilename());
    return modelAndView;
}
```

다만 tomcat 9.0.30 버전에서는 따로 설정을 해줘야함 -톰캣 깔려있는 곳에가서 tomcat 9.0/conf/context.xml

<Context> 를

<Context allowCasualMultipartParsing="true" path="/"> 교체

---

정리: request를 날림. 

서버에서 이를 받음. 

INF 밑에 web.xml을 뒤짐

받을수 있는 패스인가?-잇는패스면 어디에서 받음?-dispatcherServlet            -web.xml에 기록되어있음

dispatcherServlet이 받을수 있으면 dispatcherServlet 그것과 연결되어있는 xml에 정의된 bean 대로 받을 거임-어디?-dispatcher-Servlet.xml

dispatcherServlet.xml에 bean이 정의되어있음? 있음 보고 bean이 정의된대로 name(path)을 찾아서 컨트롤러를 맵핑

-맵핑이 안되면 annotation으로 정의되어있는 클래스들을 모두뒤져서 bean으로 정의된것들도 싹 찾아봄

컨트롤러가 맵핑이되면 컨트롤러에서 비즈니스 로직을 수행 하고 결과를 리턴

결과를 리턴받았으면 bean 바로 아래에 viewResolver라는 bean이 있네? 보고 이름(루트) 대로 맞는것을 prefix와 suffix보고 붙여서 파일을 찾아 클라이언트에게 전달



XML 기반이고 편하게 하기위해 dispatcherServlet과 viewResolver는 내가 직접구현안하고 있는 클래스 따다 그대로 쓰고 Controller만 정의



**Web.xml을 code로 바꾸기**

servlet3.0 이상부터는 annotation 기반으로 정의할수 있게 만들어짐



web.xml을 없애고 기존 코드를  수정-root-context.xml도 필요가없음

기존simpleUrl servlet으로 쓰이던 servlet에

@WebServlet(urlPatterns = "")

Filter에

@WebFilter(urlPatterns="*")

Listener에

@WebListener



클래스를 만들어서 dispatcherServlet 정의

WebApplicationInitializer 인터페이스를 구현

서블릿 3.0이상에서는 특정 인터페이스를 구현한 녀석을 찾고 그녀석을 통해서 servlet context에 동적으로 등록할 수 있게 만들어줌

ServletContainerInitializer를 구현한 SpringServletContainerInitializer 

ServletContainerInitializer를 구현하면 spring에서 이것을 찾아온다

그리고 클래스를 만들어서(WebInitializer) dispatcherServlet 정의



**dispatcherServlet을 code로 바꾸기**

클래스 작성-WebConfig -interface WebMvcConfigurer를 구현한

@Configuration 을 해줘야 설정으로 인식가능

@EnableWebMvc -annotation driven 역할을 해주는 역할

@ComponentScan("kr.ac.jejunu.user") - Component scan 을 대신해주는 역할



```java
@Configuration
@EnableWebMvc
@ComponentScan("kr.ac.jejunu.user")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("js", MediaType.APPLICATION_JSON)
                .mediaType("x",MediaType.APPLICATION_XML);//.x와 .js로 요구시에도 동작할수 있도록
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserIntercepter()).addPathPatterns("/**/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/WEB-INF/static"); //리소스파일들 위치
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //.jsp-InterResourceViewResolver
        registry.jsp().prefix("/WEB-INF/views").suffix(".jsp");
        registry.enableContentNegotiation(new MappingJackson2JsonView()); //.json으로 요구시 json으로 돌려주는역할
        registry.enableContentNegotiation(new MappingJackson2XmlView()); //.xml로 요구시 xml로 돌려주는 역할
    }
}
```



# Rest방식의 컨트롤러

```java
package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public @ResponseBody User get(@PathVariable("id") Integer id){
        return userDao.get(id);
    }
    //@ResponseBody는 body를 데이터 자체로만 받겟다 라는 의미
    // 하지만 이렇게하면 그냥 쌩으로 날려주기 때문에 message converter가 필요함-webConfig(dispatcherServlet.xml)


    @PostMapping
    public @ResponseBody User create(@RequestBody User user){
        userDao.insert(user);
        return user;
    }
}
```

```java
@Override
public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJackson2HttpMessageConverter());
}
```



## ORM

object-Relational Mapping- 관계형 데이터베이스와 객체를 연결시켜주는 맵핑

객체:객체안에 객체 또다른 객체 포함가능

관계형데이터베이스:데이터베이스안에 데이터베이스 포함 불가능-set 지원불가능(멀티컬럼불가능)-



ORM종류

MyBatis-단순함, xml로 명령을 정의하고 query문으로 삽입, dynamic query 가능, annotation으로도 가능-데이터를 분석하는 솔루션을 만들려고할때 query를 튜닝해야할때 큰데이터를 분석한 지표나 통계를 보여줘야할때 굉장히 편리

Hibernate-진정한 ORM 맵핑-1:M도 가능

JPA-javapersistent API-hibernate은 100% JPA를 지원-



spring Data jpa-jpa-Hibernate-JDBC-DB 순으로 맵핑

jpa-대표적인 인터페이스 -https://javaee.github.io/javaee-spec/javadocs/javax/persistence/EntityManager.html



먼저 JpaRepository를 상속한 interface 구현-(Repository-CrudRepository-pagingAndSortingRepository를 이어서 구현한 JpaRepository)

```java
public interface UserDao extends JpaRepository<User, Integer> { //어떤객체를 식별할지와 그 객체를 식별할 id를 제너릭으로
}
```

이러면 레포지토리 사용할 준비 ok



그리고 DO수정

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="userinfo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //GeneratedValue(자동증가한다는뜻)
    private Integer id;
//    @Column(name = "name")//만약 필드이름이 다르다면 이런식으로 정의해줘야함
    @Column
    private String name;
    @Column
    private String password;
}

```

그리고 spring을 담당할 DAOFACTORY에 @EnableJpaRepositories 삽입

```java
@Configuration
@EnableJpaRepositories(basePackages = "kr.ac.jejunu.user",entityManagerFactoryRef = "entityManagerFactoryBean")
//Enable Jpa Repositories-repository라고 등록된 녀석을 찾아서 bean으로 등록시키는 역할
//entity managerfactoryref-실제 jpa를 구현한 녀석은 무엇이냐 라고 물어보는걸 알려주는역할 밑에 빈으로 정의해줘야함
public class DaoFactory {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean //현재환경의 entityManager를 setting 해주는 역할
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource()); //어떤 데이터베이스를 연결한 데이터소스를 쓸래? 이미 정의되어있는 dataSource를 쓰겟다
        entityManagerFactoryBean.setPackagesToScan("kr.ac.jejunu.user");//@Entity를 가진녀석들을 어디서 찾을래?

        JpaVendorAdapter jpaVendorAdapter =new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);//어떤 밴더(jpa를 구현한녀석)?

        Properties jpaProperties=new Properties();
        jpaProperties.setProperty("hibernate.dialect","org.hibernate.dialect,MySQLDialect");
        //jpa를 find를 한다가정하면 hibernate를 query로 바꿀때 각 vender에 맞게 sql을 바꿔주는 역할

        entityManagerFactoryBean.setJpaProperties(jpaProperties);//jpa에서 활용할 jpaproperty

        return entityManagerFactoryBean;
    }
    
    @Bean
    //jpa를 선언할때는 transaction을 꼭 선언하도록 되어있음
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
```



그리고 기존에 설정했던 많은 userDao의 메소드들을 못쓰게 되었으니까 맞게 수정해줘야함

get=>findByID().get()=>findByID는 자바 8 부터 Nullpointer 를 없애려는 노력으로 탄생한 optional 클래스...?메소드임 그래서 뒤에 get이라던가 다른거 들어감

insert, update=>save()



이곳을 참조했음

https://jobc.tistory.com/120





---

## spring boot

spring initializer-

group-kr.ac.jejunu

artifact-user

project-gradle project

language-java

Name-User



dependency

-spring boot devtools

-loombok

-spring web

-template engines -선택

spring data-jpa, mysql driver

---------------------------------------------------

### Spring boot란?

단일 웹 어플리케이션을 빠르게 배포할 수 있는 툴

microservice에 유리

was 포함

그리고 war 가아닌 jar임





