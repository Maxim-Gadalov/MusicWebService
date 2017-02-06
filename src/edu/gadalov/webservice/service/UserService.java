package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.validation.UserValidation;

/** User service class. @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
 * @author Maxim Gadalov
 *
 */
public class UserService {
	private static final String ADMIN_ROLE_NAME = "admin";
	/**Return User found by login
	 * @param login - String nickname or email
	 * @return User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 */
	public User getUser(String login){
		User user = new User();
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
	/**Set new Discount to the User.
	 * @param discount - Discount @see {@link Discount#Discount(int, int)}
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 * @return true if discount was set successfully, false - otherwise.
	 */
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
		User user = new User();
		UserDAO userDAO = new UserDAO();
		try{
			user = userDAO.findById(id);
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return user;
	}
}
