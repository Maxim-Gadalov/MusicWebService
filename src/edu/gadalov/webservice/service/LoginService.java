package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.util.EncryptionPassword;
import edu.gadalov.webservice.validation.UserValidation;

public class LoginService {
	private static final String WRONG_EMAIL_MESSAGE = "Incorrect email, please try again";
	private static final String WRONG_NICKNAME_MESSAGE = "Incorrect nickname, please try again";
	private static final String WRONG_PASSWORD_MESSAGE = "Incorrect login or password";
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
	public User getLoginUser(String nickname){
		User user = null;
		UserDAO userDAO = new UserDAO();
		UserValidation validation = new UserValidation();
		try{
		if(validation.isEmail(nickname)){
			user = userDAO.findByEmail(nickname);
		} else {
			user = userDAO.findByNickname(nickname);
		}
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return user;
	}
}
