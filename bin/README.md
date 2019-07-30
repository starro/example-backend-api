# 백엔드 API 개발 예제(example-backend-api)
  * backend 개발을 위한 기본 개발환경을 제공한다.  


# 이력
  * v0.0.1-SNAPSHOT
    * 2019.07.03
        * 테스트 케이스 작성 구조 변경
        * spring rest docs 추가
    * 2019.07.02
      * 패키지 구조 변경
      * 샘플 복잡도를 제거하기 위해 제거
        * querydsl 제거
        * entity graph 제거
      * form 에서 사용하던 mapper를 controller 로 변경하여 커플링 제거
      * 엔티티 기반 코드 제너레이터(coffee maker) 추가
        * 생성 : entity, form, controller, service, repository, predicate, test case, test helper 
        * form : 필드명, 유효성검증(notnull, notemtpy, length, range), 필수여부 작성
          * entity 의 연관관계가 lazy 로 되어있어 form 연관관계 응답정보를 추가하면 api 로 바로 반영 됨
          * osiv(open session in view) 가 적용되어 있으므로 위 상황이 가능
        * swagger : 필드명 동기화
        * predicate : entity의 nullable=false 를 대상으로 조건 검색          
    * 2018.10.17 
      * 최초 등록
      
# 구성
  * 버전
    * example-backend-api-0.0.1-SNAPSHOT
  * 환경
    * windows 10, mac
    * intellij 2018.2.5
    * spring tool suite 4.0.0.RELEASE(build 201809220817)
    * d2coding-1.3.2
    * sourcetree 2.6.10
    * apache-maven-3.5.4
    * h2-1.4.197 (scope:runtime)
    * openjdk 1.8.0.171
    * rabbit mq
    * redis
  * 프레임워크
    * spring boot 2.0.7.RELEASE
      * spring framework 5.0.9.RELEASE
      * spring data jpa
      * spring data redis
      * spring rest docs
      * spring amqp
      * spring websocket
      * spring test(scope:test)
      * spring dev-tools(scope:runtime)
      * embedded tomcat 8.5.34(scope:provided)
      * hibernate 5.2.17.Final
      * hibernate validator 6.0.12.Final
      * hikaricp 2.7.9
      * jackson 2.9.6
    * flyway 5.0.7
    * querydsl 4.1.4
    * lombok 1.18.6
    * commons-lang3 3.7
    * commons-pool2 2.5.0
    * commons-io 2.5
    * lettuce-5.0.5.RELEASE
    * mybatis 1.3.2
    * swagger 2.7.0
    * mapstruct 1.3.0.Final
    * log4jdbc 1.16(profiles:default)
    * jackson-dataformat-yaml 2.9.7
    * postgresql 42.1.4
>>>
  더 자세한 내용은 /{local}/repository/org/springframework/boot/spring-boot-dependencies/2.0.7.RELEASE/spring-boot-dependencies-2.0.7.RELEASE.pom dependency
>>>
    

