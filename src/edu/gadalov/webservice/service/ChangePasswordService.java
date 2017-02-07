package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.util.EncryptionPassword;
import edu.gadalov.webservice.validation.UserValidation;

/**Cjange password service class.
 * @author Maxim Gadalov
 *
 */
public class ChangePasswordService {
	private static final String ERROR_MESSAGE_PASSWORD_VALIDITY = "Password is invalid";
	private static final String ERROR_MESSAGE_PASSWORD_CONFIRM = "Passwords must match";

	/**Check new password validation
	 * @param password - new String password
	 * @param confirmPassword - new String confirm password
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return empty String if new password valid, else return String errorMessage
	 */
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
	/**Update user password
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @param password - String new password
	 * @return true if update was successful, false - otherwise
	 */
	public boolean changePassword(User user,String password){
		UserDAO userDAO = new UserDAO();
		user.setPassword(EncryptionPassword.encrypt(password));
		try{
			return userDAO.updateUserPassword(user);
		} finally{
			userDAO.close(userDAO.getConnection());
		}
	}
}
