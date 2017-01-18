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
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;

public class TrackOrderDAO extends AbstractDAO<Integer, TrackOrder>{
	private static final Logger LOG = LogManager.getLogger(TrackOrderDAO.class);
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	private static final String SELECT_ALL_ORDERS = "SELECT * FROM `mydb`.`track_order`";
	private static final String ADD_ORDER = "INSERT INTO `mydb`.`track_order`(`id_user`,`id_audio_track`) VALUES (?,?)";
	private static final String SELECT_ORDERS_BY_USER = "SELECT * FROM `mydb`.`track_order` WHERE `id_user` = ";
	private static final String SELECT_ORDERS_BY_TRACK = "SELECT * FROM `mydb`.`track_order` WHERE `id_audio_track` = ";

	@Override
	public List<TrackOrder> findAll() {
		List<TrackOrder> orders = new ArrayList<>();
		Statement st = null;
		try{
			st = cn.createStatement();
			 ResultSet rs = st.executeQuery(SELECT_ALL_ORDERS);
			 while(rs.next()){
				 UserDAO user = new UserDAO();
				 AudioTrackDAO track = new AudioTrackDAO();
				 try{
				 TrackOrder order = new TrackOrder(
						 user.findById(rs.getInt("id_user")),
						 track.findById(rs.getInt("id_audio_track")));
				 orders.add(order);
				 
			 }finally{
				 user.close(user.getConnection());
				 track.close(track.getConnection());
			 }
			 } 
			 

		}catch(SQLException e){
			 LOG.error(e);
		}finally{
			statementClose(st);
		}
		
		return orders;
	}

	@Override
	public TrackOrder findById(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(TrackOrder entity) {
		PreparedStatement st = null;
		boolean result = false;
		try{
			st = cn.prepareStatement(ADD_ORDER);
			st.setInt(1,entity.getUser().getId());
			st.setInt(2,entity.getTrack().getId());
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
	public boolean delete(TrackOrder entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) {
		throw new UnsupportedOperationException();
	}
	public List<TrackOrder> findByUser(User entity) {
		List<TrackOrder> orders = new ArrayList<>();
		Statement st = null;
		try{
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(SELECT_ORDERS_BY_USER + entity.getId());
			while(rs.next()){
				 AudioTrackDAO track = new AudioTrackDAO();
				 try{
				 TrackOrder order = new TrackOrder(
						 entity,
						 track.findById(rs.getInt("id_audio_track")));
				 orders.add(order);
			 }finally{
				 track.close(track.getConnection());
				 }
				 } 
			}catch(SQLException e){
				LOG.error(e);
				}finally{
					statementClose(st);
					}
		return orders;	
    }
	public List<TrackOrder> findByTrack(AudioTrack entity) {
		List<TrackOrder> orders = new ArrayList<>();
		Statement st = null;
		try{
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(SELECT_ORDERS_BY_TRACK + entity.getId());
			while(rs.next()){
				UserDAO user = new UserDAO();
				try{
					TrackOrder order = new TrackOrder(
						 user.findById(rs.getInt("id_user")),
						 entity);
				 orders.add(order);
				 }finally{
					 user.close(user.getConnection());
					 }
				} 
			}catch(SQLException e){
				 LOG.error(e);
			}finally{
				statementClose(st);
			}
			return orders;	
	}
}