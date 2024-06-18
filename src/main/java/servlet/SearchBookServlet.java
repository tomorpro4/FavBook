package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookList;
import model.GetXmlLogic;
import model.Keyword;
import model.LoginUser;
import model.StrToInt;
import model.UrlCreate;
import model.User;
import model.XMLmodel;
import model.XmlToBookLogic;

/**
 * Servlet implementation class SerchBookServlet
 */
@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Keyword keyword = (Keyword)session.getAttribute("keyword");
		request.setCharacterEncoding("UTF-8");
		String nextPosition = request.getParameter("nextPosition");
		if(keyword==null || nextPosition==null) {
			nextPosition="1";
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/bookSearchForm.jsp");
			dispatcher.forward(request, response);
		}else {
			System.out.println("getマックス"+keyword.getMaximumRecords());
			System.out.println("doPostに転送");
			keyword.setStartRecord(Integer.parseInt(nextPosition));
			BookList bookList = (BookList)request.getAttribute("bookList");
//			keyword.setMaximumRecords(0)
			session.setAttribute("keyword", keyword);
			System.out.println("移動します");
			doPost(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		BookList bookList = (BookList)request.getAttribute("bookList");

		request.setCharacterEncoding("UTF-8");
		String bookTitle = request.getParameter("bookTitle");
		System.out.println("移動しました");
		StrToInt strToInt = new StrToInt();
		int bookTitleCon = strToInt.StrToIntLog(request.getParameter("bookTitleCon"));
		System.out.println("移動しました");
		String isbn = request.getParameter("isbn");
		String creatorName = request.getParameter("creatorName");
		int creatorNameCon = strToInt.StrToIntLog(request.getParameter("creatorNameCon"));
		String publisherName = request.getParameter("publisherName");
		int publisherNameCon = strToInt.StrToIntLog(request.getParameter("publisherNameCon"));
		String categoryName = request.getParameter("categoryName");
		int categoryNameCon = strToInt.StrToIntLog(request.getParameter("categoryNameCon"));
		String maximumRecords = request.getParameter("maximumRecords");
		String startRecord = request.getParameter("nextPosition");
		Keyword keyword = (Keyword)session.getAttribute("keyword");
		if(keyword!=null && startRecord!=null) {
			int startRecordI = keyword.getStartRecord();
			if(startRecordI != 0) {
				startRecord = String.valueOf(startRecordI);
			}
		}
		
		if(keyword == null || startRecord == null) {
			System.out.println("ここは大丈夫そう");
			startRecord = "1";
			keyword = new Keyword(bookTitleCon, bookTitle, 3, isbn, creatorNameCon, creatorName, publisherNameCon, publisherName,categoryNameCon, categoryName);
			System.out.println("ここは怪しい");
			keyword.setMaximumRecords(Integer.parseInt(maximumRecords));
		}else {
			System.out.println("ここは怪しい");
//			maximumRecords = String.valueOf(keyword.getMaximumRecords());
//			startRecord = String.valueOf(keyword.getStartRecord());
		}
		UrlCreate urlCreate = new UrlCreate();
		urlCreate.setMaximumRecords(maximumRecords);
		urlCreate.setStartRecord(startRecord);
//		urlCreate.setStartRecord("51");
		String urlStr = urlCreate.CreateSearchUrl(keyword);
		GetXmlLogic getXmlLogic = new GetXmlLogic();
		String xmlStr = getXmlLogic.GetXmlByUrl(urlStr);
		System.out.println("SearchBookServlet;64");
		XMLmodel xmLmodel = new XMLmodel(xmlStr);
		System.out.println("SearchBookServlet;65");
		XmlToBookLogic xmlToBookLogic = new XmlToBookLogic();
		System.out.println("SearchBookServlet;67");
		System.out.println("SearchBookServlet;69");
		User user = (LoginUser)session.getAttribute("loginUser");
		System.out.println("SearchBookServlet;72");
		
//		BookList bookList = xmlToBookLogic.XmlToBookList(user, xmLmodel);
		bookList = xmlToBookLogic.XmlToBookList(user, xmLmodel);
//		bookList = xmlToBookLogic.XmlToBookList(user, xmLmodel);
		System.out.println("SearchBookServlet;69");
		System.out.println("favbookiD");
//		System.out.println(((FavBook)bookList.get(0)).getFavBookId());
		session.setAttribute("bookList2", bookList);
		request.setAttribute("bookList", bookList);
//		keyword.setStartRecord(bookList.getNextRecord());
		System.out.println("マックス:"+keyword.getMaximumRecords());
		session.setAttribute("keyword", keyword);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/bookSearchForm.jsp");
		dispatcher.forward(request, response);
	}

}
