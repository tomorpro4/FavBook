package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.LoginUser;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String from = "login";
//		request.setAttribute("from", from);
		HttpSession session = request.getSession();
		String moto = (String)session.getAttribute("moto");
		System.out.println();
        System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+new Throwable().getStackTrace()[0].getLineNumber());
		if(moto == null) {
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"リクエストスコープからmoto取得できなかった");

			moto = request.getHeader("REFERER");
		}else {
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"リクエストスコープからmoto取得");
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+moto);
		}
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"遷移元："+moto);
		if(moto !=null && !moto.contains("LoginServlet")) {
			if(moto.equals("")) {
				moto = "MainMenuServlet";
			}
			session.setAttribute("moto", moto);
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp");
		dispatcher.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		User user = new User(userId, pass);
		String forward = null;
		
		LoginLogic loginLogic = new LoginLogic();
		LoginUser loginUser = loginLogic.login(user);
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		String moto = (String)session.getAttribute("moto");
		
		
		
		
		
		
		
		
		
		
		
		if(loginUser != null) {
			forward = moto;
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"login成功");
			response.sendRedirect(forward);
		}else {
			forward = "WEB-INF/jsp/loginForm.jsp";
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"login失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
		

		
		
		
	}

}
