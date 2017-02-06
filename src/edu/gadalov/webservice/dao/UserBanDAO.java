package edu.gadalov.webservice.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.entity.UserBan;
import edu.gadalov.webservice.util.Bool;

/**UserBan DAO class @see {@link UserBan#UserBan(int, edu.gadalov.webservice.entity.User, String, int)}
 * @author Maxim Gadalov
 *
 */
public class UserBanDAO extends AbstractDAO<Integer, UserBan>{
	private static final String SELECT_ALL = "SELECT `id_banlist`,`id_user`,`reason`,`id_admin` FROM `mydb`.`banlist`";
	private static final String ADD_BAN = "INSERT INTO `mydb`.`banlist` (`id_user`,`reason`,`id_admin`) VALUES (?,?,?)";
	private static final String REMOVE_BAN = "DELETE FROM `mydb`.`banlist` WHERE `id_user` = ?";
	private static final String SELECT_BAN_BY_USER = "SELECT `id_banlist`,`id_user`,`reason`,`id_admin` FROM `mydb`.`banlist` WHERE `id_user` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	/**Return connection taken from ConnectionPool @see {@link ConnectionPool#getConnectionFromPool()}
	 * @return connection
	 */
	public Connection getConnection(){
		return cn;
	}

	@Override
	public List<UserBan> findAll() {
		 List<UserBan> bans = new ArrayList<>();
		 Statement st = null;
		 ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.createStatement();
				ResultSet rs = st.executeQuery(SELECT_ALL);
				while(rs.next()){
					UserDAO userDAO = new UserDAO();
					try{
						UserBan ban = new UserBan(
							rs.getInt("id_banlist"),
							userDAO.findById(rs.getInt("id_user")),
							rs.getString("reason"),
							rs.getInt("id_admin"));
					bans.add(ban);
					} finally{
						userDAO.close(userDAO.getConnection());
					}
				}
			}
		};
		exceptionHandling(method, st);
		return bans;
	}

	@Override
	public UserBan findById(Integer idUser) {
		UserBan ban = new UserBan();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_BAN_BY_USER);
				((PreparedStatement) st).setInt(1, idUser);
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					UserDAO userDAO = new UserDAO();
					try{
						ban.setId(rs.getInt("id_banlist"));
					    ban.setUser(userDAO.findById(rs.getInt("id_user")));
						ban.setReason(rs.getString("reason"));
					    ban.setIdAdmin(rs.getInt("id_admin"));
					} finally{
						userDAO.close(userDAO.getConnection());
					}
				}
			}
		};
		exceptionHandling(method, st);
		return ban;
	}
	
	@Override
	public boolean create(UserBan entity) {
		PreparedStatement st = null;
		Bool result = new Bool();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(ADD_BAN);
				((PreparedStatement) st).setInt(1,entity.getUser().getId());
				((PreparedStatement) st).setString(2,entity.getReason());
				((PreparedStatement) st).setInt(3, entity.getIdAdmin());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}

	@Override
	public boolean delete(UserBan entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer idUser) {
		PreparedStatement st = null;
		Bool result = new Bool();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(REMOVE_BAN);
				((PreparedStatement) st).setInt(1,idUser);
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	} 
	
}
