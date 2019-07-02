 

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
		// loginForm에서 보낸 파라미터. 
		String id = request.getParameter("id"); // null값 체크해주기
		String pwd = request.getParameter("pwd"); // null값 체크
		String chk = request.getParameter("checked");
		
//		여기서 체크박스가 체크돼있다면 받은 cookie값을 보내고,
//		체크돼있지 않다면 cookie.setMaxAge(0);로 유효시간을 0으로 지정해준 후 응답 헤더에 추가한다.
		System.out.println("id값 나와라: " + id + " 나왔나");		
//		* id와 pwd가 모두 일치하는 경우
		if(id.equals("asdf") && pwd.equals("1234")) { 
			
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
			// 로그인 성공 화면으로 가라고 대답해줌. redirect이므로 2번 요청하게 됨. LoginForm이 /index.jsp로 재접속합니다.
			response.sendRedirect("/index.jsp");	
//		* id나 pwd 하나라도 일치하지 않는 경우
		} else {
			request.setAttribute("msg", "id 또는 비밀번호가 틀립니다."); // request객체에 메시지를 저장
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
