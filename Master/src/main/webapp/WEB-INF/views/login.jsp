<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title></title>

<style rel="stylesheet">
body {
   font: 13px/20px 'Helvetica Neue', Helvetica, Arial, sans-serif;
   color: #333333;
   background: #ffffff;
}

.signUp {
   position: relative;
   margin: 50px auto;
   width: 280px;
   padding: 33px 25px 29px;
   background: #FFFFFF;
   border-bottom: 1px solid #C4C4C4;
   border-radius: 5px;
   -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
   box-shadow: 0 1px 5px rgba(0, 0, 0, 0.25);
}

.signUp:before,
.signUp:after {
   content: '';
   position: absolute;
   bottom: 1px;
   left: 0;
   right: 0;
   height: 10px;
   background: inherit;
   border-bottom: 1px solid #D2D2D2;
   border-radius: 4px;
}

.signUp:after {
   bottom: 3px;
   border-color: #DCDCDC;
}

.signUpTitle {
   margin: -25px -25px 25px;
   padding: 15px 25px;
   line-height: 35px;
   font-size: 26px;
   font-weight: 300;
   color: #777;
   text-align: center;
   text-shadow: 0 1px rgba(255, 255, 255, 0.75);
   background: #F7F7F7;
}

.signUpTitle:before {
   content: '';
   position: absolute;
   top: 0;
   left: 0;
   right: 0;
   height: 8px;
   background: #D8CEF6;
   border-radius: 5px 5px 0 0;
  
}

input {
   font-family: inherit;
   color: inherit;
   -webkit-box-sizing: border-box;
   -moz-box-sizing: border-box;
   box-sizing: border-box;
}

.signUpInput {
   width: 100%;
   height: 50px;
   margin-bottom: 25px;
   padding: 0 15px 2px;
   font-size: 17px;
   background: white;
   border: 2px solid #EBEBEB;
   border-radius: 4px;
   -webkit-box-shadow: inset 0 -2px #EBEBEB;
   box-shadow: inset 0 -2px #EBEBEB;
}

.signUpInput:focus {
   border-color: #DA81F5;
   outline: none;
   -webkit-box-shadow: inset 0 -2px #DA81F5;
   box-shadow: inset 0 -2px #DA81F5;
}

.lt-ie9 .signUpInput {
   line-height: 48px;
}

.signUpButton {
   position: relative;
   vertical-align: top;
   width: 100%;
   height: 54px;
   padding: 0;
   font-size: 22px;
   color: white;
   text-align: center;
   text-shadow: 0 1px 2px rgba(0, 0, 0, 0.25);
   background: #DA81F5;
   border: 0;
   border-bottom: 2px solid #DA81F5;
   border-radius: 5px;
   cursor: pointer;
   -webkit-box-shadow: inset 0 -2px #DA81F5;
   box-shadow: inset 0 -2px #DA81F5;
   margin-top: 10px;
   
}

.signUpButton:active {
   top: 1px;
   outline: none;
   -webkit-box-shadow: none;
   box-shadow: none;
}

:-moz-placeholder {
   color: #AAAAAA;
   font-weight: 300;
}
</style>
<script type="text/javascript">
function login() {
	var login_form = document.getElementById("signupForm");
	
	var login_id = document.getElementById("login_id");
	var login_pw = document.getElementById("login_pw");
	
	if (login_id.value == "") {
		window.alert("아이디를 입력하세요.");
		login_id.focus();
		return
	}
	
	if (login_pw.value == "") {
		window.alert("비밀번호를 입력하세요.");
		login_pw.focus();
		return
	}
	
	login_form.submit();
}

</script>
</head>
<body>

<form class="signUp" id="signupForm" action="login_ok.do" method="post">
   <h1 class="signUpTitle">로그인을 하세요</h1>
   <input type="text" class="signUpInput" placeholder="역 이름을 입력하세요" autofocus required id="login_id" name="st_id">
   <input type="password" class="signUpInput" placeholder="비밀번호를 입력하세요" required id="login_pw" name="st_pw">
   <input type="submit" value="로그인" class="signUpButton" onclick="login();"> <!-- 로그인 성공 하면 넘어가게 -->
   <a href="join.do"><input type="button" value="회원가입" class="signUpButton"></a> <!-- 회원가입 창으로 -->
   
 
</form>

</body>
</html>