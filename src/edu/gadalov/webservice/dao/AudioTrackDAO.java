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
import edu.gadalov.webservice.util.Bool;

/**Audio track DAO class @see {@link AudioTrack#AudioTrack(int, edu.gadalov.webservice.entity.User, String, String, String, String, float, String, boolean)}
 * @author Maxim Gadalov
 *
 */
public class AudioTrackDAO extends AbstractDAO<Integer, AudioTrack>{
	private static final int INDEX_VISIBLE_TRACKS = 1;
	private static final int INDEX_INVISIBLE_TRACKS = 0;
	private static final String ADD_TRACK ="INSERT INTO `mydb`.`audio_tracks` (`singer`,`track_name`,`cost`,`id_admin`,`music_file`,`album`,`genre`,`visibility`) VALUES (?,?,?,?,?,?,?,"+INDEX_VISIBLE_TRACKS+")";
	private static final String SELECT_ALL_TRACKS = "SELECT `id_audio_track`,`singer`,`track_name`,`cost`,`id_admin`,`music_file`,`album`,`genre`,`visibility` "
			+ "FROM `mydb`.`audio_tracks`";
	private static final String SELECT_TRACK_BY_ID = "SELECT `id_audio_track`,`singer`,`track_name`,`cost`,`id_admin`,`music_file`,`album`,`genre`,`visibility`"
			+ " FROM `mydb`.`audio_tracks` WHERE `id_audio_track` = ?";
	private static final String SELECT_TRACKS_BY_GENRE = "SELECT `id_audio_track`,`singer`,`track_name`,`cost`,`id_admin`,`music_file`,`album`,`genre`,`visibility`"
			+ " FROM `mydb`.`audio_tracks` WHERE `genre` = ? AND `visibility`= "+INDEX_VISIBLE_TRACKS;
	private static final String DELETE_TRACK = "UPDATE `mydb`.`audio_tracks` SET `visibility`= "+INDEX_INVISIBLE_TRACKS+" WHERE `singer` = ? AND `track_name` = ?";
	private static final String SELECT_VISIBLE_TRACKS = "SELECT `id_audio_track`,`singer`,`track_name`,`cost`,`id_admin`,`music_file`,`album`,`genre`,`visibility`"
			+ " FROM `mydb`.`audio_tracks` WHERE `visibility` = "+INDEX_VISIBLE_TRACKS;
	private static final String UPDATE_TRACK = "UPDATE `mydb`.`audio_tracks` SET `singer` = ?, `track_name` = ?, `cost` = ?, `album` = ?, `genre` = ? WHERE `id_audio_track` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	
	public Connection getConnection(){
		return cn;
	}

