package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.BanService;
import edu.gadalov.webservice.service.UserService;

public class UnbanUserCommand extends AdminPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
    private static final String NO_SUCH_USER_MESSAGE = "User not found";
    private static final String UNBAN_ERROR_MESSAGE = "This user was not banned";
	
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		String userLogin = request.getParameter("user-login");
		UserService userService = new UserService();
		User user = userService.getUser(userLogin);
		BanService service = new BanService();
		String errorMessage = new String();
		if(user == null){
			errorMessage = NO_SUCH_USER_MESSAGE;
		} else if(service.checkBanUser(user.getId())){
			errorMessage = UNBAN_ERROR_MESSAGE;
		}
		if(errorMessage.isEmpty()){
			service.removeBan(user.getId());
		} else{
			request.setAttribute("unbanErrorMessage", errorMessage);
		}
		return super.execute(request);	
	}

}
