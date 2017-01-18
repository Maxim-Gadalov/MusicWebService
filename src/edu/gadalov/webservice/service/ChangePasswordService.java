package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.util.EncryptionPassword;
import edu.gadalov.webservice.validation.UserValidation;

public class ChangePasswordService {
	private static final String ERROR_MESSAGE_PASSWORD_VALIDITY = "Password is invalid";
	private static final String ERROR_MESSAGE_PASSWORD_CONFIRM = "Passwords must match";

	public String checkPassword(String password,String confirmPassword,User user){
		String errorMessage = new String();
		UserValidation validation = new UserValidation();
		if(!validation.passwordValidity(password)){
			errorMessage = ERROR_MESSAGE_PASSWORD_VALIDITY;
		}
		if(!password.equals(confirmPassword)){
			errorMessage = ERROR_MESSAGE_PASSWORD_CONFIRM;
		}
		return errorMessage;
	}
	public boolean changePassword(User user,String password){
		UserDAO userDAO = new UserDAO();
		boolean result = false;
		user.setPassword(EncryptionPassword.encrypt(password));
		try{
		if(userDAO.updateUserPassword(user)){
			result = true;
		}
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return result;
	}
}
