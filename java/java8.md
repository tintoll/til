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
int[] numberArr = {1, 2, 3, 4, 5, 6};
List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6);
Set<Integer> numberSet = new HashSet<>(numberList);

Arrays.stream(numberArr);
Stream.of(1, 2, 3, 4, 5, 6);
numberList.stream();
numberSet.stream();
```

#### stream vs collection
컬렉션은 자료구조들의 구현체고 스트림은 자료구조들을 다루는 역할을 한다. 컬렉션은 데이터를 담는 것이 제역할이기때문에 제공하는 API들도 데이터를 넣었다 빼는것들이 대부분이다.

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.get(0);
numbers.remove(1);

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evenList = new ArrayList<>();

for(int number : numbers){
    if(number % 2 == 0){
        evenList.add(number);
    }
}
System.out.println(evenList);
```
이에반해 스트림은 연속된 자료들을 다루고 연산하는 API를 지원한다. 컬렉션과는 다르게 요소를 추가한다거나 삭제하는건 불가능하다. 연산을 지원하기때문에 짝수만 걸러내는것도 매우 명시적이고 선언형으로 짤 수 있다.

```java
List<Integer> evenList = Stream.iterate(1, n -> n+1)
        .limit(6)
        .filter(number -> number % 2 == 0)
        .collect(toList());

System.out.println(evenList);
```

#### 중간 연산 & 최종연산
스트림을 사용한 코드는 계속해서 도트(Dot) 연산자로 메서드 체이닝(Method Chaining)을 일으킨다. 초보개발자의 경우 도트가 연속해서 나오면 코드를 이해하기 어려워하는 경우도 많은데 저런 경우는 한가지만 명확히 생각하면 된다. 메서드 체이닝 중간에 있는 메서드는 절대 void 형태가 아니며 메서드가 반환하는 어떤 객체가 도트 뒤에 나오는 메서드를 갖고있다고 보면 되는것이다.

```java
/*  limit() 메서드는 무언가 객체를 반환할것이다.
    void타입의 메서드라면 filter() 메서드를 호출할 수가 없기때문이다.
    limit()이 반환하는 객체는 filter() 메서드를 갖고있는 객체다.
    그리고 filter() 메서드역시 collect() 라는 메서드를 갖고있는 객체를 반환한다.
 */
.limit(6)
.filter(number -> number % 2 == 0)
.collect(toList());
```
limit(), filter(), collect() 같은 메서드들은 전부 스트림에서 제공하는 메서들이다. 결국 스트림 메서드들은 전부 연속해서 스트림을 반환하고 있기때문에 저런 코드가 가능한것이다.

마지막으로 collect() 메서드는 스트림이 아니라 List 타입을 반환하기때문에 List 변수에 무언가 값을 저장하고있는걸 알 수 있는데 스트림 API는 이런식으로 스트림을 반환하는 메서드와 스트림이 아닌 값을 반환하는 메서드로 나뉜다.

스트림을 반환하여 메서드 체이닝의 근간이 되게하는 메서드들을 중간 연산 메서드라 부르고 스트림이 아닌 값을 반환하여 메서드 체이닝을 끊는 메서드를 최종 연산 메서드라 부른다.

##### 중간연산 대표 API
-Stream<R> map(Function<A, R>)
-Stream<T> filter(Predicate<T>)
-Stream<T> peek(Consumer<T>)

##### 최종연산  대표 API
-R collect(Collector)
-void forEach(Consumer<T>)
-Optional<T> reduce(BinaryOperator<T>)
-boolean allMatch(Predicate<T>)
-boolean anyMath(Predicate<T>)


#### Lazy & ShortCircuit
스트림은 게으르(Lazy)다. 결론부터 말하자면 최종연산이 존재하지않으면 중간연산은 실행되지 않는다. 위에서부터 말했지만 스트림은 어디까지나 연산을 위한 객체로 그 자체로 자료구조의 역할을 하지 않는다. 때문에 최종연산이 존재하지않는 스트림은 그 의미가 없다고 볼 수 있다. 스트림이 게으르다는걸 확인할 수 있는 샘플코드는 어렵지않게 짤 수 있다.

```java
Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
Stream<Integer> s = stream.peek(System.out::println);
// peek()메서드는 forEach()처럼 스트림의 요소를 순회하며 소비(Consumer<T>)하는 메서드이다. forEach()와 한가지 다른점은 중간 연산이라는점이다. 때문에 해당코드를 실행하면 우리가 기대하는 출력문은 출력되지 않는다.

s.collect(toList());
// 최종연산이 호출될때 비로소 중간연산들도 실행되는걸 볼 수 있다.
```

Lazy외에도 스트림은 여러 최적화기법들을 도입했는데 그 중하나가 Short Circuit이다. 이건 뭔말일꼬.. 할 수 있는데 &&(And), ||(Or) 연산을 생각하면 이해가 쉽다. &&연산은 좌항과 우항 모두 true일때 true을 반환하는 연산인데 좌항이 false면 우항은 쳐다도보지않는다.
```java
Object obj = null;

boolean b = 1 == 2 && obj.toString().equals(123);
// obj가 null이기때문에 거기다가 toString()를 호출하면 NullPointerException이 발생해야한다. 하지만 이미 좌항이 false이기때문에 우항은 실행도 하지않게되고, 그리하여 아무런 예외없이 코드는 실행된다.
```

### Closure
참조 URL - http://egloos.zum.com/ryukato/v/1160506
### High Order Function
다음 중 한 가지 이상을 만족하는 함수
- 파라미터로 함수를 받을 수 있다.
- 결과값으로 함수를 리턴한다.

```java
public class HigherOrderFunctionExamples {
	public static void main(String[] args) {
		Function<Function<Integer, String>, String> f1 = g -> g.apply(10);
		System.out.println(
				f1.apply(i -> "#" + i) // "#10"
		); 
		Function<Integer, Function<Integer, Integer>> f2 = i -> (i2 -> i + i2);
		System.out.println(
				f2.apply(1).apply(9) // 10
		);
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		List<String> mappedList = map(list, i -> "#"+i);
		System.out.println(mappedList); //[#1, #2, #3, #4, #5]
		System.out.println(
			list.stream()
				.map(i -> "#"+i)
				.collect(toList())
		);
		Function<Integer, Function<Integer, Function<Integer, Integer>>> f3 = i1 -> i2 -> i3 -> i1 + i2 + i3;
		System.out.println(f3.apply(1).apply(2).apply(3)); //6
	}
	private static <T, R> List<R> map(List<T> list, Function<T, R> mapper){
		List<R> result = new ArrayList<R>();
		for(T t : list){
			result.add(mapper.apply(t));
		}
		return result;
	}
}
```

### Method Reference
참조 URL - https://imcts.github.io/java-method-reference/

