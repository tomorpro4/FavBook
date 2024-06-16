package test;

import dao.FavoritelistDAO;
import model.AddFavBookLogic;
import model.Category;
import model.Creator;
import model.FavBook;
import model.Keyword;
import model.Publisher;
import model.Status;
import model.User;

public class FavoriteBookListDAOsearchTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		insertTest();
	}
	
	public static void insertTest() {
		User user = new User("tt", "1234");
		Status status = new Status(4);
		String memo = "用aaaaaaaaaaaaaaaaaaa";
		int bookId = 24;
		String bookTitle = "オブジェクト指向のためのJava入門2";
		String isbn = "4501531401";
		String creatorName = "増田, 英孝, 1965-";
		Creator creator = new Creator(creatorName);
		String publisherName = "東京電機大学出版局";
		Publisher publisher = new Publisher(publisherName);
		String categoryName = "プログラミング (コンピュータ)";
		Category category = new Category(categoryName);
//		FavBook book = new FavBook( bookId,  bookTitle,  isbn,  creator,  publisher,  category);
		FavBook book = new FavBook();
		book.setBookId(bookId);
		book.setStatus(status);
		book.setMemo(memo);
//		FavoritelistDAO favoritelistDAO = new FavoritelistDAO();
//		FavBook favBook = favoritelistDAO.listFavBook(user, book);
//		if(favBook.getFavBookId() != 0) {
//			System.out.println(favBook.getBookId());
//		}else {
//			System.out.println("未登録");
//		}
		
		AddFavBookLogic addFavBookLogic = new AddFavBookLogic();
		addFavBookLogic.AddFavBook(user, book);
	}

	public static void FavoriteBookListOK() {

		User user = new User("id","1234");
		
		int isbnCon = 0;
		String isbn = null;
		int titleCon = 0;
		String title = "i";
		int creatorCon = 0;
		String creator = "C";
		int publisherCon = 0;
		String publisher = "A";
		int categoryCon = 0;
		String category = null;
		Keyword keyword = new Keyword(titleCon, title, isbnCon, isbn, creatorCon, creator, publisherCon, publisher, categoryCon, category);

		FavoritelistDAO favoritelistDAO = new FavoritelistDAO();
		favoritelistDAO.listFavBook(user, keyword);
	}
}
