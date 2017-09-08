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
