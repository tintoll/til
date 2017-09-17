# File Handling
파이썬 파일 처리를 위해서 "open" 키워드를 사용함
```python
f = open("<파일이름>","접근 모드")
f.close()
```
- 접근모드 
    - r : 읽기모드 - 파일을 읽기만 할 때 사용
    - w : 쓰기모드 - 파일에 내용을 쓸 때 사용
    - a : 추가모드 - 파일의 마지막에 새로운 내용을 추가 시킬때 사용

## File Read
- f.read() : txt 파일 안에 있는 내용을 문자열로 반환
```python
f = open("i_have_a_dream.txt","r")
contents = f.read()
print(contents)
f.close()
```  
- with구문과 함께 사용하기
```python
with open("i_have_a_dream.txt","r") as my_file :
    contents = my_file.read()
    print(type(contents), contents)
```
- 한줄씩 읽어 List Type으로 반환
```python
with open("i_have_a_dream.txt","r") as my_file :
    content_list = my_file.readlines() # 파일 전체를 list로 반환
    print(type(content_list))
    print(content_list)
```
- 단어 통계 정보 산출
```python
with open("i_have_a_dream.txt", "r") as my_file:
    contents = my_file.read()
    word_list = contents.split(" ")	 #빈칸 기준으로 단어를 분리 리스트
    line_list = contents.split("\n")	 #한줄 씩 분리하여 리스트

print("Total Number of Characters :", len(contents))
print("Total Number of Words:", len(word_list))
print("Total Number of Lines :", len(line_list))
```
## File Write
- 쓰기모드
```python
f = open("count_log.txt", 'w', encoding="utf8")
for i in range(1, 11):
    data = "%d번째 줄입니다.\n" % i
    f.write(data)
f.close()
```
- 추가모드
```python
with open("count_log.txt", 'a', encoding="utf8") as f:
    for i in range(100, 111):
        data = "%d번째 줄입니다.\n" % i
        f.write(data)
```

## Directory 만들기
- os 모듈을 사용하여 directory만들기
```python
import os
os.mkdir("log")
```
- 디렉토리가 있는지 확인하기
```python
if not os.path.isdir("log") :
    os.mkdir("log")
```
- Log 파일 생성하기 예제
```python
import os
if not os.path.isdir("log"):
    os.mkdir("log")
if not os.path.exists("log/count_log.txt"):
    f = open("log/count_log.txt", 'w', encoding="utf8")
    f.write("기록이 시작됩니다\n")
    f.close()

with open("log/count_log.txt", 'a', encoding="utf8") as f:
    import random, datetime
    for i in range(1, 11):
        stamp = str(datetime.datetime.now())
        value = random.random() * 1000000
        log_line = stamp + "\t" + str(value) +"값이 생성되었습니다"
        f.write(log_line)

```

## CSV 파일 읽기/쓰기
- csv 읽기
```python
line_counter = 0	#파일의 총 줄수를 세는 변수
data_header = []	#data의 필드값을 저장하는 list
customer_list = []	#cutomer 개별 List를 저장하는 List

with open ("customers.csv") as customer_data: #customer.csv 파일을 customer_data 객체에 저장

    while 1:
        data = customer_data.readline() #customer.csv에 한줄씩 data 변수에 저장
        if not data: break	 #데이터가 없을 때, Loop 종료
        if line_counter==0: 	 #첫번째 데이터는 데이터의 필드
            data_header = data.split(",") 	#데이터의 필드는 data_header List에 저장, 데이터 저장시 “,”로 분리
        else:
            customer_list.append(data.split(",")) #일반 데이터는 customer_list 객체에 저장, 데이터 저장시 “,”로 분리
        line_counter += 1

print("Header :\t", data_header)	#데이터 필드 값 출력
for i in range(0,10):		 #데이터 출력 (샘플 10개만)
    print ("Data",i,":\t\t",customer_list[i])
print (len(customer_list))		 #전체 데이터 크기 출력

```
- csv 쓰기
```python
line_counter = 0
data_header = []
employee = []
customer_USA_only_list = []
customer = None

with open ("customers.csv", "r") as customer_data:
    while 1:
        data = customer_data.readline()
        if not data:
            break
        if line_counter==0:
            data_header = data.split(",")
        else:
            customer = data.split(",")
            if customer[10].upper() == "USA":	#customer 데이터의 offset 10번째 값
                customer_USA_only_list.append(customer)	 #즉 country 필드가 “USA” 것만
        line_counter+=1			 #sutomer_USA_only_list에 저장

print ("Header :\t", data_header)
for i in range(0,10):
    print ("Data :\t\t",customer_USA_only_list[i])
print (len(customer_USA_only_list))

with open ("customers_USA_only.csv", "w") as customer_USA_only_csv:
    for customer in customer_USA_only_list:
        customer_USA_only_csv.write(",".join(customer).strip('\n')+"\n")
        #cutomer_USA_only_list 객체에 있는 데이터를 customers_USA_only.csv 파일에 쓰기
```

### CSV 객체활용
```python
import csv
reader = csv.reader(f, delimiter=',', quotechar='"', quoting=csv.QUOTE_ALL)

# delimiter : 글자는 나누는 기준 (default : ,)
# lineteminator : 줄바꿈 기준 (default : \r\n)
# quotechar : 문자열을 둘러싸는 신호문자 (default : ")
# quoting : 데이터 나누는 기준이 quotechar에 의해 둘러싸인 레벨 (default : QUOTE_MINIMAL)
```
- 유동인구 데이터 중 성남 데이터만 수집한 예제
```python
import csv
seoung_nam_data = []
header = []
rownum = 0

with open("korea_floating_population_data.csv","r", encoding="cp949") as p_file:
    csv_data = csv.reader(p_file) #csv 객체를 이용해서 csv_data 읽기
    for row in csv_data:	 #읽어온 데이터를  한 줄씩 처리
        if rownum == 0:
            header = row #첫 번째 줄은 데이터 필드로 따로 저장
        location = row[7]
        #“행정구역”필드 데이터 추출, 한글 처리로 유니코드 데이터를 cp949로 변환
        if location.find(u"성남시") != -1:
            seoung_nam_data.append(row)
        #”행정구역” 데이터에 성남시가 들어가 있으면 seoung_nam_data List에 추가
        rownum +=1

with open("seoung_nam_floating_population_data.csv","w", encoding="utf8") as s_p_file:
    writer = csv.writer(s_p_file, delimiter='\t', quotechar="'", quoting=csv.QUOTE_ALL)
    # csv.writer를 사용해서 csv 파일 만들기 delimiter 필드 구분자
    # quotechar는 필드 각 데이터는 묶는 문자, quoting는 묶는 범위
    writer.writerow(header)		 #제목 필드 파일에 쓰기
    for row in seoung_nam_data:
        writer.writerow(row)	 #seoung_nam_data에 있는 정보 list에 쓰기

```