	@Override
	public List<AudioTrack> findAll() {
		 List<AudioTrack> tracks = new ArrayList<>();
		 Statement st = null;
		 ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.createStatement();
				 ResultSet rs = st.executeQuery(SELECT_ALL_TRACKS);
				 while(rs.next()){
					 UserDAO userDAO = new UserDAO();
					 try{
					 AudioTrack track = new AudioTrack(
							 rs.getInt("id_audio_track"),
							 userDAO.findById(rs.getInt("id_admin")),
							 rs.getString("singer"),
							 rs.getString("track_name"),
							 rs.getString("album"),
							 rs.getString("music_file"),
							 rs.getFloat("cost"),
							 rs.getString("genre"),
							 rs.getBoolean("visibility"));
					 tracks.add(track);
					 } finally{
						 userDAO.close(userDAO.getConnection());
					 }
				 }
			}
		};
		exceptionHandling(method, st);
		return tracks;
	}

	@Override
	public AudioTrack findById(Integer id) {
		PreparedStatement st = null;
		AudioTrack track = new AudioTrack();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_TRACK_BY_ID);
				((PreparedStatement) st).setInt(1, id);
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					 UserDAO userDAO = new UserDAO();
					 try{
					     track.setId(rs.getInt("id_audio_track"));
						 track.setUser(userDAO.findById(rs.getInt("id_admin")));
						 track.setSinger(rs.getString("singer"));
						 track.setTrackName(rs.getString("track_name"));
						 track.setAlbum(rs.getString("album"));
						 track.setFilePath(rs.getString("music_file"));
						 track.setCost(rs.getFloat("cost"));
						 track.setGenre(rs.getString("genre"));
						 track.setVisibility(rs.getBoolean("visibility"));
					 } finally{
						 userDAO.close(userDAO.getConnection());
					 }
				 }
			}
		};
		exceptionHandling(method, st);
		return track;
	}

	@Override
	public boolean create(AudioTrack entity) {
		PreparedStatement st = null;
		Bool result = new Bool();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(ADD_TRACK);
				((PreparedStatement) st).setString(1,entity.getSinger());
				((PreparedStatement) st).setString(2,entity.getTrackName());
				((PreparedStatement) st).setFloat(3, entity.getCost());
				((PreparedStatement) st).setInt(4, entity.getUser().getId());
				((PreparedStatement) st).setString(5, entity.getFilePath());
				((PreparedStatement) st).setString(6, entity.getAlbum());
				((PreparedStatement) st).setString(7, entity.getGenre());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}

	@Override
	public boolean delete(AudioTrack entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) {
		throw new UnsupportedOperationException();
	}
	/**Returns list of objects found by audio track genre
	 * @param genre - @see {@link AudioTrack#getGenre()}
	 * @return list of AudioTrack objects @see {@link AudioTrack#AudioTrack(int, edu.gadalov.webservice.entity.User, String, String, String, String, float, String, boolean)}
	 */
	public List<AudioTrack> findByGenre(String genre){
		List<AudioTrack> tracks = new ArrayList<>();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_TRACKS_BY_GENRE);
				((PreparedStatement) st).setString(1, genre);
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					 UserDAO userDAO = new UserDAO();
					 try{
						AudioTrack track = new AudioTrack(
							 rs.getInt("id_audio_track"),
							 userDAO.findById(rs.getInt("id_admin")),
							 rs.getString("singer"),
							 rs.getString("track_name"),
							 rs.getString("album"),
							 rs.getString("music_file"),
							 rs.getFloat("cost"),
							 rs.getString("genre"),
							 rs.getBoolean("visibility"));
						tracks.add(track);
					 } finally{
						 userDAO.close(userDAO.getConnection());
					 }
				 }
			}
		};
		exceptionHandling(method, st);
		return tracks;
	}
	/**Returns non-removal marked AudioTracks @see {@link AudioTrack#AudioTrack(int, edu.gadalov.webservice.entity.User, String, String, String, String, float, String, boolean)}
	 * @return list of AduioTrack 
	 */
	public List<AudioTrack> findVisibleTracks() {
		 List<AudioTrack> tracks = new ArrayList<>();
		 Statement st = null;
		 ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.createStatement();
				 ResultSet rs = st.executeQuery(SELECT_VISIBLE_TRACKS);
				 while(rs.next()){
					 UserDAO userDAO = new UserDAO();
					 try{
					 AudioTrack track = new AudioTrack(
							 rs.getInt("id_audio_track"),
							 userDAO.findById(rs.getInt("id_admin")),
							 rs.getString("singer"),
							 rs.getString("track_name"),
							 rs.getString("album"),
							 rs.getString("music_file"),
							 rs.getFloat("cost"),
							 rs.getString("genre"),
							 rs.getBoolean("visibility"));
					 tracks.add(track);
					 } finally{
						 userDAO.close(userDAO.getConnection());
					 }
				 }
			}
		};
		exceptionHandling(method, st);
		return tracks;
	}
	/**Update database table.
	 * @param entity - AudioTrack @see {@link AudioTrack#AudioTrack(int, edu.gadalov.webservice.entity.User, String, String, String, String, float, String, boolean)}
	 * @return true if object update successfully , false - otherwise
	 */
	public boolean updateTrack(AudioTrack entity){
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(UPDATE_TRACK);
				((PreparedStatement) st).setString(1,entity.getSinger());
				((PreparedStatement) st).setString(2, entity.getTrackName());
				((PreparedStatement) st).setFloat(3, entity.getCost());
				((PreparedStatement) st).setString(4, entity.getAlbum());
				((PreparedStatement) st).setString(5, entity.getGenre());
				((PreparedStatement) st).setInt(6, entity.getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
	/**Mark audio track as invisible.(visibility = false) 
	 * @param singer - name of performer @see {@link AudioTrack#getSinger()}
	 * @param trackName - audio track name @see {@link AudioTrack#getTrackName()}
	 * @return true if update was successful, false - otherwise
	 */
	public boolean deleteTrack(String singer, String trackName){
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(DELETE_TRACK);
				((PreparedStatement) st).setString(1, singer);
				((PreparedStatement) st).setString(2, trackName);
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
}
