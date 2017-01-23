package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.CommentService;

public class EditCommentCommand extends TracksPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String EDIT_COMMENT_ERROR_MESSAGE = "Can not update comment";
	
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
		}
		return super.execute(request);
	}

}
