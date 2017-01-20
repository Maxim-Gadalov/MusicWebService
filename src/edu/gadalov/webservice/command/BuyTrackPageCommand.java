package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.BuyTrackService;
import edu.gadalov.webservice.service.TrackService;

public class BuyTrackPageCommand extends AbstractCommand{
	private static final String BUY_TRACK_PAGE = "jsp/pay.jsp";
	private static final String TRACKS_PAGE = "jsp/tracks.jsp";
	private static final int MAX_TRACKS_ON_PAGE = 10;
	private static final String LOGIN_PAGE = "jsp/login.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return LOGIN_PAGE;
		}
		User user = (User)request.getSession().getAttribute("user");
		String idTrack = request.getParameter("idTrack");
		TrackService service = new TrackService();
		BuyTrackService buyService = new BuyTrackService();
		String errorMessage = new String();
		String page = new String();
		AudioTrack track = service.getTrackById(Integer.valueOf(idTrack));
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
				if(list.size() > MAX_TRACKS_ON_PAGE){
					request.setAttribute("trackList", list.subList(0, MAX_TRACKS_ON_PAGE));
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
