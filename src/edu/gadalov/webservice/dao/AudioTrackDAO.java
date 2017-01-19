package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.entity.AudioTrack;

public class AudioTrackDAO extends AbstractDAO<Integer, AudioTrack>{
	private static final Logger LOG = LogManager.getLogger(AudioTrackDAO.class);
	private static final String ADD_TRACK ="INSERT INTO `mydb`.`audio_tracks` (`singer`,`track_name`,`cost`,`id_admin`,`music_file`,`albom`,`genre`) VALUES (?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_TRACKS = "SELECT * FROM `mydb`.`audio_tracks`";
	private static final String SELECT_TRACK_BY_ID = "SELECT * FROM `mydb`.`audio_tracks` WHERE `id_audio_track` = ?";
	private static final String SELECT_TRACKS_BY_GENRE = "SELECT * FROM `mydb`.`audio_tracks` WHERE `genre` = ?";
	private static final String DELETE_TRACK_BY_ID = "DELETE * FROM `mydb`.`audio_tracks` WHERE `id_audio_track` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	public Connection getConnection(){
		return cn;
	}

	@Override
	public List<AudioTrack> findAll() {
		 List<AudioTrack> tracks = new ArrayList<>();
		 Statement st = null;
		 try{
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
						 rs.getString("albom"),
						 rs.getString("music_file"),
						 rs.getFloat("cost"),
						 rs.getString("genre"));
				 tracks.add(track);
				 } finally{
					 userDAO.close(userDAO.getConnection());
				 }
			 }
		 } catch(SQLException e){
			 LOG.error(e);
		 }
		 finally{
			 statementClose(st);
		 }
		return tracks;
	}

	@Override
	public AudioTrack findById(Integer id) {
		PreparedStatement st = null;
		AudioTrack track = null;
		try{
			st = cn.prepareStatement(SELECT_TRACK_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				 UserDAO userDAO = new UserDAO();
				 try{
				  track = new AudioTrack(
						 rs.getInt("id_audio_track"),
						 userDAO.findById(rs.getInt("id_admin")),
						 rs.getString("singer"),
						 rs.getString("track_name"),
						 rs.getString("albom"),
						 rs.getString("music_file"),
						 rs.getFloat("cost"),
						 rs.getString("genre"));
				 } finally{
					 userDAO.close(userDAO.getConnection());
				 }
			 }
		 } catch(SQLException e){
			 LOG.error(e);
		 }
		 finally{
			 statementClose(st);
		 }
		return track;
	}

	@Override
	public boolean create(AudioTrack entity) {
		PreparedStatement st = null;
		boolean result = false;
		try{
			st = cn.prepareStatement(ADD_TRACK);
			st.setString(1,entity.getSinger());
			st.setString(2,entity.getTrackName());
			st.setFloat(3, entity.getCost());
			st.setInt(4, entity.getUser().getId());
			st.setString(5, entity.getFilePath());
			st.setString(6, entity.getAlbum());
			st.setString(7, entity.getGenre());
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
	public boolean delete(AudioTrack entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(DELETE_TRACK_BY_ID);
			st.setInt(1, id);
			st.executeUpdate();
			result = true;
		} catch (SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);
		}
		return result;
	}
	public List<AudioTrack> findByGenre(String genre){
		List<AudioTrack> tracks = new ArrayList<>();
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(SELECT_TRACKS_BY_GENRE);
			st.setString(1, genre);
			st.executeUpdate();
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				 UserDAO userDAO = new UserDAO();
				 try{
					AudioTrack track = new AudioTrack(
						 rs.getInt("id_audio_track"),
						 userDAO.findById(rs.getInt("id_admin")),
						 rs.getString("singer"),
						 rs.getString("track_name"),
						 rs.getString("albom"),
						 rs.getString("music_file"),
						 rs.getFloat("cost"),
						 rs.getString("genre"));
					tracks.add(track);
				 } finally{
					 userDAO.close(userDAO.getConnection());
				 }
			 }
		 } catch(SQLException e){
			 LOG.error(e);
		 }
		 finally{
			 statementClose(st);
		 }
		return tracks;	
	}
}
