package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.validation.UserValidation;

public class UserService {
	public User getUser(String login){
		User user = null;
		UserValidation validation = new UserValidation();
		UserDAO userDAO = new UserDAO();
		try{
			if(validation.isEmail(login)){
				user = userDAO.findByEmail(login);
			} else{
				user = userDAO.findByNickname(login);
			}
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return user;
	}

}
