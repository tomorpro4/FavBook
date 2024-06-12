package model;

public class NewUser extends User {

	private String name;
	
	public NewUser() {
		super();
	}
	public NewUser(String userId, String email, String pass) {
		super(userId, pass, email);
	}
	public NewUser(String userId, String email, String pass, String name) {
		super(userId, pass, email);
		this.name = name;
	}
	public String getName() {
		return name;
	}
	

}
