##헬로우 월드
문제 : 입력을 받아 대문자로 바꿔 출력하는 함수를 작성하세요.
```
function upperCaser(str){
  return str.toUpperCase();;
}
module.exports = upperCaser;
```
##고차함수
문제 : 첫 번째 인자로 함수, 두 번째 인자로 숫자 num을 받아 넘겨진 함수를 num번 실행하는 함수를 구현하세요.
```
function repeat(operation, num) {
    if (num <= 0) return;
    operation();
    return repeat(operation, --num);
}
module.exports = repeat;
```
##기초 : Map
문제 : 다음 코드의 for 반복문을 Array#map으로 바꾸세요.
```
    function doubleAll(numbers) {
      var result = []
      for (var i = 0; i < numbers.length; i++) {
        result.push(numbers[i] * 2)
      }
      return result
    }
    module.exports = doubleAll
```
```
module.exports = function doubleAll(numbers) {
	return numbers.map(function double(num) {
    	return num * 2
    })
}
```
