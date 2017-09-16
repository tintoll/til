# OOP
- 객체 : 실생활에서 일종의 물건 속성(Attribute)와 행동(Action)을 가짐
- OOP는 이러한 객체 개념을 프로그램으로 표현. 속성은 variable, 행동은 함수로 표현됨
- OOP는 설계도에 해동하는 클래스(class)와 실제 구현제인 인스턴스(instance)로 나눔

- 축구선수 정보를 Class로 구현하기
```python
class SoccerPlayer(object):
    # 객체 초기화 예약함수
    def __init__(self, name, position, back_number):
        self.name = name
        self.position = position
        self.back_number = back_number

    def change_back_number(self, new_number):
        print("선수의 등번호를 변경합니다 : From %d to %d" % (self.back_number, new_number))
        self.back_number = new_number

    def __str__(self):
        return "Hello, My name is %s. I play in %s in center " % (self.name, self.position)

jinhyun = SoccerPlayer("Jinhyun", "MF", 10)
print(jinhyun)

print("현재 선수의 등번호는 :", jinhyun.back_number)
jinhyun.change_back_number(5)
print("현재 선수의 등번호는 :", jinhyun.back_number)
```
## 알아둘것
1. 변수와 Class명 함수명은 짓는 방식이 존재한다.
    - snake_case : 띄워쓰기 부분에 "_"를 추가(함수/변수명에 사용)
    - CamelCase : 띄워쓰기부분에 대문자(Class명에 사용)
2. Attrubute 추가는 _init_, self와 함께 해준다.   
3. __의 의 미는 특수한 예약 함수나 변수에 사용된다.
    - _main_, _add_, _str_
4. Function추가시 기존 함수와 같으나, 반드시 **self를 추가** 해야만 class함수로 인정된.

## OOP 특징
### Inheritance(상속)
- 부모클래스로 부터 속성과 Method를 물려받은 자식 클래스를 생성하는 것 
```python
class Person(object):
    def __init__(self, name, age):
        self.name = name
        self.age = age

class Korean(Person):
    pass

first_korean = Korean("Sungchul", 35)
print(first_korean.name)
```
```python
class Person:  # 부모 클래스 Person 선언
    def __init__(self, name, age, gender):
        self.name = name  # 속성값 지정, 해당 변수가 클래스의 attribute임을 명확히하기 위해 self를 붙임
        self.age = age
        self.gender = gender

    def about_me(self):  # Method 선언
        print("저의 이름은 ", self.name, "이구요, 제 나이는 ", str(self.age), "살 입니다.")


class Employee(Person):  # 부모 클래스 Person으로 부터 상속
    def __init__(self, name, age, gender, salary, hire_date):
        super().__init__(name, age, gender)  # 부모객체 사용
        self.salary = salary
        self.hire_date = hire_date  # 속성값 추가

    def do_work(self):  # 새로운 메서드 추가
        print("열심히 일을 합니다.")

    def about_me(self):  # 부모 클래스 함수 재정의
        super().about_me()  # 부모 클래스 함수 사용
        print("제 급여는 ", self.salary, "원 이구요, 제 입사일은 ", self.hire_date, " 입니다.")


myPerson = Person("John", 34, "Male")
myEmployee = Employee("Daeho", 34, "Male", 300000, "2012/03/01")
myPerson.about_me()
myEmployee.about_me()

```
### Polymorphism(다형성)
- 같은 이름 메소드의 내부 로직을 다르게 작성
- Dynamic Typing 특성으로 인해 파이썬에서는 같은 부모클래스의 상속에서 주로 발생함
```python
class Animal:
    def __init__(self, name):  # Constructor of the class
        self.name = name

    def talk(self):  # Abstract method, defined by convention only
        raise NotImplementedError("Subclass must implement abstract method")


class Cat(Animal):
    def talk(self):
        return 'Meow!'


class Dog(Animal):
    def talk(self):
        return 'Woof! Woof!'


animals = [Cat('Missy'),
           Cat('Mr. Mistoffelees'),
           Dog('Lassie')]

for animal in animals:
    print(animal.name + ': ' + animal.talk())

```

### Visibility(가시성)
- 객체의 정보를 볼 수 있는 레벨을 조절하는 것
- 누구나 객체 안에 모든 변수를 볼 필요가 없음
    1. 객체를 사용하는 사용자가 임의로 정보 수정
    2. 필요 없는 정보에는 접근 할 필요가 없음
    3. 만약 제품으로 판매한다면? 소스의 보호

#### 예제1
- Product 객체를 Inventory객체에 추가
- Inventory에는 오직 Product객체만 들어감
- Inventory에 Product가 몇개인지 확인이 필요
- Invertory에 Product items는 직접 접근이 불가
```python
class Product(object):
    pass

class Inventory(object):
    def __init__(self):
        self.__items = []

    def add_new_item(self, product):
        if type(product) == Product:
            self.__items.append(product)
            print("new item added")
        else:
            raise ValueError("Invalid Item")

    def get_number_of_items(self):
        return len(self.__items)

my_inventory = Inventory()
my_inventory.add_new_item(Product())
my_inventory.add_new_item(Product())
print(my_inventory.get_number_of_items())
print(my_inventory.__items)
my_inventory.add_new_item(object)
```  
#### 예제2
- Product 객체를 Inventory객체에 추가
- Inventory에는 오직 Product객체만 들어감
- Inventory에 Product가 몇개인지 확인이 필요
- Invertory에 Product items 접근 허용
```python
class Product(object):
    pass

class Inventory(object):
    def __init__(self):
        self.__items = [] # private 변수로 선언 남들이 접근 못함

    def add_new_item(self, product):
        if type(product) == Product:
            self.__items.append(product)
            print("new item added")
        else:
            raise ValueError("Invalid Item")

    def get_number_of_items(self):
        return len(self.__items)

    # property decorator 숨겨진 변수를 반환하게 해줌 - 함수를 변수처럼 호출할수 있음.
    @property
    def items(self):
        return self.__items


my_inventory = Inventory()
my_inventory.add_new_item(Product())
my_inventory.add_new_item(Product())
print(my_inventory.get_number_of_items())

items = my_inventory.items
items.append(Product())
print(my_inventory.get_number_of_items())

```

 ### Encapsulation
 - 캡슐화 또는 정보 은닉
 - Class를 설계할 때, 클래스 간 간섭/정보공유의 최소화
 - 캡슐을 던지듯, 인터페이스만 알아서 써야함

