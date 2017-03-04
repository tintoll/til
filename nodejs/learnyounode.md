##헬로 월드
Node.js 프로그램을 만드려면 .js 확장자의 새 파일을 만들고 JavaScript 코드를 짜기 시작하세요! 프로그램은 node 명령어와 함께 실행하면 됩니다. 예를 들어,

    $ node program.js

전역 process 객체를 통해 커맨드 라인 인자에 접근할 수 있습니다. process객체는 argv라는 모든 커맨드 라인을 가지고 있는 배열 속성을 가지고 있습니다.
process.argv 배열의 첫 번째 요소는 항상 'node'이고 두 번째 요소는 항상 program.js 파일의 경로이므로, 세 번째 요소(인덱스 2)부터 시작합니다.
```
console.log(process.argv)

$ node program.js 1 2 3
[ '/usr/local/bin/node',
  '/Users/tinoll/dev/learnyounode/program.js',
  '1',
  '2',
  '3' ]
```
숫자들을 커맨드 라인 인자로 받아 그 숫자들을의 합을 구하는 예제
```
//예전방식
var sum = 0;
for(var i=2; i < process.argv.length; i++) {
    sum = sum + Number(process.argv[i]);
}
console.log(sum);
//function programming 방식(전역변수를 사용안해도 됨)
process.argv.slice(2) //2번째 값부터 가져옴.
    .reduce((accum, val) => {
        accum[0] = accum[0]+Number(val);
        return accum;
    }, [0]) //return 숫자로 나와서 [0]으로 해줍니다. 1번째 인자가 accum에 들어가값
    .forEach(val => console.log(val));
```
##I/O 시작하기
파일 시스템 연산을 수행하기 위해 Node 핵심 모듈의 fs 모듈이 필요합니다.이런 종류 "전역" 모듈을 불러오려면, 다음의 주문을 사용하세요

     var fs = require('fs')

이제 fs 모듈 전체를 fs라는 변수로 사용할 수 있습니다.
fs 모듈 안의 모든 동기(블록킹) 파일 시스템 메소드는 'Sync'로 끝납니다.파일을 읽으려면, fs.readFileSync('/path/to/file')를 사용할 필요가 있습니다. 이 메소드는 파일의 모든 내용을 담고 있는 Buffer 객체를 반환합니다.

파일을 읽어서 개행의 수를 콘솔로 출력하시오.
```
var fs = require('fs');
var filePath = process.argv[2];
var buffer = fs.readFileSync(filePath);
console.log(filePath); //버퍼로 출력됨
buffer.toString().split('\n').forEach(val => console.log(val))

var result =   buffer.toString().split('\n').length -1;
console.log(result);
```
## 비동기로 I/O 시작하기
```
const fs = require('fs');
const filePath = process.argv[2];
fs.readFile(filePath,(err, buffer) => {
    if(err) return console.log(err);
    var result =   buffer.toString().split('\n').length -1;
    console.log(result);
});
```
##LS 거르기
```
const fs = require('fs');
const path = require('path');
const folderPath = process.argv[2];
const extension = process.argv[3];
fs.readdir(folderPath, (err, fileNames) => {
    var filtered = fileNames.filter((fileName) =>{
        var ext = path.extname(fileName);	//확장자를 가져올수 있다. .txt,.md
        if(ext === '.'+extension){
            return true;
        }else {
            return false;
        }
    }).forEach(val => console.log(val));

});
```





