#Git 명령어

##http://marklodato.github.io/visual-git-guide/index-ko.html  내용정리
Git은 3가지의 영역을 가지고 있다.
1. 작업 디렉토리
2. Stage 영역(Index 라고도 부르는)
3. (HEAD)히스토리(저장된 커밋들)

**git add files** : (현재의) files 파일들을 Stage 영역으로 복사합니다.
**git commit** : Stage 영역의 현재 Snapshot을 커밋으로 저장합니다.
**git reset -- files** : 마지막 커밋에서 Stage 영역으로 files 파일들을 복사합니다. git add files 명령에 대한 '되돌리기' 명령입니다. git reset 명령으로 모든 파일을 이전 커밋으로 복원할 수 있습니다.
**git checkout -- files** : Stage 영역에서 작업 디렉토리로 파일을 복사합니다. Stage 영역에 추가하지 않은 변경 내용에 대한 되돌리기 명령입니다.

**git reset -p**나 **git checkout -p** 또는 **git add -p** 명령과 같이 -p 옵션을 사용하여 파일을 지정하지 않고 어떤 파일에 대해 명령을 적용할 지 대화형 명령을 사용할 수 있습니다.

**git commit -a** : 마지막 커밋에 존재하는 모든 파일들에 대하여 git add 명령을 적용한 후 git commit 명령을 적용하는 것과 다르지 않다.
**git commit files** : 마지막 커밋을 기반으로 files의 변경된 내용을 포함하는 새로운 커밋을 하나 만든다. 이 때 files은 Stage 영역에 추가된다.
**git checkout HEAD -- files** : files 을 마지막 커밋으로부터 Stage 영역과 현재 작업 디렉토리에 동시에 복사한다.

**git commit --amend** : 현재 커밋과 부모가 같은 새 커밋을 만듭니다. (실수했던 커밋을 다른 커밋이나 브랜치가 사용하지 않았다면 자동으로 없어질 것입니다.)

**git checkout -b name ** : 새로운 브랜치를 만듭니다.


##http://rogerdudler.github.io/git-guide/index.ko.html  내용정리

**git init** : 새로운 git 저장소가 만들어집니다.
**git clone /로컬/저장소/경로** :로컬 저장소를 복제(clone)할때
**git clone 사용자명@호스트:/원격/저장소/경로**  : 원격 서버의 저장소를 복제할때
**git add <파일 이름>** , **git add * ** : 변경된 파일은 아래 명령어로 (인덱스에) 추가
**git commit -m "이번 확정본에 대한 설명"** : 실제로 변경 내용을 확정할때,이제 변경된 파일이 HEAD에 반영됐어요.하지만, 원격 저장소에는 아직 반영이 안 됐답니다.

**git push origin master** : 변경 내용을 원격 서버로 올립니다.
**git remote add origin <원격 서버 주소>** : 기존에 있던 원격 저장소를 복제한 것이 아니라면, 원격 서버의 주소를 git에게 알려줘야 한다

**git checkout -b feature_x** : "feature_x"라는 이름의 가지를 만들고 갈아탑니다
**git checkout master** :  master 가지로 돌아온다.
**git branch -d feature_x** : 가지를 삭제한다.
**git pull** : 로컬 저장소를 원격 저장소에 맞춰 갱신할때, 원격 저장소의 변경 내용이 로컬 작업 디렉토리에 받아지고(fetch), 병합(merge)된답니다
**git merge <가지 이름>** : 다른 가지에 있는 변경 내용을 현재 가지(예를 들면, master 가지)에 병합할때, 항상 성공하는 게 아니라 가끔 충돌(conflicts)이 일어나기도 한다는 거예요. 이렇게 충돌이 발생하면, git이 알려주는 파일의 충돌 부분을 여러분이 직접 수정해서 병합이 가능하도록 해야 하죠. 충돌을 해결했다면, 아래 명령으로 git에게
 아까의 파일을 병합하라고 알려주세요 **git add <파일 이름>**

**it diff <원래 가지> <비교 대상 가지>** : 변경 내용을 병합하기 전에, 어떻게 바뀌었는지 비교해볼때
**git tag 1.0.0 1b2e1d63ff** : 새로운 꼬리표인 1.0.0을 달 수있다.
**git checkout -- <파일 이름>** : 실수로 무언가 잘못한 경우, 아래 명령으로 로컬의 변경 내용을 되돌릴 수 있어요. 로컬의 변경 내용을 변경 전 상태(HEAD)로 되돌려줘요

** git fetch origin **, **git reset --hard origin/master** : 원격 저장소의 최신 이력을 가져오고, 로컬 master 가지가 저 이력을 가리키도록 할 수 있어요.

**gitk** : git의 내장 GUI
**git config color.ui true** : 콘솔에서 git output을 컬러로 출력하기
**git config format.pretty oneline** : 이력(log)에서 확정본 1개를 딱 한 줄로만 표시하기
**git add -i** : 파일을 추가할 때 대화식으로 추가하기


## 추가 내용

- 개발브랜치(dev-test)가 원격에 까지 올라간상태에서 master로 돌리고 싶을 경우
**git reset --hard master** : 로컬에 있는 현재 dev-test를 master로 reset 해준다.
**git push -f origin dev-test** : 원격에 있는 dev-test를 현재 있는 값으로 강제로 push 해준다.
- master의 커밋 내용을 개발브랜치로 가져올때
**git rebase {가져올 Branch 이름}** : 대상 Branch의 변경 사항을 모두 가져와서 현재 Branch에 반영하려 할 때 다음과 같은 명령어를 사용합니다.
