#jquery 핵심정리

### 일반노드찾기
| 내용 | 사용법 |
|--------|--------|
|ID로 노드찾기| $('#아이디이름')|
|태그이름으로로 노드찾기| $('태그이름')|
|클래스 이름로 노드찾기| $('.클래스이름')|
|속성으로 노드찾기| $('[속성이름=값]')|

### 찾은 노드 다루기
| 내용 | 사용법 |
|--------|--------|
|찾은 개수 구하기| $대상.length;|
|n번째 노드 접근하기| $대상.eq(index);|
|자바스크립트 DOM객체 접근하기| $대상.get(index); <br /> $대상[index];|
|순차적으로 노드 접근하기| $대상.each(function(index){ <br /> &nbsp;&nbsp;&nbsp;&nbsp;  $(this).eq(index); <br /> });|
|찾은 노드중에서 특정노드만 찾기 | $대상.filter('선택자');|
|찾은 노드의 자손노드 중 특정노드만 찾기 | $대상.find('선택자');|
|인덱스 값 구하기 | $대상.index(); <br /> $(목록).index($대상); $목록.index(대상 DOM 객체); |

### 자식 노드 찾기
| 내용 | 사용법 |
|--------|--------|
|모든 자식 노드 찾기| $대상.children();|
|특정 자식 노드 찾기| $대상.children('선택자');|
|첫번째 자식 노드 찾기| $대상.children().first(); <br /> $대상.children().eq(0); <br /> $대상.children(':first'); <br /> $대상.children(':eq(0)'); |
|마지막 자식 노드 찾기| $대상.children().last(); <br /> $대상.children().eq($대상.children().length-1); <br /> $대상.children(':last'); <br /> $대상.children(':eq('+$대상.children().length-1+)'); <br /> $대상.children().eq(-1); <br /> $대상.children(':eq(-1)'); |
|n번째 자식 노드 찾기| $대상.children(':eq('+index+')'); <br /> $대상.children.eq(index);|

### 부모 노드 찾기
| 내용 | 사용법 |
|--------|--------|
|부모 노드 찾기| $대상.parent();|
|조상 노드 찾기| $대상.parents(['선택자']);|

### 형제 노드 찾기
| 내용 | 사용법 |
|--------|--------|
|이전 형제 노드 찾기| $대상.prev(); <br /> $대상.prevAll(['선택자']);|
|다음 형제 노드 찾기| $대상.next(); <br /> $대상.nextAll(['선택자']);|

### 노드 생성 / 추가
| 내용 | 사용법 |
|--------|--------|
|노드 생성| $('추가노드 DOM 문자열');|
|첫번째 자식 노드로 추가 | $부모노드.prepend($추가노드); <br /> $추가노드.prependTo($부모노드);|
|마지막 자식 노드로 추가 | $부모노드.append($추가노드); <br /> $추가노드.appendTo($부모노드);|
|특정 노드의 이전 위치에 추가 | $추가노드.insertAfter($기존노드); <br /> $기준노드.after($추가노드);|
|특정 노드의 다음 위치에 추가 | $추가노드.insertBefore($기존노드); <br /> $기준노드.before($추가노드);|

### 노드 이동
| 내용 | 사용법 |
|--------|--------|
|첫번째 자식노드로 이동| $부모노드.prepend($이동노드); <br /> $이동노드.prependTo($부모노드); |
|마지막 자식노드로 이동| $부모노드.append($추가노드); <br /> $추가노드.appendTo($부모노드); |
|특정 노드이 이전 노드로 이동| $이동노드.insertBefore($기준노드); <br /> $기준노드.before($이동노드); |
|특정 노드이 다음 노드로 이동|$추가노드.insertAfter($기존노드); <br /> $기준노드.after($추가노드); |

### 노드 제거
| 내용 | 사용법 |
|--------|--------|
|특정노드 제거| $대상.remove(); |
|모든 자식노드 제거| $대상.children().remove(); |

### 노드 내용 읽기 및 변경
| 내용 | 사용법 |
|--------|--------|
|노드 내용을 문자열로 읽기| $대상.html(); <br />  $대상.text();|
|노드 내용을 수정하기| $대상.html('수정할 태그 문자열'); <br />  $대상.text('수정한할 텍스트');|
|노드 내용을 이용해 여러 개의 자식노드 추가하기| $대상.html('추가할 태그 문자열');|
|노드 내용을 이용해 모든 자식노드 제거| $대상.html('');|

