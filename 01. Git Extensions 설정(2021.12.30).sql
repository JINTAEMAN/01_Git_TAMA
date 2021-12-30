 
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
+--------------------------------------// Git Extensions 설정 //---------------------------------/ 22021.12.30(목) ) /----+ 
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

 
- [Git] Local repository 생성 및 Github 올리기
https://dcsun59.tistory.com/33

1. local repository 생성
1.1. local repository 생성하고자 하는 위치로 이동
D:\91_Git_TAMA

1.2. 우클릭 + Git Bash Here 실행

1.3. Git명령어 창에 입력 : git init

1.4. Git명령어 창에 입력 : git status
- 로컬 저장소 상태 확인

 : On branch master -> master branch 사용
 : No commits yet -> commit한 적 없음(로컬 저장한 적 없음)
 : Nothing to commit -> 로컬 저장소에 파일 없음
 
2. github repository 생성
https://github.com/JINTAEMAN/WRM.git 

3. local - github 연결
3.1. github repository 주소 복사

3.2. git remote add origin [ repository 주소 ]
git remote add origin  https://github.com/JINTAEMAN/WRM.git

3.3. git remote -v
- 로컬저정소와 원격저장소 연결 완료

4. push
4.1. new file add
- 로컬 repository에 새로운 Test 파일을 하나 생성합니다.
- 해당 파일을 로컬 repository에 add해 줍니다.
- git add [파일명 or 폴더명] / git add . (모두 add 할 경우)

4.2. commit
- git commit -m "해당 commit을 설명하는 내용"
해당 commit을 설명하는 내용을 입력하고 commit

4.3. push
- git push origin master

- github email과 password 입력
==================================================================================================================
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 
+----------------------------------------------------// End //----------------------------------------------------+
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

  