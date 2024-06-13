package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Category;
import model.Creator;
import model.FavBook;
import model.Keyword;
import model.Publisher;
import model.Status;
import model.User;

public class FavoritelistDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/favorite_book";
	private final String DB_USER = "root";
	private final String DB_PASS = "0322ja";

	public void readDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバを読み込めませんでした");
			//			LogTest01.LogTest("ドライバを読み込めませんでした");
		}
	}

	public String AddAND(int i, String sql) {
		if(i==0) sql += " WHERE";
		if(i>0) sql += " AND";
		return sql;
	}
	public String AddOR(int i, String sql) {
		if(i==0) sql += " WHERE";
		if(i>0) sql += " OR";
		return sql;
	}
	
	public ArrayList<FavBook> listFavBook(User user) {
		readDriver();
		ResultSet rs = null;
		ArrayList<FavBook> favBookList = new ArrayList<FavBook>();
		
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;
			String sql = "SELECT favBookId, userId, favoritebooklist.statusId, statuslist.status, memo,  favoritebooklist.bookId,booklist.bookTitle, isbn, booklist.creatorId, creatorlist.creatorName, booklist.publisherId, publisherlist.publisherName, booklist.categoryId, categorylist.categoryName  FROM favoritebooklist JOIN booklist ON favoritebooklist.bookId = booklist.bookListId JOIN publisherlist ON booklist.publisherId = publisherlist.publisherId JOIN creatorlist ON booklist.creatorId = creatorlist.creatorId JOIN categorylist ON booklist.categoryId = categorylist.categoryId JOIN statuslist ON favoritebooklist.statusId = statuslist.statusId";
			
			
			if (!(user.getUserId() == null || user.getUserId().equals(""))) {
				sql = AddAND(i, sql);
				sql += " userId = ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			int j = 0;
			if (!(user.getUserId() == null || user.getUserId().equals(""))) {
				j++;
				pStmt.setString(j, user.getUserId());
			}

			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while (rs.next()) {
				int favBookId = Integer.parseInt(rs.getString("favBookId"));
				int statusId = Integer.parseInt(rs.getString("statusId"));
				String statusStr = rs.getString("status");
				Status status = new Status(statusId, statusStr);
				String memo = rs.getString("memo");
				int bookId = Integer.parseInt(rs.getString("bookId"));
				String bookTitle = rs.getString("bookTitle");
				String isbn = rs.getString("isbn");
				int creatorId = Integer.parseInt(rs.getString("creatorId"));
				String creatorName = rs.getString("creatorName");
				Creator creator = new Creator(creatorId, creatorName);
				int publisherId = Integer.parseInt(rs.getString("publisherId"));
				String publisherName = rs.getString("creatorName");
				Publisher publisher = new Publisher(publisherId,publisherName);
				int categoryId = Integer.parseInt(rs.getString("categoryId"));
				String categoryName = rs.getString("publisherName");
				Category category = new Category(categoryId, categoryName);
				if (!(bookTitle == null || bookTitle.equals(""))) {
					FavBook favBook = new FavBook(favBookId, status, memo, bookId, bookTitle, isbn, creator, publisher, category);
					favBookList.add(favBook);
					System.out.println(favBookId);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return favBookList;
	}
	
	public ArrayList<FavBook> listFavBook(User user, Keyword keyword) {
		readDriver();
		ResultSet rs = null;
		ArrayList<FavBook> favBookList = new ArrayList<FavBook>();
		
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			
			
			int i = 0;
			String sql = "SELECT favBookId, userId, favoritebooklist.statusId, statuslist.status, memo,  favoritebooklist.bookId,booklist.bookTitle, isbn, booklist.creatorId, creatorlist.creatorName, booklist.publisherId, publisherlist.publisherName, booklist.categoryId, categorylist.categoryName  FROM favoritebooklist JOIN booklist ON favoritebooklist.bookId = booklist.bookListId JOIN publisherlist ON booklist.publisherId = publisherlist.publisherId JOIN creatorlist ON booklist.creatorId = creatorlist.creatorId JOIN categorylist ON booklist.categoryId = categorylist.categoryId JOIN statuslist ON favoritebooklist.statusId = statuslist.statusId";
			
			
			if (!(user.getUserId() == null || user.getUserId().equals(""))) {
				sql = AddAND(i, sql);
				sql += " userId = ?";
				i++;
			}
			if (!(keyword.getTitle() == null || keyword.getTitle().equals(""))) {
				sql = AddAND(i, sql);
				sql += " BookTitle LIKE ?";
				i++;
			}
			if (!(keyword.getIsbn() == null || keyword.getIsbn().equals(""))) {
				sql = AddAND(i, sql);
				sql += " ISBN LIKE ?";
				i++;
			}
			if (!(keyword.getCreator() == null || keyword.getCreator().equals(""))) {
				sql = AddAND(i, sql);
				sql += " Creator LIKE ?";
				i++;
			}
			if (!(keyword.getPublisher() == null || keyword.getPublisher().equals(""))) {
				sql = AddAND(i, sql);
				sql += " Publisher LIKE ?";
				i++;
			}
			if (!(keyword.getCategory() == null || keyword.getCategory().equals(""))) {
				sql = AddAND(i, sql);
				sql += " Category LIKE ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
			
			
			int j = 0;
			if (!(user.getUserId() == null || user.getUserId().equals(""))) {
				j++;
				pStmt.setString(j, user.getUserId());
			}
			if (!(keyword.getTitle() == null || keyword.getTitle().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getNewTitle());
			}
			if (!(keyword.getIsbn() == null || keyword.getIsbn().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getNewIsbn());
			}
			if (!(keyword.getCreator() == null || keyword.getCreator().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getNewCreator());
			}
			if (!(keyword.getPublisher() == null || keyword.getPublisher().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getNewPublisher());
			}
			if (!(keyword.getCategory() == null || keyword.getCategory().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getNewCategory());
			}

			
			
			
			
			
			
			
			
			
			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while (rs.next()) {
				int favBookId = Integer.parseInt(rs.getString("favBookId"));
				int statusId = Integer.parseInt(rs.getString("statusId"));
				String statusStr = rs.getString("status");
				Status status = new Status(statusId, statusStr);
				String memo = rs.getString("memo");
				int bookId = Integer.parseInt(rs.getString("bookId"));
				String bookTitle = rs.getString("bookTitle");
				String isbn = rs.getString("isbn");
				int creatorId = Integer.parseInt(rs.getString("creatorId"));
				String creatorName = rs.getString("creatorName");
				Creator creator = new Creator(creatorId, creatorName);
				int publisherId = Integer.parseInt(rs.getString("publisherId"));
				String publisherName = rs.getString("creatorName");
				Publisher publisher = new Publisher(publisherId,publisherName);
				int categoryId = Integer.parseInt(rs.getString("categoryId"));
				String categoryName = rs.getString("publisherName");
				Category category = new Category(categoryId, categoryName);
				if (!(bookTitle == null || bookTitle.equals(""))) {
					FavBook favBook = new FavBook(favBookId, status, memo, bookId, bookTitle, isbn, creator, publisher, category);
					favBookList.add(favBook);
					System.out.println(favBookId);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return favBookList;
	}

	
	

}
