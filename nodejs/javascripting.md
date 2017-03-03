##변수
변수는 특정 값을 참조하는 이름입니다. 변수는 `var`와 변수의 이름으로 선언합니다.
```
var example; //선언되었지만 정의되지 않았습니다.
var example = 'some string';
```
##문자열
1) 문자열의 길이 구하기 - length 속성
```
var example = 'some string';
console.log(example.length); //length속성으로 구할수 있다.
```
2) 문자열 교체 - replace() 함수
```
var example = 'this example exists';
example = example.replace('exists', 'is awesome');
console.log(example);
```
##숫자
숫자는 2, 14, 2323같은 정수나 3.14, 1.5같은 실수일 수 있습니다. 문자열과 다르게 따옴표로 감쌀 필요가 없습니다.
```
var example = 123456789;
```
1) 숫자 반올림 - Matn.round() 함수 사용
+, -, *, /, % 같은 익숙한 연사자를 사용해 기본적인 연산을 할 수 있습니다.
더 복잡한 연산은 Math객체를 사용해서 할 수 있습니다.
```
var roundUp = 1.5;
console.log(Math.round(roundUp));
```
2) 숫자를 문자열으로 - toString();
```
var n = 256;
console.log(typeof n);
n = n.toString();
console.log(typeof n);
```
##IF 문
```
var fruit;
fruit = 'orange';
if(fruit.length > 5) {
    console.log('The fruit name has more than five characters');
} else {
    console.log('The fruit name has five characters or less');
}
```
##FOR 문
 변수 i가 0부터 시작해 1씩 증가하는 for 반복문을 만듭니다.
```
var total = 0;
for (var i=0; i < 10; i++) {
    total += i;
}
console.log(total);
```
##배열
```
var pizzaToppings = ['tomato sauce','cheese','pepperoni'];
```
##배열 필터
배열을 조작하는 방법은 여러가지가 있는데 특정값만 가진 배열로 필터링하는 것있는데 .filter() 메소드를 사용할수 있다.
```
var numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
var filtered = numbers.filter(function(val){
    if(val % 2 === 0) {
        //짝수
        return true;
    }else{
        //홀수
        return false;
    }
});
console.log(filtered);
```
##배열 값에 접근하기
```
var food = ['apple', 'pizza', 'pear'];
console.log(food[1]);
```
##배열을 루프하기
```
var pets = ['cat', 'dog', 'rat'];
for(var i=0; i < pets.length; i++){
	pets[i] = pets[i] + 's';
}
console.log(pets);
```
##객체
객체는 배열과 비슷한 값의 목록입니다. 배열과 다른점은 정수 대신 **키**를 사용해 **값**을 확인하는 점입니다.
```
var pizza = {
	toppings: ['cheese', 'sauce', 'pepperoni'],
    crust: 'deep dish',
    serves: 2
};
console.log(pizza);
```
##객체 속성
```
var food = {
	types : 'only pizza'
}
console.log(food['types']);
console.log(food.types);
```
##함수
```
function eat(food) {
	return food + ' tasted really good';
}
console.log(eat('banana'));
```
##함수 인자
함수는 몇개의 인자도 받도록 선언할수 있다. 인자는 어떤 타입도 사용가능합니다.
```
function math(x, y, z){
    return y * z + x;
}
console.log(math(53,61,67));
```
##스코프
스코프는 접근할 수 있는 변수, 객체, 함수의 집합입니다.
JavaScript에는 전역과 지역 두 개의 스코프가 있습니다. 함수 선언 밖에 선언된 변수는 전역 변수이고, 그 값은 프로그램 전체에서 접근하고 수정할 수 있습니다. 함수 선언 안에 선언된 변수는 지역 변수입니다. 지역 변수는 함수가 실행 될 때마다 만들어지고 파괴되고, 함수 밖의 코드에서 접근할 수 없습니다.
```
var a = 4;    // 전연 변수 아래에 있는 함수에서 접근 가능

function foo() {
	var b = a * 3;    // b는 foo 함수 밖에서 접근할 수 없지만, foo 함수 안에서
                      // 선언된 함수에서는 접근 가능
    function bar(c) {
    	var b = 2;  // bar 함수 스코프 안에서 생성한 다른 `b` 변수
                     // 새로 만든 `b` 변수를 변경해도 오래된 `b` 변수에는 영향이 없음
        console.log( a, b, c );
    }
     bar(b * 4);
}
foo(); // 4, 2, 48
```
```
(function(){ // 함수식은 괄호로 둘러 쌈
             // 변수 선언은 여기서
             // 밖에서 접근할 수 없음
})(); // 함수는 즉시 실행됨
```




