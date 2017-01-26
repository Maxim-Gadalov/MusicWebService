package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.BanList;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.BanService;
import edu.gadalov.webservice.service.UserService;

/**Ban user command class.
 * @author Maxim Gadalov
 *
 */
public class BanUserCommand extends AdminPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
    private static final String NO_SUCH_USER_MESSAGE = "User not found";
    private static final String BAN_ADMIN_MESSAGE = "You can not ban administrator";
    private static final String DOUBLE_BAN_ERROR_MESSAGE = "This user is already banned";
    private static final String SUCCESS_URL_PATH = "/MusicWebService/MusicServiceServlet?command=admin-page";
	
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		String userLogin = request.getParameter("user-login");
		String reason = request.getParameter("reason");
		User admin = (User)request.getSession().getAttribute("user");
		UserService userService = new UserService();
		User user = userService.getUser(userLogin);
		BanService service = new BanService();
		String errorMessage = new String();
		if(user == null){
			errorMessage = NO_SUCH_USER_MESSAGE;
		} else if(userService.isAdmin(user)){
			errorMessage = BAN_ADMIN_MESSAGE;
		} else if(!service.checkBanUser(user.getId())){
			errorMessage = DOUBLE_BAN_ERROR_MESSAGE;
		}
		if(errorMessage.isEmpty()){
			if(service.banUser(new BanList(1,user,reason,admin.getId()))){
				request.setAttribute("success", true);
				return SUCCESS_URL_PATH;
			}
		} else{
			request.setAttribute("banErrorMessage", errorMessage);
		}
		return super.execute(request);	
	}

}
