#Angular2 프로그래밍

### 4. 컴포넌트
#### 4.1 컴포넌트 소개
컴포넌트는 Angular 에서 화면을 구성하는 요소입니다.
컴포넌트가 가져야할 특징
- 컴포넌트는 명세에 따른 배포, 조립 가능한 독립 구성 단위여야 합니다.
```javascript
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
```javascript
import { Component } from '@angular/core';
import { LoggerService } from './logger.service';
```
##### 4.2.2 컴포넌트 장식자 영역
@Component는 컴포넌트 장식자라고 합니다. 컴포넌트 장식자에는 컴포넌트와 관련된 설정 정보를 입력할 수 있습니다.
```javascript
@Component({
	selector : 'intro-component',
    template : '<div>App Hellp</div>',
    //templateUrl :  'intro.component.html',
    styles : ['div{background:blue;}']
    //styleUrls : ['./intro.component.css']
})
```
##### 4.2.2 컴포넌트 클래스 영역
템플릿 데이터 출력과 관련된 로직을 처리합니다.

#### 4.3 컴포넌트 실습
```javascript
//hello.component.ts

import { Component } from '@angular/core';

@Component({
  selector: 'app-hello',
  template: `
    <h1>{{title}}</h1>
    <input type="text" [(ngModel)]="title">
    <textarea [(ngModel)]="title"></textarea>`,
    styles : [`input,textarea{margin-top:20px;display:block;}`]
})
export class HelloComponent {
  title = 'Hello Component';
}

//app.module.ts
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HelloComponent } from './hello.component';

@NgModule({
  //새로만든 컴포넌트를 사용할려명 아래 declarations에 등록해줘야한다.
  declarations: [
    HelloComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [HelloComponent]
})
export class AppModule { }
```
##### 4.4.2 input 장식자를 이용한 값 받기
@Input 장식자는 외부에서 전달된 값을 받기 위해 사용하는 장식자 입니다.
```javascript
//parent-to-child-input.component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'parent-to-child-input',
  template: `<div>부모
              <child-input [name1]="fruit1" [name2]="fruit2()" [name3]="fruit3" [name4]="1+1" [name5]="fruit5" [name6]="fruit6"></child-input>
          </div>`,
  styles: [`div{border: 2px solid #666;padding:10px;width:400px;height:250px;}`]
})
export class ParentToChildInputComponent {
  fruit1="사과";

  fruit2(){
    return "배";
  }

  fruit3 = ['딸기','포도','참외'];

  static fruit5="수박"; // static 형은 곧바로 전달 불가
  get fruit6(){
    return ParentToChildInputComponent.fruit5;
  }
}

//child-input.component.ts
import { Component, Input } from '@angular/core';

@Component({
  selector: 'child-input',
  template: `<div>
        자식<br>
        name1 : {{name1}}<br>
        name2 : {{name2}}<br>
        name3 : <span *ngFor="let i of name3"> {{i}}</span><br>
        name4 : {{name4}}<br>
        name5 : {{name5}}<br>
        name6 : {{name6}}<br>
        </div>`,
  styles: [`div{border: 2px dotted #666;padding:10px;margin-top:5px;width:90%;height:75%;}`]
})

export class ChildInputComponent {
  @Input() name1: string;
  @Input() name2: string;
  @Input() name3: string[];
  @Input() name4: number;
  @Input() name5: string;
  @Input() name6: string;
}
```
##### 4.4.3 inputs 속성을 이용한 값 받기
```javascript
//부모.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-parent-to-child-inputs',
  template: `<div>부모
              <child-inputs [name1]="name1" [name2]="name2"></child-inputs>
          </div>`,
  styles: [`div{border: 2px solid #666;padding:10px;width:400px;height:200px;}`]
})
export class ParentToChildInputsComponent {
  name1 = "사과";
  name2 = "바나나";
}
//자식.ts
import { Component, Input } from '@angular/core';

@Component({
    selector: 'child-inputs',
    template: `<div>자식<br><br>
        inputs 프로퍼티로 받은 값 : {{name1}}, {{name2}}</div>`,
    styles: [`div{border: 2px dotted #666;padding:10px;margin-top:5px;width:90%;height:75%;}`],
    inputs: ['name1', 'name2']
})

export class ChildInputsComponent {
    name1: string;
    name2: string;
}
```
##### 4.4.4 EventEmitter를 이용한 값 전달
자식컴포넌트에서 부모 컴포넌트로 값을 전달 하려면 @Output장식자로 선언한 변수를 EventEmitter로 초기화 합니다. 그리고 부모에게 보낼 시점이 되면 emit()메서드를 사용해 부모로 이벤트를 전달합니다.

```javascript
//자식
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'child',
  template: `
  <div>자식 <button (click)="updateParent(active)">부모에게 이벤트보내기</button></div>`,
  styles: [`div{border: 2px dotted #666;padding:10px;width:90%;height:50%;}`]
})

export class ChildComponent {
  active = false;
  @Output() outputProperty = new EventEmitter<boolean>();

  updateParent(active: boolean) {
    this.active = !active;
    this.outputProperty.emit(this.active);
  }
}
//부모
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `<div>부모<br>
              클릭수 : {{cntClick}}<br>
              자식상태 : {{active}}
              <child (outputProperty)="outputEvent($event)"></child>
          </div>`,
  styles: [`div{border: 2px solid #666;padding:10px;width:400px;height:200px;}`]
})
export class ChildToParentComponent {

  cntClick = 0;
  active = false;

  outputEvent(active: boolean) {
    this.cntClick++;
    this.active = active;//자식으로 부터 받은 값
  }
}
```