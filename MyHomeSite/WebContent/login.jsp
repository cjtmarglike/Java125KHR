<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    

<%-- �α��� ���н� ��� �޽��� �޾ƿ��� --%>
<%
String msg = (String)request.getAttribute("msg");
if(msg!=null) {
%>
	<lable><b><%=msg%></b></lable>
<%} %>
    
    
<%-- 1. �Է¹ޱ� --%>
<%
	Cookie[] cookies = request.getCookies();
	String id = "";
	String checked = "";

// 2. �۾�
// 2.1 ��Ű�� �ִ��� Ȯ���Ѵ�.
// 2.1.1 ��Ű�� ������, ��Ű�� �о id�� �ѷ��ְ�, üũ�ڽ� üũ
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			// ��Ű �߿��� �̸��� id�� ���� �ִ��� Ȯ��
			String name = cookies[i].getName();
			if (name.equals("id")) {
				// ������ id���� ���� id�� ����
				id = cookies[i].getValue();
				checked = "checked='checked'";
			}
		}
	}

	// board�� �о null���� ���ο� ���� fromboard��(form�� ���� �ּ�)�� ����.
	String fromboard = request.getParameter("board");
	if(fromboard != null) {
		fromboard = "'/loginAction?board=' + 'fromboard'";
	} else {
		fromboard = "/loginAction";
	}
%>

<%-- 3. ����ϱ� --%>
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
		<input type='submit' value='�α���'>
	</form>
</body>
</html>