<%@page import="kr.co.master.dto.Station"%>
<%@page import="java.util.Calendar"%>
<%@page import="kr.co.master.dto.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Station st_member = (Station)session.getAttribute("st_member");
ArrayList<List> item = (ArrayList<List>)request.getAttribute("item");
Calendar calendar = Calendar.getInstance();
int year = calendar.get(Calendar.YEAR);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="1800">
<title>Insert title here</title>
<style>
#main{
	width: 1000px;
	margin: 0px auto;
}
#wh{
   width: 1000px;
   border: 1px solid #cccccc;
}
#tab{
   width: 100%;
}
table {
    border-collapse: collapse;
}
.num{
	border: 1px solid black;
	text-align: center;
	width: 100px;
}
.btn{
	border: 1px solid black;
	text-align: center;
    width: 80px;
}
.title{
    text-align: center;
}
#make{
	margin-left: 730px;
}
#logbar{
	width: 287px;
	height: 75px;
	border: 1px solid #cccccc;
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
}
.menu_btn {
	width: 70px;
    height: 30px;
    border: 0;
    border-radius: 0;
    background: #DA81F5;
    color: #fff;
    text-shadow: 0 1px rgba(0,0,0,.2);
    font-weight: 700;
    font-size: 12px;
    cursor: pointer;
}
.logout{
	width: 70px;
    height: 30px;
    border: 0;
    border-radius: 0;
    background: #ffffff;
    color: #000;
    text-shadow: 0 1px rgba(0,0,0,.2);
    font-weight: 700;
    font-size: 12px;
    cursor: pointer;
}
.sh_btn {
	width: 60px;
    height: 22px;
    border: 0;
    border-radius: 0;
    background: #DA81F5;
    color: #fff;
    text-shadow: 0 1px rgba(0,0,0,.2);
    font-weight: 700;
    font-size: 12px;
    cursor: pointer;
    vertical-align:top;
    margin-bottom: 2px;
}
#name{
	color: #5858FA;
}
#name:hover{
	cursor: pointer;
	text-decoration: underline;
}
</style>
<script type="text/javascript">
	function postp(id) {
		window.open("post.do?id="+id,
	            "childForm", "width=350, height=250, resizable = no, scrollbars = no"); 
	}
	function delappr(id) {
		if (confirm("삭제 하시겠습니까?")) {
			document.location.href="delappr2.do?id=" + id;
		}
	}
	function searchRe() {
		var search_form_re = document.getElementById("search_form_re");
		
		search_form_re.submit();
	}
	function sh_yearRe() {
		var year_form_re = document.getElementById("year_form_re");
		
		year_form_re.submit();
	}
</script>
</head>
<body>
<div id="main">
	<div id="logbar">
	<div style="float: left; margin-top: 6px; margin-left: 3px"><a><%=st_member.getSt_id() %>  </a></div>
	<div style="float: right; border: 1px solid #cccccc; margin-bottom: 10px;">
	<a href="logout.do"><input type="button" value="로그아웃" class="logout"></a></div>
	<table>
		<tr>
			<td><a href="request.do"><input type="button" value="신청내역" class="menu_btn"></a></td>
			<td><a href="approval.do"><input type="button" value="승인내역" class="menu_btn"/></a></td>
			<td><a href="refuse.do"><input type="button" value="거절내역" class="menu_btn"/></a></td>
			<td><a href="booking.do"><input type="button" value="예약내역" class="menu_btn"></a></td>
		</tr>
	</table>
	</div>
	<div id = "wh">
	<h3>거절 내역</h3>
	<form action="sh_year_re.do" method="post" id="year_form_re">
		<select name="year">
			<option value="0">년</option>
			<option value="<%=year-6 %>"><%=year-6 %></option>
			<option value="<%=year-5 %>"><%=year-5 %></option>
			<option value="<%=year-4 %>"><%=year-4 %></option>
			<option value="<%=year-3 %>"><%=year-3 %></option>
			<option value="<%=year-2 %>"><%=year-2 %></option>
			<option value="<%=year-1 %>"><%=year-1 %></option>
			<option value="<%=year %>"><%=year %></option>
		</select>
	<input type="button" value="검색" class="sh_btn" onclick="sh_yearRe();">
	</form>
	<table id = "tab">
	<tr>
		<th class="num">번호</th>
	    <th class="num">년</th>
	    <th class="num">월</th>
	    <th class="num">일</th>
	    <th class="num">시</th>
	    <th class="num">분</th>
	    <th class="num">역</th>
	    <th class="num">당 역</th>
	    <th class="num">상대 역</th>
	    <th class="num">이름</th>
	    <th class="btn">삭제</th>
	</tr>
	<%for(int i = 0; i < item.size(); i++){ %>
	<tr>
		<td class="num"><%=item.size()-i %></td>
	    <td class="num"><%=item.get(i).getRequest().getLi_year() %>년</td>
	    <td class="num"><%=item.get(i).getRequest().getLi_month() %>월</td>
	    <td class="num"><%=item.get(i).getRequest().getLi_day() %>일</td>
	    <td class="num"><%=item.get(i).getRequest().getLi_hour() %>시</td>
	    <td class="num"><%=item.get(i).getRequest().getLi_minute() %>분</td>
	    <%
	    Long kind = item.get(i).getRequest().getLi_kind();
	    String kname = "";
	    String enname = "";
	    if(kind == 0){
	    	kname = "출발";
	    	enname = " 도착";
	    }else{
	    	kname = "도착";
	    	enname = " 출발";
	    }
	    %>
	    <td class="num"><%=item.get(i).getRequest().getLi_station() %></td>
	    <td class="num"><%=kname %></td>
	    <td class="num"><%=item.get(i).getRequest().getLi_otherSta()+enname%></td>
	    <td class="num"><a onclick="postp(<%=item.get(i).getRequest().getId() %>);" id="name"><%=item.get(i).getMember().getMem_name() %></a></td>
	    <td class="btn"><input type="button" value="삭제" class="ok_btn" onclick="delappr(<%=item.get(i).getRequest().getId() %>);"></td>
	</tr>
	<%} %>
	</table>
	
	<div style="margin-top: 20px;">
	<form action="search_re.do" method="post" id="search_form_re">
		<select name="month">
			<option value="0">월</option>
			<%for(int i = 1; i < 13; i++){ %>
			<option value="<%=i %>"><%=i %></option>
			<%} %>
		</select>
		<select name="day">
			<option value="0">일</option>
			<%for(int i = 1; i < 32; i++){ %>
			<option value="<%=i %>"><%=i %></option>
			<%} %>
		</select>
	
		<select name="kind">
			<option value="2">전체</option>
			<option value="0">출발</option>
			<option value="1">도착</option>
		</select>
		<input type="text" placeholder="이름" style="vertical-align:top;" name="name">
		<input type="button" value="검색" class="sh_btn" onclick="searchRe();">
	</form>
	</div>
	
	</div>
</div>
</body>
</html>