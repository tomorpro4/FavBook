package model;

public class NewUser extends User {

	private String email;
	private String name;
	
	public NewUser() {
		super();
	}
	public NewUser(String userId, String email, String pass) {
		super(userId, pass);
		this.email = email;
	}
	public NewUser(String userId, String email, String pass, String name) {
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
