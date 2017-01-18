package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.validation.UserValidation;

public class RegistrationService {
	private static final String ERROR_MESSAGE_VALIDATION_FORM = "Registration form invalid";
	private static final String ERROR_MESSAGE_UNIQUENESS_EMAIL = "This email is already exists";
	private static final String ERROR_MESSAGE_UNIQUENESS_NICKNAME = "This nickname is already exists";
	public String registration(User user){
		UserValidation validation = new UserValidation();
		String message = new String();
		if(validation.checkEmailUniqueness(user)){
			if(validation.checkNicknameUniqueness(user)){
				if(addUser(user)){
				} else{
					message = ERROR_MESSAGE_VALIDATION_FORM;
				}
			} else{
				message = ERROR_MESSAGE_UNIQUENESS_NICKNAME;
			}
		} else{
			message = ERROR_MESSAGE_UNIQUENESS_EMAIL;
		}
		return message;
	}
	public boolean addUser(User user){
		boolean result = false;
		UserDAO userDAO = null;
		UserValidation validation = new UserValidation();
		try{
		if(validation.registrationFormValidity(user)){
				userDAO = new UserDAO();
				result = userDAO.create(user);
		}
		}
		finally{
			if(userDAO != null){
				userDAO.close(userDAO.getConnection());
			}
		}
		return result;
	}

}