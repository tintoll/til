# javascript clean code
**[clean-code-javascript](https://github.com/ryanmcdermott/clean-code-javascript/commit/ac6a51d640064ec4ac6cada7af477df8967dd193) 의 내용중 모르는 내용만 정리했다.[번역본](https://github.com/qkraudghgh/clean-code-javascript-ko)**

### 함수 인자는 2개 이하가 이상적입니다
매개변수의 개수를 제한 하는 것은 함수 테스팅을 쉽게 만들어 주기 때문에 중요합니다. 만약 매개변수가 3개 이상일 경우엔
테스트 해야하는 경우의 수가 많아지고 각기 다른 인수들로 여러 사례들을 테스트 해야합니다.

1개나 2개의 인자를 가지고 있는 것이 가장 이상적인 케이스입니다.
그리고 3개의 인자는 가능한 피해야합니다. 그것보다 더 많다면 통합되어야합니다.
만약 당신이 2개 이상의 인자를 가진 함수를 사용한다면 그 함수에게 너무 많은 역할을 하게 만든 것입니다.
그렇지 않은 경우라면 대부분의 경우 상위 객체는 1개의 인자만으로 충분합니다.

JavaScript를 사용할 때 많은 보일러플레이트 없이 바로 객체를 만들 수 있습니다.
그러므로 당신이 만약 많은 인자들을 사용해야 한다면 객체를 이용할 수 있습니다.

함수가 기대하는 속성을 좀더 명확히 하기 위해서 es6의 비구조화(destructuring) 구문을 사용할 수 있고
이 구문에는 몇가지 장점이 있습니다.

1. 어떤 사람이 그 함수의 시그니쳐(인자의 타입, 반환되는 값의 타입 등)를 볼 때 어떤 속성이 사용되는지
즉시 알 수 있습니다.
2. 또한 비구조화는 함수에 전달된 인수 객체의 지정된 기본타입 값을 복제하며 이는 사이드이펙트가
일어나는 것을 방지합니다. 참고로 인수 객체로부터 비구조화된 객체와 배열은 복제되지 않습니다.
3. Linter를 사용하면 사용하지않는 인자에 대해 경고해주거나 비구조화 없이 코드를 짤 수 없게 할 수 있습니다.



**안좋은 예:**
```javascript
function createMenu(title, body, buttonText, cancellable) {
  // ...
}
```

**좋은 예:**
```javascript
function createMenu({ title, body, buttonText, cancellable }) {
  // ...
}

createMenu({
  title: 'Foo',
  body: 'Bar',
  buttonText: 'Baz',
  cancellable: true
});
```
### 함수는 하나의 행동만 해야합니다
이것은 소프트웨어 엔지니어링에서 가장 중요한 규칙입니다. 함수가 1개 이상의 행동을 한다면 작성하는 것도, 테스트하는 것도, 이해하는 것도 어려워집니다.
당신이 하나의 함수에 하나의 행동을 정의하는 것이 가능해진다면 함수는 좀 더 고치기 쉬워지고 코드들은 읽기 쉬워질 것입니다.
많은 원칙들 중 이것만 알아간다 하더라도 당신은 많은 개발자들을 앞설 수 있습니다.

**안좋은 예:**
```javascript
function emailClients(clients) {
  clients.forEach(client => {
    const clientRecord = database.lookup(client);
    if (clientRecord.isActive()) {
      email(client);
    }
  });
}
```

**좋은 예:**
```javascript
function emailClients(clients) {
  clients
    .filter(isClientActive)
    .forEach(email);
}

function isClientActive(client) {
  const clientRecord = database.lookup(client);
  return clientRecord.isActive();
}
```
### Object.assign을 사용해 기본 객체를 만드세요

**안좋은 예:**
```javascript
const menuConfig = {
  title: null,
  body: 'Bar',
  buttonText: null,
  cancellable: true
};

function createMenu(config) {
  config.title = config.title || 'Foo';
  config.body = config.body || 'Bar';
  config.buttonText = config.buttonText || 'Baz';
  config.cancellable = config.cancellable === undefined ? config.cancellable : true;

}

createMenu(menuConfig);
```

**좋은 예:**
```javascript
const menuConfig = {
  title: 'Order',
  // 유저가 'body' key의 value를 정하지 않았다.
  buttonText: 'Send',
  cancellable: true
};

function createMenu(config) {
  config = Object.assign({
    title: 'Foo',
    body: 'Bar',
    buttonText: 'Baz',
    cancellable: true
  }, config);

  // config는 이제 다음과 동일합니다: {title: "Order", body: "Bar", buttonText: "Send", cancellable: true}
  // ...
}

createMenu(menuConfig);
```

### 매개변수로 플래그를 사용하지 마세요
플래그를 사용하는 것 자체가 그 함수가 한가지 이상의 역할을 하고 있다는 것을 뜻합니다.
boolean 기반으로 함수가 실행되는 코드가 나뉜다면 함수를 분리하세요.

**안좋은 예:**
```javascript
function createFile(name, temp) {
  if (temp) {
    fs.create(`./temp/${name}`);
  } else {
    fs.create(name);
  }
}
```

**좋은 예:**
```javascript
function createFile(name) {
  fs.create(name);
}

function createTempFile(name) {
  createFile(`./temp/${name}`);
}
```
### 사이드 이펙트를 피하세요 (part 1)
함수는 값을 받아서 어떤 일을 하거나 값을 리턴할때 사이드 이팩트를 만들어냅니다.
사이드 이팩트는 파일에 쓰여질 수도 있고, 전역 변수를 수정할 수 있으며, 실수로 모든 돈을 다른 사람에게 보낼 수도 있습니다.

당신은 때때로 프로그램에서 사이드 이팩트를 만들어야 할 때가 있습니다. 아까 들었던 예들 중 하나인 파일작성을 할 때와 같이 말이죠.
이 때 여러분이 해야할 일은 파일 작성을 하는 한 개의 함수를 만드는 일 입니다. 파일을 작성하는 함수나 클래스가
여러개 존재하면 안됩니다. 반드시 하나만 있어야 합니다.

즉, 어떠한 구조체도 없이 객체 사이의 상태를 공유하거나, 무엇이든 쓸 수 있는 변경 가능한 데이터 유형을 사용하거나,
같은 사이드 이펙트를 만들어내는 것을 여러개 만들거나하면 안됩니다. 여러분들이 이러한 것들을 지키며 코드를 작성한다면
대부분의 다른 개발자들보다 행복할 수 있습니다.

**안좋은 예:**
```javascript
// 아래 함수에 의해 참조되는 전역 변수입니다.
// 이 전역 변수를 사용하는 또 하나의 함수가 있다고 생각해보세요. 이제 이 변수는 배열이 될 것이고, 프로그램을 망가뜨리겠죠.
let name = 'Ryan McDermott';

function splitIntoFirstAndLastName() {
  name = name.split(' ');
}

splitIntoFirstAndLastName();

console.log(name); // ['Ryan', 'McDermott'];
```

**좋은 예:**
```javascript
function splitIntoFirstAndLastName(name) {
  return name.split(' ');
}

const name = 'Ryan McDermott';
const newName = splitIntoFirstAndLastName(name);

console.log(name); // 'Ryan McDermott';
console.log(newName); // ['Ryan', 'McDermott'];
```
### 전역 함수를 사용하지 마세요
전역 환경을 사용하는 것은 JavaScript에서 나쁜 관행입니다. 왜냐하면 다른 라이브러리들과의 충돌이 일어날 수 있고,
당신의 API를 쓰는 유저들은 운영환경에서 예외가 발생하기 전까지는 문제를 인지하지 못할 것이기 때문입니다. 예제를 하나 생각해봅시다.
JavaScript의 네이티브 Array 메소드를 확장하여 두 배열 간의 차이를 보여줄 수있는 `diff` 메소드를 사용하려면 어떻게 해야할까요? 
새로운 함수를 `Array.prototype`에 쓸 수도 있지만, 똑같은 일을 시도한 다른 라이브러리와 충돌 할 수 있습니다.
다른 라이브러리가 `diff` 메소드를 사용하여 첫번째 요소와 마지막 요소의 차이점을 찾으면 어떻게 될까요?
이것이 그냥 ES2015/ES6의 classes를 사용해서 전역 `Array`를 상속해버리는 것이 훨씬 더 나은 이유입니다.

**안좋은 예:**
```javascript
Array.prototype.diff = function diff(comparisonArray) {
  const hash = new Set(comparisonArray);
  return this.filter(elem => !hash.has(elem));
};
```

**좋은 예:**
```javascript
class SuperArray extends Array {
  diff(comparisonArray) {
    const hash = new Set(comparisonArray);
    return this.filter(elem => !hash.has(elem));
  }
}
```
### 명령형 프로그래밍보다 함수형 프로그래밍을 지향하세요
JavaScript는 Haskell처럼 함수형 프로그래밍 언어는 아니지만 함수형 프로그래밍처럼 작성할 수 있습니다.
함수형 언어는 더 깔끔하고 테스트하기 쉽습니다. 가능하면 이 방식을 사용하도록 해보세요.

**안좋은 예:**
```javascript
const programmerOutput = [
  {
    name: 'Uncle Bobby',
    linesOfCode: 500
  }, {
    name: 'Suzie Q',
    linesOfCode: 1500
  }, {
    name: 'Jimmy Gosling',
    linesOfCode: 150
  }, {
    name: 'Gracie Hopper',
    linesOfCode: 1000
  }
];

let totalOutput = 0;

for (let i = 0; i < programmerOutput.length; i++) {
  totalOutput += programmerOutput[i].linesOfCode;
}
```

**좋은 예:**
```javascript
const programmerOutput = [
  {
    name: 'Uncle Bobby',
    linesOfCode: 500
  }, {
    name: 'Suzie Q',
    linesOfCode: 1500
  }, {
    name: 'Jimmy Gosling',
    linesOfCode: 150
  }, {
    name: 'Gracie Hopper',
    linesOfCode: 1000
  }
];

const totalOutput = programmerOutput
  .map(programmer => programmer.linesOfCode)
  .reduce((acc, linesOfCode) => acc + linesOfCode, INITIAL_VALUE);
```
### 조건문을 캡슐화 하세요

**안좋은 예:**
```javascript
if (fsm.state === 'fetching' && isEmpty(listNode)) {
  // ...
}
```

**좋은 예:**
```javascript
function shouldShowSpinner(fsm, listNode) {
  return fsm.state === 'fetching' && isEmpty(listNode);
}

if (shouldShowSpinner(fsmInstance, listNodeInstance)) {
  // ...
}
```
### 부정조건문을 사용하지 마세요

**안좋은 예:**
```javascript
function isDOMNodeNotPresent(node) {
  // ...
}

if (!isDOMNodeNotPresent(node)) {
  // ...
}
```

**좋은 예:**
```javascript
function isDOMNodePresent(node) {
  // ...
}

if (isDOMNodePresent(node)) {
  // ...
}
```
### 조건문 작성을 피하세요
조건문 작성을 피하라는 것은 매우 불가능한 일로 보입니다. 이 얘기를 처음 듣는 사람들은 대부분 "`If`문 없이 어떻게 코드를 짜나요?"라고 말합니다.
하지만 다형성을 이용한다면 동일한 작업을 수행할 수 있습니다. 두번째 질문은 보통 "네 좋네요 근데 내가 왜 그렇게 해야하나요?"이죠.
그에 대한 대답은, 앞서 우리가 공부했던 clean code 컨셉에 있습니다. 함수는 단 하나의 일만 수행하여야 합니다.
당신이 함수나 클래스에 `if`문을 쓴다면 그것은 그 함수나 클래스가 한가지 이상의 일을 수행하고 있다고 말하는 것과 같습니다.
기억하세요, 하나의 함수는 딱 하나의 일만 해야합니다.

**안좋은 예:**
```javascript
class Airplane {
  // ...
  getCruisingAltitude() {
    switch (this.type) {
      case '777':
        return this.getMaxAltitude() - this.getPassengerCount();
      case 'Air Force One':
        return this.getMaxAltitude();
      case 'Cessna':
        return this.getMaxAltitude() - this.getFuelExpenditure();
    }
  }
}
```

**좋은 예:**
```javascript
class Airplane {
  // ...
}

class Boeing777 extends Airplane {
  // ...
  getCruisingAltitude() {
    return this.getMaxAltitude() - this.getPassengerCount();
  }
}

class AirForceOne extends Airplane {
  // ...
  getCruisingAltitude() {
    return this.getMaxAltitude();
  }
}

class Cessna extends Airplane {
  // ...
  getCruisingAltitude() {
    return this.getMaxAltitude() - this.getFuelExpenditure();
  }
}
```
### getter와 setter를 사용하세요
JavaScript는 인터페이스와 타입을 가지고있지 않고 이러한 패턴을 적용하기가 힘듭니다.
왜냐하면 `public`이나 `private`같은 키워드가 없기 때문이죠.
그렇기 때문에 getter 및 setter를 사용하여 객체의 데이터에 접근하는 것이 객체의 속성을 찾는 것보다 훨씬 낫습니다.
"왜요?"라고 물으실 수도 있겠습니다. 왜 그런지에 대해서 몇 가지 이유를 두서없이 적어봤습니다.

* 객체의 속성을 얻는 것 이상의 많은 것을 하고싶을 때, 코드에서 모든 접근자를 찾아 바꾸고 할 필요가 없습니다.
* `set`할때 검증로직을 추가하는 것이 코드를 더 간단하게 만듭니다.
* 내부용 API를 캡슐화 할 수 있습니다.
* `getting`과 `setting`할 때 로그를 찾거나 에러처리를 하기 쉽습니다.
* 클래스를 상속해서 디폴트 동작을 재정의할 수 있습니다.
* 서버에서 객체 속성을 받아올 때 lazy load 할 수 있습니다.

**안좋은 예:**
```javascript
class BankAccount {
  constructor() {
    this.balance = 1000;
  }
}

const bankAccount = new BankAccount();

// 신발을 구매할 때...
bankAccount.balance -= 100;
```

**좋은 예:**
```javascript
class BankAccount {
  constructor(balance = 1000) {
	   this._balance = balance;
  }

  // getter/setter를 정의할 때 `get`, `set` 같은 접두사가 필요하지 않습니다.
  set balance(amount) {
      if (this.verifyIfAmountCanBeSetted(amount)) {
        this._balance = amount;
      }
    }
  
  get balance() {
    return this._balance;
  }

  verifyIfAmountCanBeSetted(val) {
    // ...
  }
}

const bankAccount = new BankAccount();
    
// 신발을 구매할 때...
bankAccount.balance -= shoesPrice;

// balance 값을 얻을 때
let balance = bankAccount.balance;
```
## **클래스(Classes)**
### 단일 책임 원칙 (Single Responsibility Prinsiple, SRP)
Clean Code에서 말하길 "클래스를 수정 할 때는 수정 해야하는 이유가 2개 이상 있으면 안됩니다".
이것은 하나의 클래스에 많은 기능을 쑤셔넣는 것이나 다름 없습니다. 마치 비행기를 탈때 가방을 1개만 가지고 탈 수
있을 때 처럼 말이죠. 이 문제는 당신의 클래스가 개념적으로 응집되어 있지 않다는 것이고, 클래스를 바꿔야할 많은 이유가 됩니다.
클래스를 수정하는데 들이는 시간을 줄이는 것은 중요합니다. 왜냐면 하나의 클래스에 너무 많은 기능들이 있고
당신이 이 작은 기능들을 수정할 때 이 코드가 다른 모듈들에 어떠한 영향을 끼치는지 이해하기 어려울 수 있기 때문입니다.

**안좋은 예:**
```javascript
class UserSettings {
  constructor(user) {
    this.user = user;
  }

  changeSettings(settings) {
    if (this.verifyCredentials()) {
      // ...
    }
  }

  verifyCredentials() {
    // ...
  }
}
```

**좋은 예:**
```javascript
class UserAuth {
  constructor(user) {
    this.user = user;
  }

  verifyCredentials() {
    // ...
  }
}


class UserSettings {
  constructor(user) {
    this.user = user;
    this.auth = new UserAuth(user);
  }

  changeSettings(settings) {
    if (this.auth.verifyCredentials()) {
      // ...
    }
  }
}
```
### 개방/폐쇄 원칙 (Open/Closed Principle, OCP)
Bertrand Meyer에 말에 의하면 "소프트웨어 개체(클래스, 모듈, 함수 등)는 확장을 위해 개방적이어야 하며 수정시엔
폐쇄적이어야 합니다." 이것에 의미는 무엇일까요? 이 원리는 기본적으로 사용자가 `.js` 소스 코드 파일을 열어 수동으로 조작하지 않고도
모듈의 기능을 확장하도록 허용해야한다고 말합니다.

**안좋은 예:**
```javascript
class AjaxAdapter extends Adapter {
  constructor() {
    super();
    this.name = 'ajaxAdapter';
  }
}

class NodeAdapter extends Adapter {
  constructor() {
    super();
    this.name = 'nodeAdapter';
  }
}

class HttpRequester {
  constructor(adapter) {
    this.adapter = adapter;
  }

  fetch(url) {
    if (this.adapter.name === 'ajaxAdapter') {
      return makeAjaxCall(url).then((response) => {
        // transform response and return
      });
    } else if (this.adapter.name === 'httpNodeAdapter') {
      return makeHttpCall(url).then((response) => {
        // transform response and return
      });
    }
  }
}

function makeAjaxCall(url) {
  // request and return promise
}

function makeHttpCall(url) {
  // request and return promise
}
```

**좋은 예:**
```javascript
class AjaxAdapter extends Adapter {
  constructor() {
    super();
    this.name = 'ajaxAdapter';
  }

  request(url) {
    // request and return promise
  }
}

class NodeAdapter extends Adapter {
  constructor() {
    super();
    this.name = 'nodeAdapter';
  }

  request(url) {
    // request and return promise
  }
}

class HttpRequester {
  constructor(adapter) {
    this.adapter = adapter;
  }

  fetch(url) {
    return this.adapter.request(url).then((response) => {
      // transform response and return
    });
  }
}
```
### 리스코프 치환 원칙 (Liskov Substitution Principle, LSP)
이것은 매우 간단하지만 강력한 원칙입니다. 리스코프 원칙이란 자료형 S가 자료형 T의 하위형이라면,
프로그램이 갖추어야 할 속성들(정확성, 수행되는 작업 등)의 변경사항 없이, 자료형 T의 객체를 자료형 S의 객체로
교체(치환)할 수 있어야 한다는 원칙입니다.

이 원칙을 예를 들어 설명하자면 당신이 부모 클래스와 자식 클래스를 가지고 있을 때 베이스 클래스와 하위 클래스를
잘못된 결과 없이 서로 교환하여 사용할 수 있습니다. 여전히 이해가 안간다면 정사각형-직사각형 예제를 봅시다.
수학적으로 정사각형은 직사각형이지만 상속을 통해 "is-a" 관계를 사용하여 모델링한다면 문제가 발생합니다.

**안좋은 예:**
```javascript
class Rectangle {
  constructor() {
    this.width = 0;
    this.height = 0;
  }

  setColor(color) {
    // ...
  }

  render(area) {
    // ...
  }

  setWidth(width) {
    this.width = width;
  }

  setHeight(height) {
    this.height = height;
  }

  getArea() {
    return this.width * this.height;
  }
}

class Square extends Rectangle {
  setWidth(width) {
    this.width = width;
    this.height = width;
  }

  setHeight(height) {
    this.width = height;
    this.height = height;
  }
}

function renderLargeRectangles(rectangles) {
  rectangles.forEach((rectangle) => {
    rectangle.setWidth(4);
    rectangle.setHeight(5);
    const area = rectangle.getArea(); // 정사각형일때 25를 리턴합니다. 하지만 20이어야 하는게 맞습니다.
    rectangle.render(area);
  });
}

const rectangles = [new Rectangle(), new Rectangle(), new Square()];
renderLargeRectangles(rectangles);
```

**좋은 예:**
```javascript
class Shape {
  setColor(color) {
    // ...
  }

  render(area) {
    // ...
  }
}

class Rectangle extends Shape {
  constructor(width, height) {
    super();
    this.width = width;
    this.height = height;
  }

  getArea() {
    return this.width * this.height;
  }
}

class Square extends Shape {
  constructor(length) {
    super();
    this.length = length;
  }

  getArea() {
    return this.length * this.length;
  }
}

function renderLargeShapes(shapes) {
  shapes.forEach((shape) => {
      const area = shape.getArea();
      shape.render(area);
    });
  }

const shapes = [new Rectangle(4, 5), new Rectangle(4, 5), new Square(5)];
renderLargeShapes(shapes);
```
### 인터페이스 분리 원칙 (Interface Segregation Principle, ISP)
JavaScript는 인터페이스가 없기 때문에 다른 원칙들처럼 딱 맞게 적용할 수는 없습니다.
그러나, JavaScript에 타입 시스템이 없다 하더라도 중요하고 관계있는 원칙입니다.

ISP에 의하면 "클라이언트는 사용하지 않는 인터페이스에 의존하도록 강요 받으면 안됩니다."
덕 타이핑 때문에 인터페이스는 JavaScript에서는 암시적인 계약일 뿐입니다.

JavaScript에서 이것을 보여주는 가장 좋은 예는 방대한 양의 설정 객체가 필요한 클래스입니다.
클라이언트가 방대한 양의 옵션을 설정하지 않는 것이 좋습니다. 왜냐하면 대부분의 경우 설정들이 전부 다 필요한 건 아니기 때문입니다.
설정을 선택적으로 할 수 있다면 "무거운 인터페이스(fat interface)"를 만드는 것을 방지할 수 있습니다.

**안좋은 예:**
```javascript
class DOMTraverser {
  constructor(settings) {
    this.settings = settings;
    this.setup();
  }

  setup() {
    this.rootNode = this.settings.rootNode;
    this.animationModule.setup();
  }

  traverse() {
    // ...
  }
}

const $ = new DOMTraverser({
  rootNode: document.getElementsByTagName('body'),
  animationModule() {} // 우리는 대부분의 경우 DOM을 탐색할 때 애니메이션이 필요하지 않습니다.
  // ...
});

```

**좋은 예:**
```javascript
class DOMTraverser {
  constructor(settings) {
    this.settings = settings;
    this.options = settings.options;
    this.setup();
  }

  setup() {
    this.rootNode = this.settings.rootNode;
    this.setupOptions();
  }

  setupOptions() {
    if (this.options.animationModule) {
      // ...
    }
  }

  traverse() {
    // ...
  }
}

const $ = new DOMTraverser({
  rootNode: document.getElementsByTagName('body'),
  options: {
    animationModule() {}
  }
});
```
### 의존성 역전 원칙 (Dependency Inversion Principle, DIP)
이 원칙은 두가지 중요한 요소를 가지고 있습니다.

1. 상위 모듈은 하위 모듈에 종속되어서는 안됩니다. 둘 다 추상화에 의존해야 합니다.
2. 추상화는 세부사항에 의존하지 않습니다. 세부사항은 추상화에 의해 달라져야 합니다.

처음에는 이것을 이해하는데 어려울 수 있습니다. 하지만 만약 Angular.js로 작업해본적이 있다면
의존성 주입(Dependency Injection) 형태로 이 원리를 구현한 것을 보았을 것입니다.
DIP는 동일한 개념은 아니지만 상위 모듈이 하위 모듈의 세부사항을 알지 못하게 합니다.
이는 의존성 주입을 통해 달성할 수 있습니다. DI의 장점은 모듈 간의 의존성을 감소시키는 데에 있습니다.
모듈간의 의존성이 높을수록 코드를 리팩토링 하는데 어려워지고 이것은 매우 나쁜 개발 패턴들 중 하나입니다.

앞에서 설명한 것처럼 JavaScript에는 인터페이스가 없으므로 추상화에 의존하는 것은 암시적인 약속입니다.
이말인즉슨, 다른 객체나 클래스에 노출되는 메소드와 속성이 바로 암시적인 약속(추상화)가 된다는 것이죠.
아래 예제에서 암시적인 약속은 `InventoryTracker`에대한 모든 요청 모듈이 `requestItems` 메소드를
가질 것이라는 점입니다.

**안좋은 예:**
```javascript
class InventoryRequester {
  constructor() {
    this.REQ_METHODS = ['HTTP'];
  }

  requestItem(item) {
    // ...
  }
}

class InventoryTracker {
  constructor(items) {
    this.items = items;

    // 안좋은 이유: 특정 요청방법 구현에 대한 의존성을 만들었습니다.
    // requestItems는 한가지 요청방법을 필요로 합니다.
    this.requester = new InventoryRequester();
  }

  requestItems() {
    this.items.forEach(item => {
      this.requester.requestItem(item);
    });
  }
}

const inventoryTracker = new InventoryTracker(['apples', 'bananas']);
inventoryTracker.requestItems();
```

**좋은 예:**
```javascript
class InventoryTracker {
  constructor(items, requester) {
    this.items = items;
    this.requester = requester;
  }

  requestItems() {
    this.items.forEach(item => {
      this.requester.requestItem(item);
    });
  }
}

class InventoryRequesterV1 {
  constructor() {
    this.REQ_METHODS = ['HTTP'];
  }

  requestItem(item) {
    // ...
  }
}

class InventoryRequesterV2 {
  constructor() {
    this.REQ_METHODS = ['WS'];
  }

  requestItem(item) {
    // ...
  }
}

// 의존성을 외부에서 만들어 주입해줌으로써,
// 요청 모듈을 새롭게 만든 웹소켓 사용 모듈로 쉽게 바꿔 끼울 수 있게 되었습니다.
const inventoryTracker = new InventoryTracker(['apples', 'bananas'], new InventoryRequesterV2());
inventoryTracker.requestItems();
```
### 메소드 체이닝을 사용하세요
JavaScript에서 메소드 체이닝은 매우 유용한 패턴이며 jQuery나 Lodash같은 많은 라이브러리에서 이 패턴을 찾아볼 수 있습니다.
이는 코드를 간결하고 이해하기 쉽게 만들어줍니다.
이런 이유들로 메소드 체이닝을 쓰는 것을 권하고, 사용해본뒤 얼마나 코드가 깔끔해졌는지 꼭 확인 해보길 바랍니다.
클래스 함수에서 단순히 모든 함수의 끝에 'this'를 리턴해주는 것으로 클래스 메소드를 추가로 연결할 수 있습니다.

**안좋은 예:**
```javascript
class Car {
  constructor() {
    this.make = 'Honda';
    this.model = 'Accord';
    this.color = 'white';
  }

  setMake(make) {
    this.make = make;
  }

  setModel(model) {
    this.model = model;
  }

  setColor(color) {
    this.color = color;
  }

  save() {
    console.log(this.make, this.model, this.color);
  }
}

const car = new Car();
car.setColor('pink');
car.setMake('Ford');
car.setModel('F-150');
car.save();
```

**좋은 예:**
```javascript
class Car {
  constructor() {
    this.make = 'Honda';
    this.model = 'Accord';
    this.color = 'white';
  }

  setMake(make) {
    this.make = make;
    // 메모: 체이닝을 위해 this를 리턴합니다.
    return this;
  }

  setModel(model) {
    this.model = model;
    // 메모: 체이닝을 위해 this를 리턴합니다.
    return this;
  }

  setColor(color) {
    this.color = color;
    // 메모: 체이닝을 위해 this를 리턴합니다.
    return this;
  }

  save() {
    console.log(this.make, this.model, this.color);
    // 메모: 체이닝을 위해 this를 리턴합니다.
    return this;
  }
}

const car = new Car()
  .setColor('pink')
  .setMake('Ford')
  .setModel('F-150')
  .save();
```
## **테스트(Testing)**
테스트는 배포하는 것보다 중요합니다. 테스트 없이 배포한다는 것은 당신이 짜놓은 코드가 언제든 오작동해도 이상하지 않다는 얘기와 같습니다.
테스트에 얼마나 시간을 투자할지는 당신이 함께 일하는 팀에 달려있지만 Coverage가 100%라는 것은 개발자들에게 높은 자신감과 안도감을 줍니다.
이 말은 훌륭한 테스트 도구를 보유해야 하는 것 뿐만 아니라 [훌륭한 Coverage 도구](http://gotwarlost.github.io/istanbul/)를 사용해야한다는 것을 의미합니다.

테스트 코드를 작성하지 않는다는 것은 그 무엇도 변명이 될 수 없습니다. 여기 [훌륭하고 많은 JavaScript 테스트 프레임워크들](http://jstherightway.org/#testing-tools)
이 있습니다. 당신의 팀의 기호에 맞는 프레임워크를 고르기만 하면 됩니다. 테스트 프레임워크를 골랐다면 이제부터는 팀의 목표를
모든 새로운 기능/모듈을 짤 때 테스트 코드를 작성하는 것으로 하세요. 만약 테스트 주도 개발 방법론(Test Driven Development, TDD)이 당신에게 맞는 방법이라면
그건 훌륭한 개발 방법이 될 수 있습니다. 그러나 중요한 것은 당신이 어떠한 기능을 개발하거나 코드를 리팩토링 할 때
당신이 정한 Coverage 목표를 달성하는 것에 있습니다.

### 테스트 컵셉

**안좋은 예:**
```javascript
const assert = require('assert');

describe('MakeMomentJSGreatAgain', () => {
  it('handles date boundaries', () => {
    let date;

    date = new MakeMomentJSGreatAgain('1/1/2015');
    date.addDays(30);
    assert.equal('1/31/2015', date);

    date = new MakeMomentJSGreatAgain('2/1/2016');
    date.addDays(28);
    assert.equal('02/29/2016', date);

    date = new MakeMomentJSGreatAgain('2/1/2015');
    date.addDays(28);
    assert.equal('03/01/2015', date);
  });
});
```

**좋은 예:**
```javascript
const assert = require('assert');

describe('MakeMomentJSGreatAgain', () => {
  it('handles 30-day months', () => {
    const date = new MakeMomentJSGreatAgain('1/1/2015');
    date.addDays(30);
    assert.equal('1/31/2015', date);
  });

  it('handles leap year', () => {
    const date = new MakeMomentJSGreatAgain('2/1/2016');
    date.addDays(28);
    assert.equal('02/29/2016', date);
  });

  it('handles non-leap year', () => {
    const date = new MakeMomentJSGreatAgain('2/1/2015');
    date.addDays(28);
    assert.equal('03/01/2015', date);
  });
});
```
## **동시성(Concurrency)**
### Callback 대신 Promise를 사용하세요
Callback은 깔끔하지 않습니다. 그리고 엄청나게 많은 중괄호 중첩을 만들어 냅니다.
ES2015/ES6에선 Promise가 내장되어 있습니다. 이걸 쓰세요!

**안좋은 예:**
```javascript
require('request').get('https://en.wikipedia.org/wiki/Robert_Cecil_Martin', (requestErr, response) => {
  if (requestErr) {
    console.error(requestErr);
  } else {
    require('fs').writeFile('article.html', response.body, (writeErr) => {
      if (writeErr) {
        console.error(writeErr);
      } else {
        console.log('File written');
      }
    });
  }
});

```

**좋은 예:**
```javascript
require('request-promise').get('https://en.wikipedia.org/wiki/Robert_Cecil_Martin')
  .then((response) => {
    return require('fs-promise').writeFile('article.html', response);
  })
  .then(() => {
    console.log('File written');
  })
  .catch((err) => {
    console.error(err);
  });

```

### Async/Await은 Promise보다 더욱 깔끔합니다
Promise도 Callback에 비해 정말 깔끔하지만 ES2017/ES8에선 async와 await이 있습니다.
이들은 Callback에대한 더욱 깔끔한 해결책을 줍니다. 오직 필요한 것은 함수앞에 `async`를 붙이는 것 뿐입니다.
그러면 함수를 논리적으로 연결하기위해 더이상 `then`을 쓰지 않아도 됩니다. 
만약 당신이 ES2017/ES8 사용할 수 있다면 이것을 사용하세요!

**안좋은 예:**
```javascript
require('request-promise').get('https://en.wikipedia.org/wiki/Robert_Cecil_Martin')
  .then(response => {
    return require('fs-promise').writeFile('article.html', response);
  })
  .then(() => {
    console.log('File written');
  })
  .catch(err => {
    console.error(err);
  })

```

**좋은 예:**
```javascript
async function getCleanCodeArticle() {
  try {
    const response = await require('request-promise').get('https://en.wikipedia.org/wiki/Robert_Cecil_Martin');
    await require('fs-promise').writeFile('article.html', response);
    console.log('File written');
  } catch(err) {
    console.error(err);
  }
}
```
## **에러 처리(Error Handling)**
에러를 뱉는다는 것은 좋은 것입니다! 즉, 프로그램에서 무언가가 잘못되었을 때 런타임에서 성공적으로 확인되면 
현재 스택에서 함수 실행을 중단하고 (노드에서) 프로세스를 종료하고 스택 추적으로 콘솔에서 사용자에게 
그 이유를 알려줍니다.
 
### 단순히 에러를 확인만 하지마세요
단순히 에러를 확인하는 것만으로 그 에러가 해결되거나 대응 할 수 있게 되는 것은 아닙니다.
`console.log`를 통해 콘솔에 로그를 기록하는 것은 에러 로그를 잃어버리기 쉽기 때문에 좋은 방법이 아닙니다.
만약에 `try/catch`로 어떤 코드를 감쌌다면 그건 당신이 그 코드에 어떤 에러가 날지도 모르기 때문에 감싼 것이므로
그에대한 계획이 있거나 어떠한 장치를 해야합니다.

**안좋은 예:**
```javascript
try {
  functionThatMightThrow();
} catch (error) {
  console.log(error);
}
```

**좋은 예:**
```javascript
try {
  functionThatMightThrow();
} catch (error) {
  // 첫번째 방법은 console.error를 이용하는 것입니다. 이건 console.log보다 조금 더 알아채기 쉽습니다.
  console.error(error);
  // 다른 방법은 유저에게 알리는 방법입니다.
  notifyUserOfError(error);
  // 또 다른 방법은 서비스 자체에 에러를 기록하는 방법입니다.
  reportErrorToService(error);
  // 혹은 그 어떤 방법이 될 수 있습니다.
}
```
### Promise가 실패된 것을 무시하지 마세요
위의 원칙과 같은 이유입니다.

**안좋은 예:**
```javascript
getdata()
.then(data => {
  functionThatMightThrow(data);
})
.catch(error => {
  console.log(error);
});
```

**좋은 예:**
```javascript
getdata()
.then(data => {
  functionThatMightThrow(data);
})
.catch(error => {
  // 첫번째 방법은 console.error를 이용하는 것입니다. 이건 console.log보다 조금 더 알아채기 쉽습니다.
  console.error(error);
  // 다른 방법은 유저에게 알리는 방법입니다.
  notifyUserOfError(error);
  // 또 다른 방법은 서비스 자체에 에러를 기록하는 방법입니다.
  reportErrorToService(error);
  // 혹은 그 어떤 방법이 될 수 있습니다.
});
```