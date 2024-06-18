package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FavBook;
import model.FavoriteBookListLogic;
import model.LoginUser;

/**
 * Servlet implementation class FavoriteBookView
 */
@WebServlet("/Favorite/FavoriteBookViewServlet")
public class FavoriteBookViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/jsp/favoriteBookView.jsp");
		HttpSession session = request.getSession();
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
		FavoriteBookListLogic favoriteBookListLogic = new FavoriteBookListLogic();
		ArrayList<FavBook> favBookList = favoriteBookListLogic.FabBookListView(loginUser);
		session.setAttribute("favBookList", favBookList);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
