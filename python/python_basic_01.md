# Python

## 기본 자료형
```python
a = 1 # Integer
b = 1.5 # Float
c = "ABC" # String
d = '123' # String
e = True  #Boolean
```

## 연산자
- +,-,\*,/ 같은 기호를 연산자라고 합니다.
- 수식에서 연산자의 역할은 수학에서 연산자와 동일합니다.
- 문자간에 +는 이어주는 기능을 합니다.

```python
print(3*3*3*3*3)  #3을 5번 곱함.
print(3**5) # 3의 5승
print(7%2) # 7나누기 2의 나머지

a = 1;
a += 1; # 증가연산
a -= 1; # 감소연산
```

## 데이터 형변환
```python
a =  10
b = float(a)  # float로 형변환
c = int(10.44)  # int로 형변환

d = '75.4'
e = float(d)  # 문자형도 숫자데이터면 형변환이 됨.

f = str(a)  # string으로 형변환

type(a) # 타입을 확인할수 있는 함수
```

## Console
- **input()** 함수는 콘솔창에서 문자열을 입력받는 함수
```python
print("Enter your name")
somebody = input() # 입력받기
print("Hi", somebody)

temperature = float(input("온도를 입력하세요 : ")) # 입력시 바로 형변환하기
print(temperature)
```

- 섭씨를 화씨로 변환해주는 프로그램을 작성하시오. (공식 : ((9/5) * 섭씨온도) + 32)
```python
temperature = float(input("변환하고 싶은 섭씨 온도를 입력해 주세요 :"))
print(temperature)
print("섭씨온도 : ",temperature)
fahrenheit = ((9/5) * temperature) + 32
print("화씨온도 : ",float(fahrenheit))
```

## List
- 시퀀스 자료형, 여러데이터들의 집합
- Int, Float 같은 다양한 데이터 Type 포함
```python
colors = ['red','blue','green']
print(colors[0]) # red
print(colors[1]) # blue
print(len(colors)) # 3  len()함수는 list의 길이를 반환
```
- 슬라이싱(Slicing) : list의 값들을 잘라서 쓰는 것
- append, extend, insert, remove, del 등 추가와 삭제
```python
colors = ['red','blue','green']
city = ["서울","부산","인천","대구","대전","광주","울산"]
print(city[0:6]), " AND ", a[-9:]) # 0부터 5번째까지, -9부터 끝까지
print(city[:])  # 변수의 처음부터 끝까지
print(city[-50:50]) # 범위를 넘어가면 전체를
print(city[::2]," AND ", city[::-1]) # 2칸씩 이동후 뒤에서 한칸씩 이동

print('blue1' in city) # 존재여부 확인
print(city +  colors) # 두 리스트 합치기

colors.append("white")  # 추가
colors.extend(["black","purple"]) # 리스트에 새로운 리스트 추가
colors.insert(0,"orange") # 0번째 주소에 추가
print(colors)
colors.remove("white") # 삭제
del colors[0]    # 0번째 주소 객체 삭제
print(colors)
```
- 다양한 Data Type이 하나의 List에 들어감
```python
a = ["color", 1, 0.2]
color = ["blue","black"]
a[0] = color # 리스트도 넣을수 있음
```
### 리스트 메모리 저장방식
```python
a = [5,4,3,2,1]
b = [1,2,3,4,5]
b = a   # '='의 의미는 같다가 아닌 메모리 주소에 해당 값을 할당(연결)한다는 의미
print(b) # 5,4,3,2,1
a.sort() # 정렬
print(b) # 1,2,3,4,5
```
### 패킹 과 언패킹
-  패킹 : 한 변수에 여러개의 데이터를 넣는 것
- 언패킹 : 한 변수의 데이터를 각각의 변수로 반환
```python
t = [1,2,3] # 1,2,3을 변수 t 패킹
d,f,g = t # t에 있는 값을 언패킹
print(t,d,f,g)
```

## 조건문
조건문은 **조건을 나타내는 기준**과 **실행해야 할 명령**으로 구성된다. 

