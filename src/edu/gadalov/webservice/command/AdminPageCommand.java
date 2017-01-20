package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.AdminMenuService;

public class AdminPageCommand extends AbstractCommand{
	private static final String ADMIN_PAGE = "jsp/admin-menu.jsp";
	private static final String MAIN_PAGE = "main.jsp";
	// add banlist and track list

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		AdminMenuService adminMenu = new AdminMenuService();
		List<User> userList = adminMenu.getAllUsers();
		List<AudioTrack> trackList = adminMenu.getAllTracks(); // tracks
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
		return ADMIN_PAGE;
	}

}
