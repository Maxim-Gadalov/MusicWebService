package edu.gadalov.webservice.validation;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;

public class UserValidation {
	private static final String EMAIL_REGEXP = "^[a-z0-9_-]+@[a-z0-9-]+\\.([a-z]{1,6}\\.)?[a-z]{2,6}";
	private static final String NICKNAME_REGEXP = "[^a-zA-Z0-9]";
	private static final String DIGITAL_REGEXP = "(.*)[0-9](.*)";
	private static final String LOWERCASE_LETTER_REGEXP = "(.*)[a-z](.*)";
	private static final String UPPERCASE_LETTER_REGEXP = "(.*)[A-Z](.*)";
	private static final String EMAIL_CHECK_REGEXP = "(.*)@(.*)";
	public boolean registrationFormValidity(User user){
		return (emailValidity(user) && nicknameValidity(user) && passwordValidity(user));
	}
	public boolean emailValidity(User user){
		return user.getEmail().matches(EMAIL_REGEXP);
	}
	public boolean nicknameValidity(User user){
		return (!user.getNickname().matches(NICKNAME_REGEXP) && user.getNickname().length() >= 5);
	}
	public boolean passwordValidity(User user){
		return ((user.getPassword().length() >= 8) && (user.getPassword().length() <=30) && user.getPassword().matches(DIGITAL_REGEXP) &&
				user.getPassword().matches(LOWERCASE_LETTER_REGEXP) && user.getPassword().matches(UPPERCASE_LETTER_REGEXP));
	}
	public boolean checkEmailUniqueness(User user){
		UserDAO userDAO = new UserDAO();
		User temp = null;
		try{
			temp = userDAO.findByEmail(user.getEmail());
		}
		finally{
			if(userDAO != null){
				userDAO.close(userDAO.getConnection());
			}
		}
		return (temp == null);
			
	}
	public boolean checkNicknameUniqueness(User user){
		UserDAO userDAO = new UserDAO();
		User temp = null;
		try{
			temp = userDAO.findByNickname(user.getNickname());
		}
		finally{
			if(userDAO != null){
				userDAO.close(userDAO.getConnection());
			}
		}
		return (temp == null);
	}
	public boolean checkNicknameUniqueness(String nickname){
		UserDAO userDAO = new UserDAO();
		User temp = null;
		try{
			temp = userDAO.findByNickname(nickname);
		}
		finally{
			if(userDAO != null){  // ???
				userDAO.close(userDAO.getConnection());
			}
		}
		return (temp == null);
	}
	public boolean checkEmailUniqueness(String email){
		UserDAO userDAO = new UserDAO();
		User temp = null;
		try{
			temp = userDAO.findByEmail(email);
		}
		finally{
			if(userDAO != null){ // ?? что за херня?
				userDAO.close(userDAO.getConnection());
			}
		}
		return (temp == null);		
	}
	public boolean emailValidity(String email){
		return email.matches(EMAIL_REGEXP);
	}
	public boolean nicknameValidity(String nickname){
		return (!nickname.matches(NICKNAME_REGEXP) && nickname.length() >= 5);
	}
	public boolean passwordValidity(String password){
		return ((password.length() >= 8) && (password.length() <=30) && password.matches(DIGITAL_REGEXP) &&
				password.matches(LOWERCASE_LETTER_REGEXP) && password.matches(UPPERCASE_LETTER_REGEXP));
	}
	public boolean isEmail(String login){
		return(login.matches(EMAIL_CHECK_REGEXP));
	}
	
}
