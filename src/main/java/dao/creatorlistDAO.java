package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Creator;

public class creatorlistDAO {
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
	
	
	public Creator insertCreator(Creator creator) {
		readDriver();
		int rs;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO creatorlist (creatorName) VALUE(?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, creator.getCreatorName());
			rs = pStmt.executeUpdate();
			creator = searchCreatorByName(creator);
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return creator;
	}
	
	
	public Creator searchCreatorById(Creator creator) {
		readDriver();
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;
			String sql = "SELECT * FROM creatorlist WHERE ";
			if (!(creator.getCreatorId() == 0)) {
				sql = AddAND(i, sql);
				sql += " creatorId = ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			int j = 0;
			if (!(creator.getCreatorId() == 0)) {
				j++;
				pStmt.setInt(j, creator.getCreatorId());
			}

			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+pStmt);
			rs = pStmt.executeQuery();
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"rs:" + rs);
			while (rs.next()) {
				int creatorId = rs.getInt("creatorId");
				String creatorName = rs.getString("creatorName");
				if (!(creatorName == null || creatorName.equals(""))) {
					creator.setCreatorName(creatorName);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return creator;
	}
	
	

	public Creator searchCreatorByName(Creator creator) {
		readDriver();
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;
			String sql = "SELECT * FROM creatorlist WHERE ";
			if (!(creator.getCreatorName() == null)) {
				sql = AddAND(i, sql);
				sql += " creatorName = ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			int j = 0;
			if (!(creator.getCreatorName() == null)) {
				j++;
				pStmt.setString(j, creator.getCreatorName());
			}

			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+pStmt);
			rs = pStmt.executeQuery();
			System.out.println(this.getClass().getName()+":"+new Throwable().getStackTrace()[0].getLineNumber()+";"+"rs:" + rs);
			while (rs.next()) {
				int creatorId = rs.getInt("creatorId");
				String creatorName = rs.getString("creatorName");
				if (!(creatorId == 0)){
					creator.setCreatorId(creatorId);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return creator;
	}

}
