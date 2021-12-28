 
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
+--------------------------------------// Git Extensions 설정 방법 //-------------------------------/ 2021.12.08(수) /----+ 
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

 
- Git Extensions 설정 방법[2021.12.08(수)] @@@
01. 저장소 생성(웹(https://github.com)에서)
https://github.com/JINTAEMAN/01_Git_TAMA   ----> JINTAEMAN/22tadhtlqenf!
2) HTTPS: https://github.com/JINTAEMAN/01_Git_TAMA.git
----------------------------------------------------------------------------------------------------

02. 저장소 Clone(Clone repository)  @@@
1. 로컬 탐색기에서 D:\91_Git_TAMA 폴더: 마우스 우 클릭 ==> GitExt Clone
2. Clone(새창)
 1) Repositories to clone: https://github.com/JINTAEMAN/01_Git_TAMA.git
 2) Destination: D:\91_Git_TAMA
 3) Subdirectory to create:  01_TEST
 4) Branch:  (default: remote HEAD) 
 5) repository type: Personal repository --> Clone: 클릭
==> .git 생성됨(D:\91_Git_TAMA\01_TEST)	 ===> ■■■
3. D:\91_Git_TAMA\01_TEST 폴더: 마우스 우 클릭 ==> GitExt Open repository
 
03. 저장소 수정 테스트 @@@
1. D:\91_Git_TAMA\01_TEST 폴더에서 01. Git Extensions 설정 방법.sql 파일 생성
2. Git Extensions 화면에서 Tab(Commit(1))로 표시됨  ==> Commit(1)): 클릭
3. Commit 화면(새창)
 1)  01. Git Extensions 설정 방법.sql 파일 ==> Stage 버튼 클릭
 2) Comment messsage: [DEV] [SI-메뉴얼게시판] [공통] [진태만] AS 게시판 관련 수정
 3) Commit: 클릭
 4) Process(새창) 
  Done   ==> OK: 클릭
4. ▶ main [DEV] [SI-메뉴얼게시판] [공통] [진태만] Git Extensions 설정 방법  ===> 표시됨 ■■■ 
----------------------------------------------------------------------------------------------------

5. Git Extensions 화면에서 Tab(▲) : 클릭
 1) Push(새창) 
   가. Remote: origin
   나. Branch to push: main to amin    ==> Push: 클릭
   다. Process(새창) 
    Done  ==> OK: 클릭 
 2) Push 확인
   가. https://github.com/JINTAEMAN/01_Git_TAMA==> JINTAEMAN/01_Git_TAMA  
JINTAEMAN 			[DEV] [SI-메뉴얼게시판] [공통] [진태만] Git Extensions 설정 방법 	f0f04ba		1 minute ago	  4 commits    ===> 표시됨 ■■■
01. Git Extensions 설정 방법.sql	[DEV] [SI-메뉴얼게시판] [공통] [진태만] Git Extensions 설정 방법_02	2 minutes ago 	  ==> 개발 서버 @@
01. Git Extensions 설정 방법.sql	[TST] [SI-메뉴얼게시판] [공통] [진태만] Git Extensions 설정 방법_02	1 hours ago    ==> 통시 서버 @@
01. Git Extensions 설정 방법.sql	[STG] [SI-메뉴얼게시판] [공통] [진태만] Git Extensions 설정 방법_02	2 hours ago    ==> 스테이지 서버 @@
==================================================================================================================
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 
+----------------------------------------------------// End //----------------------------------------------------+
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

 

- Git 강좌 - 01.Git 시작하기[영욱 스튜디오]
https://www.youtube.com/watch?v=JZJQ4_8XoPM&list=PLHF1wYTaCuixewA1hAn8u6hzx5mNenAGM&index=1
======================================================================================================================

- 시간만에 끝내는 깃허브(Github) 입문[개발자의 품격]
https://youtu.be/-27WScuoKQs

- 우리는 Github를 이렇게 사용한다.
https://medium.com/returnvalues/%EC%9A%B0%EB%A6%AC%EB%8A%94-github%EB%A5%BC-%EC%9D%B4%EB%A0%87%EA%B2%8C-%EC%82%AC%EC%9A%A9%ED%95%9C%EB%8B%A4-83789075e5b6
======================================================================================================================

- 지옥에서 온 Git - 수업소개[생활코딩]
https://youtu.be/hFJZwOfme6w

> cd D:\91_Git_TAMA\WRM
> git init 
.git 	---> 파일 생성됨 @@@

> git init 

> git status     ---> git 상태 확인 @@@
> echo hello world! > test_01.txt    ---> test_01.txt  파일이 생성됨 @@@
> vim test_01.txt 		---> test_01.txt  파일 수정 @@@

> echo hello world! > test_02.txt	 ---> test_02.txt  파일이 생성됨 @@@

