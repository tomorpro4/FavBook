package model;

public class LoginUser extends User {

	private String email;
	private String name;
	
	public LoginUser() {
		super();
	}
	public LoginUser(String userId, String email, String pass) {
		super(userId, pass);
		this.email = email;
	}
	public LoginUser(String userId, String email, String pass, String name) {
		super(userId, pass);
		this.email = email;
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	

}
