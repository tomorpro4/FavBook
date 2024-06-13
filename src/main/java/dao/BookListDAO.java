package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Book;
import model.Category;
import model.Creator;
import model.Keyword;
import model.Publisher;

public class BookListDAO {

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
	
	
	public ArrayList<Book> searchBook(Keyword keyword) {
		//		String isbn;
		//		String title;
		//		ArrayList<String> creator;
		//		String issued;
		//		String category;
		//		String recordCategory;
		//		String recordSubCategory;

		readDriver();
		ArrayList<Book> bookList = new ArrayList<Book>();
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;

			String sql = "SELECT bookListId, bookTitle, isbn, creatorlist.creatorId, creatorName, publisherlist.publisherId, publisherName, categorylist.categoryId, categoryName, Photo FROM booklist JOIN publisherlist ON booklist.publisherId = publisherlist.publisherId JOIN creatorlist ON booklist.creatorId = creatorlist.creatorId JOIN categorylist ON booklist.categoryId = categorylist.categoryId";
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
				int bookId = Integer.parseInt(rs.getString("bookListId"));
				String isbn = rs.getString("isbn");
				String bookTitle = rs.getString("bookTitle");
				//				ArrayList<String> creator = new ArrayList<String>();
				//				creator.add(rs.getString("Creator"));
				Creator creator = new Creator(Integer.parseInt(rs.getString("creatorId")), rs.getString("creatorName"));
				Publisher publisher = new Publisher(Integer.parseInt(rs.getString("publisherId")), rs.getString("publisherName"));
				Category category = new Category(Integer.parseInt(rs.getString("categoryId")), rs.getString("categoryName"));
				String recordCategory = "";
				String recordSubCategory = "";
				if (!(bookTitle == null || bookTitle.equals(""))) {
//					System.out.println(id);
//					System.out.println(title);
//					System.out.println(isbn);
//					System.out.println(creator);
//					System.out.println(issued);
//					System.out.println(category);
					Book book = new Book(bookId, bookTitle, isbn, creator, publisher, category);
					bookList.add(book);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bookList;
	}

}
