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
