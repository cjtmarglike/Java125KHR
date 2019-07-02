<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>



<%-- 1. 로그인이 되어있는지 확인한다. --%>
<%--    되어있는지 확인은?  --%>

<%
	String memberId = (String)session.getAttribute("MEMBERID");
	boolean login = memberId == null ? false : true;
%>

<%-- 1-1. 되어있지 않다면->login페이지로 --%>

<%
	if(!login) {		
		response.sendRedirect("/login.jsp?board="+"board");	
	}
	// 로그인이 되어있지 않다면 login.jsp로 재접속하면 되니까. + 근데 내가 지금 board에서 접근한다는 정보를 줘야 해.
	// 아래 코드는 실행하지 않을 것. 아래는 로그인이 되어있을 경우.
%>




<%-- 1-2. 되어있다면->게시판 출력 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>board</title>
</head>
<body>
	
	<h1>게시판이에요?? 게시판을 보여줍니다.</h1>

<%-- 게시판에서 홈으로 돌아가는 버튼 --%>
	<form action="/index.jsp" method=post>
		<input type="submit" value="Home!Sweet!Home!">	
	</form>	
	
</body>
</html>


