package model;

import java.io.Serializable;

public class User implements Serializable {

	
	private String userId;
	private String pass;
	private String email;

	
	public String getEmail() {
		return email;
	}

	public User() {}

	public User(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getPass() {
		return pass;
	}

	

}