# 실행
  ## openjdk 8.0.202.08 설치
  * https://adoptopenjdk.net/index.html -다운로드 후 설치
  * 설치 확인  
    ``` 
      java -version 
    ```  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-jdk-4.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-jdk-3.png" width="400" height="240" />   
        
  ## sourcetree 2.6.10 설치
  * https://www.sourcetreeapp.com 다운로드 후 설치  
  * 설치 안되어있을경우 git 다운로드  
  * repository clone  
    ```
      https://gitlab.com/niceday.lucas/example-backend-api.git
    ```
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sourcetree-1.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sourcetree-2.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sourcetree-3.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sourcetree-4.png" width="400" height="240" />      

  ## spring tool suite 4.0.0.RELEASE 설치
  * https://spring.io/tools 다운로드 후 압축 해제   
  * {sts설치디렉토리}/SpringToolSuite4.ini 수정  
  * sts 실행
  * import-mavent-existing maven projects 실행 후 clone 받은 프로젝트 연결  
  * 프로젝트 - debug as - maven update
  * maven build 완료되면 lombok 설치  
  * 프로젝트 - debug as - maven build(goal : clean package)  
  * 프로젝트 - debug as - maven update  
  * 프로젝트 - debug as - junit test  
  * 모든 테스트 케이스가 success 되어야 정상 동작
  * 현재 테스트 케이스 총 20개(현재 redis 관련 4개 케이스 ignore)
  * RedisTest의 모든 테스트 케이스는 ignore 되어 있으며 redis 설치 후 테스트 가능
    * windows용 redis 다운로드(https://github.com/MicrosoftArchive/redis/releases)
    ``` 
      -vm  
      C:\Program Files\ojdkbuild\java-1.8.0-openjdk-1.8.0.171-1\bin\javaw.exe
    ```    
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-1.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-2.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-3.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-4.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-5.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-6.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-7.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-8.png" width="400" height="240" />   
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-9.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-sts-10.png" width="400" height="240" />  


  ## intellij 2018.2.5 설치
  * https://www.jetbrains.com/idea/download/#section=windows 다운로드 후 설치
  * intellij 실행
  * import project - sourcetree 에서 받는 프로젝트 경로 - maven
  * import-mavent-existing maven projects 실행 후 clone 받은 프로젝트 연결  
  * setting - plugins - browse repositories - lombok install 후 재시작
  * maven build 완료되면 lombok 설치  
  * 프로젝트 - maven - generate sources and update folders
  * 프로젝트 - debug - all tests
  * 모든 테스트 케이스가 success 되어야 정상 동작
  * 현재 테스트 케이스 총 20개(현재 redis 관련 4개 케이스 ignore)
  * RedisTest의 모든 테스트 케이스는 ignore 되어 있으며 redis 설치 후 테스트 가능
    * windows용 redis 다운로드(https://github.com/MicrosoftArchive/redis/releases)
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-intellij-1.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-intellij-2.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-intellij-3.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-intellij-4.png" width="400" height="240" /> 
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-intellij-5.png" width="400" height="240" />  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/install-intellij-6.png" width="400" height="240" /> 


  ## 기타 실행
  * h2
  * swagger  
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-h2-1.png" width="400" height="240" />
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-h2-2.png" width="400" height="240" />
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-swagger-1.png" width="400" height="240" />
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-swagger-2.png" width="400" height="240" /> 


# 개발
  ## 개발 프로세스
  * 이슈 단계
  issues - boards(open, todo, doing, merged develop, merged master, close)
    * 'open' 단계에 앞으로 진행 될 이슈 생성(마일스톤, 작업자, 일정, 내용)  
    * 스프린트 단위로 한 주가 시작되기 전 'open' -> 'todo' 상태 변경하여 해당 주의 스프린트가 시작 됨을 알림
    * 해당 작업자는 이슈 상태 'doing' 변경하여 이슈 해결 시작을 알림
    * /develop 브랜치를 기반으로 /feature/이슈번호 브랜치 생성하여 개발 시작
    * 개발 완료 후 /feature/이슈번호에 commit/push 후 merge request 생성
    * /feature/이슈번호 맞는 merge request 를 해당 팀원은 모두 코드를 확인하고 변경사항이 있다면 소스 위치에 수정내용 작성, 없다면 '확인 완료' 커맨트 작성
    * 작업자는 merge request 의 모든 커맨트를 확인하고 변경 작업 후에 완료했다는 커맨트를 등록, 'mark as resolved' 선택, 
      만약 '확인 완료' 만 있다면 'mark as resolved' 선택하고 병합
    * 변경요청 한 팀원은 변경 된 내용을 확인하고 작업자의 커맨트에 'mark as resolved'
    * 모든 작업이 완료되었다면 /develop 브랜치로 병합
    * 해당 프로젝트의 빌드전(배포서버에서) slack의 해당 채널에 배포 시작을 모든 팀원에게 알리고, 해당 프로젝트 deploy를 시작
    * deploy가 모두 완료되면 slack의 해당 채널에 배포 완료를 모든 팀원에게 알림
    * 해당 테스크 상태 'merged develop' 변경
    * 테스트 진행
    * 위 내용은 스프린트(단위:1주) 단위로 이루어 지며 모든 이슈에 대해서 위와 같이 동일하게 반복
  <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-gitlab-2.png" width="400" height="240" />
  <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-gitlab-3.png" width="400" height="240" /> 
  <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-gitlab-4.png" width="400" height="240" /> 
  <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-gitlab-5.png" width="400" height="240" /> 
  
  ## 배포 프로세스
  * 개발 : git(branch-develop) - jenkins - deploy - was restart
  * 운영 : git(branch-master)  - jenkins - deploy - was restart
  <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-jenkins-1.png" width="400" height="240" /> 
  
  ## 소스 버전 관리 
  * 해당 브랜치 구조는 git-flow 정책을 따름
  
  * 브랜치 구조  
  feature - develop - release - tag - master - hotfix
    * feature : 테스크 단위 브랜치
    * develop : 전체 개발 단위 브랜치
    * release : 릴리즈 단위 브랜치
    * master  : 전체 운영 단위 브랜치
    * hotfix  : 전체운영 핫픽스 단위 브랜치
  * 태그
    * master에 배포되는 모든 단위 기록
  <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-gitlab-1.png" width="400" height="240" />
  <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/tool-sourcetree-1.png" width="400" height="240" /> 

  ## 디렉토리 구조
  * 기본 패키지 구성은 MAVEN PACKAGE 기준을 따른다.
  * /src/main/java     : 공통, 비지니스 로직를 모두 포함하는 영역
  * /src/main/resource : 외부 설정 영역
  * /src/test/java     : 테스트 영역(테스트케이스는 /src/main/java/com.lucas... 의 테스트 대상 소스의 패키지를 따라간다.)
    * ex. 대상 : com.lucas.sample.controller.PostController, 테스트케이스 : com.lucas.sample.PostTest, com.lucas.sample.PostHelper
  ## 패키지 구조   
  | 패키지 | 설명 |
  |  --------                           |  --------                                               |
  |  com.lucas.common                   | 공통 패키지(비니지스 로직이 포함되지 않는 상위 영역 ex. aop, config..) |
  |  com.lucas.sample                   | 샘플 패키지(기본 CRUD)                                      |
  |  com.lucas.프로젝트                 | 비지니스 로직 영역                                  |
  |  com.lucas.프로젝트.common          | 비지니스 공통 영역                                  |
  
  ## 기능별 이하 패키지 구조
  | 패키지 | 설명 |
  |  --------                           |  --------                            |
  | com.lucas.sample.entity     | 엔티티 영역(Entity)                         |
  | com.lucas.sample.form       | 폼 영역(Dto)                         |
  | com.lucas.sample.mapper     | 매퍼 영역(Mapper)                                |
  | com.lucas.sample.controller | 컨트롤러 영역(Controller)                        |
  | com.lucas.sample.service    | 서비스 영역(Service)                             |
  | com.lucas.sample.enumerate  | 코드(열거형) 영역 |
  | com.lucas.sample.predicate | 조건 영역(Predicate) |
  | com.lucas.sample.repository | 레파지토리 영역(Repository, Querydsl) |

  
  ## 기능별 이하 테스트 패키지 구조
  | 패키지 | 설명 |
  |  --------                           |  --------                            |
  | com.lucas.sample   | 테스트 케이스 영역(Test, Helper) |

  ## REST API 디자인
  | 기능         | 함수명     | API                                               | 
  |  --------    |  --------  | --------                                          |
  | 페이지 목록  | getPage    | GET    /api/samples/pages                   | 
  | 목록         | getAll     | GET    /api/samples                         | 
  | 조건 목록    | getAll     | GET    /api/samples/likes/title/content     | 
  | 조건 조회    | get        | GET    /api/samples/like/title/content      | 
  | 관계 목록    | getAll     | GET    /api/samples/{포스트아이디}/comments |
  | 등록         | add        | POST   /api/samples                         |
  | 수정         | modify     | PUT    /api/samples/{포스트아이디}          | 
  | 삭제         | remove     | DELETE /api/samples/{포스트아이디}          | 
  | 존재 여부     | exists   | HEAD    /api/samples/{포스트아이디}           |
  
  * 페이지 : 마지막 uri에 pages작성
  * 목록 : 복수로 작성
  * 조건 목록 : 복수 뒤에 likes(목록) 를 붙이고 이하로 조건들을 작성
  * 조건 조회 : 복수 뒤에 like(단일) 를 붙이고 이하로 조건들을 작성
  * 관계 목록 : 포스트의 댓글목록을 가져오는 것이다. 이 처럼 도메인의 구조에 맞춰 데이터를 가져올 경우 이와 같이 상하 구조대로 api를 디자인 한다.
  * 등록 : 복수로 작성
  * 수정 : 복수 뒤에 아이디
  * 삭제 : 복수 뒤에 아이디 
  * 존재 여부 : 복수 뒤에 아이디 
  



# 예제
  * 표준 API 개발 시 작성해야 할 내용들을 activity diagram 으로 표현
  * 상세 내용은 아래의 내용을 참조
    <img src="https://gitlab.com/niceday.lucas/images/raw/master/example-backend-api/process-1.png" width="1258" height="488" />  
  ## form
  * form - request/response - api - parameter 기준으로 요청/응답 정보 작성
  * parameter 별 validation 처리가 가능하며 복잡한 validation 의 경우 @AssertTrue, @AssertFalse로 작성
  * javax.validation, org.hibernate.validator 사용
```java
/**   
 * @since       2018.10.02
 * @author      lucas
 * @description sample form
 **********************************************************************************************************************/
public class SampleForm {

    public static class Request {

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Find {

            @ApiModelProperty(value="사용자아이디", required=true)
            private String userId;

            @ApiModelProperty(value="제목", required=true)
            private String title;

            @ApiModelProperty(value="시작일시(형식:yyyy-MM-dd)")
            @PastOrPresent
            @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
            private LocalDate startCreatedAt;

            @ApiModelProperty(value="종료일시(형식:yyyy-MM-dd)")
            @PastOrPresent
            @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
            private LocalDate endCreatedAt;

            @AssertTrue
            public boolean isValidDateRange() {
                return DateValidator.isValidDateRange(startCreatedAt, endCreatedAt);
            }
        }

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Add {

            @ApiModelProperty(value="사용자아이디", required=true)
            @NotBlank
            @Length(max=50)
            private String userId;

            @ApiModelProperty(value="제목", required=true)
            @NotBlank
            @Length(max=100)
            private String title;

            @ApiModelProperty(value="내용", required=true)
            @NotBlank
            @Length(max=200)
            private String content;
        }

        @Getter
        @Setter
        @Builder
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Modify {

            @ApiModelProperty(value="사용자아이디", required=true)
            @NotBlank
            @Length(max=50)
            private String userId;

            @ApiModelProperty(value="제목", required=true)
            @NotBlank
            @Length(max=100)
            private String title;

            @ApiModelProperty(value="내용", required=true)
            @NotBlank
            @Length(max=200)
            private String content;
        }
    }

    public static class Response {

        @Data
        public static class FindAll {

            @ApiModelProperty(value="샘플일련번호")
            private Long id;

            @ApiModelProperty(value="사용자아이디")
            private String userId;

            @ApiModelProperty(value="제목")
            private String title;

            @ApiModelProperty(value="등록일시")
            private String createdAt;
        }

        @Data
        public static class FindOne {

            @ApiModelProperty(value="샘플일련번호")
            private Long id;

            @ApiModelProperty(value="사용자아이디")
            private String userId;

            @ApiModelProperty(value="제목")
            private String title;

            @ApiModelProperty(value="등록일시")
            private String createdAt;
        }
    }
}
```
  
  ## mapper
  * request/response 사이에서 dtd - entity 매핑
  * mapstruct 사용(reflection 방식이 아닌 build 시 java code 를 생성하도록하는 개념)
```java
/**   
 * @since       2018.10.03
 * @author      lucas
 * @description sample mapper  
 **********************************************************************************************************************/
@Mapper(unmappedTargetPolicy=ReportingPolicy.IGNORE)
public interface SampleMapper {

    SampleMapper mapper = Mappers.getMapper(SampleMapper.class);

    Sample  toSample (Add    form);
    Sample  toSample (Modify form);
    FindOne toFindOne(Sample entity);
    FindAll toFindAll(Sample entity);

    @Mappings({
         @Mapping(target="id",        ignore=true)
        ,@Mapping(target="createdAt", ignore=true)
    })
    Sample modify(Sample source, @MappingTarget Sample target);
}
```


  ## controller
  * property.api.end-point : /api
  * restful 기준에 맞는 url 작성
  * @Valid validation 체크 여부
  * pageable 페이징 여부(/api/samples/samples?page=0&size=10&sort=userId,asc&sort=title,desc)
  * service 요청 전달 시 form를 entity 로 매핑, api 응답 반환 시 entity를 form 으로 매핑
    * 즉 request - controller - (form->entity) - service - (entity->form) - controller - response
  * 검색조긴이 있을 경우 predicate, 없을 경우 entity 를 service 로 전달
  * @RequiredArgsConstructor 통한 constructor injection(injection을 위하여 field는 final로 정의)
```java
/**   
 * @since       2018.10.03
 * @author      lucas
 * @description sample controller
 **********************************************************************************************************************/
@Api(description="샘플")
@RestController
@RequiredArgsConstructor
@RequestMapping("${property.api.end-point}")
public class SampleController {

    private final SampleService sampleService;

    @ApiOperation("목록")
    @GetMapping("/samples")
    public Page<FindAll> getAll(@Valid Find find, @PageableDefault Pageable pageable){
        return sampleService.getAll(SamplePredicate.search(find), pageable).map(mapper::toFindAll);
    }

    @ApiOperation("조회")
    @GetMapping("/samples/{sampleId}")
    public FindOne get(@PathVariable Long sampleId){
        return mapper.toFindOne(sampleService.get(sampleId));
    }

    @ApiOperation("등록")
    @PostMapping("/samples")
    public FindOne add(@Valid @RequestBody Add add){
        return mapper.toFindOne(sampleService.add(mapper.toSample(add)));
    }

    @ApiOperation("수정")
    @PutMapping("/samples/{sampleId}")
    public FindOne modify(@PathVariable Long sampleId, @Valid @RequestBody Modify modify){
        return mapper.toFindOne(sampleService.modify(sampleId, mapper.toSample(modify)));
    }

    @ApiOperation("삭제")
    @DeleteMapping("/samples/{sampleId}")
    public void remove(@PathVariable Long sampleId){
        sampleService.remove(sampleId);
    }
}
```


  ## entity
  * lombok 이용(getter/setter, default constructor, all args constructor, builder 등)
  * base entity 상속으로 등록 시 createdAt, 수정 시 updatedAt 이 자동 설정
  * jpa 기반의 entity 구성
  * OneToMany, ManyToOne 등의 annotation 의 엔티티 관계 정의(현 샘플은 sample(1) : (n)comment )
  * default fetch 전략은 lazy 정의
  * n+1 문제는 fetch join(graph entity, querydsl), batch size, subquery 등으로 해결(graph entity, querydsl 권장)
>>>
  더 자세한 내용은 https://docs.spring.io/spring-data/jpa/docs/2.0.7.RELEASE/reference/html
>>>
```java
/**
 * @since       2018.10.02
 * @author      lucas
 * @description sample
 **********************************************************************************************************************/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@Entity(name="sample")
@Description("샘플")
public class Sample extends Base {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Description("샘플일련번호")
    @Column(nullable=false, precision=20)
    private Long id;

    @Description("사용자아이디")
    @Column(nullable=false, length=50)
    private String userId;

    @Description("제목")
    @Column(length=100)
    private String title;

    @Description("내용")
    @Column(length=500)
    private String content;
}
```


  ## service
  * transaction 이 전체 선언되어 있고 조회 시 read olny 설정
  * entity 수정 시에는 mapper 이용
```java
/**
 * @since       2018.10.03
 * @author      lucas
 * @description sample service
 **********************************************************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    @Transactional(readOnly=true)
    public Page<Sample> getAll(Predicate predicate, Pageable pageable) {
        return sampleRepository.findAll(predicate, pageable);
    }

    @Transactional(readOnly=true)
    public Sample get(Long sampleId) {
        return sampleRepository.getOne(sampleId);
    }

    public Sample add(Sample sample) {
        return sampleRepository.save(sample);
    }

    public Sample modify(Long sampleId, Sample sample) {
        return mapper.modify(sample, get(sampleId));
    }
    
    public void remove(Long sampleId) {
        sampleRepository.delete(get(sampleId));
    }
}
```


  ## repository
  * predicate 사용 시 QuerydslPredicateExecutor 선언
  * qyerydsl 을 통한 jpql 사용 시 PostRepositoryQuerydsl 선언, PostRepositoryImpl 구현
  * jpa repository 기본 기능 사용(findAll, saveAll, deleteAllInBatch, getOne, findOne, save, count, exists 등등..)
  * 일반적인 조건은 query creation, predicate
  * 복잡한 조건은 querydsl + predicate
  * jqpl로 작성하기 힘들경우에 한해 mybatis 사용(권장하지 않음)
    * com.diquest.sample.repository.SampleMybatisRepository 참조
  * 객체 기반 쿼리 작성(복잡성을 낮추기 위해 해당 내용은 제외)
    * QPost 는 build 시 /target/generated-sources/java/... 에 자동 생성되어 사용가능
    * 생성이 안되었을 경우 프로젝트 maven build, maven update 후 다시 확인
>>>
  더 자세한 내용은 https://docs.spring.io/spring-data/jpa/docs/2.0.7.RELEASE/reference/html
  더 자세한 내용은 http://www.querydsl.com/static/querydsl/4.1.4/reference/html
>>>
```java
/**   
 * @since       2018.10.03
 * @author      lucas
 * @description sample repository 
 **********************************************************************************************************************/
@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>, QuerydslPredicateExecutor<Sample> {

}

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description sample repository querydsl
 **********************************************************************************************************************/
public interface SampleCustomRepository {

    Page<Post> findAllAsQueryDsl(Predicate predicate, Pageable pageable);
}

/**    
 * @since       2018.10.03
 * @author      lucas
 * @description sample repository impl 
 **********************************************************************************************************************/
public class SampleRepositoryImpl extends QuerydslRepositorySupport implements SampleCustomRepository {

    public PostRepositoryImpl() {
        super(Post.class);
    }
    
    public Page<Post> findAllAsQueryDsl(Predicate predicate, Pageable pageable){
        final QPost    sample  = QPost.sample;
        final QComment comment = QComment.comment;
        JPQLQuery<Post> query =  from    (sample)
                                .distinct()
                                .leftJoin(sample.comments, comment).fetchJoin()
                                .where   (predicate)
                                .orderBy (sample.postNo.desc(), comment.commentNo.desc());
        
        return new PageImpl<>(getQuerydsl().applyPagination(pageable, query).fetch(), pageable, query.fetchCount());
    } 
}
```


  ## predicate
  * 검색 조건 명시
  * QPost 는 build 시 /target/generated-sources/java/... 에 자동 생성되어 사용가능
    * 생성이 안되었을 경우 프로젝트 maven build, maven update 후 다시 확인
>>>
  더 자세한 내용은 http://www.querydsl.com/static/querydsl/4.1.4/reference/html
>>>    
    
```java
/**    
 * @since       2018.10.03
 * @author      lucas
 * @description sample predicate 
 **********************************************************************************************************************/
public class PostPredicate {
    
    public static Predicate search(Find find) {
        
        QPost          sample    = QPost.sample;
        BooleanBuilder builder = new BooleanBuilder();
        
        Optional.ofNullable(find.getUserId()).ifPresent(p -> builder.and(sample.userId.eq(p)));
        Optional.ofNullable(find.getTitle ()).ifPresent(p -> builder.and(sample.title.eq(p)));
        
        return builder;
    }
}
```


  ## test helper
  * api 요청/응답을 별도로 작성한 이유는 white box test, black box test 모두를 위한 정책
  * 각 api 별로 구성하여 black box test 시 조합하여 사용 할 수 있도록 제공
  * newXXX(test case 작성 시 조합하여 사용하므로 테스트 데이터도 helper 안에 작성)
    * 사용자 정의는 lombok.builder 자유롭게 설정하고 빠르게 테스트를 하기 위함이라면  default value 사용(super.getDefaultInstance)

       | type                | default value            |
       | --------            | --------                 |
       | Long.class          | NumberUtils.LONG_ZERO    |
       | Integer.class       | NumberUtils.INTEGER_ZERO |
       | BigDecimal.class    | BigDecimal.ZERO          |
       | Boolean.class       | Boolean.TRUE             |
       | String.class        | field.getName()          |
       | LocalTime.class     | LocalDate.now()          |
       | LocalDate.class     | LocalDate.now()          |
       | LocalDateTime.class | LocalDate.now()          |  
       
```java
/**
 * @since       2018.10.03
 * @author      lucas
 * @description sample helper
 **********************************************************************************************************************/
public class SampleHelper extends TestHelper {

    @SneakyThrows
    public static void getAll(Request.Find find) {
        mock.perform(get  ("/api/samples")
                .content  (toJson(find)))
                .andExpect(status().isOk())
                .andDo    (print());
    }

    @SneakyThrows
    public static void get(Long sampleId) {
        mock.perform(get  ("/api/samples/{sampleId}", sampleId))
                .andExpect(status().isOk())
                .andDo    (print());
    }

    @SneakyThrows
    public static Response.FindOne add(Request.Add add) {
        return toInstance(Response.FindOne.class,
                mock.perform(post ("/api/samples")
                        .content  (toJson(add)))
                        .andExpect(status().isOk())
                        .andDo    (print()));
    }

    @SneakyThrows
    public static Response.FindOne  modify(Long sampleId, Request.Modify modify) {
        return toInstance(Response.FindOne.class,
                mock.perform(put  ("/api/samples/{sampleId}", sampleId)
                        .content  (toJson(modify)))
                        .andExpect(status().isOk())
                        .andDo    (print()));
    }

    @SneakyThrows
    public static void remove(Long sampleId) {
        mock.perform(delete("/api/samples/{sampleId}", sampleId))
                .andExpect (status().isOk())
                .andDo     (print());
    }

    @SneakyThrows
    public static void get_notFound(Long sampleId) {
        mock.perform(get  ("/api/samples/{sampleId}", sampleId))
                .andExpect(status().isNotFound())
                .andDo    (print());
    }

    @SneakyThrows
    public static void modify_notFound(Long sampleId, Request.Modify modify) {
        mock.perform(put  ("/api/samples/{sampleId}", sampleId)
                .content  (toJson(modify)))
                .andExpect(status().isNotFound())
                .andDo    (print());
    }

    @SneakyThrows
    public static void remove_notFound(Long sampleId) {
        mock.perform(delete("/api/samples/{sampleId}", sampleId))
                .andExpect (status().isNotFound())
                .andDo     (print());
    }
}

```


  ## test
  * 각 api 별로 테스트 진행
  * 예외 처리에 대한 테스트 진행
  * black box test 시 각 test helper 릅 조합하여 구성
  * 모든 api 는 해당 테스트 케이스가 만들어져야 하며 해당 테스트 케이스는 배포 시 자동진행되어 최소한의 side effect 를 감지
```java
/**
 * @since       2018.10.03
 * @author      lucas
 * @description sample test
 **********************************************************************************************************************/
@SpringBootTest    
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class SampleTest {

    @Test
    public void t01_getAll() {
        add   (newInstance(Request.Add.class ));
        add   (newInstance(Request.Add.class ));
        getAll(newInstance(Request.Find.class));
    }

    @Test 
    public void t04_get() {
        get(add(newInstance(Request.Add.class)).getId());
    }

    @Test 
    public void t05_add() {
        add(newInstance(Request.Add.class));
    } 
 
    @Test 
    public void t06_modify() {
        modify(add(newInstance(Request.Add.class)).getId(), newInstance(Request.Modify.class));
    } 

    @Test 
    public void t07_remove() {
        remove(add(newInstance(Request.Add.class)).getId());
    }

    @Test
    public void t08_get_notFound() {
        get_notFound(Long.MIN_VALUE);
    }

    @Test
    public void t09_modify_notFound() {
        modify_notFound(Long.MIN_VALUE, newInstance(Request.Modify.class));
    }

    @Test
    public void t10_remove_notFound() {
        remove_notFound(Long.MIN_VALUE);
    }
}
```