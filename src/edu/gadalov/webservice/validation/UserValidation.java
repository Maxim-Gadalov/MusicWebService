package edu.gadalov.webservice.validation;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;

/**User validation class, @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}.
 * @author Maxim Gadalov
 *
 */
public class UserValidation {
	private static final String EMAIL_REGEXP = "^[a-z0-9_-]+@[a-z0-9-]+\\.([a-z]{1,6}\\.)?[a-z]{2,6}";
	private static final String NICKNAME_REGEXP = "[^a-zA-Z0-9]";
	private static final String DIGITAL_REGEXP = "(.*)[0-9](.*)";
	private static final String LOWERCASE_LETTER_REGEXP = "(.*)[a-z](.*)";
	private static final String UPPERCASE_LETTER_REGEXP = "(.*)[A-Z](.*)";
	/**Check registration form
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if form valid, false - otherwise
	 */
	public boolean registrationFormValidity(User user){
		return (emailValidity(user) && nicknameValidity(user) && passwordValidity(user));
	}
	/**Check user email validation @see {@link UserValidation#emailValidity(String)}
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if email valid, false - otherwise
	 */
	public boolean emailValidity(User user){
		return user.getEmail().matches(EMAIL_REGEXP);
	}
	/**Check user nickname validation @see {@link UserValidation#nicknameValidity(String)}
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if nickname valid, else - otherwise
	 */
	public boolean nicknameValidity(User user){
		return (!user.getNickname().matches(NICKNAME_REGEXP) && user.getNickname().length() >= 5);
	}
	/**Check user password validity @see {@link UserValidation#passwordValidity(String)}
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if password valid, false - otherwise
	 */
	public boolean passwordValidity(User user){
		return ((user.getPassword().length() >= 8) && (user.getPassword().length() <=30) && user.getPassword().matches(DIGITAL_REGEXP) &&
				user.getPassword().matches(LOWERCASE_LETTER_REGEXP) && user.getPassword().matches(UPPERCASE_LETTER_REGEXP));
	}
	/**Check user email uniqueness @see {@link UserValidation#checkEmailUniqueness(String)}
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if email unique, false - otherwise
	 */
	public boolean checkEmailUniqueness(User user){
		UserDAO userDAO = new UserDAO();
		User temp = new User();
		try{
			temp = userDAO.findByEmail(user.getEmail());
		}
		finally{
			userDAO.close(userDAO.getConnection());
		}
		return (temp.isUntapped());
			
	}
	/**Check user nickname uniqueness @see {@link UserValidation#checkNicknameUniqueness(String)}
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if nickname unique, false = otherwise
	 */
	public boolean checkNicknameUniqueness(User user){
		UserDAO userDAO = new UserDAO();
		User temp = new User();
		try{
			temp = userDAO.findByNickname(user.getNickname());
		}
		finally{
			userDAO.close(userDAO.getConnection());	
		}
		return (temp.isUntapped());
	}
	/**Check nickname uniqueness @see {@link UserValidation#checkNicknameUniqueness(User)}
	 * @param nickname - String nickname
	 * @return true if nickname unique, false - otherwise
	 */
	public boolean checkNicknameUniqueness(String nickname){
		UserDAO userDAO = new UserDAO();
		User temp = null;
		try{
			temp = userDAO.findByNickname(nickname);
		}
		finally{
			userDAO.close(userDAO.getConnection());
		}
		return (temp == null);
	}
	/**Check email uniqueness @see {@link UserValidation#checkEmailUniqueness(User)}
	 * @param email - String email
	 * @return true if email unique, false - otherwise
	 */
	public boolean checkEmailUniqueness(String email){
		UserDAO userDAO = new UserDAO();
		User temp = null;
		try{
			temp = userDAO.findByEmail(email);
		}
		finally{
			userDAO.close(userDAO.getConnection());
		}
		return (temp == null);		
	}
	/**Check email validation @see {@link UserValidation#emailValidity(User)}
	 * @param email - String email
	 * @return true if email valid, false - otherwise
	 */
	public boolean emailValidity(String email){
		return email.matches(EMAIL_REGEXP);
	}
	/**Check nickname validation @see {@link UserValidation#nicknameValidity(User)}
	 * @param nickname - String nickname
	 * @return true if nickname valid, false - otherwise
	 */
	public boolean nicknameValidity(String nickname){
		return (!nickname.matches(NICKNAME_REGEXP) && nickname.length() >= 5);
	}
	/**Check password validation @see {@link UserValidation#passwordValidity(User)}
	 * @param password - String password
	 * @return true if password valid, false - otherwise
	 */
	public boolean passwordValidity(String password){
		return ((password.length() >= 8) && (password.length() <=30) && password.matches(DIGITAL_REGEXP) &&
				password.matches(LOWERCASE_LETTER_REGEXP) && password.matches(UPPERCASE_LETTER_REGEXP));
	}
}
