package edu.gadalov.webservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.service.TrackService;

public class TracksPageCommand extends AbstractCommand{
	private static final String TRACKS_PAGE = "jsp/tracks.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		TrackService service = new TrackService();
		List<AudioTrack> list = null;
		list = service.getAllTracks();
		if(!list.isEmpty()){
			if(list.size() > 10){
				request.setAttribute("trackList", list.subList(0, 10));
			} else{
				request.setAttribute("trackList", list);
			}
			request.setAttribute("numberOfElements", list.size());
		}
		return TRACKS_PAGE;
	}

}
