package edu.gadalov.webservice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.AdminMenuService;
import edu.gadalov.webservice.service.UserService;
import edu.gadalov.webservice.validation.TrackValidation;

public class AddTrackCommand extends AbstractCommand{
	private static final Logger LOG = LogManager.getLogger(AddTrackCommand.class);
	private static final String ADMIN_PAGE = "jsp/admin-menu.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		AdminMenuService adminMenu = new AdminMenuService();
		TrackValidation validation = new TrackValidation();
		String errorMessage = new String();
		String singer = request.getParameter("singer");
		String trackName = request.getParameter("track-name");
		String cost = request.getParameter("cost");
		String album = request.getParameter("album");
		String adminLogin = request.getSession().getAttribute("nickname").toString();
		String filePath = null;
		try {
			filePath = adminMenu.uploadTrack(request);
		} catch (IOException | ServletException e) {
			LOG.error("Some problems with uploading file "+e);
		}
		String genre = request.getParameter("genre");
		UserService userService = new UserService();
		User admin = userService.getUser(adminLogin);
		System.out.println(singer);
		System.out.println(trackName);
		System.out.println(cost);
		System.out.println(album);
		System.out.println(filePath);
		System.out.println(genre);
		errorMessage = validation.checkCostValidation(cost);
		if(errorMessage.isEmpty()){
		AudioTrack track = new AudioTrack(1,admin,singer,trackName,album,filePath,Float.valueOf(cost),genre);
		adminMenu.createTrack(track);
		} else {
			request.setAttribute("addTrackError", errorMessage);
		}
		return ADMIN_PAGE;
	}

}
