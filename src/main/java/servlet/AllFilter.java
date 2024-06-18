package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.LoginUser;

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
		
		
		LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
//		System.out.println("fill:"+loginUser);
		if(loginUser == null) {
//		if(loginUser == null && from == null) {
			System.out.println("Servlet前フィルタ実行");
//			((HttpServletResponse)response).sendRedirect("LoginServlet");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../LoginServlet");
			dispatcher.forward(request, response);
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
