#Angular2 프로그래밍

### 4. 컴포넌트
#### 4.1 컴포넌트 소개
컴포넌트는 Angular 에서 화면을 구성하는 요소입니다.
컴포넌트가 가져야할 특징
- 컴포넌트는 명세에 따른 배포, 조립 가능한 독립 구성 단위여야 합니다.
```
@Component({
	//컴포넌트 메타데이터 설정
})
export class HelloComponent{
	//컴포넌트 로직 작성
}
```
- 컴포넌트 파일내에는 여러개의 컴포넌트르 두지 않고 하나의 컴포넌트만 선언해야하며, 단일책임원칙을 지키도록 설계되어야 합니다.

※ 쉐도우DOM : 웹페이지가 실행되는 도중 가상으로 생성되는 DOM
#### 4.2 컴포넌트 구조
컴포넌트 내부는 크게 세 영역으로 나뉩니다.
##### 4.2.1 import 영역
다른 모듈들과 구분하기 위해서 Angular 라이브러리 모듈은 @를 붙여서 가져옵니다.정의한 모듈은 상대경로인 ./를 통해 외부 모듈을 호출 합니다.
```
import { Component } from '@angular/core';
import { LoggerService } from './logger.service';
```
##### 4.2.2 컴포넌트 장식자 영역
@Component는 컴포넌트 장식자라고 합니다. 컴포넌트 장식자에는 컴포넌트와 관련된 설정 정보를 입력할 수 있습니다.
```
@Component({
	selector : 'intro-component',
    template : '<div>App Hellp</div>',
    styles : ['div{background:blue;}']
})
```
* selector 속성

