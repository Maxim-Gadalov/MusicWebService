package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Comment;

/**Comment DAO class @see {@link Comment#Comment(int, edu.gadalov.webservice.entity.User, AudioTrack, String, java.sql.Timestamp)}
 * @author Maxim Gadalov
 *
 */
public class CommentDAO extends AbstractDAO<Integer, Comment>{
	private static final Logger LOG = LogManager.getLogger(CommentDAO.class);
	private static final String SELECT_COMMENT_BY_TRACK = "SELECT * FROM `mydb`.`comments` WHERE `id_audio_track` = ?";
	private static final String UPDATE_COMMENT = "UPDATE `mydb`.`comments` SET `comment_text` = ? WHERE id_comments = ?";
	private static final String ADD_COMMENT = "INSERT INTO `mydb`.`comments` (`id_user`,`comment_text`,`date`,`id_audio_track`) VALUES (?,?,NOW(),?)";
	private static final String DELETE_COMMENT = "DELETE * FROM `mydb`.`comments` WHERE `id_comments` = ?";
	private static final String SELECT_COMMENT_BY_ID = "SELECT * FROM `mydb`.`comments` WHERE `id_comments` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	/**Return connection taken from ConnectionPool @see {@link ConnectionPool#getConnectionFromPool()}
	 * @return connection
	 */
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
		Comment comment = null;
		try{
			st = cn.prepareStatement(SELECT_COMMENT_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				UserDAO userDAO = new UserDAO();
				AudioTrackDAO trackDAO = new AudioTrackDAO();
				try{
				comment = new Comment(
						rs.getInt("id_comments"),
						userDAO.findById(rs.getInt("id_user")),
						trackDAO.findById(rs.getInt("id_audio_track")),
						rs.getString("comment_text"),
						rs.getTimestamp("date"));
				} finally{
					userDAO.close(userDAO.getConnection());
					trackDAO.close(trackDAO.getConnection());
				}
			}
		}catch(SQLException e){
			LOG.error(e);
			}
		finally{
			statementClose(st);
			}
		return comment;
	}
	@Override
	public boolean create(Comment entity) {
		PreparedStatement st = null;
		boolean result = false;
		try{
			st = cn.prepareStatement(ADD_COMMENT);
			st.setInt(1,entity.getUser().getId());
			st.setString(2,entity.getText());
			st.setInt(3, entity.getTrack().getId());
			st.executeUpdate();
			result = true;
		}catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);
		}
		return result;
	}
	@Override
	public boolean delete(Comment entity) {
		PreparedStatement st = null;
		boolean result = false;
		try{
			st = cn.prepareStatement(DELETE_COMMENT);
			st.setInt(1, entity.getId());
			st.executeUpdate();
			result = true;
		}catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);
		}
		return result;
		
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
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(UPDATE_COMMENT);
			st.setString(1, entity.getText());
			st.setInt(2, entity.getId());
			st.executeUpdate();
			result = true;
		}catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);
		}
		return result;
	}
	/**Return list of comments found by audio track
	 * @param track - audio track @see {@link AudioTrack#AudioTrack(int, edu.gadalov.webservice.entity.User, String, String, String, String, float, String, boolean)}
	 * @return list of comments @see {@link Comment#Comment(int, edu.gadalov.webservice.entity.User, AudioTrack, String, java.sql.Timestamp)}
	 */
	public List<Comment> findByTrack(AudioTrack track){
		PreparedStatement st = null;
		List<Comment> list = new ArrayList<>();
		try{
			st = cn.prepareStatement(SELECT_COMMENT_BY_TRACK);
			st.setInt(1, track.getId());
			ResultSet rs = st.executeQuery();
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
		}catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);
		}
		return list;
	}
}
