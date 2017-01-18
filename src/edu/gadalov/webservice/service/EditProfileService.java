package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.validation.UserValidation;

public class EditProfileService {
	private static final String ERROR_MESSAGE_UNIQUENESS_EMAIL = "This email is already exists";
	private static final String ERROR_MESSAGE_UNIQUENESS_NICKNAME = "This nickname is already exists";
	private static final String ERROR_MESSAGE_EMAIL_VALIDITY = "This email is invalid";
	private static final String ERROR_MESSAGE_NICKNAME_VALIDITY = "This nickname is invalid";
	public String checkProfile(String nickname,String email,User user){
		UserValidation validation = new UserValidation();
		String errorMessage = new String();
		if(!validation.checkEmailUniqueness(email) && !user.getEmail().equals(email)){
			errorMessage = ERROR_MESSAGE_UNIQUENESS_EMAIL;
		} else if(!validation.emailValidity(email)){
			errorMessage = ERROR_MESSAGE_EMAIL_VALIDITY;
		}
		if(!validation.checkNicknameUniqueness(nickname) && !user.getNickname().equals(nickname)){
			errorMessage = ERROR_MESSAGE_UNIQUENESS_NICKNAME;
		} else if(!validation.nicknameValidity(nickname)){
			errorMessage = ERROR_MESSAGE_NICKNAME_VALIDITY;
		}
		return errorMessage;
	}
	public boolean editUserProfile(User user){
		UserDAO userDAO = new UserDAO();
		boolean result = false;
		try{
		if(userDAO.updateUser(user)){
			result = true;
		} 
		}finally{
			userDAO.close(userDAO.getConnection());
		}
		return result;
		
	}

}
