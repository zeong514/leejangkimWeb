<%@page import="kr.co.master.dto.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    List item = (List)request.getAttribute("item");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#main{
		width: 330px;
		height: 100px;
		border: 1px solid #cccccc;
	}
	#sub{
		width: 330px;
		height: 100px;
		border: 1px solid #cccccc;
	}
	#mold{
		width: 330px;
		height: 230px;
	}
	.ok_btn {
		width: 80px;
	    height: 30px;
	    border: 0;
	    border-radius: 0;
	    background: #DA81F5;
	    color: #fff;
	    text-shadow: 0 1px rgba(0,0,0,.2);
	    font-weight: 700;
	    font-size: 12px;
	    cursor: pointer;
	    float: right;
	    margin-top: 2px;
	}
	th, td {
	    border: 1px solid #cccccc;
	    border-collapse: collapse;
	}
	td{
		text-align: center;
	}
	#tab{
		width: 100%;
	}
</style>
<script type="text/javascript">
	function closeP() {
		window.close();
	}
	function refusal(id) {
		if (confirm("거절 하시겠습니까?")) {
			document.location.href="refusal.do?id=" + id;
		}
	}
	
</script>
</head>
<body>
<div id="mold">
	<div id="main">
	<%if(item.getRequest().getLi_post() == null){ %>
		<a>없음</a>
	<%}else{ %>
		<a><%=item.getRequest().getLi_post() %></a>
	<%} %>
	</div>
	<div id="sub">
		<table id="tab">
			<tr>
				<th>아이디</th>
				<td><%=item.getMember().getMem_mid() %></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><%=item.getMember().getMem_myp() %></td>
			</tr>
			<tr>
				<th>보호자</th>
				<td><%=item.getMember().getMem_subp() %></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=item.getMember().getMem_email() %></td>
			</tr>
		</table>
	</div>
	<input type="button" value="닫기" class="ok_btn" onclick="closeP();" style="margin-left: 5px">
	<%if(item.getRequest().getLi_appr() == 0){ %>
	<input type="button" value="거절" class="ok_btn" onclick="refusal(<%=item.getRequest().getId() %>);">
	<%} %>
</div>
</body>
</html>