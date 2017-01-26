package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.service.TrackService;

/**Edit AudioTrack page forward command class.
 * @author Maxim Gadalov
 *
 */
public class EditTrackPageCommand extends AdminPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String EDIT_TRACK_PAGE = "jsp/edit-track.jsp";
	private static final String TRACK_ERROR = "Can not find track";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		String id = request.getParameter("idTrack");
		TrackService service = new TrackService();
		String errorMessage = new String();
		AudioTrack track = service.getTrackById(Integer.valueOf(id));
		if(track == null){
			errorMessage = TRACK_ERROR;
			request.setAttribute("editErrorMessage", errorMessage);
			return super.execute(request);
		} else{
			request.setAttribute("track", track);
			return EDIT_TRACK_PAGE;
		}
	}
	

}
