package test;

import java.util.ArrayList;

import dao.BookListDAO;
import model.Book;
import model.Keyword;

public class BookListDAOsearchTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		BookSearchOK();
	}

	public static void BookSearchOK() {
		String isbn = null;
		String title = "%A%";
		String creator = null;
		String issued = "Publisher_A";
		String category = null;
		Keyword keyword = new Keyword(title, isbn, creator, issued, category);
		BookListDAO bookListDAO = new BookListDAO();
		ArrayList<Book> bookList = bookListDAO.searchBook(keyword);
		for(int i=0;i<bookList.size();i++) {
			System.out.println(bookList.get(i).getId());
			System.out.println(bookList.get(i).getTitle());
			System.out.println(bookList.get(i).getIsbn());
			System.out.println(bookList.get(i).getCreator());
			System.out.println(bookList.get(i).getIssued());
			System.out.println(bookList.get(i).getCategory());
			
		}
	}
}