### 스타일다루기
| 내용 | 사용법 |
|--------|--------|
|스타일 값 구하기| $대상.css('스타일속성이름'); <br /> $대상.css(['스타일속성이름',....]); |
|스타일 값 설정하기| $대상.css('스타일속성이름', 값); <br /> $대상.css([{스타일속성이름:값,....]); |
|클래스 추가| $대상.addClass('클래스이름1 [클래스이름2 ..]'); |
|클래스 삭제| $대상.removeClass('클래스이름1 [클래스이름2 ..]'); |

### 속성 다루기
| 내용 | 사용법 |
|--------|--------|
|속성 값 구하기| $대상.attr('속성이름'); <br /> $대상.data('data-속성이름'); |
|속성 값 설정하기| $대상.attr('속성이름', '값'); <br /> $대상.data('data-속성이름', '값'); |

### 이벤트 다루기
| 내용 | 사용법 |
|--------|--------|
|일반 이벤트 등록| $대상.on('이벤트이름', 이벤트리스너);|
|단축 이벤트 등록| $대상.단축이벤트(이벤트리스너);|
|등록한 이벤트 제거| $대상.off('click',삭제하고 이벤트 리스너 명); <br /> $대상.off('click') <br /> $대상.off();|
|이벤트 한번만 실행| $대상.one('이벤트이름', 이벤트리스너); |
|기본 행동 취소| 이벤트객체.preventDefault();|
|버블링 멈추기| 이벤트객체.stopPropagation();|
|버블링활용 : 하나의 이벤트| $대상.on('이벤트명','선택자', 이벤트리스너);|

### 위치 및 크기 다루기
| 내용 | 사용법 |
|--------|--------|
|부모 좌표 노드 구하기| $대상.offsetParent();|
|지역 좌표 위치 다루기| //지역좌표위치구하기 <br /> $대상.position().left; <br />$대상.position().top; <br /> //지역좌표위치설정하기 <br /> $대상.css('left', 위치값);  <br />  $대상.css('top', 위치값); <br /> $대상.css({left:위치값,top:위치값});|
|전역 좌표 위치 다루기| //전역좌표위치구하기 <br /> $대상.offset().left; <br /> $대상.offset().top; //전역좌표위치설정하기 <br /> $대상.offset({left:위치값,top:위치값}); |
|요소 크기 다루기| //기본 크기 구하기 <br /> $대상.width(); <br /> $대상.height(); <br /> //기본크기 + padding크기구하기 <br /> $대상.innerWidth(); <br /> $대상.innerHeight(); <br /> //기본크기 + padding+border크기구하기 <br /> $대상.outerWidth(); <br /> $대상.outerHeight(); <br /> //기본크기 + padding+border+margin 크기구하기 <br /> $대상.outerWidth(ture); <br /> $대상.outerHeight(true); <br /> //기본크기 설정하기 <br /> $대상.width(크기값); <br /> $대상.height(크기값); <br /> //기본크기+padding 크기 설정하기 <br /> $대상.innerWidth(크기값); <br /> $대상.innerHeight(크기값);|
|요소 스크롤 위치 다루기| //스크롤위치구하기 <br /> $대상.scrollLeft(); <br /> $대상.scrollTop(); <br />//스크롤위치설정하기 <br /> $대상.scrollLeft(위치값); <br /> $대상.scrollTop(위치값); |

### 문서 위치 및 크기관련기능
| 내용 | 사용법 |
|--------|--------|
|문서 크기 구하기| $(document).width(); <br /> $(document).height();|

### 화면의 위치  및 크기관련기능
| 내용 | 사용법 |
|--------|--------|
|전체 화면  크기 구하기| screen.width; <br /> screen.height; |
|유효한 전체 화면  크기 구하기| screen.availWidth; <br /> screen.availHeight; |
|윈도우 크기 구하기| //기본크기 구하기 <br /> window.innerWidth; <br /> window.innerHeight; <br />  //기본크기+메뉴바+툴바 영역이 포함된 크기 구하기 <br /> $(window).width(); <br /> $(window).height();<br />  //기본크기+메뉴바+툴바+스크롤바 영역이 포함된 크기 구하기 <br /> window.outerWidth; <br /> window.outerHeight;|
|윈도우 크기 설정하기| window.resizeTo(width, height); |
|윈도우 변경 이벤트 처리| $(window).on('resize',function(){}; |
|윈도우 위치 다루기| //위치 값 구하기 <br /> window.screenLeft; <br /> window.screenTop; <br /> //위치 값 설정하기 <br /> window.moveTo(dx, dy); <br /> window.moveBy(dx,dy); <br /> |
|윈도우 스크롤 다루기 | //스크롤 위치 값 구하기 <br /> window.pageXOffset; <br /> window.pageYOffset; <br /> //스크롤 위치 값 설정하기 <br /> window.scrollTo(x, y); <br /> window.scrollBy(x,y); <br /> //스크롤 위치 값 설정하기 <br /> $(window).on('scroll', function(){})|

### 마우스위치 및 크기관련기능
| 내용 | 사용법 |
|--------|--------|
|클릭한 전역 위치|//윈도우 영역을 기준으로 하는 전역위치  <br /> mouseEvent.clientX; <br />  mouseEvent.clientY; <br /> //문서 영역을 기준으로 하는 전역위치  <br /> mouseEvent.pageX; <br />  mouseEvent.pageY; |
|클릭한 지역 위치| var offsetX = mouseEvent.pageX -  $(타켓).offset().left; <br /> var offsetY = mouseEvent.pageY -  $(타켓).offset().top; |


### 애니메이션 일반 효과 다루기
| 내용 | 사용법 |
|--------|--------|
|나타나고 사라지는 효과| $대상.shwo(); <br /> $대상.hide(); |
|페이드인/아웃효과| $대상.fadeIn(); <br /> $대상.fadeOut(); |
|슬라이드 업/다운 효과| $대상.slideUp(); <br /> $대상.slideDown(); |

### 애니메이션 사용자 정의 효과 다루기
| 내용 | 사용법 |
|--------|--------|
|사용자 정의 애니매이션 효과 만들기 | $대상.animate(properties, options); <br /> $대상.animate(properties[,duration][,easing]][,complete]]);|
|사애니매이션 효과 멈추기 | $대상.stop();|




