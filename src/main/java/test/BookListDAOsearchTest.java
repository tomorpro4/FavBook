package test;

import java.util.ArrayList;

import dao.BookListDAO;
import model.Book;
import model.Category;
import model.Creator;
import model.Keyword;
import model.Publisher;
import model.SearchBookLogic;

public class BookListDAOsearchTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		SearchBookTest();
	}

	public static void SearchBookTest() {
		String sql = "INSERT INTO booklist (bookTitle, isbn, publiserId) VALUE(\"オブジェクト指向のためのJava入門\",\"4501531401\",8);";
		String bookTitle = "オブジェクト指向のためのJava入門3";
		String isbn = "4501531401";
		String creatorName = "増田, 英孝, 1965-";
		Creator creator = new Creator(creatorName);
		String publisherName = "東京電機大学出版局";
		Publisher publisher = new Publisher(publisherName);
		String categoryName = "プログラミング (コンピュータ)";
		Category category = new Category(categoryName);
		Book book = new Book(bookTitle, isbn, creator, publisher, category);
		SearchBookLogic searchBookLogic = new SearchBookLogic();
		
		int id = searchBookLogic.SearchBook(book);
		System.out.println(id	);
	}
	
	public static void BookSearchOK() {
		int isbnCon = 0;
		String isbn = null;
		int titleCon = 0;
		String title = "C";
		int creatorCon = 0;
		String creator = "B";
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
