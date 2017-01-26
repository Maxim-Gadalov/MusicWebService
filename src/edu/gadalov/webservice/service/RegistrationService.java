package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.validation.UserValidation;

/**Registration service class.
 * @author Maxim Gadalov
 *
 */
public class RegistrationService {
	private static final String ERROR_MESSAGE_VALIDATION_FORM = "Registration form invalid";
	private static final String ERROR_MESSAGE_UNIQUENESS_EMAIL = "This email is already exists";
	private static final String ERROR_MESSAGE_UNIQUENESS_NICKNAME = "This nickname is already exists";
	/**Check registration validation
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return empty String if User valid and added to the database, else return String errorMessage
	 */
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
	/**Add new user to the database
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if insert was successful , false - otherwise
	 */
	public boolean addUser(User user){
		boolean result = false;
		UserDAO userDAO = new UserDAO();
		UserValidation validation = new UserValidation();
		try{
			if(validation.registrationFormValidity(user)){
				result = userDAO.create(user);
				}
			}
		finally{
			userDAO.close(userDAO.getConnection());
			}
		return result;
	}
}
