package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.LoginService;

public class LoginCommand extends AbstractCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String LOGIN_PAGE = "jsp/login.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		String page = LOGIN_PAGE;
		String errorMessage = new String();
		String login = request.getParameter("nick-or-mail");
		String password = request.getParameter("password");
		LoginService loginService = new LoginService();
	    errorMessage = loginService.loginCheck(login, password);
		request.setAttribute("errorMessage", errorMessage);
		if(errorMessage.isEmpty()){
			page = MAIN_PAGE;
			User user = loginService.getLoginUser(login);
			request.getSession().setAttribute("nickname", user.getNickname());
			request.getSession().setAttribute("role", user.getRole().getRoleName());
		}
		return page;
	}

}
