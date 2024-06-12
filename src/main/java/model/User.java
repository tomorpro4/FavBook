package model;

import java.io.Serializable;

public class User implements Serializable {

	
	private String userId;
	private String pass;
	private String email;
	
	
	public User() {}
	public User(String userId, String email, String pass) {
		this.userId = userId;
		this.pass = pass;
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public String getPass() {
		return pass;
	}
	public String getEmail() {
		return email;
	}
	
	

}
