package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bookidcategoryidlistDAO {

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
	
	
	
	public int insert(int bookListId, int categoryId) {
		// TODO 自動生成されたコンストラクター・スタブ
		readDriver();
		int rs = 0;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql ="INSERT INTO bookidcategoryidlist VALUE(?,?);";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, bookListId);
			pStmt.setInt(2, categoryId);
			System.out.println(pStmt);
			rs = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return rs;

	}

}
