package model;

import dao.BookListDAO;
import dao.FavoritelistDAO;

public class AddFavBookLogic {

	public void AddFavBook(User user,FavBook book) {
		// TODO 自動生成されたコンストラクター・スタブ
		
		FavoritelistDAO favoritelistDAO = new FavoritelistDAO();
		FavBook favBook = favoritelistDAO.listFavBook(user, book);
		System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+book.getStatus().getStatus());
		int favBookId = favBook.getFavBookId();
		if(favBookId != 0) {
			favBook = book;
			favBook.setFavBookId(favBookId);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBook.getMemo());
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBook.getStatus().getStatus());
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBook.getBookId());
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"登録済");
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBook.getFavBookId());
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBook.getStatus().getStatusId());
			favoritelistDAO.UpdFavBook(favBook, user);
			
		}else {
			BookListDAO bookListDAO = new BookListDAO();
			favBook = (FavBook)bookListDAO.MachBook(book);
			favBook.setStatus(book.getStatus());
			favBook.setMemo(book.getMemo());
			
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"未登録");
			favoritelistDAO.AddFavBook(favBook, user);

		}
		
	}

}
