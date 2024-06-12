package model;

public class LoginUser extends User {

	private String name;
	
	public LoginUser() {
		super();
	}
	public LoginUser(String userId, String email, String pass) {
		super(userId, email, pass);
	}
	public LoginUser(String userId, String email, String pass, String name) {
		super(userId, email, pass);
		this.name = name;
	}
	public String getName() {
		return name;
	}
	

}
