<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	// 로그아웃을 눌렀을 때(요청 파라미터 값이 null이 아니면) 로그아웃을 한다!
	String logout = request.getParameter("Logout");
	if(logout != null) {
		session.invalidate();
	}

	// 1. 세션 MEMBERID가 존재하는지 확인한다.
	String memberId = null; // 세션 아이디를 저장하기 위한 변수.
	try { // 로그아웃 누른 후  session is already invalidated 예외가 떠서 try문 처리.
		memberId = (String)session.getAttribute("MEMBERID");
	} catch (IllegalStateException ignored) {}
	
	// 1-1. 존재하지 않을 경우(login안돼있음) => login버튼은 보이고, logout버튼은 안보이게.
	String loginTag = "'Login'  type='submit'";	// login 초기값 type submit
	String logoutType = "'hidden'"; // logout 초기값 type hidden
		
	// 1-2. 존재한다면 => ~님 환영 뜨고, logout버튼은 보이게.
	boolean login = memberId == null ? false : true; // 세션이 null인가
	if(login) { //로그인이 되어있다면 로그인 내용변경,로그아웃 보이기
		loginTag = "'★" + memberId + "님 축 로그인★' size=13";
		logoutType = "'submit'"; 	//로그인이 되면 logout제출태그는 숨기기
	}
%>


<form action="/index.jsp" method=post>	
	<input type="submit" value="Home">
</form>	

<form action="/login.jsp" method=post>	
	<input value=<%=loginTag%> name="Login" >
</form>		

<form action="/index.jsp" method=post>	
	<input type=<%=logoutType%> name="Logout" value="Logout">
</form>	

<form action="/board.jsp" method=post>
	<input type="submit" name="Board" value="Board">	
</form>		
	
</body>
</html>