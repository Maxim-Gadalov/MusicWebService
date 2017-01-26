package edu.gadalov.webservice.command;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.service.TrackService;

/**AudioTrack pagitation command class.
 * @author Maxim Gadalov
 *
 */
public class PaginationCommand extends AbstractCommand{
	private static final String TRACKS_PAGE = "jsp/tracks.jsp";
	private static final String DATABASE_ERROR = "Database connection interrupted";

	@Override
	public String execute(HttpServletRequest request) {
		TrackService service = new TrackService();
		List<AudioTrack> list = null;
		String genre = request.getParameter("genre");
		if((genre == null) || (genre.isEmpty()) || ("null".equals(genre))){
			list = service.getVisibleTracks();
		}else {
			list = service.getVisibleTracksByGenre(genre);
		}
		String lowerLimit = request.getParameter("lowerLimit");
		String upperLimit = request.getParameter("upperLimit");
		int indexFrom = Integer.valueOf(lowerLimit);
		int indexTo = Integer.valueOf(upperLimit);
		
		if(!list.isEmpty()){
			Collections.reverse(list);
			request.setAttribute("trackList", list.subList(indexFrom - 1, indexTo));
			request.setAttribute("numberOfElements", list.size());
		} else{
			request.setAttribute("trackList", list);
			request.setAttribute("numberOfElements", 0);
			request.setAttribute("errorMessage", DATABASE_ERROR);
		}
		return TRACKS_PAGE;
	}
}
