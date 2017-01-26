package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordPageCommand extends AbstractCommand{
	private static final String CHANGE_PASSWORD_PAGE = "jsp/change-password.jsp";
	private static final String MAIN_PAGE = "main.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		} else{
			return CHANGE_PASSWORD_PAGE;
		}
	}

}