test_01.txt  	--> untracked file(새로 만들어진 파일: 버전 관리되지 않는 파일) @@@
tracked file(Git이 알고 있는 파일: 버전 관리되고 있는 파일):	--> unmodified, modified @@@

> git add test_01.txt		---> 수정된 파일을 staging area로 옮기기 @@@
test_01.txt	  --> tracked file: staging area로 옮겨진 파일 @@@
 
> git status
Changes to be commited:
 modified test_01.txt  	 --> 초록색으로 보임 @@@

Changes not staged for commit:
 modified test_01.txt   	--> 빨간색으로 보임 @@@

> git config --global user.name tamario						--> 개인 성명 셋팅 @@@
> git config --global user.email tamario@naver.com	--> 개인 이메일 셋팅 @@@
---------------------------------------------------------------------------------------

> vim test_03.txt 		---> test_03.txt 파일 수정 @@@
> git commit -a   --> add 처리 안하고 코멘트 작성하고 커밋 처리 @@@
1 fille changee, 1 insertion(+)
---------------------------------------------------------------------------------------

> git commit    --> 커밋 처리: repository 서버로 올라가감 @@@
> git commit -am "comment_작성"    --> add 처리 안하고 코멘트 작성하고 커밋 처리 @@@
> git commit -help		  --> commit 도움말 보기 @@@

> git log		-- Git 로그 확인 @@@
> git log -p	-- Git 로그 차이점 확인 @@@
> git diff 5ce6b234..441c4234234	-- Git 로그 commit ID와 commit ID 차이점 확인 @@@

> git reset --hard 3ce6b234		-- commit ID(3ce6b234)로 돌아감[공유 전[로컬 서버]에 해야 함] --> revert 유사함 @@@ 

======================================================================================================================


> git branch		-- branch 보기 @@@
* master 		-- 현재의 branch 보여줌 @@@

> git branch exp		-- exp branch 생성 @@@
Switch to branch 'exp'

> git branch		-- branch 보기 @@@
exp 		 
* master  -- 현재의 branch 보여줌 @@@

> git checkout exp		-- master branch에서 체크아웃하고 exp branch로 들어감(exp가 master branch 그래로 복사함) @@@
Switched to branch 'exp'

> git branch		-- branch 보기 @@@
* exp 		-- 현재의 branch 보여줌 @@@
master  



======================================================================================================================

- Visual Studio Code를 활용한 Git, Github 사용 가이드[두원이 Doowonee]
https://youtu.be/qkRuIUSdXnw
======================================================================================================================


■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
+-----------------------------------------// Git 정보 //-----------------------------------------------/ 2021.11.30(화) /---+ 
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
 
- #1 Git 설치 (Git & Git extensions) _ Git 환경에서 S/W 개발하기 첫번째 git 환경 구축[개발자를 꿈꾸다]
https://www.youtube.com/watch?v=ESePPSQSh24&list=PLk3PkBqRCyIEzFyMMrbxPo02JcfvVVtU5
 -----------------------------------------------------------------------------------------------------
 
1. Git Extensions 다운로드 및 설치(추천)
 1) GitExtensions224SetupComplete.msi  ==> https://sourceforge.net/projects/gitextensions
-----------------------------------------------------------------------------------------------------

2. Git Extensions 다운로드 및 설치  
 1) GitExtensions-3.4.3.9999-d4b0f48bb.msi ==> https://imdct.tistory.com/entry/Git-Extensions-%EC%84%A4%EC%B9%98
 2) Merge Tool  다운로드 및 설치
  가. Win Merge ==> https://winmerge.org/downloads/?lang=en
==============================================================================================================

- Cmder [Windows 콘솔 에뮬레이터] 설치 방법 + 한글사용방법
https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=altmshfkgudtjr&logNo=221461221105
==============================================================================================================

- 깃, 깃허브 제대로 배우기 (기본 마스터편, 실무에서 꿀리지 말자)  [드림코딩 by 엘리]
https://youtu.be/Z9dvM7qgN9s
==============================================================================================================

- Git에 들어가기 앞서
https://blog.huny.dev/git/
============================================================================================================== 

- [GitHub 깃허브] Repository 레파지토리 삭제 방법
출처: https://afsdzvcx123.tistory.com/entry/GitHub-깃허브-Repository-레파지토리-삭제-방법 [BeomBeomJoJo - Programmer]
============================================================================================================== 
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
+----------------------------------------------------// End //------------------------------------------------------------+
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■


■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
+-----------------------------------------// Git 정보2 //-----------------------------------------------/ 2021.11.30(화) /---+ 
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■


- Git 정보 @@@
> mkdir git
> cd git
> git init     ---> git 초기화
> git -al 
 ==============================================================================================================
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
+----------------------------------------------------// End //------------------------------------------------------------+
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
\

