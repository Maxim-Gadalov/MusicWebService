package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.CommentService;
import edu.gadalov.webservice.service.TrackService;

/**Add comment command class.
 * @author Maxim Gadalov
 *
 */
public class AddCommentCommand extends TracksPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String COMMENT_ERROR = "Comment message can't be empty";
	private static final String DB_ERROR = "Database connection was interrupted";
	private static final String SUCCESS_URL_PATH = "/MusicWebService/MusicServiceServlet?command=tracks-page";
	
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
		String errorMessage = new String();
		if((track.isUntapped())){
			errorMessage = DB_ERROR;
		} 
		if(commentText.trim().isEmpty()){
			errorMessage = COMMENT_ERROR;
		}
		if(errorMessage.isEmpty()){
			if(service.addComment(user, track, commentText)){
			request.setAttribute("success", true);
			return SUCCESS_URL_PATH;
			}
		} else{
			request.setAttribute("errorMessage", errorMessage);
		}
		return super.execute(request);
	}

}
