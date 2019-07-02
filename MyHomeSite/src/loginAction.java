

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.tomcat.util.descriptor.web.SessionConfig;

/**
 * Servlet implementation class loginAction
 */
@WebServlet("/loginAction")
public class loginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		login.jsp���� ���� �Ķ����. 
		String id = request.getParameter("id"); // null�� üũ���ֱ�
		String pwd = request.getParameter("pwd"); // null�� üũ
		String chk = request.getParameter("remember");
		
//		���⼭ üũ�ڽ��� üũ���ִٸ� ���� cookie���� ������,
//		üũ������ �ʴٸ� cookie.setMaxAge(0);�� ��ȿ�ð��� 0���� �������� �� ���� ����� �߰��Ѵ�.		
//		* id�� pwd�� null�� �ƴϰ�, ��� ��ġ�ϴ� ���
		if(id != null && id.equals("bit") && pwd != null && pwd.equals("1234")) { 
			
			// request �⺻ ��ü�� getSession() �޼��带 ����� ������ ���ϰ� . �α��� ���¸� �����Ѵ�.			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("MEMBERID", id);
			
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
			// board���� �θ� �Ŷ�� board��, index���� �θ� �Ŷ�� index �������� �������ؾ� ��.
			// 1. board���� login�� �Ǿ����� ���� �� login�������� ��û �Ķ���� board�� ������.
			// 2. login���������� ��û �Ķ���� board�� �о null�� ���ο� ���� form�� ���� �ּҸ� �����Ѵ�.
			//    (�����ְ� ���� ���� ����/������)
			// 3. loginAction���� ��û�Ķ���� board�� �д´�.
			// 3-1. null�� �ƴ϶�� board���� �� ������ �����ϰ� board�� �����̷�Ʈ.
			// 3-2. null�̶�� Home���� �� ������ �����ϰ� index�� �����̷�Ʈ.
			String fromboard = request.getParameter("board");
			if(fromboard != null) {
				response.sendRedirect("/board.jsp");
			} else {
				// index�� ������. redirect�̹Ƿ� 2�� ��û�ϰ� ��. login�� "/index.jsp"�� �������մϴ�.
				response.sendRedirect("/index.jsp");	
			}
//		* id�� pwd �ϳ��� ��ġ���� �ʴ� ���
		} else {
			request.setAttribute("msg", "id �Ǵ� ��й�ȣ�� Ʋ���ϴ�."); // request��ü�� �޽����� ����
			RequestDispatcher reqDis = request.getRequestDispatcher("/login.jsp");
			reqDis.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
