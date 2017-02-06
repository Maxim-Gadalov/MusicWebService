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
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.util.Bool;

/**TrackOrder DAO class, @see {@link TrackOrder#TrackOrder(User, AudioTrack)}
 * @author Maxim Gadalov
 *
 */
public class TrackOrderDAO extends AbstractDAO<Integer, TrackOrder>{
	private static final String SELECT_ALL_ORDERS = "SELECT `id_audio_track`,`id_user` FROM `mydb`.`track_order`";
	private static final String ADD_ORDER = "INSERT INTO `mydb`.`track_order`(`id_user`,`id_audio_track`) VALUES (?,?)";
	private static final String SELECT_ORDERS_BY_USER = "SELECT `id_audio_track`,`id_user` FROM `mydb`.`track_order` WHERE `id_user` = ";
	private static final String SELECT_ORDERS_BY_TRACK = "SELECT `id_audio_track`,`id_user` FROM `mydb`.`track_order` WHERE `id_audio_track` = ";
	private static final String SELECT_ORDER = "SELECT `id_audio_track`,`id_user` FROM `mydb`.`track_order` WHERE `id_user` = ? AND `id_audio_track` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	
	public Connection getConnection(){
		return this.cn;
	}

	@Override
	public List<TrackOrder> findAll() {
		List<TrackOrder> orders = new ArrayList<>();
		Statement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
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
			}
		};
		exceptionHandling(method, st);
		return orders;
	}

	@Override
	public TrackOrder findById(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean create(TrackOrder entity) {
		PreparedStatement st = null;
		Bool result = new Bool();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(ADD_ORDER);
				((PreparedStatement) st).setInt(1,entity.getUser().getId());
				((PreparedStatement) st).setInt(2,entity.getTrack().getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();	
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}

	@Override
	public boolean delete(TrackOrder entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) {
		throw new UnsupportedOperationException();
	}
	/**Return tracks that user ordered
	 * @param entity User @see User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)
	 * @return list of TrackOrder @see {@link TrackOrder#TrackOrder(User, AudioTrack)}
	 */
	public List<TrackOrder> findByUser(User entity) {
		List<TrackOrder> orders = new ArrayList<>();
		Statement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
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
			}
		};
		exceptionHandling(method, st);
		return orders;
	}
	
	/**Return list of TrackOrder objects found by AudioTrack
	 * @param entity - AudioTrack @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @return list of TrackOrders 
	 */
	public List<TrackOrder> findByTrack(AudioTrack entity) {
		List<TrackOrder> orders = new ArrayList<>();
		Statement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
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
			}
		};
		exceptionHandling(method, st);
		return orders;
	}
	/**Check track order
	 * @param order - TrackOrder @see {@link TrackOrder#TrackOrder(User, AudioTrack)}
	 * @return true if track was ordered earlier, false - otherwise
	 */
	public boolean findByTrackOrder(TrackOrder order){
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_ORDER);
				((PreparedStatement) st).setInt(1, order.getUser().getId());
				((PreparedStatement) st).setInt(2, order.getTrack().getId());
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					result.setTrueValue(); 
				 }
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
}
