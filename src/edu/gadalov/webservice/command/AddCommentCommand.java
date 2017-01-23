package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.CommentService;
import edu.gadalov.webservice.service.TrackService;

public class AddCommentCommand extends TracksPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		User user = (User)request.getSession().getAttribute("user");
		String idTrack = request.getParameter("idTrack");
		String commentText = request.getParameter("comment_text");
		CommentService service = new CommentService();
		TrackService trackService = new TrackService();
		AudioTrack track = trackService.getTrackById(Integer.valueOf(idTrack));
		if((user != null) && (track != null) && !commentText.isEmpty()){
		service.addComment(user, track, commentText);
		} else{
			System.out.println("command error, some parameters are empty");
		}
		return super.execute(request);
	}

}
