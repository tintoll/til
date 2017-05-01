# 자바 웹 프로그래밍 Next Step

## 2장

이클립스 단축키
1. Alt+Command 화살표아래방향 : 선택된 부분이 복제됨. 해당줄에서 하면 한줄만 복제됨.
2. Command F11 : java 실행
3. Alt+ 화살표방향 : 해당라인을 위/아래로 옴길수 있다.
4. Alt+Command+M : Refator>Extract Method. 해당영역을 메서드로 추출

템플릿메소드 사용
test3을 작성후 Control+Space하면 메뉴 나오고 거기서 테스트용 메소드를 생성할수 있다.

리팩토링을 연습할 경우에는 심할정도까지 리팩토링해본다. 메서드는 하나의 일만 하도록
```java
public class StringCalculator {
	// add 메서드를 봤을때 다른 메서드는 의미가 명확해서 소스파악할 필요가 없음.
		public int add(String text) {
			if(isBlank(text) ){
				return 0;
			}
			return sum(toInts(split(text)));
		}

		// 값이 없음을 체크
		private boolean isBlank(String text) {
			return text == null || text.isEmpty();
		}
		// 문자열을 나눠준다.
		private String[] split(String text) {
			Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
			if(m.find()) {
				String customDelimeter = m.group(1);
				return m.group(2).split(customDelimeter);
			}
			// ESLE를 가급적 안쓰는 습관을 들이자
			return text.split(",|:");
		}
		// 문자들을 숫자화해준다.
		private int[] toInts(String[] values) {
			int[] numbers = new int[values.length];
			for (int i = 0; i < values.length; i++) {
				numbers[i] = toPositive(values[i]);
			}
			// 인덴트가 2번이상이면 메서드로빼서 리펙토링하는 습관을 가지자
			return numbers;
		}

		private int toPositive(String value) {
			int number = Integer.parseInt(value);
			if(number < 0){
				throw new RuntimeException();
			}
			return number;
		}
		// 숫자들을 합해준다.
		private int sum(int[] numbers) {
			int sum = 0;
			for(int number : numbers) {
				sum += number;
			}
			return sum;
		}
}
```

## 3장

### 개발서버 구축하기
각 계정별 UTF-8 인코딩 설정
```
$ sudo locale-gen ko_KR.EUC-KR ko_KR.UTF-8
$ sudo dpkg-reconfigure locales
$ vi .bash_profile
	LANG="ko_KR.UTF-8"
	LANGUAGE="ko_KR:ko:en_US:en"
$ source .bash_profile
$ env
```
### java, Maven 설치
```
// 다운로드
$ wget --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u121-b13/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-linux-x64.tar.gz
// 압축풀기
$ gunzip jdk-8u121-linux-x64.tar.gz
$ tar -xvf jdk-8u121-linux-x64.tar
// 심볼릭링크 걸어주기
$ ln -s jdk1.8.0_121/ java
// .bash_profile에 설정해주기
	export JAVA_HOME=~/java
	export PATH=$PATH:$JAVA_HOME/bin
// Maven Downlod
$ wget http://mirror.navercorp.com/apache/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz
$ gunzip apache-maven-3.5.0-bin.tar.gz
$ tar -xvf apache-maven-3.5.0-bin.tar
$ ln -s apache-maven-3.5.0/ maven
	export MAVEN_HOME=~/maven
	export PATH=$PATH:$MAVEN_HOME/bin
```
### git 설치및 웹서버 구동
```
$ sudo apt-get install git
$ git clone https://github.com/tintoll/web-application-server.git
$ mvn clean package
$ java -cp target/classes:target/dependency/* webserver.WebServer 8080 &
```
방화벽 허용 : (우분투)http://webdir.tistory.com/206


### 리눅스에서 개발하려면 알아야할 것들(한상곤-https://youtu.be/JbH-xzD7IkE)
```
tail -f log | grep --line-buffered Error > error.txt
man
zsh 쉘 사용
tmux - 백그라운드에 프로그램을 실행시킬수 있게 해주는 프로그램
upstart, systemd - 시작프로그램 등록 해주는거
ssh
scp -  파일복사용
sftp
rsyslog - 시스템로그관련
cron - 반복작업
vim / emacs - 에디터
```

### 읽어볼만한 사이트
https://www.html5rocks.com/en/tutorials/internals/howbrowserswork/ : 브라우저가 일하는 방법에 관련된 튜토리얼
https://guides.github.com/features/mastering-markdown/ : 마크다운 가이드

