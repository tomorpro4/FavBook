package model;

import dao.BookListDAO;
import dao.FavoritelistDAO;

public class AddFavBookLogic {

	public void AddFavBook(User user,FavBook book) {
		// TODO 自動生成されたコンストラクター・スタブ
		
		FavoritelistDAO favoritelistDAO = new FavoritelistDAO();
		FavBook favBook = favoritelistDAO.listFavBook(user, book);
		System.out.println(book.getStatus().getStatus());
		int favBookId = favBook.getFavBookId();
		if(favBookId != 0) {
			favBook = book;
			favBook.setFavBookId(favBookId);
			System.out.println(favBook.getMemo());
			System.out.println(favBook.getStatus().getStatus());
			System.out.println(favBook.getBookId());
			System.out.println("登録済");
			System.out.println(favBook.getFavBookId());
			System.out.println(favBook.getStatus().getStatusId());
			favoritelistDAO.UpdFavBook(favBook, user);
			
		}else {
			BookListDAO bookListDAO = new BookListDAO();
			favBook = (FavBook)bookListDAO.MachBook(book);
			favBook.setStatus(book.getStatus());
			favBook.setMemo(book.getMemo());
			
			System.out.println("未登録");
			favoritelistDAO.AddFavBook(favBook, user);

		}
		
	}

}
