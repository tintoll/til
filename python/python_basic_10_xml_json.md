# XML & JSON

## XML
- 데이터의 구조와 의미를 설명하는 TAG를 사용하여 표시하는 언어
- TAG와 TAG사이에 값이 표시되고, 구조적인 정보를 표현 할수 있음
- 정보의 구조에 대한 정보인 스키마와 DTD등으로 정보에 대한 정보(메타정보)가 표현되며, 용도에 따라 다양한 형태로 변경가능
- XML은 컴퓨터(예:PC<->스마트폰)간에 정보를 주고받기 매우 유용한 저장방식으로 쓰이고 있음
- 파이썬에는 beautifulsoup라는 parser를 이용해서 파싱한다.

### xml 예제
```xml
<?xml version="1.0"?>
  <books>
    <book>
        <author>Carson</author>
        <price format="dollar">31.95</price>
        <pubdate>05/01/2001</pubdate>
    </book>
    <pubinfo>
        <publisher>MSPress</publisher>
        <state>WA</state>
    </pubinfo>
    <book>
        <author>Sungchul</author>
        <price format="dollar">29.95</price>
        <pubdate>05/01/2012</pubdate>
    </book>
    <pubinfo>
        <publisher>Gachon</publisher>
        <state>SeoungNam</state>
    </pubinfo>
  </books>
```

### BeautifulSoup
- HTML, XML등 Makrup 언어 Scraping을 위한 대표적인 도구
- https://www.crummy.com/software/BeautifulSoup/
- lxml과 html5lib와 같은 Parser를 사용함
- 속도는 상대적으로 느리나 간편히 사용할 수 있음

#### conda 가상환경에서 설치방법
```
activate python_kmooc
conda install lxml
conda install -c anaconda beautifulsoup4=4.5.1
```
#### 사용방법
```python
# 모듈 호출
from bs4 import BeautifulSoup
# 객체 생성
soup = BeautifulSoup(books_xml, "lxml")
# Tag 찾는 함수 find_all 생성
soup.find_all("author") # 해당 패턴 모두반환
# get_text() : 반환된 패턴의 값 반환
```
- 예제
```python
from bs4 import BeautifulSoup

with open("books.xml", "r", encoding="utf8") as books_file:
    books_xml = books_file.read()  # File을 String으로 읽어오기

# xml 모듈을 사용해서 데이터 분석
soup  = BeautifulSoup(books_xml, "lxml")
# author가 들어간 모든 element 추출
for book_info in soup.find_all("author"):
    print (book_info)
    print (book_info.get_text())

```

## JavaScript Object Notation(JSON)
- javascript의 데이터 객체 표현방식
- 간결성으로 기계/인간이 모두 이해하기 펴한
- 데이터 용량이 적고, Code로의 전환이 쉬움
- xml의 대체제로 많이 활용됨.
- 파이썬의 dict type과 데이터저장및 읽기가 상호호환됨.

json 구조 
```javascript
{"employees":[
    {"firstName":"John", "lastName":"Doe"},
    {"firstName":"Anna", "lastName":"Smith"},
    {"firstName":"Peter", "lastName":"Jones"}
]}
```

### json read
```python
import json

with open("json_example.json", "r", encoding="utf8") as f:
    contents = f.read()
    json_data =  json.loads(contents)
    print(json_data["employees"])
```
### json write
```python
import json

dict_data = {'Name': 'Zara', 'Age': 7, 'Class': 'First'}

with open("data.json", "w") as f:
    json.dump(dict_data, f)

```
