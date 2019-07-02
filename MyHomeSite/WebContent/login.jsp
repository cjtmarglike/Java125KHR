<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    

<%-- 로그인 실패시 띄운 메시지 받아오기 --%>
<%
String msg = (String)request.getAttribute("msg");
if(msg!=null) {
%>
	<lable><b><%=msg%></b></lable>
<%} %>
    
    
<%-- 1. 입력받기 --%>
<%
	Cookie[] cookies = request.getCookies();
	String id = "";
	String checked = "";

// 2. 작업
// 2.1 쿠키가 있는지 확인한다.
// 2.1.1 쿠키가 있으면, 쿠키를 읽어서 id를 뿌려주고, 체크박스 체크
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			// 쿠키 중에서 이름이 id인 것이 있는지 확인
			String name = cookies[i].getName();
			if (name.equals("id")) {
				// 있으면 id값을 변수 id에 저장
				id = cookies[i].getValue();
				checked = "checked='checked'";
			}
		}
	}

	// board를 읽어서 null인지 여부에 따라 fromboard값(form을 보낼 주소)을 지정.
	String fromboard = request.getParameter("board");
	if(fromboard != null) {
		fromboard = "'/loginAction?board=' + 'fromboard'";
	} else {
		fromboard = "/loginAction";
	}
%>

<%-- 3. 출력하기 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action=<%=fromboard %> method=post>
		id: <input type='text' name='id' value=<%=id%>><br><br>
		pwd: <input	type='password' name='pwd'><br><br>
		<input type='checkbox' name='remember' <%=checked%>>remember me<br><br>
		<input type='submit' value='로그인'>
	</form>
</body>
</html>