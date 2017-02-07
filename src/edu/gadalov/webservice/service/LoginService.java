package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.util.EncryptionPassword;

/**Log in service class.
 * @author Maxim Gadalov
 *
 */
public class LoginService {
	private static final String WRONG_EMAIL_MESSAGE = "Incorrect email! Try again,please";
	private static final String WRONG_NICKNAME_MESSAGE = "Incorrect nickname! Try again,please";
	private static final String WRONG_PASSWORD_MESSAGE = "Incorrect login or password! Try again,please";
	/**Check correctness login and password
	 * @param login - String user login(email or nickname)
	 * @param password - String password
	 * @return empty String if login and password are right, else return String errorMessage
	 */
	public String loginCheck(String login,String password){
		String errorMessage = new String();
		UserService validation = new UserService();
		User user = new User();
		UserDAO userDAO = new UserDAO();
		try{
		if(validation.isEmail(login)){
			user = userDAO.findByEmail(login);
			if(user.isUntapped()){
				errorMessage = WRONG_EMAIL_MESSAGE;
			} else{
				if(!user.getPassword().equals(EncryptionPassword.encrypt(password))){
					errorMessage = WRONG_PASSWORD_MESSAGE;
				}
			}
		}else{
			user = userDAO.findByNickname(login);
			if(user.isUntapped()){
				errorMessage = WRONG_NICKNAME_MESSAGE;
			} else{
				if(!user.getPassword().equals(EncryptionPassword.encrypt(password))){
					errorMessage = WRONG_PASSWORD_MESSAGE;
				}
			}
		}
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return errorMessage;
	}
}
