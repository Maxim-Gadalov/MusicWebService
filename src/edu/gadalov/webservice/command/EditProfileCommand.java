package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.EditProfileService;
import edu.gadalov.webservice.service.UserService;

public class EditProfileCommand extends AbstractCommand{
	private static final String EDIT_PROFILE_PAGE = "jsp/edit-profile.jsp";
	private static final String PROFILE_PAGE = "jsp/user-profile.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		String nickname = request.getSession().getAttribute("nickname").toString();
		String userNickname = request.getParameter("userNickname");
		String userEmail = request.getParameter("userEmail");
		String skype = request.getParameter("userSkype");
		String phNumber = request.getParameter("userNumber");
		String page = EDIT_PROFILE_PAGE;
		String errorMessage = new String();
		User user = null;
		EditProfileService service = new EditProfileService();
		UserService userService = new UserService();
		user = userService.getUser(nickname);
		errorMessage = service.checkProfile(userNickname, userEmail, user);
		if(errorMessage.isEmpty()){
			user.setEmail(userEmail);
			user.setNickname(userNickname);
			user.setSkype(skype);
			user.setPhoneNumber(phNumber);
			if(service.editUserProfile(user)){
				page = PROFILE_PAGE;
				request.getSession().setAttribute("nickname", user.getNickname());
			}
		} else{
			request.setAttribute("errorMessage", errorMessage);
		}	
		request.setAttribute("userNickname", user.getNickname());
		request.setAttribute("userEmail", user.getEmail());
		request.setAttribute("skype", user.getSkype());
		request.setAttribute("phnumber", user.getPhoneNumber());
		return page;
	}


}
