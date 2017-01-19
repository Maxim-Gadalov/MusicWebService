package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.util.EncryptionPassword;
import edu.gadalov.webservice.validation.UserValidation;

public class LoginService {
	private static final String WRONG_EMAIL_MESSAGE = "Incorrect email! Try again,please";
	private static final String WRONG_NICKNAME_MESSAGE = "Incorrect nickname! Try again,please";
	private static final String WRONG_PASSWORD_MESSAGE = "Incorrect login or password! Try again,please";
	public String loginCheck(String login,String password){
		String errorMessage = new String();
		UserValidation validation = new UserValidation();
		User user = null;
		UserDAO userDAO = new UserDAO();
		try{
		if(validation.isEmail(login)){
			user = userDAO.findByEmail(login);
			if(user == null){
				errorMessage = WRONG_EMAIL_MESSAGE;
			} else{
				if(!user.getPassword().equals(EncryptionPassword.encrypt(password))){
					errorMessage = WRONG_PASSWORD_MESSAGE;
				}
			}
		}else{
			user = userDAO.findByNickname(login);
			if(user == null){
				errorMessage = WRONG_NICKNAME_MESSAGE;
			} else{
				if(!user.getPassword().equals(EncryptionPassword.encrypt(password))){
					errorMessage = WRONG_PASSWORD_MESSAGE;
				}
			}
		}
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return errorMessage;
	}
}
