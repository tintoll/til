# Function

## 1. 원칙
- Function은 한가지 일만 해야한다. 
- 함수의 크기는 4줄이어야 한다.(로버트 C 마틴)
- indentation, while, nested if등이 없어야 한다.
- 잘 지어진 서술적인 긴 이름을 갖는 많은/작은 함수들로 유지해야 한다.(이름을 잘지으면 주석이 필요없다.)

## 2. The First Rule of Functions
- 더 이상 작아질 수 없을 만큼 작어야 한다.
- 큰 함수를 보면 클래스로 추출할 생각을 해야한다. (Extract Method Object 기법)
- 클래스는 일련의 변수들에 동작하는 기능의 집합 

## 3. 예제 Fitness를 리팩토링
### 리팩토링 전
<image src="./images/fitness_before.png">

### 리팩토링 후
<image src="./images/fitness_after.png">

### 리팩토링 한 내용
<image src="./images/fitness_refactoring.png">

## 4. 개선된점
- 읽기 쉬워지고 이해하기 쉬워지고 함수가 자신의 의도를 잘 전달함.

## 5. 개선의 원인
1. small
	- 함수의 첫번째 규칙
	- 함수는 작아질 수 있는한 최대한 작아야 함
2. 블록이 적어야함.
	- if, else, while 문장 등의 내부 블록은 한줄이어야 함.	
3. Indenting 이 적어야함.	
	- 함수는 중첩 구조를 갖을 만큼 크면 안됨.
	- 들여쓰기는 한 두단계 정도만

## 6. 함수는 하나의 일만 해야한다?
리팩토링 후 함수의 역할이 3가지 아닌가?	

1. 페이지가 테스트 페이지인지 결정
2. 테스트 페이지인경우 setups,teardowns를 include하고
3. HTML로 페이지를 렌더링

※ 함수의 각 스텝들이 함수 이름이 갖는 추상화 수준보다 한 단계 낮은 것으로만 이뤄졌다면 함수는 한가지 일만 하는 것이다. 


print-prime 리팩토링 : https://github.com/msbaek/print-prime

※ Fitness 예제나 print-prime 예제를 계속 리팩토링 연습을 해보면서 구조를 함수를 더 작게 나누고 추상화를 하는 방법을 익혀야 한다. 
