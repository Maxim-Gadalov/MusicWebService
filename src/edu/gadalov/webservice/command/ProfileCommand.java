package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.TrackService;


public class ProfileCommand extends AbstractCommand{
	private static final String PROFILE_PAGE ="jsp/user-profile.jsp";
	private static final String EDIT_PROFILE_PAGE = "jsp/edit-profile.jsp";
	private static final String MAIN_PAGE = "main.jsp";
	private String page;
	public ProfileCommand(String suffix){
		if("edit".equals(suffix)){
			this.page = EDIT_PROFILE_PAGE;
		} else{
			this.page = PROFILE_PAGE;
		}
	}
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		User user = (User)request.getSession().getAttribute("user");
		TrackService service = new TrackService();
		List<AudioTrack> list = service.getUserTracks(user);
		request.setAttribute("trackList", list);
		return page;
	}
}
