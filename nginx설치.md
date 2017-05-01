## nginx 설치(우분투)

apt-get 명령어를 통해 설치할 수 있습니다. Ubuntu 14.04 LTS에 포함되어 있는 기본 패키지는 구버전(1.4)이기 때문에 최신 버전 설치를 위해 다음과 같이 repository를 추가해주니다.
```
$ sudo apt-get install python-software-properties
$ sudo add-apt-repository ppa:nginx/stable
$ sudo apt-get update
$ sudo apt-get install nginx
$ ps -ef | grep nginx
$ curl http://localhost
```
기본적으로 /etc/nginx 폴더에 설치 됩니다.


## nginx와 tomcat 연동
/etc/nginx/sites-enabled/default 심볼릭 링크를 삭제하고 새로운 파일로 만들어서 심볼릭 링크를 걸어준다. 그 다음 재시작 해주면 된다.
```
// jwp-basic
server {

        location / {
                proxy_redirect  off;
                proxy_set_header        Host            $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $remote_addr;

                proxy_pass http://127.0.0.1:8080;
                charset utg-8;

                index index.jsp;
        }

}
```
```
$ sudo nginx -s reload  // ngin 재시작
```

## nginx를 이용하여 점검페이지 보여주기

```
scp -i ~/.ssh/my-ssh-key startbootstrap-freelancer-4-dev.zip tintoll@104.196.232.123:. // 서버에 파일 업로드
```

점검페이지에 대한 설정을 한 파일을 만든 다음에 nginx로 실행시켜주고 작업이 다 끝나면 다시 원래 nginx 설정 파일로 변경해준다.

