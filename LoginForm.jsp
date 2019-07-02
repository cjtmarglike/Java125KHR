<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.net.URLEncoder"%>
<%@ page import = "java.net.URLDecoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%-- 로그인 실패시 메시지 출력 --%>>
<%
String msg = (String)request.getAttribute("msg");
if(msg!=null) {
%>
	<lable><b><%=msg%></b></lable>
<%} %>
<%
Cookie[] cookies = request.getCookies(); // 쿠키 목록을 불러온다.
String tmp= "";
String check = "";
if(cookies != null && cookies.length>0) { // 쿠키가 null이 아니고 1개 이상이라면.
	for(int i=0; i<cookies.length; i++) { // 쿠키를 읽어서 id가 존재한다면 그 value를 변수에 저장하고, checked로 만든다.
		if(cookies[i].getName().equals("id")) {
			tmp = cookies[i].getValue(); // 해당 name이 있다면 그 value를 얻어서 변수 tmp에 저장.
			check = "checked='checked'"; // id가 존재한다면 id 기억하기에 체크를 해둔다.
		}
	}
} 
%>

<form action="/LoginAction" method=get>
	id: <input name="id" type=text value=<%=tmp%> ><br>
	pwd: <input name="pwd" type=password value=""><br>
	<input type="checkbox" name="checked" <%=check%>>id 기억하기<br>
	<input type=submit value="로그인">
</form>
</body>
</html>