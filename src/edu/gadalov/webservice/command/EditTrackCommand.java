package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.service.TrackService;
import edu.gadalov.webservice.validation.TrackValidation;

public class EditTrackCommand extends AdminPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String EDIT_TRACK_PAGE = "jsp/edit-track.jsp";
	private static final String DB_ERROR_UPDATE = "Can not update this track(DB error)";
	private static final String DB_ERROR_SELECT = "Can not select this track(DB error)";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		String singer = request.getParameter("singer");
		String trackName = request.getParameter("track-name");
		String album = request.getParameter("album");
		String cost = request.getParameter("cost");
		String genre = request.getParameter("genre");
		String trackId = request.getParameter("trackId");
		TrackService service = new TrackService();
		TrackValidation validation = new TrackValidation();
		AudioTrack track = service.getTrackById(Integer.valueOf(trackId));
		String errorMessage = new String();
		errorMessage = validation.checkCostValidation(cost);
		if(track == null){
			errorMessage = DB_ERROR_SELECT;
		}
		if(errorMessage.isEmpty()){
			track.setAlbum(album);
			track.setCost(Float.valueOf(cost));
			track.setGenre(genre);
			track.setSinger(singer);
			track.setTrackName(trackName);
			if(!service.updateTrack(track)){
				errorMessage = DB_ERROR_UPDATE;
				request.setAttribute("editErrorMessage", errorMessage);
			}

			return super.execute(request);
			
		} else{
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("track", track);
			return EDIT_TRACK_PAGE;
		}		
	}
}
