# Web Scraping
- 웹에서 데이터 다운로드 예제
```python
import urllib.request	#urllib 모듈 호출

url = "http://storage.googleapis.com/patents/grant_full_text/2014/ipg140107.zip"
# 다운로드 URL 주소
print ("Start Download")
fname, header = urllib.request.urlretrieve(url, 'ipg140107.zip')
#urlretrieve 함수 호출 (url 주소, 다운로드 될 파일명), 결과값으로 다운로드된 파일명과 Header 정보를 언패킹
print ("End Download")
```

## 정규식 - Regular Expression
- 복잡한 문자열 패턴을 정의하는 문자 표현 공식
- 특정한 규칙을 가진 문자열의 집합을 추출
```
010-0000-0000 -> ^\d{3}\-\d{4}\-\d{4}$
203.252.101.40 -> ^\d{1,3}\.\d{1,3}.\d{1,3}\.\d{1,3}$
```
- 정규표현식을 이용한 HTML Parsing
- 관련자료 : http://nextree.co.kr/p4327/

### 기본 문법
- 참고 사이트 : https://wikidocs.net/4308
- 연습 사이트 : https://regexr.com
- 문자 클래스 **[** **]** : [ 와 ] 사이의 문자들과 매치 라는 의미
    - 예) [abc] <-- 해당 글자가 a,b,c중 하나가 있다
- "-"를 사용하여 범위를 지정할 수 있음
    - 예) [a-zA-Z]  <-- 알파벳 전체, [0-9]  <-- 숫자 전체    

### 메타문자
- 정규식 표현을 위해 원래 의미가 아니라, 다른 용도로 사용되는 무자
```
. ^ $ * + ? { } [ ] \ | ( )

. : 줄바꿈 문자인 \n를 제외한 모든 문자와 매치
* : 앞에 있는 글자를 반복해서 나올 수 있음
+ : 앞에 있는 글자를 최소 1회 이상 반복
{m,n} : 반복횟수를 지정
? : 반복 횟수가 1회
| : or 
^ : not
```    


### 정규식 예제들
```python
import re
import urllib.request

url = "http://goo.gl/U7mSQl"
html = urllib.request.urlopen(url)
html_contents = str(html.read())
id_results = re.findall(r"([A-Za-z0-9]+\*\*\*)", html_contents)
#findall 전체 찾기, 패턴대로 데이터 찾기

for result in id_results:
    print (result)
```

```python
import urllib.request # urllib 모듈 호출
import re

url = "http://www.google.com/googlebooks/uspto-patents-grants-text.html" #url 값 입력
html = urllib.request.urlopen(url)	 # url 열기
html_contents = str(html.read().decode("utf8"))  # html 파일 읽고, 문자열로 변환

url_list = re.findall(r"(http)(.+)(zip)", html_contents)
for url in url_list:
    print("".join(url))  # 출력된 Tuple 형태 데이터 str으로 join
```

```python
import urllib.request # urllib 모듈 호출
import re

base_url = "http://web.eecs.umich.edu/~radev/coursera-slides/" #url 값 입력
html = urllib.request.urlopen(base_url)	 # url 열기
html_contents = str(html.read().decode("utf8"))  # html 파일 읽고, 문자열로 변환
# print(html_contents )
url_list = re.findall(r"nlp[0-9a-zA-Z\_\.]*\.pdf", html_contents)
for url in url_list:
    file_name = "".join(url)
    full_url = base_url + file_name
    print(full_url)
    fname, header = urllib.request.urlretrieve(full_url, file_name)
    print ("End Download")

```
- html 태그 파싱
```python
import urllib.request
import re

url = "http://finance.naver.com/item/main.nhn?code=005930"
html = urllib.request.urlopen(url)
html_contents = str(html.read().decode("ms949"))

stock_results = re.findall("(\<dl class=\"blind\"\>)([\s\S]+?)(\<\/dl\>)", html_contents)
samsung_stock = stock_results[0] # 두 개 tuple 값중 첫번째 패턴
samsung_index = samsung_stock[1] # 세 개의 tuple 값중 두 번째 값
                                                  # 하나의 괄호가 tuple index가 됨
index_list= re.findall("(\<dd\>)([\s\S]+?)(\<\/dd\>)", samsung_index)

for index in index_list:
    print (index[1]) # 세 개의 tuple 값중 두 번째 값
```

