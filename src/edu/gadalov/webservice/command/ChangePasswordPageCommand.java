package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordPageCommand extends AbstractCommand{
	private static final String CHANGE_PASSWORD_PAGE = "jsp/change-password.jsp";
	// перепилить на href

	@Override
	public String execute(HttpServletRequest request) {
		return CHANGE_PASSWORD_PAGE;
	}

}
