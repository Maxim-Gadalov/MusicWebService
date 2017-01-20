package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.service.TrackService;

public class TracksPageCommand extends AbstractCommand{
	private static final String TRACKS_PAGE = "jsp/tracks.jsp";
	private static final int MAX_TRACKS_ON_PAGE = 10;
	private static final String DATABASE_ERROR = "Database connection interrupted";

	@Override
	public String execute(HttpServletRequest request) {
		TrackService service = new TrackService();
		List<AudioTrack> list = null;
		list = service.getAllTracks();
		if(!list.isEmpty()){
			if(list.size() > MAX_TRACKS_ON_PAGE){
				request.setAttribute("trackList", list.subList(0, MAX_TRACKS_ON_PAGE));
			} else{
				request.setAttribute("trackList", list);
			}
			request.setAttribute("numberOfElements", list.size());
		} else {
			request.setAttribute("numberOfElements", 0);
			request.setAttribute("trackList", list);
			request.setAttribute("errorMessage", DATABASE_ERROR);
		}
		return TRACKS_PAGE;
	}
}
