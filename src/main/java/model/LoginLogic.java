package model;

import dao.UserDAO;

public class LoginLogic {
	public LoginUser login(User user) {
		UserDAO dao = new UserDAO();
		LoginUser loginUser = dao.loginUser(user);
		return loginUser;
	}
}
