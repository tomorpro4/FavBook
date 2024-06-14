package test;

import dao.UserDAO;
import model.LoginUser;
import model.NewUser;
import model.User;

public class UserDAO_loginTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		loginOKTest();
		loginNGTest();
		registerOKTest();
		registerNGTest();
	}
	
	
	public static void loginOKTest() {
		User user = new User("tomo", "1234");
		UserDAO userDAO = new UserDAO();
		LoginUser loginUser = userDAO.loginUser(user);
		if(
				(loginUser.getUserId() != null && loginUser.getUserId().equals("tomo")) &&
				(loginUser.getName() != null && loginUser.getName().equals("津島")) &&
				(loginUser.getEmail() != null && loginUser.getEmail().equals("tomorpro4@gmail.com"))) {
			System.out.println("loginOKTest成功");
		}else {
			System.out.println("loginOKTest失敗");

		}
	}
	
	
	public static void loginNGTest() {
		User user = new User("tomo","124");
		UserDAO userDAO = new UserDAO();
		LoginUser loginUser = userDAO.loginUser(user);
		if(loginUser == null) {
			System.out.println("loginNGTest成功");
		}else {
			System.out.println("loginNGTest失敗");
		}
	}
	
	
	public static void registerOKTest() {
		NewUser newUser = new NewUser("idascd","a@accccccccccsaaa","pass","name");
		UserDAO userDAO = new UserDAO();
		boolean bo = userDAO.registerUser(newUser);
		
		if(bo) {
			System.out.println("registerOKTest成功");
		}else {
			System.out.println("registerOKTest失敗");

		}
	}
	
	public static void registerNGTest() {
		NewUser newUser = new NewUser("ttatdadca","a@aadsa","pass","name");
		UserDAO userDAO = new UserDAO();
		boolean bo = userDAO.registerUser(newUser);
		
		if(!bo) {
			System.out.println("registerNGTest成功");
		}else {
			System.out.println("registerNGTest失敗");

		}
	}

}
