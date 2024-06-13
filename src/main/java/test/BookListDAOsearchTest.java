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
		int isbnCon = 0;
		String isbn = null;
		int titleCon = 3;
		String title = "Title_C";
		int creatorCon = 0;
		String creator = null;
		int publisherCon = 0;
		String publisher = null;
		int categoryCon = 0;
		String category = null;
		Keyword keyword = new Keyword(titleCon, title, isbnCon, isbn, creatorCon, creator, publisherCon, publisher, categoryCon, category);

		BookListDAO bookListDAO = new BookListDAO();
		ArrayList<Book> bookList = bookListDAO.searchBook(keyword);
		for(int i=0;i<bookList.size();i++) {
			System.out.println(bookList.get(i).getBookId());
			System.out.println(bookList.get(i).getBookTitle());
			System.out.println(bookList.get(i).getIsbn());
			System.out.println(bookList.get(i).getCreator().getCreatorName());
			System.out.println(bookList.get(i).getPublisher().getPublisherName());
			System.out.println(bookList.get(i).getCategory().getCategoryName());
			
		}
	}
}
