# java8

## 왜 함수형 프로그래밍을 배워야 하는가?
- 병렬 프로그래밍을 쉽게 할 수 있는 장점이 있다.

### First Class Citizen(1급객체)
1. 변수나 데이타에 할당 할 수 있어야 한다.
2. 객체의 인자로 넘길 수 있어야 한다.
3. 객체의 리턴값으로 리턴 할수 있어야 한다.

참조 URL :
https://medium.com/@lazysoul/functional-programming-%EC%97%90%EC%84%9C-1%EA%B8%89-%EA%B0%9D%EC%B2%B4%EB%9E%80-ba1aeb048059

### First Class Function
- 다른언어의 Function에 해당하는 Java의 Method는 1급객체가 아니다. Java에서는 First Class Citizen을 지원을 못하고 First Class Function을 지원한다. java8부터 지원.

### 람다 표현식(Lambda Expression)
- 람다의 핵심은 지울수 있는건 모두 지우자는 것이다. 모든걸 컴파일러의 추론에 의지하고 코드로 표현하는건 다 없애버려 코드를 간결하게 만드는 것이다.


### Function
#### FunctionalInterface
1. Function : 하나의 인자와 리턴타입을 가지며 그걸 제네릭으로 지정해줄수있다. 그래서 타입파라미터(Type Parameter)가 2개다.
```java
		Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        // 람다 표현식으로 변경하면
        Function<String, Integer> toInt2 = (s) -> Integer.parseInt(s);

        System.out.println(toInt.apply("100"));
        System.out.println(toInt2.apply("100"));
```

2. Consumer : 리턴을 하지않고(void), 인자를 받는 메서드를 갖고있다. 인자를 받아 소모한다는 뜻으로 인터페이스 명칭을 이해하면 될듯 하다.
```java
		Consumer<String> greetings = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello "+s);
            }
        };
        // 람다 표현식으로 변경하면
        Consumer<String> greetings2 = (s) -> System.out.println("Hi "+s);
        greetings.accept("World!");
        greetings2.accept("tintoll");
```

3. Predicate : 하나의 인자와 리턴타입을 가진다. Function과 비슷해보이지만 리턴타입을 지정하는 타입파라미터가 안보인다. 반환타입은 boolean 타입으로 고정되어있다.
```java
		Predicate<Integer> isPositive = i -> i > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(-1));
        final List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        final List<Integer> positiveNumbers = new ArrayList<>();
        for (final Integer num : numbers) {
            if (isPositive.test(num)) {
                positiveNumbers.add(num);
            }
        }
        System.out.println("positive integers: " + positiveNumbers);
        final Predicate<Integer> lessThan3 = i -> i < 3;
        final List<Integer> numbersLessThan3 = new ArrayList<>();
        for (final Integer num : numbers) {
            if (lessThan3.test(num)) {
                numbersLessThan3.add(num);
            }
        }
        System.out.println("less than 3: " + numbersLessThan3);
        System.out.println("positive integers: " + filter(numbers, isPositive));
        System.out.println("less than 3: " + filter(numbers, lessThan3));
```

4. Supplier : 인자는 받지않으며 리턴타입만 존재하는 메서드를 갖고있다. 순수함수에서 결과를 바꾸는건 오직 인풋(input) 뿐이다. 그런데 인풋이 없다는건 내부에서 랜덤함수같은것을 쓰는게 아닌이상 항상 같은 것을 리턴하는 메서드라는걸 알 수 있다.
```java
		Supplier<String> valueSupplier = () -> "Hello";
        System.out.println(valueSupplier.get());

        System.out.println("\nSupplier 사용하지 않고 시간 오래 걸리는 메소드 호출");
        callingExpensiveMethodWithoutSupplier();

        System.out.println("\nSupplier 사용해서 시간 오래 걸리는 메소드 호출");
        callingExpensiveMethodWithSupplier();
```

- 더많은 FunctionInterface는 URL 참조 : http://multifrontgarden.tistory.com/125

#### Custom FunctionalInterface 만들기

```java
@FunctionalInterface  // 명시를 해줘야 Function이라는 것을 알수 있고 두개 이상의 메서드가 올경우 에러라고 표시해줌.
interface Function3<T1, T2, T3, R> {
  R apply(T1 t1, T2 t2, T3 t3);

//  void print(int i); // 하나 이상의 메서드를 가지면 에러남
}

@FunctionalInterface
interface BigDecimalToCurrency {
  String toCurrency(BigDecimal value);
}

/**
 * Generic method를 가지는 FunctionalInterface는
 * Lambda Expression을 사용할수 없습니다.
 */
@FunctionalInterface
interface InvalidFunctionalInterface {
  <T> String mkString(T value);
}
```

### Stream
- 스트림은 자바8에 추가된 API로 자바의 자료구조들을 선언적으로 다루는 역할을 한다. 함수형 인터페이스 들이 엄청나게 등장을 하니 스트림을 다룬다면 외우기 싫어도 외워질수밖에 없을 것이다.
자료구조들을 다루는 역할을 하기때문에 스트림은 배열이나 List처럼 생성한 다음 요소를 추가하는 형태가 아니다. 정적 팩토리 메서드(Static Factory Method)를 이용해 자료구조로부터 생성한다.
```java

```
http://multifrontgarden.tistory.com/128

- Stream : lazy collection builder

#### 중간연산
#### 최종연산

### Closure

### High Order Function

### Method Reference


