## Scouter 설치

1. https://github.com/scouter-project/scouter/releases/에서 scouter-all-2.5.1.tar.gz 파일을 다운로드 받는다.  
2. 압축을 풀면 아래와 같은 폴더가 있습니다. 
  - agent.batch : 사용안해봄.
  - agent.host : 서버의 cpu, 메모리, 디스크정보등을 Collector 서버에 보내줍니다. 
  - agent.java : 모니터링할 Java Program이 실행될때 attach되어 모니터링을 수행합니다.
  - server : Collector 서버 
  - webapp : WebApi를 사용하기 위해서 기동되는 서버 
3. 자세한 방법 : http://gunsdevlog.blogspot.com/2017/07/scouter-apm-1.html



## Scouter 1.8 -> 2.5.1로 업그레이드시 변경할 부분

1. 아래 모듈들 전체 교체 하여 줍니다. 
  - scouter/agent.host <-- JavaApp가 있는 서버에 다 변경 해야함.
  - scouter/agent.java <-- JavaApp가 있는 서버에 다 변경 해야함.
  - scouter/server 
2. /conf에 있는 scouter.conf 파일만 기존에 사용하던 걸로 교체 하여준다.



##  Alert Plug-in 사용 방법

- 이메일이나 슬랙으로 알림을 보내고 싶을때 플러그인을 추가하여 사용할수 있다. 

Email Plug-in : https://github.com/scouter-project/scouter-plugin-server-alert-email
Slack Plug-in : https://github.com/scouter-project/scouter-plugin-server-alert-slack



1. 설치 방법은 디펜저시 라이브러리들을 scouter/server/lib 에 넣어줍니다. 

2. scouter/server/conf/scouter.conf 에서 해당 옵션들을 설정하여주면 됩니다. 

3. scouter/server/startup.sh 하면 된다. 로그에 아래와 같이 되어야 설정이 정상적으로 설치가 된거다.

  ```
  20190108 11:01:48 [BuiltInPlugin]scouter.plugin.server.alert.email.EmailPlugin=>xlog
  20190108 11:01:48 [BuiltInPlugin]scouter.plugin.server.alert.email.EmailPlugin=>alert
  20190108 11:01:48 [BuiltInPlugin]scouter.plugin.server.alert.email.EmailPlugin=>object
  20190108 11:01:48 [BuiltInPlugin]scouter.plugin.server.alert.email.EmailPlugin=>counter
  ```

4. 자신이 원하는 alert을 받고 싶을때는 해당 plug-in을 커스터마이징해서 사용하면 된다. 



##  Scouter 사용자 알림 설정

참조 URL : http://gunsdevlog.blogspot.com/2018/05/scouter-customizable-alert.html

### 기존적으로 제공하는 알림

1. CPU관련 알림 설정 옵션들(agent.host/conf/scouter.conf  추가)
   	cpu_alert_enabled : cpu 알림을 활성화하거나 비활성화 합니다.(기본 : true)
   	cpu_warnig_pct : cpu waring 레벨 알림의 퍼센트입니다.(정수로 설정)
   	cpu_fatal_pct : cpu fatal 레벨 알림의 퍼센트입니다.(정수로 설정)
   	cpu_check_period_ms : 어느 기간동안 cpu를 체크할지를 지정합니다.
   	cpu_waring_history, cpu_fatal_history : 각 레벨에 설정한 값의 초과가 몇회 발생하였을때 알림을 알릴지를 설정합니다.
   	예를 들어 cpu_fatal_pct가 90%이고 cpu_check_period_ms가 5분(300000ms), cpu_fatal_history가 3이라면 CPU가 최근 5분간 90%를 3회 넘는 경우 알림을 발송하게 됩니다.
   	cpu_alert_interval_ms : 설정 시간동안 동일한 알림을 발송하지 않습니다.
2. Disk 사용량 알림 설정 옵션 (agent.host/conf/scouter.conf 추가)
   	disk_alert_enabled=true
   	disk_warning_pct=70
   	disk_fatal_pct=90



## WebApi를 사용하고자 할 경우 

가이드 URL : https://github.com/scouter-project/scouter/blob/master/scouter.document/tech/Web-API-Guide.md



1. soucter/webapps/startup.sh 실행

2. scouter/server/conf/scouter.conf 아래 옵션 활성화

  ```
  net_http_server_enabled=true
  net_http_api_enabled=true
  net_http_port=6180(Default)
  ```

  

##  Slack 으로 알림 받고 싶을때

1. webhookURL 정보를 받는다. (https://zeddios.tistory.com/123)

2. 아래와 같이 전송하면 된다. 

  ```
  curl -s -d "payload={"text":"자신이 쓰고싶은 내용"}" "https://hooks.slack.com/services/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"
  ```

  