### if-else문
```python
print("Tell me your age?")
myage = int(input())
if (myage < 30):
    print("Welcome to the Club")
else:
    print("Oh! No. You are not accepted")
```
### 논리키워드
- 조건문을 표현할때 집합의 논리 키워드를 함께 사용하여 참과 거짓을 판단하기도 함
```python
a = 8
b = 5
if a == 8 and b == 4   # 거짓
if a > 7 or b > 7      # 참
if not(a > 7)          # 거짓  
```
### 학점 계산 예제
```python
if scroe >= 90 : grade = 'A'
elif scroe >= 80 : grade = 'B'
elif scroe >= 70 : grade = 'C'
elif scroe >= 60 : grade = 'D'
else : grade = 'F'
```
### 자신이 다니는 학교를 구하는 예제
```python
print("당신이 태어난 년도를 입력하세요")
birth_year = input()
age = 2017 - int(birth_year) + 1

if age <=26 and age >= 20:
    print("대학생")
elif age < 20 and age >= 17:
    print("고등학교")
elif age < 17 and age >= 14:
    print("중학생")
elif age < 14 and age >= 8:
    print("초등학생")
else:
    print("학생이 아닙니다")
```

## 반복문

### for 문
```python
for looper in [1,2,3,4,5]:
    print(looper)
for looper in range(1,6): # range()는 마지막 숫자 바로 앞까지 리스트를 만들어줌.
    print(looper)
for i in range(1, 10, 2): # 1부터 10까지 2씩증가 시키면서 반복
    print(i)
for i in range(10, 1, -2): # 10부터 1까지 -2씩 감소시키면서 반복
    print(i)
```

### while 문
- for문은 while문으로 변환이 가능함
- 일반 적으로 반복실행횟수를 명확히 알때는 for를 실행횟수가 명확하지 않을때는 while를 사용합니다.

```python
i = 0
while i < 5:
    print(i)
    i = i + 1
```

### 반복의 제어 - break, continue, else
break : 특정 조건에서 반복 종료
```python
for i in range(10):
    if i == 5 : break   # i가 5가되면 반복 종료
    print(i)
print("EOP")
```
continue : 특정 조건에서 남은 반복 명령 skip
```python
for i in range(10):
    if i == 5 : continue   # i가 5가되면 i를 출력하지 않음 
    print(i)
print("EOP")
```
else : 반복조건이 만족하지 않을 경우 반복 종료시 1회 수행
```python
i = 0
while i < 5:
    print(i)
    i = i + 1
else :
    print("EOP")   # break 로 종료된 반복문은 else block이 수행되지 않음.
```

### 구구단 프로그램
```python
print("구구단 몇 단을 계산할까요(1~9)?")
x = 1
while (x is not 0):
    x = int(input())
    if x == 0: continue
    if not(1 <= x <= 9):
        print ("잘못 입력하셨습니다",
            "1부터 9사이 숫자를 입력해주세요")
        continue
    else:
        print ("구구단 " + str(x) +"단을 계산합니다.")
        for i in range(1,10):
            print (str(x) + " X " + str(i) + " = " + str(x*i))
        print ("구구단 몇 단을 계산할까요(1~9)?")
print ("구구단 게임을 종료합니다")
```

### 숫자 찾기 게임
```python
import random			# 난수 발생 함수 호출
guess_number = random.randint(1, 100) 	# 1~100 사이 정수 난수 발생
print ("숫자를 맞춰보세요 (1 ~ 100)")
users_input = int(input())	     	# 사용자 입력을 받음
while (users_input is not guess_number):    	# 사용자 입력과 난수가 같은지 판단
    if users_input > guess_number: 	# 사용자 입력이 클 경우
        print ("숫자가 너무 큽니다")
    else: 		    		# 사용자 입력이 작은 경우
        print ("숫자가 너무 작습니다")
    users_input = int(input())	# 다시 사용자 입력을 받음
else:
    print ("정답입니다. ",
        "입력한 숫자는 ", users_input , "입니다")	# 종료 조건
```

### 사람별 평균을 구하라
```python
kor_score = [49,79,20,100,80]
math_score = [43,59,85,30, 90]
eng_score = [49,79,48,60,100]
midterm_score = [kor_score, math_score, eng_score]

student_score = [0,0,0,0,0]
i = 0
for subject in midterm_score:
    for score in subject:
        student_score[i] += score	# 각 학생마다 개별로 교과 점수를 저장
        i += 1			 	# 학생 index 구분
    i = 0				# 과목이 바뀔 때 학생 인덱스 초기화
else:
    a, b, c, d, e = student_score     	# 학생 별 점수를 unpacking
    student_average = [a/3,b/3,c/3,d/3,e/3] #
    print (student_average)
```
