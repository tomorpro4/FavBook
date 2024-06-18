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
		
		if(loginUser != null) {
			forward = "MainMenuServlet";
			System.out.println("login成功");
			response.sendRedirect(forward);
		}else {
			forward = "WEB-INF/jsp/loginForm.jsp";
			System.out.println("login失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
		

		
		
		
	}

}
