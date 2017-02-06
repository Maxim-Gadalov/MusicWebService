package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Comment;
import edu.gadalov.webservice.util.Bool;

/**Comment DAO class @see {@link Comment#Comment(int, edu.gadalov.webservice.entity.User, AudioTrack, String, java.sql.Timestamp)}
 * @author Maxim Gadalov
 *
 */
public class CommentDAO extends AbstractDAO<Integer, Comment>{
	private static final String SELECT_COMMENT_BY_TRACK = "SELECT `id_comments`,`id_user`,`comment_text`,`date`,`id_audio_track` FROM `mydb`.`comments` WHERE `id_audio_track` = ?";
	private static final String UPDATE_COMMENT = "UPDATE `mydb`.`comments` SET `comment_text` = ? WHERE id_comments = ?";
	private static final String ADD_COMMENT = "INSERT INTO `mydb`.`comments` (`id_user`,`comment_text`,`date`,`id_audio_track`) VALUES (?,?,NOW(),?)";
	private static final String DELETE_COMMENT = "DELETE FROM `mydb`.`comments` WHERE `id_comments` = ?"; 
	private static final String SELECT_COMMENT_BY_ID = "SELECT `id_comments`,`id_user`,`comment_text`,`date`,`id_audio_track` FROM `mydb`.`comments` WHERE `id_comments` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();

	public Connection getConnection(){
		return cn;
	} 
	@Override
	public List<Comment> findAll() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Comment findById(Integer id) {
		PreparedStatement st = null;
		Comment comment = new Comment();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_COMMENT_BY_ID);
				((PreparedStatement) st).setInt(1, id);
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					UserDAO userDAO = new UserDAO();
					AudioTrackDAO trackDAO = new AudioTrackDAO();
					try{
						comment.setId(rs.getInt("id_comments"));
						comment.setUser(userDAO.findById(rs.getInt("id_user")));
						comment.setTrack(trackDAO.findById(rs.getInt("id_audio_track")));
						comment.setText(rs.getString("comment_text"));
						comment.setDate(rs.getTimestamp("date"));
					} finally{
						userDAO.close(userDAO.getConnection());
						trackDAO.close(trackDAO.getConnection());
					}
				}	
			}
		};
		exceptionHandling(method, st);
		return comment;
	}
	
	@Override
	public boolean create(Comment entity) {
		PreparedStatement st = null;
		Bool result = new Bool();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(ADD_COMMENT);
				((PreparedStatement) st).setInt(1,entity.getUser().getId());
				((PreparedStatement) st).setString(2,entity.getText());
				((PreparedStatement) st).setInt(3, entity.getTrack().getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}

	@Override // отсутствует в бизнес логике (покуда) 
	public boolean delete(Comment entity) {
		PreparedStatement st = null;
		Bool result = new Bool();
		ExceptionHandling mathod = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(DELETE_COMMENT);
				((PreparedStatement) st).setInt(1, entity.getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(mathod, st);
		return result.getBoolValue();
	}
	@Override
	public boolean deleteById(Integer id) {
		throw new UnsupportedOperationException();
	}
	/**update database table.
	 * @param entity - comment @see {@link Comment#Comment(int, edu.gadalov.webservice.entity.User, AudioTrack, String, java.sql.Timestamp)}
	 * @return true if update was successfully , else - otherwise
	 */
	public boolean updateComment(Comment entity){
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(UPDATE_COMMENT);
				((PreparedStatement) st).setString(1, entity.getText());
				((PreparedStatement) st).setInt(2, entity.getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
	/**Return list of comments found by audio track
	 * @param track - audio track @see {@link AudioTrack#AudioTrack(int, edu.gadalov.webservice.entity.User, String, String, String, String, float, String, boolean)}
	 * @return list of comments @see {@link Comment#Comment(int, edu.gadalov.webservice.entity.User, AudioTrack, String, java.sql.Timestamp)}
	 */
	public List<Comment> findByTrack(AudioTrack track){
		PreparedStatement st = null;
		List<Comment> list = new ArrayList<>();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_COMMENT_BY_TRACK);
				((PreparedStatement) st).setInt(1, track.getId());
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					UserDAO userDAO = new UserDAO();
					try{
					Comment comment = new Comment(
							rs.getInt("id_comments"),
							userDAO.findById(rs.getInt("id_user")),
							track,
							rs.getString("comment_text"),
							rs.getTimestamp("date"));
					list.add(comment);
					} finally{
						userDAO.close(userDAO.getConnection());
					}
				}
			}
		};
		exceptionHandling(method, st);
		return list;
	}

}
