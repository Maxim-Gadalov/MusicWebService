package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.CommentService;

/**Edit Comment command class.
 * @author Maxim Gadalov
 *
 */
public class EditCommentCommand extends TracksPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String EDIT_COMMENT_ERROR_MESSAGE = "Can not update comment";
	private static final String SUCCESS_URL_PATH = "/MusicWebService/MusicServiceServlet?command=tracks-page";
	
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		User user = (User)request.getSession().getAttribute("user");
		String id = request.getParameter("commentId");
		String updateText = request.getParameter("editArea"+id);
		CommentService service = new CommentService();
		String errorMessage = new String();
		if(!service.editComment(Integer.valueOf(id), updateText, user)){
			errorMessage = EDIT_COMMENT_ERROR_MESSAGE;
			request.setAttribute("errorMessage", errorMessage);
		} else{
			request.setAttribute("success", true);
			return SUCCESS_URL_PATH;
		}
		return super.execute(request);
	}

}
