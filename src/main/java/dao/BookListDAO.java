package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Book;
import model.Keyword;

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
		if(i>0) sql += " AND";
		return sql;
	}
	public String AddOR(int i, String sql) {
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

			String sql = "SELECT BookListId, BookTitle, ISBN, Creator, Publisher, Category, Photo FROM book_list JOIN  WHERE";
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
			if (!(keyword.getIssued() == null || keyword.getIssued().equals(""))) {
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
			if (!(keyword.getTitle() == null || keyword.getTitle().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getTitle());
			}
			if (!(keyword.getIsbn() == null || keyword.getIsbn().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getIsbn());
			}
			if (!(keyword.getCreator() == null || keyword.getCreator().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getCreator());
			}
			if (!(keyword.getIssued() == null || keyword.getIssued().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getIssued());
			}
			if (!(keyword.getCategory() == null || keyword.getCategory().equals(""))) {
				j++;
				pStmt.setString(j, keyword.getCategory());
			}

			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while (rs.next()) {
				String id = rs.getString("BookListId");
				String isbn = rs.getString("ISBN");
				String title = rs.getString("BookTitle");
				//				ArrayList<String> creator = new ArrayList<String>();
				//				creator.add(rs.getString("Creator"));
				String creator = rs.getString("Creator");
				String issued = rs.getString("Publisher");
				String category = rs.getString("Category");
				String recordCategory = "";
				String recordSubCategory = "";
				if (!(id == null || id.equals(""))) {
//					System.out.println(id);
//					System.out.println(title);
//					System.out.println(isbn);
//					System.out.println(creator);
//					System.out.println(issued);
//					System.out.println(category);
					Book book = new Book(id, title, isbn, creator, issued, category);
					bookList.add(book);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bookList;
	}

}
