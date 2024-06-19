package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookList;
import model.FavBook;
import model.Keyword;
import model.LoginUser;
import model.SearchBookLogic;
import model.Status;
import model.User;

/**
 * Servlet Filter implementation class AllFilter
 */
@WebFilter("/Favorite/*")
public class AllFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AllFilter() {
        super();
        // TODO Auto-generated constructor stub
		System.out.println("Allフィルタ実行");

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		request.setCharacterEncoding("UTF-8");
		
//		String from = (String)session.getAttribute("from");
//		System.out.println(from);
		
//		String from = "login";
		
		
		
		
		
		
		BookList addFavBookList = new BookList();
		
		BookList bookList = (BookList)session.getAttribute("bookList2");
//		BookList bookList = (BookList)request.getAttribute("bookList");
		Keyword keyword = (Keyword)session.getAttribute("keyword");
		User user = (User)session.getAttribute("loginUser");
		int max = keyword.getMaximumRecords();
		request.setCharacterEncoding("UTF-8");
//		ArrayList<Integer> statusList = new ArrayList<Integer>();
		for(int i=0;i<max;i++) {
			String statusId = request.getParameter("status" + (i+1));
			System.out.println("status取得" + statusId);
			
			if(!(statusId == null || statusId.equals("") || statusId.equals("0"))) {
				Status status = new Status(Integer.parseInt(statusId));
				String memo = request.getParameter("memo" + (i+1));
				bookList.get(i).setStatus(status);
				bookList.get(i).setMemo(memo);
				
				System.out.println(bookList.get(i).getStatus().getStatus());
				System.out.println(bookList.get(i).getMemo());
				FavBook favBook = bookList.get(i);
				SearchBookLogic searchBookLogic = new SearchBookLogic();
				int bookId = searchBookLogic.SearchBook(favBook);
				favBook.setBookId(bookId);
				addFavBookList.add(favBook);
//				AddFavBookLogic addFavBookLogic = new AddFavBookLogic();
//				addFavBookLogic.AddFavBook(user, favBook);
				
			}
			
			
			
			
			System.out.print(i+1);
			System.out.println(":");
		}

		
		
		
		
		
		
		
		
		
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
//		System.out.println("fill:"+loginUser);
		if(loginUser == null) {
//		if(loginUser == null && from == null) {
			System.out.println("Servlet前フィルタ実行");

			session.setAttribute("addFavBookList", addFavBookList);
			session.setAttribute("aaa", "aaa");
			
			
			
			((HttpServletResponse)response).sendRedirect("../LoginServlet");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("../LoginServlet");
//			dispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
		
		
		// pass the request along the filter chain
//		session.setAttribute("from", "login");

		System.out.println("Servlet後フィルタ実行");

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
