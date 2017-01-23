package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.service.TrackService;

public class DeleteTrackCommand extends AdminPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String REMOVE_TRACK_ERROR_MESSAGE = "Track not found";
	
	@Override
	public String execute(HttpServletRequest request){
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		String singer = request.getParameter("singer-remove");
		String trackName = request.getParameter("track-name-remove");
		String errorMessage = new String();
		TrackService service = new TrackService();
		if(!service.deleteTrack(singer, trackName)){
			errorMessage = REMOVE_TRACK_ERROR_MESSAGE;
			request.setAttribute("removeErrorMessage", errorMessage);
		}
		return super.execute(request);
	}

}