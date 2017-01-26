package edu.gadalov.webservice.service;

import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.dao.CommentDAO;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Comment;
import edu.gadalov.webservice.entity.User;

/**Comment service class.
 * @author Maxim Gadalov
 *
 */
public class CommentService {
	/**Return list of Comments found by AudioTrack
	 * @param track - AudioTrack @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @return list of Comment objects
	 */
	public List<Comment> getTrackComments(AudioTrack track){
		List<Comment> list = new ArrayList<>();
		CommentDAO dao = new CommentDAO();
		try{
			list = dao.findByTrack(track);
		} finally{
			dao.close(dao.getConnection());
		}
		return list;
	}
	/**Insert new comment into database
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @param track - AudioTrack @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @param text - String comment text
	 * @return true if insert was successful , false - otherwise
	 */
	public boolean addComment(User user, AudioTrack track, String text){
		boolean result = false;
		CommentDAO dao = new CommentDAO();
		try{
			result = dao.create(new Comment(1,user,track,text,null));
		} finally{
			dao.close(dao.getConnection());
		}
		return result;
	}
	/**Return Comment found by id
	 * @param id - integer id
	 * @return Comment @see {@link Comment#Comment(int, User, AudioTrack, String, java.sql.Timestamp)}
	 */
	public Comment getComment(Integer id){
		CommentDAO dao = new CommentDAO();
		Comment comment = null;
		try{
			comment = dao.findById(id);
		} finally{
			dao.close(dao.getConnection());
		}
		return comment;
	}
	/**Update user comment
	 * @param id - integer id
	 * @param newText - String new text
	 * @param admin - id admin, who edit comment
	 * @return true if update was successful, false - otherwise
	 */
	public boolean editComment(Integer id,String newText, User admin){
		boolean result = false;
		Comment comment = getComment(id);
		if(comment != null){
			comment.setText(newText+"(edit by "+admin.getNickname()+")");
			CommentDAO dao = new CommentDAO();
			try{
			result = dao.updateComment(comment);
			} finally{
				dao.close(dao.getConnection());
			}
		}
		return result;
	}

}
