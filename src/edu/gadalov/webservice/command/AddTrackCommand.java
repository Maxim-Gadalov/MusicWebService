package edu.gadalov.webservice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.AdminMenuService;
import edu.gadalov.webservice.validation.TrackValidation;

public class AddTrackCommand extends AdminPageCommand{
	private static final Logger LOG = LogManager.getLogger(AddTrackCommand.class);
	private static final String MAIN_PAGE = "main.jsp";
	
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		AdminMenuService adminMenu = new AdminMenuService();
		TrackValidation validation = new TrackValidation();
		String errorMessage = new String();
		String singer = request.getParameter("singer");
		String trackName = request.getParameter("track-name");
		String cost = request.getParameter("cost");
		String album = request.getParameter("album");
		String filePath = null;
		try {
			filePath = adminMenu.uploadTrack(request);
		} catch (IOException | ServletException e) {
			LOG.error("Some problems with uploading file "+e);
		}
		String genre = request.getParameter("genre");
		errorMessage = validation.checkCostValidation(cost);
		User admin = (User) request.getSession().getAttribute("user");
		// add tracks and users monitor 
		if(errorMessage.isEmpty()){
		AudioTrack track = new AudioTrack(1,admin,singer,trackName,album,filePath,Float.valueOf(cost),genre,true);
		adminMenu.createTrack(track);
		} else {
			request.setAttribute("addTrackError", errorMessage);
		}
		return super.execute(request);
	}
}
