package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.service.TrackService;

public class PaginationCommand extends AbstractCommand{
	private static final String TRACKS_PAGE = "jsp/tracks.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		TrackService service = new TrackService();
		List<AudioTrack> list = null;
		list = service.getAllTracks();
		String lowerLimit = request.getParameter("lowerLimit");
		String upperLimit = request.getParameter("upperLimit");
		int indexFrom = Integer.valueOf(lowerLimit);
		int indexTo = Integer.valueOf(upperLimit);
		if(!list.isEmpty()){
			request.setAttribute("trackList", list.subList(indexFrom - 1, indexTo));
			request.setAttribute("numberOfElements", list.size());
		}
		return TRACKS_PAGE;
	}

}
