package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class GetQrServlet
 */
@WebServlet("/GetQrServlet")
public class GetQrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isbn = request.getParameter("isbn");
		if(isbn.length()==13 && isbn.indexOf("978")==0){
			doqr(request, response, isbn);
		}else if(isbn.length()==13 && isbn.indexOf("979")==0){
			doqr(request, response, isbn);
		}if(isbn.length()==10){
			doqr(request, response, isbn);

			
//			System.out.println("ISBN"+isbn);
//			Keyword keyword = new Keyword(0,isbn);
//			BookList bookList = new BookList();
//			
//			UrlCreate urlCreate = new UrlCreate();
//			urlCreate.setMaximumRecords("1");
//			urlCreate.setStartRecord("1");
//			//		urlCreate.setStartRecord("51");
//			String urlStr = urlCreate.CreateSearchUrl(keyword);
//			GetXmlLogic getXmlLogic = new GetXmlLogic();
//			String xmlStr = getXmlLogic.GetXmlByUrl(urlStr);
//			XMLmodel xmLmodel = new XMLmodel(xmlStr);
//			XmlToBookLogic xmlToBookLogic = new XmlToBookLogic();
//			HttpSession session = request.getSession();
//			User user = (LoginUser) session.getAttribute("loginUser");
//			bookList = xmlToBookLogic.XmlToBookList(user, xmLmodel);
//			request.setAttribute("bookList", bookList);
//			if(bookList.size()>0) {
//				JSONObject mapp = new JSONObject();
//				String bookTitle =bookList.get(0).getBookTitle();
//				System.out.println(bookTitle);
//				mapp.put("title", bookTitle);
//				String mappStr = mapp.toString();
//				System.out.println(mappStr);
//				response.getWriter().write(mappStr);
//			}
		}
		
	}

	protected void doqr(HttpServletRequest request, HttpServletResponse response,String isbn) throws ServletException, IOException{
		JSONObject mapp = new JSONObject();
		
		String url = "SearchBookServlet?bookTitle=&bookTitleCon=0&isbn="+isbn+"&isbnCon=&creatorName=&creatorNameCon=0&publisherName=&publisherNameCon=0&categoryName=&categoryNameCon=0&maximumRecords=1&action=search";
		mapp.put("url", url);
		String mappStr = mapp.toString();
		System.out.println(mappStr);
		response.getWriter().write(mappStr);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
