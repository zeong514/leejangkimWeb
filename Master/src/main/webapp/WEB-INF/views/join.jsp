<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h3 {
	margin: 19px 0px 8px 0px;
    font-size: 14px;
    font-weight: 700;
}

#wrap {
	width:420px;
	margin : 0px auto;
}
.textbar{
	width:277px;
	padding:15px;
	border : 1px solid #dadada;
}
.textbar2{
	width:388px;
	padding:15px;
	border : 1px solid #dadada;
}
.day{
	width:80px;
	padding:15px;
	border : 1px solid #dadada;
}
.month {
	width:100px;
	padding:14px;
	border : 1px solid #dadada;
}
.check_id {
	width: 100px;
    height: 45px;
    border: 0;
    border-radius: 0;
    background: #DA81F5;
    color: #fff;
    text-shadow: 0 1px rgba(0,0,0,.2);
    font-weight: 700;
    font-size: 12px;
    cursor: pointer;
    margin-left: 10px;
}


.join_btn {
	width: 420px;
    height: 50px;
    border: 0;
    border-radius: 0;
    background: #DA81F5;
    color: #fff;
    text-shadow: 0 1px rgba(0,0,0,.2);
    font-weight: 700;
    font-size: 12px;
    cursor: pointer;
    margin-top: 10px;
}
.check_err {
	width:100%;
	height:15px;
	color:#ff0000;
	font-size:15px;
	margin-top:3px;
	display:none;
}
.c_gender  {
    display: block;
    height: 49px;
    border: solid 1px #dcdcdc;
}
.jender {
    display: block;
    float: left;
    position: relative;
    width: 50%;
    height: 49px;
    border-right: solid 1px #dcdcdc;
    z-index: 10;
    box-sizing: border-box;
}
.jender input {
    position: absolute;
    top: 50%;
    left: 75px;
    width: 20px;
    height: 20px;
    margin-top: -8px;
    line-height: 49px;
}
.jender input:checked+label {
    margin: -1px;
    color: #DA81F5;
    border: solid 1px #DA81F5;
}

.jender label {
    background: #fff;
}

.jender label {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 49px;
    font-size: 15px;
    line-height: 51px;
    cursor: pointer;
    text-align: center;
    color: #8e8e8e;
}
</style>
<script>
function joinOk() {
	var join_form = document.getElementById("join_form");
	var id = document.getElementById("input_id");
	var pw_1 = document.getElementById("pw_1");
	var pw_2 = document.getElementById("pw_2");
	var name = document.getElementById("name");
	var phone = document.getElementById("phone");
	var checkId = document.getElementById("check_id");
	
	
	if (id.value == "") {
		var err_id = document.getElementById("err_id");
		err_id.style.display = "block";
		err_id.innerHTML = "역명을 입력하세요.";
		return ;
	} else {
		var err_id = document.getElementById("err_id");
		err_id.style.display = "none";
	}
	
	if (checkId.value == "0") {
		var err_id = document.getElementById("err_id");
		err_id.style.display = "block";
		err_id.innerHTML = "역명 중복체크를 하세요";
		return ;
	}
	
	if (pw_1.value == "") {
		var err_pw_1 = document.getElementById("err_pw_1");
		err_pw_1.style.display = "block";
		err_pw_1.innerHTML = "비밀번호를 입력하세요.";
		return ;
	} else {
		var err_pw_1 = document.getElementById("err_pw_1");
		err_pw_1.style.display = "none";
	}
	
	if (pw_2.value == "") {
		var err_pw_2 = document.getElementById("err_pw_2");
		err_pw_2.style.display = "block";
		err_pw_2.innerHTML = "비밀번호를 입력하세요.";
		return ;
	} else {
		var err_pw_2 = document.getElementById("err_pw_2");
		err_pw_2.style.display = "none";
	}
	
	if (pw_1.value != "" && pw_2.value != "" && (pw_1.value != pw_2.value)) {
		var err_pw_2 = document.getElementById("err_pw_2");
		err_pw_2.style.display = "block";
		err_pw_2.innerHTML = "비밀번호가 맞지 않습니다.";
		return ;
	}
	
	if (name.value == "") {
		var name = document.getElementById("err_name");
		name.style.display = "block";
		name.innerHTML = "이름을 입력하세요.";
		return ;
	} else {
		var name = document.getElementById("err_name");
		name.style.display = "none";
	}
	
	if (phone.value == "") {
		var phone = document.getElementById("err_phone");
		phone.style.display = "block";
		phone.innerHTML = "전화 번호를 입력하세요.";
		return ;
	} else {
		var phone = document.getElementById("err_phone");
		phone.style.display = "none";
	}
	
	
	window.alert("가입이 완료되었습니다.");
	
	join_form.submit();
}

function checkId() {
	window.open("idCheck.do",
            "childForm", "width=350, height=250, resizable = no, scrollbars = no"); 
}
</script>
</head>
<body>
<form action="join_ok.do" method="post" id="join_form">
<input type="hidden" id="check_id" value="0" />
	<div id="wrap">
		<h3>역명</h3>
		<div>
			<input type="text" id="input_id" class="textbar" name="id"/><input type="button" value="중복확인" class="check_id"
			onclick="checkId();"/>
		</div>
		<div id="err_id" class="check_err">
		</div>
		<h3>비밀번호</h3>
		<div>
			<input type="password" id="pw_1" class="textbar2" name="pw"/>
		</div>
		<div id="err_pw_1" class="check_err">
		</div>
		<h3>비밀번호 확인</h3>
		<div>
			<input type="password" id="pw_2" class="textbar2"/>
		</div>
		<div id="err_pw_2" class="check_err">
		</div>
		<h3>관리자 이름</h3>
		<div>
			<input type="text" id="name" class="textbar2" name="name"/>
		</div>
		<div id="err_name" class="check_err">
		</div>
		<h3>관리자 전화번호</h3>
		<div>
			<input type="text" id="phone" class="textbar2" name="phone"/>
		</div>
		<div id="err_phone" class="check_err">
		</div><br>
		<input type="button" class="join_btn" value="회원가입" onclick="joinOk();" />
	</div>
</form>
</body>
</html>