package edu.gadalov.webservice.service;

import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.dao.CommentDAO;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Comment;
import edu.gadalov.webservice.entity.User;

public class CommentService {
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
