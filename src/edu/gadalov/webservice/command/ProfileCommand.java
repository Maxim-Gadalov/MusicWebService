package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.UserService;

public class ProfileCommand extends AbstractCommand{
	private static final String PROFILE_PAGE ="jsp/user-profile.jsp";
	private static final String EDIT_PROFILE_PAGE = "jsp/edit-profile.jsp";
	private String page;
	public ProfileCommand(String suffix){
		if("edit".equals(suffix)){
			this.page = EDIT_PROFILE_PAGE;
		} else{
			this.page = PROFILE_PAGE;
		}
	}
	@Override
	public String execute(HttpServletRequest request) {
		String nickname = request.getSession().getAttribute("nickname").toString();
		User user = null;
		UserService service = new UserService();
		user = service.getUser(nickname);
		request.setAttribute("userNickname", user.getNickname());
		request.setAttribute("userEmail", user.getEmail());
		request.setAttribute("skype", user.getSkype());
		request.setAttribute("phnumber", user.getPhoneNumber());
		return page;
	}

}
