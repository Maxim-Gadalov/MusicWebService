package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.BuyTrackService;
import edu.gadalov.webservice.service.TrackService;
import edu.gadalov.webservice.service.UserService;

public class BuyTrackPageCommand extends AbstractCommand{
	private static final String BUY_TRACK_PAGE = "jsp/pay.jsp";
	private static final String TRACKS_PAGE = "jsp/tracks.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		String nickname = request.getSession().getAttribute("nickname").toString();
	
		String idTrack = request.getParameter("idTrack");
		TrackService service = new TrackService();
		UserService userService = new UserService();
		BuyTrackService buyService = new BuyTrackService();
		String errorMessage = new String();
		String page = new String();
		AudioTrack track = service.getTrackById(Integer.valueOf(idTrack));
		request.getSession().setAttribute("track", track);
		User user = userService.getUser(nickname);
		TrackOrder order = new TrackOrder(user,track);
		float trackCost = buyService.getDiscountCost(user, track);
		errorMessage = service.checkTrackOrder(order);
		if(errorMessage.isEmpty()){
			request.setAttribute("singer", track.getSinger());
			request.setAttribute("trackName", track.getTrackName());
			request.setAttribute("cost", trackCost);
			request.setAttribute("idTrack", track.getId());
			page = BUY_TRACK_PAGE;
		} else{
			request.setAttribute("errorMessage", errorMessage);
			List<AudioTrack> list = service.getAllTracks();
			if(!list.isEmpty()){
				if(list.size() > 10){
					request.setAttribute("trackList", list.subList(0, 10));
				} else{
					request.setAttribute("trackList", list);
				}
				request.setAttribute("numberOfElements", list.size());
			}
			page = TRACKS_PAGE;
		}
		return page;
	}

}
