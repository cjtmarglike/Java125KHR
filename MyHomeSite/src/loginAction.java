

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

//		login.jsp에서 보낸 파라미터. 
		String id = request.getParameter("id"); // null값 체크해주기
		String pwd = request.getParameter("pwd"); // null값 체크
		String chk = request.getParameter("remember");
		
//		여기서 체크박스가 체크돼있다면 받은 cookie값을 보내고,
//		체크돼있지 않다면 cookie.setMaxAge(0);로 유효시간을 0으로 지정해준 후 응답 헤더에 추가한다.		
//		* id와 pwd가 null이 아니고, 모두 일치하는 경우
		if(id != null && id.equals("bit") && pwd != null && pwd.equals("1234")) { 
			
			// request 기본 객체의 getSession() 메서드를 사용해 세션을 구하고 . 로그인 상태를 저장한다.			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("MEMBERID", id);
			
			// 1. 받은 파라미터값이 null이 아니라면(check가 돼있다면, id기억하기)
			if(chk != null) { 		
				// 해당 name(id)에 불러온 value를 넣는다. 
				// 쿠키는 맵처럼 네임과 밸류로 구성돼있으므로 새로 추가하면 id의 값이 변경됨.
				Cookie cookie = new Cookie("id", id);
				response.addCookie(cookie); 			// 새로 만든 쿠키를 더한다.
				
			// 2. 받은 파라미터값이 null이라면(기억X) 
			} else {	
				// 쿠키를 불러들인다.
				Cookie[] cookies = request.getCookies();
				// 쿠키가 null이 아니고, 1개 이상의 쿠키가 존재할 때 cookie의 유효시간을 0으로 지정하고 응답 헤더에 추가한다.
				if(cookies != null && cookies.length>0) {	
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals("id")) { // id를 네임으로 하는 쿠키가 있는지 찾는다.
							Cookie cookie = new Cookie("id", "");	// 있다면 value를 ""으로 만들어주고
							cookie.setMaxAge(0);					// 수명을 0으로 만들어서
							response.addCookie(cookie);				// 쿠키에 넣는다.
						}
					}
				}
			}
			// board에서 부른 거라면 board로, index에서 부른 거라면 index 페이지로 재접속해야 함.
			// 1. board에서 login이 되어있지 않을 때 login페이지로 요청 파라미터 board를 보낸다.
			// 2. login페이지에서 요청 파라미터 board를 읽어서 null값 여부에 따라 form을 보낼 주소를 조정한다.
			//    (전해주고 싶은 정보 포함/비포함)
			// 3. loginAction에서 요청파라미터 board를 읽는다.
			// 3-1. null이 아니라면 board에서 온 것으로 간주하고 board로 리다이렉트.
			// 3-2. null이라면 Home에서 온 것으로 간주하고 index로 리다이렉트.
			String fromboard = request.getParameter("board");
			if(fromboard != null) {
				response.sendRedirect("/board.jsp");
			} else {
				// index를 보여줌. redirect이므로 2번 요청하게 됨. login이 "/index.jsp"로 재접속합니다.
				response.sendRedirect("/index.jsp");	
			}
//		* id나 pwd 하나라도 일치하지 않는 경우
		} else {
			request.setAttribute("msg", "id 또는 비밀번호가 틀립니다."); // request객체에 메시지를 저장
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
