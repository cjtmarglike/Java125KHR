<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>



<%-- 1. �α����� �Ǿ��ִ��� Ȯ���Ѵ�. --%>
<%--    �Ǿ��ִ��� Ȯ����?  --%>

<%
	String memberId = (String)session.getAttribute("MEMBERID");
	boolean login = memberId == null ? false : true;
%>

<%-- 1-1. �Ǿ����� �ʴٸ�->login�������� --%>

<%
	if(!login) {		
		response.sendRedirect("/login.jsp?board="+"board");	
	}
	// �α����� �Ǿ����� �ʴٸ� login.jsp�� �������ϸ� �Ǵϱ�. + �ٵ� ���� ���� board���� �����Ѵٴ� ������ ��� ��.
	// �Ʒ� �ڵ�� �������� ���� ��. �Ʒ��� �α����� �Ǿ����� ���.
%>




<%-- 1-2. �Ǿ��ִٸ�->�Խ��� ��� --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>board</title>
</head>
<body>
	
	<h1>�Խ����̿���?? �Խ����� �����ݴϴ�.</h1>

<%-- �Խ��ǿ��� Ȩ���� ���ư��� ��ư --%>
	<form action="/index.jsp" method=post>
		<input type="submit" value="Home!Sweet!Home!">	
	</form>	
	
</body>
</html>


