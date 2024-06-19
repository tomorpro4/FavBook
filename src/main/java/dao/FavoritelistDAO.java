package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;
import model.BookList;
import model.Category;
import model.Creator;
import model.FavBook;
import model.Keyword;
import model.Publisher;
import model.Status;
import model.StrToInt;
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
			String sql = "SELECT favBookId, userId, favoritebooklist.statusId, statuslist.status, memo,  favoritebooklist.bookId,booklist.bookTitle, isbn, bookidcreatoridlist.creatorId, creatorlist.creatorName, booklist.publisherId, publisherlist.publisherName, bookidcategoryidlist.categoryId, categorylist.categoryName FROM favoritebooklist JOIN booklist ON favoritebooklist.bookId = booklist.bookListId JOIN publisherlist ON booklist.publisherId = publisherlist.publisherId JOIN bookidcreatoridlist ON booklist.bookListId = bookidcreatoridlist.bookListId JOIN bookidcategoryidlist ON booklist.bookListId = bookidcategoryidlist.bookListId JOIN creatorlist ON bookidcreatoridlist.creatorId = creatorlist.creatorId JOIN categorylist ON bookidcategoryidlist.categoryId = categorylist.categoryId JOIN statuslist ON favoritebooklist.statusId = statuslist.statusId";
			
			
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

			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+pStmt);
			rs = pStmt.executeQuery();
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"rs:" + rs);
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
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBookId);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return favBookList;
	}
	
	public BookList listFavBook(User user, Keyword keyword) {
		readDriver();
		ResultSet rs = null;
		BookList favBookList = new BookList();
		
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			
			
			int i = 0;
			String sql = "SELECT favBookId, userId, favoritebooklist.statusId, statuslist.status, memo,  favoritebooklist.bookId,booklist.bookTitle, isbn, bookidcreatoridlist.creatorId, creatorlist.creatorName, booklist.publisherId, publisherlist.publisherName, bookidcategoryidlist.categoryId, categorylist.categoryName FROM favoritebooklist JOIN booklist ON favoritebooklist.bookId = booklist.bookListId JOIN publisherlist ON booklist.publisherId = publisherlist.publisherId JOIN bookidcreatoridlist ON booklist.bookListId = bookidcreatoridlist.bookListId JOIN bookidcategoryidlist ON booklist.bookListId = bookidcategoryidlist.bookListId JOIN creatorlist ON bookidcreatoridlist.creatorId = creatorlist.creatorId JOIN categorylist ON bookidcategoryidlist.categoryId = categorylist.categoryId JOIN statuslist ON favoritebooklist.statusId = statuslist.statusId";
			
			
			if (!(user.getUserId() == null || user.getUserId().equals(""))) {
				sql = AddAND(i, sql);
				sql += " userId = ?";
				i++;
			}
			if (!(keyword.getTitle() == null || keyword.getTitle().equals(""))) {
				sql = AddAND(i, sql);
				sql += " bookTitle LIKE ?";
				i++;
			}
			if (!(keyword.getIsbn() == null || keyword.getIsbn().equals(""))) {
				sql = AddAND(i, sql);
				sql += " isbn LIKE ?";
				i++;
			}
			if (!(keyword.getCreator() == null || keyword.getCreator().equals(""))) {
				sql = AddAND(i, sql);
				sql += " creatorName LIKE ?";
				i++;
			}
			if (!(keyword.getPublisher() == null || keyword.getPublisher().equals(""))) {
				sql = AddAND(i, sql);
				sql += " publisherName LIKE ?";
				i++;
			}
			if (!(keyword.getCategory() == null || keyword.getCategory().equals(""))) {
				sql = AddAND(i, sql);
				sql += " categoryName LIKE ?";
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

			
			
			
			
			
			
			
			
			
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+pStmt);
			rs = pStmt.executeQuery();
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"rs:" + rs);
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
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBookId);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return favBookList;
	}
	
	
	
	public FavBook listFavBook(User user, Book book) {
		readDriver();
		ResultSet rs = null;
		BookList favBookList = new BookList();
		FavBook favBook = new FavBook(book);
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			
			
			int i = 0;
			String sql = "SELECT favBookId, favoritebooklist.statusId, statuslist.status, memo FROM favoritebooklist JOIN statuslist ON favoritebooklist.statusId = statuslist.statusId";
			
			
			if (!(user.getUserId() == null || user.getUserId().equals(""))) {
				sql = AddAND(i, sql);
				sql += " userId = ?";
				i++;
			}
			if (!(book.getBookId() == 0)) {
				sql = AddAND(i, sql);
				sql += " bookId LIKE ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
			
			
			int j = 0;
			if (!(user.getUserId() == null || user.getUserId().equals(""))) {
				j++;
				pStmt.setString(j, user.getUserId());
			}
			if (!(book.getBookId() == 0)){
				j++;
				pStmt.setInt(j, book.getBookId());
			}

			
			
			
			
			
			
			
			

			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+pStmt);
			rs = pStmt.executeQuery();
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"rs:" + rs);
			
			while (rs.next()) {

				StrToInt strToInt = new StrToInt();
				int favBookId = strToInt.StrToIntLog(rs.getString("favBookId"));
				int statusId = strToInt.StrToIntLog(rs.getString("statusId"));
				String statusStr = rs.getString("status");
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"BookListId:"+book.getBookId());
				System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"FavBookId:"+favBookId);
				Status status = new Status(statusId, statusStr);
				String memo = rs.getString("memo");
				if (!(favBookId == 0)) {
					favBook = new FavBook(book);
					favBook.setFavBookId(favBookId);
					favBook.setStatus(status);
					favBook.setMemo(memo);
					favBookList.add(favBook);
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBookId);
					System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBook);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return favBook;
	}
	
	
	
	

	public int UpdFavBook(FavBook favBook, User user) {
		readDriver();
		int rs = 0;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "UPDATE favoritebooklist SET statusId = ? , memo=? WHERE favBookId = ?;";
			int favBookId = favBook.getFavBookId();
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+favBookId);
			String memo = favBook.getMemo();
			int statusId = favBook.getStatus().getStatusId();
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, statusId);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"++++++++++++++++++++;;");
			pStmt.setString(2, memo);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"++++++++++++++++++++;;");
			pStmt.setInt(3, favBookId);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+pStmt);
			rs = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return rs;

	}
	
	
	
	public int AddFavBook(FavBook favBook, User user) {
		readDriver();
		int rs = 0;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO favoritebooklist (bookId, userId, statusId, memo) VALUE(?,?,?,?);";
			int bookId = favBook.getBookId();
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookId);
			String userId = user.getUserId();
			int statusId = favBook.getStatus().getStatusId();
			String memo = favBook.getMemo();
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"++++++++++++++++++++;;");
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+bookId);
			pStmt.setInt(1, bookId);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"++++++++++++++++++++;;");
			pStmt.setString(2, userId);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"++++++++++++++++++++;;");
			pStmt.setInt(3, statusId);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"++++++++++++++++++++;;");
			pStmt.setString(4, memo);
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"++++++++++++++++++++;;");
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+pStmt);
			rs = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return rs;

	}

}
