package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.validation.UserValidation;

public class UserService {
	private static final String ADMIN_ROLE_NAME = "admin";
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
	public boolean setDiscount(Discount discount, User user){
		boolean result = false;
		UserDAO userDAO = new UserDAO();
		try{
			result = userDAO.updateUserDiscount(discount, user);
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return result;
	}
	public boolean isAdmin(User user){
		return ADMIN_ROLE_NAME.equals(user.getRole().getRoleName());
	}
	public User getUser(int id){
		User user = null;
		UserDAO userDAO = new UserDAO();
		try{
			user = userDAO.findById(id);
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return user;
	}
}
