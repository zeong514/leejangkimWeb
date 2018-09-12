<%@page import="kr.co.master.dto.Station"%>
<%@page import="kr.co.master.dto.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Station st_member = (Station)session.getAttribute("st_member");
ArrayList<List> item = (ArrayList<List>)request.getAttribute("item");
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
	width: 150px;
	height: 80px;
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
	width: 160px;
    height: 70px;
    border: 0;
    border-radius: 0;
    background: #DA81F5;
    color: #fff;
    text-shadow: 0 1px rgba(0,0,0,.2);
    font-weight: 700;
    cursor: pointer;
    font-size: 35px;
    text-align: center;
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
td{
	 width: 160px;
     height: 70px;
     border: 1px solid #cccccc;  
}
.btd{
	width: 70px;
    height: 30px;
    border: 0px solid #ffffff;
}
</style>
<script type="text/javascript">
var today = new Date();
var date = new Date();
function prevCalendar() {
 today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
 buildCalendar(); 
}

function nextCalendar() {
     today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
     buildCalendar();
}
function buildCalendar(){
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	
    var doMonth = new Date(today.getFullYear(),today.getMonth(),1);
    var lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
    var tbCalendar = document.getElementById("calendar");
    var tbCalendarYM = document.getElementById("tbCalendarYM");
     tbCalendarYM.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월"; 

    while (tbCalendar.rows.length > 2) {
          tbCalendar.deleteRow(tbCalendar.rows.length-1);
     }
     var row = null;
     row = tbCalendar.insertRow();
     var cnt = 0;
	 for (i=0; i<doMonth.getDay(); i++) {
          cell = row.insertCell();
          cnt = cnt + 1;
     }
     for (i=1; i<=lastDate.getDate(); i++) { 
          cell = row.insertCell();
          cell.innerHTML = i;
          
          <%for(int i = 0; i < item.size(); i++){ %>
          if(yyyy == <%=item.get(i).getRequest().getLi_year()%> && mm == <%=item.get(i).getRequest().getLi_month()%> 
          && i == <%=item.get(i).getRequest().getLi_day()%>){
        	  cell.innerHTML += "<br>";
        	  cell.innerHTML += "<%=item.get(i).getMember().getMem_name()%>"+" ";
        	  cell.innerHTML += <%=item.get(i).getRequest().getLi_hour()%>+":";
        	  if(<%=item.get(i).getRequest().getLi_minute() %> == 0){
        		  cell.innerHTML += "00"
        	  }else{
        		  cell.innerHTML += "30"
        	  }
          }
          <%}%>
          cnt = cnt + 1;
      if (cnt % 7 == 1) {
        cell.innerHTML = "<font color=#F79DC2>" + i
        <%for(int i = 0; i < item.size(); i++){ %>
          if(yyyy == <%=item.get(i).getRequest().getLi_year()%> && mm == <%=item.get(i).getRequest().getLi_month()%> 
          && i == <%=item.get(i).getRequest().getLi_day()%>){
        	  cell.innerHTML += "<br>";
        	  cell.innerHTML += "<%=item.get(i).getMember().getMem_name()%>"+" ";
        	  cell.innerHTML += <%=item.get(i).getRequest().getLi_hour()%>+":";
        	  if(<%=item.get(i).getRequest().getLi_minute() %> == 0){
        		  cell.innerHTML += "00"
        	  }else{
        		  cell.innerHTML += "30"
        	  }
          }
          <%}%>
    }    
      if (cnt % 7 == 0){
          cell.innerHTML = "<font color=skyblue>" + i
          <%for(int i = 0; i < item.size(); i++){ %>
          if(yyyy == <%=item.get(i).getRequest().getLi_year()%> && mm == <%=item.get(i).getRequest().getLi_month()%> 
          && i == <%=item.get(i).getRequest().getLi_day()%>){
        	  cell.innerHTML += "<br>";
        	  cell.innerHTML += "<%=item.get(i).getMember().getMem_name()%>"+" ";
        	  cell.innerHTML += <%=item.get(i).getRequest().getLi_hour()%>+":";
        	  if(<%=item.get(i).getRequest().getLi_minute() %> == 0){
        		  cell.innerHTML += "00"
        	  }else{
        		  cell.innerHTML += "30"
        	  }
          }
          <%}%>
           row = calendar.insertRow();
      }
      if (today.getFullYear() == date.getFullYear()
         && today.getMonth() == date.getMonth()
         && i == date.getDate()) {
        cell.bgColor = "#FAF58C";
       }
     }
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
			<td class="btd"><a href="request.do"><input type="button" value="신청내역" class="menu_btn"></a></td>
			<td class="btd"><a href="approval.do"><input type="button" value="승인내역" class="menu_btn"></a></td>
			<td class="btd"><a href="refuse.do"><input type="button" value="거절내역" class="menu_btn"/></a></td>
			<td class="btd"><input type="button" value="예약내역" class="menu_btn"></td>
		</tr>
	</table>
	</div>
	<div id = "wh">
	<h3>예약 내역</h3>
	<table id="calendar">
    <tr>
        <td><input type="button" value="⇦" onclick="prevCalendar()" class="ok_btn"></td>
        <td align="center" id="tbCalendarYM" colspan="5">yyyy년 m월</td>
        <td><input type="button" value="⇨" onclick="nextCalendar()" class="ok_btn"></td>
    </tr>
    <tr>
        <td align="center"><font color ="#F79DC2">일</td>
        <td align="center">월</td>
        <td align="center">화</td>
        <td align="center">수</td>
        <td align="center">목</td>
        <td align="center">금</td>
        <td align="center"><font color ="skyblue">토</td>
    </tr> 
	</table>
	<script type="text/javascript">
	    buildCalendar();
	</script>
	</div>
</div>
</body>
