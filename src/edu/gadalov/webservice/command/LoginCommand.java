package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;


import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.entity.UserBan;
import edu.gadalov.webservice.service.BanService;
import edu.gadalov.webservice.service.LoginService;
import edu.gadalov.webservice.service.UserService;

/**Log in command class.
 * @author Maxim Gadalov
 *
 */
public class LoginCommand extends AbstractCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String LOGIN_PAGE = "jsp/login.jsp";
	private static final String BAN_PAGE = "jsp/ban.jsp";
	private static final String DATABASE_ERROR = "Database connection interrupted";

	@Override
	public String execute(HttpServletRequest request) {
		String page = LOGIN_PAGE;
		String errorMessage = new String();
		String login = request.getParameter("nick-or-mail");
		String password = request.getParameter("password");
		LoginService loginService = new LoginService();
		UserService userService = new UserService();
		BanService banService = new BanService();
	    errorMessage = loginService.loginCheck(login, password);
		if(errorMessage.isEmpty()){
			page = MAIN_PAGE;
			User user = userService.getUser(login);
			if(user != null){
				if(!banService.checkBanUser(user.getId())){
					UserBan ban = banService.getBanInfo(user.getId());
					request.setAttribute("banReason", ban.getReason());
					request.setAttribute("nickname", user.getNickname());
					request.setAttribute("email", user.getEmail());
					request.setAttribute("admin", userService.getUser(ban.getIdAdmin()).getNickname());
					return BAN_PAGE;
				}
				request.getSession().setAttribute("user", user);
			} else{
				errorMessage = DATABASE_ERROR;
				request.setAttribute("errorMessage", errorMessage);
			}
		} else{
			request.setAttribute("errorMessage", errorMessage);
		}
		return page;
	}
}
