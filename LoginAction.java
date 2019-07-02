 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginAction
 */
 
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// loginForm���� ���� �Ķ����. 
		String id = request.getParameter("id"); // null�� üũ���ֱ�
		String pwd = request.getParameter("pwd"); // null�� üũ
		String chk = request.getParameter("checked");
		
//		���⼭ üũ�ڽ��� üũ���ִٸ� ���� cookie���� ������,
//		üũ������ �ʴٸ� cookie.setMaxAge(0);�� ��ȿ�ð��� 0���� �������� �� ���� ����� �߰��Ѵ�.
		System.out.println("id�� ���Ͷ�: " + id + " ���Գ�");		
//		* id�� pwd�� ��� ��ġ�ϴ� ���
		if(id.equals("asdf") && pwd.equals("1234")) { 
			
			// 1. ���� �Ķ���Ͱ��� null�� �ƴ϶��(check�� ���ִٸ�, id����ϱ�)
			if(chk != null) { 		
				// �ش� name(id)�� �ҷ��� value�� �ִ´�. 
				// ��Ű�� ��ó�� ���Ӱ� ����� �����������Ƿ� ���� �߰��ϸ� id�� ���� �����.
				Cookie cookie = new Cookie("id", id);
				response.addCookie(cookie); 			// ���� ���� ��Ű�� ���Ѵ�.
				
			// 2. ���� �Ķ���Ͱ��� null�̶��(���X) 
			} else {	
				// ��Ű�� �ҷ����δ�.
				Cookie[] cookies = request.getCookies();
				// ��Ű�� null�� �ƴϰ�, 1�� �̻��� ��Ű�� ������ �� cookie�� ��ȿ�ð��� 0���� �����ϰ� ���� ����� �߰��Ѵ�.
				if(cookies != null && cookies.length>0) {	
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals("id")) { // id�� �������� �ϴ� ��Ű�� �ִ��� ã�´�.
							Cookie cookie = new Cookie("id", "");	// �ִٸ� value�� ""���� ������ְ�
							cookie.setMaxAge(0);					// ������ 0���� ����
							response.addCookie(cookie);				// ��Ű�� �ִ´�.
						}
					}
				}
			}
			// �α��� ���� ȭ������ ����� �������. redirect�̹Ƿ� 2�� ��û�ϰ� ��. LoginForm�� /index.jsp�� �������մϴ�.
			response.sendRedirect("/index.jsp");	
//		* id�� pwd �ϳ��� ��ġ���� �ʴ� ���
		} else {
			request.setAttribute("msg", "id �Ǵ� ��й�ȣ�� Ʋ���ϴ�."); // request��ü�� �޽����� ����
			RequestDispatcher reqDis = request.getRequestDispatcher("/LoginForm.jsp");
			reqDis.forward(request,response);
		}


//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE html>");
//		out.println("<html lang='en'>");
//		out.println("<head>");
//		out.println("<meta charset='EUC-KR'>");
//		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
//		out.println("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
//		out.println("<title>Document</title>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<h1>your id : "+id+"</h1>");
//		out.println("<h1>your password : "+pwd+"</h1>");
//		out.println("</body>");
//		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
