package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.AdminMenuService;

/**Admin page forward command class.
 * @author Maxim Gadalov
 *
 */
public class AdminPageCommand extends AbstractCommand{
	private static final String ADMIN_PAGE = "jsp/admin-menu.jsp";
	private static final String MAIN_PAGE = "main.jsp";
	private static final String DB_ERROR = "Database connection was interrupted";
	private static final String USER_ROLE = "user";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		User user = (User)request.getSession().getAttribute("user");
		if(USER_ROLE.equals(user.getRole().getRoleName())){
			return MAIN_PAGE;
		}
		AdminMenuService adminMenu = new AdminMenuService();
		List<User> userList = adminMenu.getAllUsers();
		List<AudioTrack> trackList = adminMenu.getAllTracks(); 
		List<Discount> discountList = adminMenu.getDiscounts();
		if(!userList.isEmpty()){
			request.setAttribute("list_users", userList);
		}
		if(!trackList.isEmpty()){
			request.setAttribute("list_tracks",trackList);
		}
		if(!discountList.isEmpty()){
			request.setAttribute("list_discounts", discountList);
		}
		if(trackList.isEmpty() & userList.isEmpty() & discountList.isEmpty()){
			request.setAttribute("databaseError", DB_ERROR);
		}
		return ADMIN_PAGE;
	}

}
