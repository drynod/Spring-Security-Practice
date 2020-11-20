# spring_security_web

## 2020-11-16

- 시큐리티 기본 로그인 폼을 커스텀 로그인 폼으로 변경(부트스트랩)


## 2020-11-19

- 회원가입 폼 추가 및 UI 변경
- 회원가입 구현. 비밀번호 Bcrypt로 암호화
- CustomAuthenticationProvider으로 DB 연동 인증처리(로그인 기능)

<img src="https://user-images.githubusercontent.com/73692337/99662056-1af3f500-2aa8-11eb-98a0-b9cccf99cbee.JPG" width="100%" height="100%">

- 로그아웃 구현
- 로그인 시 ID, Password 외 Secret Key 파라미터로 추가로 전송

## 2020-11-20

- 게시글 작성 기능 추가
- 게시글 리스트 UI 구현(boardList, 기능은 미완성)

- CustomSuccessHandler 추가. 
  (로그인 이전에 권한이 필요한 URL 접속 시 로그인 창으로 이동 후 인증 성공 시 해당 URL로 바로 리다이렉트)
  
- CustomFailureHandler 추가
  (username 이나 password 값에 따라서 경고문 Login Form에 출력)
  
![캡처](https://user-images.githubusercontent.com/73692337/99804271-1568de80-2b7e-11eb-8349-4c182611acee.JPG)
