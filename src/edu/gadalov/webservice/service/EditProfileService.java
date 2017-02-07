package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.validation.UserValidation;

/**Edit user profile service class.
 * @author Maxim Gadalov
 *
 */
public class EditProfileService {
	private static final String ERROR_MESSAGE_UNIQUENESS_EMAIL = "This email is already exists";
	private static final String ERROR_MESSAGE_UNIQUENESS_NICKNAME = "This nickname is already exists";
	private static final String ERROR_MESSAGE_EMAIL_VALIDITY = "This email is invalid";
	private static final String ERROR_MESSAGE_NICKNAME_VALIDITY = "This nickname is invalid";
	/**Check new user profile info
	 * @param nickname - new String nickname
	 * @param email - new String email
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return empty String if new info correct and valid, else return String errorMessage
	 */
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
	/**Update user profile
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return true if update was successful , false - otherwise
	 */
	public boolean editUserProfile(User user){
		UserDAO userDAO = new UserDAO();
		try{
			return userDAO.updateUser(user);
		}finally{
			userDAO.close(userDAO.getConnection());
		}
	}
}
