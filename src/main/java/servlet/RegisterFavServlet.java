package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AddFavBookLogic;
import model.BookList;
import model.FavBook;
import model.Keyword;
import model.SearchBookLogic;
import model.Status;
import model.User;

/**
 * Servlet implementation class RegisterFavServlet
 */
@WebServlet("/Favorite/RegisterFavServlet")
public class RegisterFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterFavServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		RequestDispatcher dispatcher = request.getRequestDispatcher("FavoriteBookViewServlet");
		//		dispatcher.forward(request, response);
		
		System.out.println("RegisterFavServlet実行！");
		
		HttpSession session = request.getSession();
		BookList bookList = (BookList) session.getAttribute("bookList2");
		//		BookList bookList = (BookList)request.getAttribute("bookList");
		Keyword keyword = (Keyword) session.getAttribute("keyword");
		User user = (User) session.getAttribute("loginUser");
		int max = keyword.getMaximumRecords();
		request.setCharacterEncoding("UTF-8");
		//		ArrayList<Integer> statusList = new ArrayList<Integer>();
		AddFavBookLogic addFavBookLogic = new AddFavBookLogic();

		String aaa = (String) session.getAttribute("aaa");
		if (aaa.equals("aaa")) {
			BookList addFavBookList = (BookList) session.getAttribute("addFavBookList");
			session.removeAttribute("aaa");
			session.removeAttribute("addFavBookList");
			for (int i = 0; i < addFavBookList.size(); i++) {
				FavBook favBook = addFavBookList.get(i);
				addFavBookLogic.AddFavBook(user, favBook);
			}

		} else {

			for (int i = 0; i < max; i++) {
				String statusId = request.getParameter("status" + (i + 1));
				System.out.println("status取得" + statusId);

				if (!(statusId == null || statusId.equals("") || statusId.equals("0"))) {
					Status status = new Status(Integer.parseInt(statusId));
					String memo = request.getParameter("memo" + (i + 1));
					bookList.get(i).setStatus(status);
					bookList.get(i).setMemo(memo);

					System.out.println(bookList.get(i).getStatus().getStatus());
					System.out.println(bookList.get(i).getMemo());
					FavBook favBook = bookList.get(i);
					SearchBookLogic searchBookLogic = new SearchBookLogic();
					int bookId = searchBookLogic.SearchBook(favBook);
					favBook.setBookId(bookId);
					addFavBookLogic.AddFavBook(user, favBook);

				}
				System.out.print(i + 1);
				System.out.println(":");
			}
		}

		response.sendRedirect("FavoriteBookViewServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		BookList bookList = (BookList) session.getAttribute("bookList2");
		//		BookList bookList = (BookList)request.getAttribute("bookList");
		Keyword keyword = (Keyword) session.getAttribute("keyword");
		User user = (User) session.getAttribute("loginUser");
		int max = keyword.getMaximumRecords();
		request.setCharacterEncoding("UTF-8");
		//		ArrayList<Integer> statusList = new ArrayList<Integer>();
		for (int i = 0; i < max; i++) {
			String statusId = request.getParameter("status" + (i + 1));
			System.out.println("status取得" + statusId);

			if (!(statusId == null || statusId.equals("") || statusId.equals("0"))) {
				Status status = new Status(Integer.parseInt(statusId));
				String memo = request.getParameter("memo" + (i + 1));
				bookList.get(i).setStatus(status);
				bookList.get(i).setMemo(memo);

				System.out.println(bookList.get(i).getStatus().getStatus());
				System.out.println(bookList.get(i).getMemo());
				FavBook favBook = bookList.get(i);
				SearchBookLogic searchBookLogic = new SearchBookLogic();
				int bookId = searchBookLogic.SearchBook(favBook);
				favBook.setBookId(bookId);
				AddFavBookLogic addFavBookLogic = new AddFavBookLogic();
				addFavBookLogic.AddFavBook(user, favBook);

			}
			System.out.print(i + 1);
			System.out.println(":");
		}

		doGet(request, response);
	}

}
