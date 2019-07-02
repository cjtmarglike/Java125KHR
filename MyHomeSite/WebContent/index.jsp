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
	// �α׾ƿ��� ������ ��(��û �Ķ���� ���� null�� �ƴϸ�) �α׾ƿ��� �Ѵ�!
	String logout = request.getParameter("Logout");
	if(logout != null) {
		session.invalidate();
	}

	// 1. ���� MEMBERID�� �����ϴ��� Ȯ���Ѵ�.
	String memberId = null; // ���� ���̵� �����ϱ� ���� ����.
	try { // �α׾ƿ� ���� ��  session is already invalidated ���ܰ� ���� try�� ó��.
		memberId = (String)session.getAttribute("MEMBERID");
	} catch (IllegalStateException ignored) {}
	
	// 1-1. �������� ���� ���(login�ȵ�����) => login��ư�� ���̰�, logout��ư�� �Ⱥ��̰�.
	String loginTag = "'Login'  type='submit'";	// login �ʱⰪ type submit
	String logoutType = "'hidden'"; // logout �ʱⰪ type hidden
		
	// 1-2. �����Ѵٸ� => ~�� ȯ�� �߰�, logout��ư�� ���̰�.
	boolean login = memberId == null ? false : true; // ������ null�ΰ�
	if(login) { //�α����� �Ǿ��ִٸ� �α��� ���뺯��,�α׾ƿ� ���̱�
		loginTag = "'��" + memberId + "�� �� �α��Ρ�' size=13";
		logoutType = "'submit'"; 	//�α����� �Ǹ� logout�����±״� �����
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