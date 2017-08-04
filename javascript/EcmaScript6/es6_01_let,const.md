# let
## Block-level scope 지원
ES5에서의 var는 lock-level scope 를 지원하지 않았다. let은 이 부분을 지원합니다.

### Function-level scope 
함수내에서 선언된 변수는 함수 내에서만 유효하며 함수 외부에서는 참조할 수 없다. 즉, 함수 내부에서 선언한 변수는 지역 변수이며 함수 외부에서 선언한 변수는 모두 전역 변수이다.
### Block-level scope 
코드 블럭 내에서 선언된 변수는 코드 블럭 내에서만 유효하며 코드 블럭 외부에서는 참조할 수 없다.  
```javascript
// ES5
console.log(foo); // undefined
var foo = 123;
console.log(foo); // 123
{
    var foo = 456;
}
console.log(foo); // 456
// ES6
let foo = 123;
{
  let foo = 456;
  let bar = 456;
}
console.log(foo); // 123
console.log(bar); // ReferenceError: bar is not defined
```
## 중복 선언 금지
var는 중복 선언이 가능하였으나 let은 중복 선언 시 SyntaxError가 발생한다.
```javascript
var foo = 123;
var foo = 456;  // OK

let bar = 123;
let bar = 456;  // Uncaught SyntaxError: Identifier 'bar' has already been declared
```
## 호이스팅(Hoisting)
ES6에서 도입된 let, const를 포함하여 모든 선언(var, let, const, function, function*, class)을 호이스팅(Hoisting)한다. 호이스팅이란 var 선언문이나 function 선언문 등을 해당 스코프의 선두로 옮기는 것을 말한다.
