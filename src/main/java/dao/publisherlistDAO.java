package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Publisher;

public class publisherlistDAO {
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
	
	
	public Publisher searchPublisherById(Publisher publisher) {
		readDriver();
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;
			String sql = "SELECT * FROM publisherlist WHERE ";
			if (!(publisher.getPublisherId() == 0)) {
				sql = AddAND(i, sql);
				sql += " publisherId = ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			int j = 0;
			if (!(publisher.getPublisherId() == 0)) {
				j++;
				pStmt.setInt(j, publisher.getPublisherId());
			}

			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while (rs.next()) {
				int publisherId = rs.getInt("publisherId");
				String publisherName = rs.getString("publisherName");
				if (!(publisherName == null || publisherName.equals(""))) {
					publisher.setPublisherName(publisherName);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return publisher;
	}
	
	

	public Publisher searchPublisherByName(Publisher publisher) {
		readDriver();
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int i = 0;
			String sql = "SELECT * FROM publisherlist WHERE ";
			if (!(publisher.getPublisherName() == null)) {
				sql = AddAND(i, sql);
				sql += " publisherName = ?";
				i++;
			}
			sql += ";";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			int j = 0;
			if (!(publisher.getPublisherName() == null)) {
				j++;
				pStmt.setString(j, publisher.getPublisherName());
			}

			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while (rs.next()) {
				int publisherId = rs.getInt("publisherId");
				String publisherName = rs.getString("publisherName");
				if (!(publisherId == 0)){
					publisher.setPublisherId(publisherId);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return publisher;
	}

}
