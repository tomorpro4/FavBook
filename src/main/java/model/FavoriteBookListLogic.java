package model;

import java.util.ArrayList;

import dao.FavoritelistDAO;

public class FavoriteBookListLogic {
	public ArrayList<FavBook> FabBookListView(LoginUser loginUser) {
		FavoritelistDAO favoritelistDAO = new FavoritelistDAO();
		ArrayList<FavBook> favBookList = favoritelistDAO.listFavBook(loginUser);
		return favBookList;
	}
}
