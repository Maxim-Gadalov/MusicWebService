package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.ChangePasswordService;
import edu.gadalov.webservice.service.UserService;

public class ChangePasswordCommand extends AbstractCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String CHANGE_PASSWORD_PAGE = "jsp/change-password.jsp";
	@Override
	public String execute(HttpServletRequest request) {
		String newPassword = request.getParameter("psw");
		String confirmNewPassword = request.getParameter("cpsw");
		String login = request.getSession().getAttribute("nickname").toString();
		String page = CHANGE_PASSWORD_PAGE;
		String errorMessage = new String();
		ChangePasswordService service = new ChangePasswordService();
		User user = null;
		UserService userService = new UserService();
		user = userService.getUser(login);
		errorMessage = service.checkPassword(newPassword, confirmNewPassword, user);
		if(errorMessage.isEmpty()){
			page = MAIN_PAGE;
			if(service.changePassword(user, newPassword)){
				request.getSession().invalidate();
			}
		} else {
			request.setAttribute("errorMessage", errorMessage);
		}
		return page;
		}
}
