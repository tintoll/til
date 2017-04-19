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