### 실습
```java
// InputStream에서 한줄씩읽기
BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
String line = br.readLine();

// 파일을 읽어 바이트로 가져오는 메서드
byte[] body = Files.readAllBytes(new File("./webapp"+url).toPath());

// 해당 문자로 시작하는 경우
String url = "/user/create?ddd=ddd";
if(url.startsWith("/user/create")){ ... }
```

### Maven
```
// 메이븐 컴파일(테스트파일은 컴파일 되지 않음)
$ mvn compile
// 컴파일러 플러그인을 이용하여 컴파일
$ mvn compiler:compile
// 테스트 파일 컴파일
$ mvn test
// 패키징 작업
$ mvn package
// 모든 파일 초기화
$ mvn clean

// eclipse플러그인에 eclipse 골을 입력
// pom.xml을 읽어서 프로젝트를 재구성해준다.
$ eclipse:eclipse
// 기존 eclipse 설정을 제거해준다.
$ eclipse:clean
```
Effective POM : 부모 POM 설정.
메이븐은 모든 작업이 플러그인 기반으로 되어있다.

메이븐 Phase 설명 (http://demon92.tistory.com/14)

기본 페이즈 순서
complie -> test -> package -> install(로컬에 저장) -> deploy(원격저장소에 저장)

```xml
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-eclipse-plugin</artifactId>
				<version>2.9</version>
				<configuration>
					<downloadSources>true</downloadSources> <!-- 소스코드 볼수있도록 설정 -->
					<!-- 컨텍스트 설정 -->
                    <wtpversion>2.0</wtpversion>
					<wtpContextName>/</wtpContextName>
				</configuration>
			</plugin>
```
### 로깅
```java
System.out.println("debug message");
```
위와같이 프로그램이 실행할때마다 디버깅메시지가 출력되면 프로그램의 성능을 떨어뜨린다. 그래서 개발중 추가한 디버깅 메시지는 실 서비스하는 시점에 제거해야한다.
로깅(logging) 프레임워크를 활용해 디버깅 메시지를 레벨화해주면 위와같은 문제를 해결해 줄 수 있다.
종류 : Log4j, Slf4j, LogBack

로그레벨 : DEBUG < INFO < WARN < ERROR
DEBUG 레벨이면 상위 단계가 다 출력된다.

프로젝트에 LogBack 설정
```xml
<!-- src/main/resources/logback.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <!-- encoders are assigned the type
         ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```
```java

package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWord {

	static final Logger logger = LoggerFactory.getLogger(HelloWord.class);

	public String message() {
		logger.debug("Did it again!");

        String user = "ddd";
        logger.debug("User : "+user); // 로그레벨이 INFO여도 안의 문장은 실행됨.
        logger.debug("User : {}", user); // 로그레벨이 INFO이면 안의 문장은 실행안됨.(이 방법을 사용해야함)
		return "say hello";
	}

}

```
java에서 매번 logger를 만들기 귀찮으니 eclipse의 templete 기능을 등록하여 사용하면 쉽게 쓸수있다.
```
${:import(org.slf4j.Logger,org.slf4j.LoggerFactory)}
private static final Logger logger = LoggerFactory.getLogger(${enclosing_type}.class);
```
## 4장

### HTTP
일단 아래 url에서 기본적인 내용만 봄.
https://www3.ntu.edu.sg/home/ehchua/programming/webprogramming/HTTP_Basics.html

-HTTP  관련 책-
1단계 - 프로가 되기 위한 웹 기술 입문(HTTP 내용보기)
2단계 - HTTP & Network : 그림으로 배우는 책으로 학습
3단계 - HTTP 완벽 가이드
### 네트워크
아래의 책들 볼것을 추천함.
1단계 - 성공과 실패를 결정하는 1%의 네트워크 원리
2단계 - 그림으로 공부하는IT 인프라 구조

## 6장

### 쉘을 이용한 배포 자동화
```
vi jwp-deploy.sh
내용 start
#!/bin/bach //쉘 설정

REPOSITORY_DIR = ~/repositories/jwp-basic
TOMCAT_DIR = ~/tomcat

cd $REPOSITORY_DIR
pwd
git pull
mvn clean package -Dmaven.test.skip=true

$TOMCAT_DIR/bin/shutdown.sh
rm -rf $TOMCAT_DIR/webapps/ROOT
pwd
mv $REPOSITORY_DIR/target/jwp-basic $TOMCAT_DIR/webapps/ROOT

$TOMCAT_DIR/bin/startup.sh

tail -500f $TOMCAT_DIR/logs/catalina.out  //마지막 500라인출력
내용 end
chmod 755 jwp-deploy.sh //실행 권한 주기

```
쿠키와 세션에 대한 차이점
 - '프로가 되기 위한 웹 기술 입문' 4장 학습

보안에 대한 내용
 - '프로가 되기 위한 휍기술 입문' 7장 학습 (SQL인젝션, 크로스 사이트 스크립팅[XSS], 세션 하이재킹, 크로스사이트 요청위조[CSRF] )
 - '그림으로 배우는 HTTP&Network' 7장,8장, 11장 도 같이 학습하면 좋음.

## 7장

### 데이터 베이스
SQL에 대한 기본적인 학습이 끝났다면 다음 단계로 학습할 내용은 성능을 고려한 설계 및 인덱스 활용에 대한 학습이다. 이 내용과 관련해 깊이 있게 학습하지는 못하더라도 ER 다이어그램, 인덱스, 정규화, 트랜잭션과 같이 데이터베이스의 핵심 내용은 학습해야 한다.

기초 내용 :  ``SQL 첫걸음(하루 30분 36강으로 배우는 완전 초보의 SQL 따라잡기)``

MySQL에 대해 더 깊이 있게 학습하고 싶다면 ``Real MySQL : 개발자와 DBA를 위한(이성욱 저, 위키북스/2012년)`` 책을 추천한다.

2010년에 접어들면서 관계형 데이터베이스 외에 카산드라, 카우치DB, 몽고DB, 레디스와 같은 수 많은 다양한 형태의 데이터베이스가 등장했다. 이 같은 데이터베이스를 통틀어 NoSQL이라고 한다. NoSQL은 점점 더 다양한 분야에 활용되고 있기 때문에 개발자가 반드시 학습해야할 주제가 되었다. 두 번째 양파 껍질에 해당하는 지금 단계는 어떤 NoSQL이 존재하고 각 NoSQL의 특징이 무엇인지 이해하는 것만으로도 충분한다. ``NoSQL : 빅 데이터 세상으로 떠나는 간결한 안내서``(프라모드 사달게이,마틴 파울러 공저/윤성준 역, 인사이트/2013년) 책은 다양한 NoSQL에 대한 특징을 살펴보기 위한 안내서가 될 것이다. 일단 이 정도 수준으로 개념만 잡고 있다 현재 진행하는 프로젝트에서 사용하는 NoSQL이 있다면 추가 학습할 것을 추천한다.

### 디자인 패턴

개인적으로 디자인 패턴은 개발 경험을 쌓은 후 학습해도 괜찮다고 생각한다. 먼저 자신의 코드에서 리팩토링할 부분을 찾아 작은 부분이라도 리팩토링을 통해 소스 코드를 개선하는 경험이 우선이다. 이런 경험을 반복하다보면 일정한 패턴을 스스로 찾는 경험도 할 수 있다. 이 순간의 짜릿함은 그 무엇과도 바꿀 수 없을 것이다.

그래도 디자인 패턴을 학습하고 싶다면 처음 책은 ``Head First Design Patterns : 스토리가 있는 패턴 학습법(에릭 프리먼 저 / 서환수 역, 한빛미디어/2005)`` 책을 추천한다. 애플리케이션 개발에서 자주 사용하고 디자인 패턴을 다양한 그림을 통해 설명하고 있다. 개발 경험이 많지 않은 상태에서 디자인 패턴을 접하면 좌절하는 경우가 많은데 스토리와 그림을 통해 설명하기 때문에 나름 쉽게 접근할 수 있다. 이 책을 읽으면 개발 단계에 바로 적용할 수 있겠다는 생각이 들지만 막상 적용하는 것은 쉽지 않다. 아는 것과 적용하는 것은 완전히 다르기 때문이다. 적용하는 능력을 키우려면 설계에 대한 많은 고민을 통해 경험을 쌓는 수 밖에 없다.

만약 소스 코드를 통해 디자인 패턴을 학습하고 싶다면 ``실전 코드로 배우는 실용주의 디자인 패턴(Allen Holub 저/송치형 역, 지앤선/2006)`` 책을 추천한다. 좋은 소스 코드를 읽을 수도 있고, 소스 코드를 통해 디자인 패턴의 의도를 파악하기 위한 목적으로도 좋다.


## 8장

### REST API 설계 및 개발
웹 백엔드는 일반 웹 애플리케이션 뿐만 아니라 모바일 앱의 백엔드로도 활용되고 있다. 한발 더 나아가 게임의 백엔드로도 활용되고 있는 실정이다. 이와 같이 웹 백엔드가 활용되는 곳이 많아지면서 HTML과 JSON/XML API를 동시에 지원하는 것은 중복 코드도 많고, 유지보수하기도 힘들어진다. 이 같은 단점을 극복하기 위해 최근 웹 백엔드는 모든 기능을 JSON/XML API만 지원하고 UI에 대한 결정은 이 API를 사용하는 클라이언트에서 결정하는 방향으로 발전하고 있다.

이와 같이 웹 백엔드 API를 설계할 때 사용하는 구조로 REST API 스타일이 널리 사용되고 있다. REST API에 대해 한 문장으로 정의하기 힘든데 REST API 스타일로 구현된 예제를 통해 REST API 스타일 무엇이다라는 것에 대한 감(sense)을 잡았으면 한다.

REST API 스타일을 학습하는 방법은 책이나 문서를 통해 학습하는 방법도 있지만 그 보다는 이미 REST API 스타일로 구현되어 있는 공개 API를 보면서 학습하는 것이 빠르고 쉬울 수 있다. Github, 페이스북, 구글과 같은 서비스의 공개 API를 읽으면 자연스럽게 REST API 스타일을 익힐 수 있다.

REST API 관련해 문서를 참고하고 싶다면 먼저 https://pages.apigee.com/rs/apigee/images/api-design-ebook-2012-03.pdf 문서를 참고한다. 영어로 되어 있지만 페이지 수도 많지 않고, 샘플 예제를 통해 REST API 스타일에 대해 알 수 있기 때문에 영어를 잘 하지 못해도 이해하는데 크게 부담되지 않는다.

만약 영어로 된 문서가 읽기 부담스럽다면 일관성 있는 ``웹 서비스 인터페이스 설계를 위한 REST API 디자인 규칙(마크 메세 저/권원상,김관래 역, 한빛미디어/2015년)`` 책을 통해 학습하는 것도 한 가지 방법이다.

REST API 스타일로 개발하는 것은 정답이 있는 영역이 아닌 설계의 영역이기 때문에 경험을 통해 지속적으로 좋은 스타일을 찾아가야한다.

## 11장

### 의존관계 주입(DI)
DI가 무엇이고, 왜 필요한 것인지에 대해 쉽게 공감하기 힘들 수 있다. 이에 대한 깨달음을 얻을 수 있는 가장 좋은 방법은 하나의 서비스를 지속적으로 운영해 보는 경험이 가장 좋다고 생각한다. 하지만 많은 경험과 시간이 필요하다. 따라서 이와 관련해 다양한 문서를 참고하다보면 어느 순간 깨달음을 얻을 수도 있기 때문에 몇 개의 문서를 추천해 본다.

- http://www.slideshare.net/baejjae93/dependency-injection-36867592: 의존관계가 무엇이고, 왜 필요한 것인지에 대해 그림을 통해 쉽게 설명하고 있는 문서이다. 다른 문서를 보기 전에 이 문서를 통해 의존관계 주입에 대한 대략적인 개념을 잡아볼 것을 추천한다.
- 토비의 스프링 3.1(이일민 저, 에이콘/2012년) 1권 1,2장 : DI와 DI를 적용했을 때의 테스트 방법에 대해 잘 설명하고 있다. DI, 객체지향 설계, 스프링 프레임워크 학습을 위한 다음 단계로 읽을 책이다. 이 책은 1,2권으로 나눠져 있고, 상당히 두꺼운 책이기 때문에 모두 읽기보다 관심있는 부분부터 일부분씩 읽어 나가는 것도 좋은 선택이다. 이 책은 혼자 읽기보다 스터디와 같은 형식을 통해 여러 사람이 같이 읽을 것을 추천한다.


## 12장
https://youtu.be/7GlCo6RHBns : 동영상은 nginx 웹 서버 설치 과정과 설정 디렉토리, 파일에 대한 간략한 설명을 다루고 있다.
https://youtu.be/QRqm5Xlw1HQ : 동영상은 nginx와 tomcat 웹 서버 연동 방법에 대해 다루고 있다.
https://youtu.be/XEaqMl7eZT4 : 동영상은 수동으로 nginx 웹 서버에 점검 페이지를 설정하는 방법을 다룬다.
https://youtu.be/ZeY0xpnXF7w : 동영상은 쉘 스크립트를 통해 점검 페이지를 자동화하는 과정에 대해 다룬다.