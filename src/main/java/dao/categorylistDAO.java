package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Category;

public class categorylistDAO {
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
	
	
	public Category insertCategory(Category category) {
		readDriver();
		int rs;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO categorylist (categoryName) VALUE(?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, category.getCategoryName());
			rs = pStmt.executeUpdate();
			category = searchCategoryByName(category);
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return category;
	}
	
	
	
	
	
	
	public Category searchCategoryById(Category category) {
		readDriver();
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;
			String sql = "SELECT * FROM categorylist WHERE ";
			if (!(category.getCategoryId() == 0)) {
				sql = AddAND(i, sql);
				sql += " categoryId = ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			int j = 0;
			if (!(category.getCategoryId() == 0)) {
				j++;
				pStmt.setInt(j, category.getCategoryId());
			}

			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while (rs.next()) {
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				if (!(categoryName == null || categoryName.equals(""))) {
					category.setCategoryName(categoryName);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return category;
	}
	
	

	public Category searchCategoryByName(Category category) {
		readDriver();
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;
			String sql = "SELECT * FROM categorylist WHERE ";
			if (!(category.getCategoryName() == null)) {
				sql = AddAND(i, sql);
				sql += " categoryName = ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			int j = 0;
			if (!(category.getCategoryName() == null)) {
				j++;
				pStmt.setString(j, category.getCategoryName());
			}

			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while (rs.next()) {
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				if (!(categoryId == 0)){
					category.setCategoryId(categoryId);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return category;
	}

}
