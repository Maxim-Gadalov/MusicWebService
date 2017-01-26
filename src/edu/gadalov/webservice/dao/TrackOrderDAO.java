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

/**TrackOrder DAO class, @see {@link TrackOrder#TrackOrder(User, AudioTrack)}
 * @author Maxim Gadalov
 *
 */
public class TrackOrderDAO extends AbstractDAO<Integer, TrackOrder>{
	private static final Logger LOG = LogManager.getLogger(TrackOrderDAO.class);
	private static final String SELECT_ALL_ORDERS = "SELECT * FROM `mydb`.`track_order`";
	private static final String ADD_ORDER = "INSERT INTO `mydb`.`track_order`(`id_user`,`id_audio_track`) VALUES (?,?)";
	private static final String SELECT_ORDERS_BY_USER = "SELECT * FROM `mydb`.`track_order` WHERE `id_user` = ";
	private static final String SELECT_ORDERS_BY_TRACK = "SELECT * FROM `mydb`.`track_order` WHERE `id_audio_track` = ";
	private static final String SELECT_ORDER = "SELECT * FROM `mydb`.`track_order` WHERE `id_user` = ? AND `id_audio_track` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	/**Return connection taken from ConnectionPool @see {@link ConnectionPool#getConnectionFromPool()}
	 * @return connection
	 */
	public Connection getConnection(){
		return this.cn;
	}
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
	/**Return list of TrackOrder objects found by AudioTrack
	 * @param entity - AudioTrack @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @return list of TrackOrders 
	 */
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
	/**Check track order
	 * @param order - TrackOrder @see {@link TrackOrder#TrackOrder(User, AudioTrack)}
	 * @return true if track was ordered earlier, false - otherwise
	 */
	public boolean findByTrackOrder(TrackOrder order){
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(SELECT_ORDER);
			st.setInt(1, order.getUser().getId());
			st.setInt(2, order.getTrack().getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				result = true; 
			 }
		 } catch(SQLException e){
			 LOG.error(e);
		 }
		 finally{
			 statementClose(st);
		 }
		return result;
	}
}
