package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;

/** User service class. @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
 * @author Maxim Gadalov
 *
 */
public class UserService {
	private static final String ADMIN_ROLE_NAME = "admin";
	private static final String EMAIL_CHECK_REGEXP = "(.*)@(.*)";
	/**Return User found by login
	 * @param login - String nickname or email
	 * @return User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 */
	public User getUser(String login){
		UserDAO userDAO = new UserDAO();
		try{
			if(isEmail(login)){
				return userDAO.findByEmail(login);
			} else{
				return userDAO.findByNickname(login);
			}
		} finally{
			userDAO.close(userDAO.getConnection());
		}
	}
	/**Set new Discount to the User.
	 * @param discount - Discount @see {@link Discount#Discount(int, int)}
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 * @return true if discount was set successfully, false - otherwise.
	 */
	public boolean setDiscount(Discount discount, User user){
		UserDAO userDAO = new UserDAO();
		try{
			return userDAO.updateUserDiscount(discount, user);
		} finally{
			userDAO.close(userDAO.getConnection());
		}
	}
	/**Check user role
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 * @return true if user role equals "admin", false - otherwise.
	 */
	public boolean isAdmin(User user){
		return ADMIN_ROLE_NAME.equals(user.getRole().getRoleName());
	}
	/**Return user found by id
	 * @param id - integer ID
	 * @return User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 */
	public User getUser(int id){
		UserDAO userDAO = new UserDAO();
		try{
			return userDAO.findById(id);
		} finally{
			userDAO.close(userDAO.getConnection());
		}
	}
	/**Defines if login - email
	 * @param login - String login
	 * @return true if login matches email content, false - nickname
	 */
	public boolean isEmail(String login){
		return (login.matches(EMAIL_CHECK_REGEXP));
	}
}
