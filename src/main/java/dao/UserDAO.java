package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.LoginUser;
import model.NewUser;
import model.User;

public class UserDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/favorite_book";
	private final String DB_USER = "root";
	private final String DB_PASS = "Tomo_20050124";

	public void readDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバを読み込めませんでした") ;
//			LogTest01.LogTest("ドライバを読み込めませんでした");
		}
	}
	
	
	public LoginUser loginUser(User user) {
		ResultSet rs = null;
		readDriver();
		LoginUser loginUser = null;
		try(Connection conn= DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql ="SELECT userId, pass, name, email FROM user WHERE (userId = ? OR email = ?) AND pass = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql); 
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getEmail());
			pStmt.setString(3, user.getPass());
			System.out.println(pStmt);
			rs = pStmt.executeQuery();
			System.out.println("rs:" + rs);
			while(rs.next()) {
				String userId = rs.getString("userId");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				String email = rs.getString("email");
				System.out.println(email);
				loginUser = new LoginUser(userId, email, pass, name);
				System.out.println(loginUser.getEmail());
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return loginUser;
	}
	
	
	
	public boolean registerUser(NewUser newUser) {
		int rs;
		readDriver();
		try(Connection conn= DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql ="INSERT INTO user (userId, email, pass, name) VALUES(?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql); 
			
			pStmt.setString(1, newUser.getUserId());
			pStmt.setString(2, newUser.getEmail());
			pStmt.setString(3, newUser.getPass());
			pStmt.setString(4, newUser.getName());
			System.out.println(pStmt);

			rs = pStmt.executeUpdate();
			if(rs>0) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
