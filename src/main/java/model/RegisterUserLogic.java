package model;

import dao.UserDAO;

public class RegisterUserLogic {
	
	public boolean registerUser(NewUser newUser) {
		UserDAO userDAO = new UserDAO();
		boolean bo = userDAO.registerUser(newUser);
		return bo;
	}
}
