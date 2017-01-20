package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.EditProfileService;
import edu.gadalov.webservice.service.TrackService;

public class EditProfileCommand extends AbstractCommand{
	private static final String EDIT_PROFILE_PAGE = "jsp/edit-profile.jsp";
	private static final String PROFILE_PAGE = "jsp/user-profile.jsp";
	private static final String MAIN_PAGE = "main.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		User user = (User)request.getSession().getAttribute("user");
		String userNickname = request.getParameter("userNickname");
		String userEmail = request.getParameter("userEmail");
		String skype = request.getParameter("userSkype");
		String phNumber = request.getParameter("userNumber");
		String page = EDIT_PROFILE_PAGE;
		String errorMessage = new String();
		EditProfileService service = new EditProfileService();
		errorMessage = service.checkProfile(userNickname, userEmail, user);
		if(errorMessage.isEmpty()){
			user.setEmail(userEmail);
			user.setNickname(userNickname);
			user.setSkype(skype);
			user.setPhoneNumber(phNumber);
			if(service.editUserProfile(user)){
				page = PROFILE_PAGE;
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", user);
				TrackService trackService = new TrackService();
				List<AudioTrack> list = trackService.getUserTracks(user);
				request.setAttribute("trackList", list);
			}
		} else{
			request.setAttribute("errorMessage", errorMessage);
		}	
		return page;
	}


}
