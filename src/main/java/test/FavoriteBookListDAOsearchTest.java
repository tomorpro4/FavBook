package test;

import dao.FavoritelistDAO;
import model.Keyword;
import model.User;

public class FavoriteBookListDAOsearchTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		FavoriteBookListOK();
	}

	public static void FavoriteBookListOK() {
		User user = new User("id","tomorpro4@gmail.com","1234");
		
		int isbnCon = 0;
		String isbn = null;
		int titleCon = 1;
		String title = "C";
		int creatorCon = 0;
		String creator = null;
		int publisherCon = 0;
		String publisher = null;
		int categoryCon = 0;
		String category = null;
		Keyword keyword = new Keyword(titleCon, title, isbnCon, isbn, creatorCon, creator, publisherCon, publisher, categoryCon, category);

		FavoritelistDAO favoritelistDAO = new FavoritelistDAO();
		favoritelistDAO.listFavBook(user, keyword);
	}
}
