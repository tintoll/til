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
## 모듈 단위로 만들기

```
module.js
//Define Function
//Export Function
const fs= require('fs');
const path = require('path');

module.exports = function(folderPath, getExt, cb){
    fs.readdir(folderPath, (err, fileNames) => {
        if(err) {
            return cb(err, null);
        }
        const filtered = fileNames.filter((fileName) => {
            var ext = path.extname(fileName);
            if(ext === '.'+getExt) {
                return true;
            }else{
                return false;
            }
        });
        return cb(null, filtered);
    })
}
```
```
useModule.js
//import Function
//use Function
const myModule = require('./module.js');
const folderPath = process.argv[2];
const getExt = process.argv[3];
myModule(folderPath, getExt, (err, filteredFileNames) =>{
    if(err) {
        return console.log(err);
    }
    filteredFileNames.forEach((fileName)=>{
        console.log(fileName);
    });
});
```
##HTTP 클라이언트
```
const http = require('http');
const URL = process.argv[2];

http.get(URL,(response) => {
    response.on('data', (buffer) => {
       console.log(buffer.toString());
    });
});
```
##HTTP 모으기
```
const http = require('http');
const URL = process.argv[2];

http.get(URL, (response) =>{
    var str = '';
    response.on('data', (buffer)=>{
        str = str + buffer; //버퍼랑합쳐도 자동으로 toString()합니다
    });
    //이벤트가 끝났을때
    response.on('end', ()=>{
        console.log(str.length);
        console.log(str);
    });
});
```
##ASYNC 다루기
```
const http = require('http');
const urls = process.argv.slice(2); //2번째 이후거 다가져오기 array

var promises = urls.map((url)=>{
    //resolve 성공, reject 실패
    return new Promise(function (resolve, reject) {
       http.get(url,(response) => {
           var str = '';
           response.on('data', (buffer)=>{
               str = str + buffer;
           });
           response.on('end', ()=>{
               resolve(str);    // 리턴이 되는게 resolve임
           });
       }).on('error',(err)=>{
           reject(err);
       });
    });
}); //배열을 반복하면서 새로 리턴되는 값들로 배열을 만든다

Promise.all(promises)   //모든연산이 끝나면
    .then((array)=>{    //연산을 해
        array.forEach((val)=>{
            console.log(val);
        });
    });
```
##시간서버
```
const net = require('net');

const server = net.createServer((socket) => {
    let date = new Date();
    let result = formatTime(date);
    socket.write(result); //값을 보내줄때
    socket.end();
});

server.listen(+process.argv[2]); //+를 사용하면 number타입으로 변환해줌


function formatTime(date){
    let year = date.getFullYear();
    let month = zeroFill(date.getMonth() + 1);     // starts at 0
    let day = zeroFill(date.getDate());      // returns the day of month
    let hour = zeroFill(date.getHours());
    let minute = zeroFill(date.getMinutes());

    let result = `${year}-${month}-${day} ${hour}:${minute}
`;
    return result;
}
function zeroFill(num) {
    return (num < 10 ? '0':'') + num;
}
```
##HTTP 파일 서버
```
const http = require('http');
const fs = require('fs');

const port = process.argv[2];
const filePath = process.argv[3];

const server = http.createServer((request, response)=>{
    let srcScream = fs.createReadStream(filePath);
    srcScream.pipe(response);
});
server.listen(port);
```
##HTTP 대문자로 만드는 서버
through2-map은 데이터의 덩어리를 받아 데이터의 덩어리를 반환하는 한 개의 함수만으로 변환 스트림을 만들 수 있습니다. 이는 스트림용 Array#map()처럼 설계되었습니다.
```
var map = require('through2-map')
inStream.pipe(map(function (chunk) {
	return chunk.toString().split('').reverse().join('');
})).pipe(outStream);
```
```
const http = require('http');
const map = require('through2-map');
const port = process.argv[2];

const server = http.createServer((request, response)=>{

    if(request.method === 'POST') {
        request.pipe(map((chunk)=>{
           return chunk.toString().toUpperCase();
        })).pipe(response);
    }
});
server.listen(port);
```
## HTTP JSON API 
```
const http = require('http');
const url = require('url');
const port = process.argv[2];

const server = http.createServer((request, response)=>{
    const query = url.parse(request.url,true).query;
    const iso = query.iso;

    let time = new Date(iso);
    let result;
    if(/^\/api\/parsetime/.test(request.url)) { //문자열이 /api/parsetime으로 시작 해야함
        result = getTimeJson(time);
    }else if(/^\/api\/unixtime/.test(request.url)) {
        result = getUnixTime(time);
    }
    if(result) {
        response.writeHead(200,{'Content-Type':'application/json'});
        response.end(JSON.stringify(result));
    } else {
        response.writeHead(404);
        response.end();
    }


});
server.listen(port);

function getTimeJson(time){
    return {
        hour : time.getHours(),
        minute : time.getMinutes(),
        second : time.getSeconds()
    }
}
function getUnixTime(time) {
    return {unixtime : time.getTime()}
}
```
