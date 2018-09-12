<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String use_id =(String)request.getAttribute("use_id");
String id =(String)request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<style>
h3 {
	margin: 19px 0px 8px 0px;
    font-size: 14px;
    font-weight: 700;
}

input[type=text]{
	width:180px;
	height:30px;
	border : 1px solid #dadada;
}

#wrap {
	width:320px;
}

.btn_use {
	width: 100px;
    height: 30px;
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

.btn_check {
	width: 50px;
    height: 30px;
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

#footer {
	padding-top:10px;
}
</style>
<script>
function checkId() {
	var form = document.getElementById("form_id_check");
	
	form.submit();
}

function useId() {
	opener.document.getElementById("check_id").value = "<%=use_id%>";
	opener.document.getElementById("input_id").value = 
		document.getElementById("input_popup_id").value;
	window.close();
}
</script>
</head>
<body>
<form id="form_id_check" action="id_check_ok.do" method="post">
<div id="wrap">
	<h3>아이디 입력</h3>
	<div id="input_layout">
		<input type="text" id="input_popup_id" value="<%=id %>" name="id" /> 
		<input type="button" value="확인" onclick="checkId();" class="btn_check"/>
	</div>
	<br/>
	<%if (use_id == null) { %>
	역명 중복체크를 하세요.
	<%} else if (use_id.equals("0")) { %>
	중복된 역명 입니다.
	<% } else { %>
	중복되지 않은 역명 입니다.
	<%} %>
	<div id="footer">
		<input type="button" value="사용하기" onclick="useId();" class="btn_use"/>
	</div>
</div>
</form>
</body>
</html>