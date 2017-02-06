package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.util.Bool;

/**User DAO class, @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
 * @author Maxim Gadalov
 *
 */
public class UserDAO extends AbstractDAO<Integer,User>{
	private final static String ADD_USER = "INSERT INTO `mydb`.`users` (`id_role`,`nickname`,`email`,`password`,`id_bonus`) VALUES (?,?,?,MD5(?),?)";
	private final static String SELECT_ALL_USERS = "SELECT `id_users`,`id_role`,`nickname`,`email`,`password`,`id_bonus`,`skype`,`phone_number` FROM `mydb`.`users`";
	private final static String SELECT_USER_BY_ID = "SELECT `id_users`,`id_role`,`nickname`,`email`,`password`,`id_bonus`,`skype`,`phone_number` FROM `mydb`.`users` WHERE `id_users` = ?";
	private final static String DELETE_USER_BY_ID = "DELETE `id_users`,`id_role`,`nickname`,`email`,`password`,`id_bonus`,`skype`,`phone_number` FROM `mydb`.`users` WHERE `id_users` = ?";
	private final static String SELECT_USER_BY_EMAIL = "SELECT `id_users`,`id_role`,`nickname`,`email`,`password`,`id_bonus`,`skype`,`phone_number` FROM `mydb`.`users` WHERE `email` = ?";
	private final static String SELECT_USER_BY_NICKNAME = "SELECT `id_users`,`id_role`,`nickname`,`email`,`password`,`id_bonus`,`skype`,`phone_number` FROM `mydb`.`users` WHERE `nickname` = ?";
	private final static String UPDATE_USER = "UPDATE `mydb`.`users` SET `nickname` = ? , `email` = ? , `skype` = ? , `phone_number` = ? WHERE `id_users` = ?";
	private final static String UPDATE_PASSWORD = "UPDATE `mydb`.`users` SET `password` = ? WHERE `id_users` = ?";
	private final static String UPDATE_DISCOUNT = "UPDATE `mydb`.`users` SET `id_bonus` = ? WHERE `id_users` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	/**Return connection taken from ConnectionPool @see {@link ConnectionPool#getConnectionFromPool()}
	 * @return connection
	 */
	public Connection getConnection(){
		return cn;
	}
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		Statement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.createStatement();
				ResultSet rs = st.executeQuery(SELECT_ALL_USERS);
				while(rs.next()){
					RoleDAO role = new RoleDAO(cn);
					DiscountDAO bonus = new DiscountDAO(cn);
					User user = new User(
							rs.getInt("id_users"),
							role.findById(rs.getInt("id_role")),
							rs.getString("nickname"),
							rs.getString("email"),
							rs.getString("password"),
							bonus.findById(rs.getInt("id_bonus")),
							rs.getString("skype"),
							rs.getString("phone_number"));
					users.add(user);
				}
			}
		};
		exceptionHandling(method,st);
		return users;
	}

	@Override
	public User findById(Integer id) {
		PreparedStatement st = null;
		User user = new User();
		ExceptionHandling method = new ExceptionHandling(){

			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_USER_BY_ID);
				((PreparedStatement) st).setInt(1, id);
				ResultSet rs = ((PreparedStatement)st).executeQuery();
				while(rs.next()){
					RoleDAO role = new RoleDAO(cn);
					DiscountDAO bonus = new DiscountDAO(cn);
					user.setId(rs.getInt("id_users"));
					user.setRole(role.findById(rs.getInt("id_role")));
					user.setNickname(rs.getString("nickname"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setBonus(bonus.findById(rs.getInt("id_bonus")));
					user.setSkype(rs.getString("skype"));
					user.setPhoneNumber(rs.getString("phone_number"));
				}
			}
		};
		exceptionHandling(method, st);
		return user;
	}

	@Override
	public boolean create(User entity) {
		PreparedStatement st = null;
		Bool result = new Bool();
		ExceptionHandling method = new ExceptionHandling(){

			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(ADD_USER);
				((PreparedStatement) st).setInt(1,entity.getRole().getId());
				((PreparedStatement) st).setString(2,entity.getNickname());
				((PreparedStatement) st).setString(3, entity.getEmail());
				((PreparedStatement) st).setString(4, entity.getPassword());
				((PreparedStatement) st).setInt(5, entity.getBonus().getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}

	@Override
	public boolean delete(User entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) { // возможно удалить, так как в данной верии не используется
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(DELETE_USER_BY_ID);
				((PreparedStatement) st).setInt(1, id);
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
	/**Return User found by email
	 * @param email - email (String)
	 * @return User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 */
	public User findByEmail(String email){
		PreparedStatement st = null;
		User user = new User();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_USER_BY_EMAIL);
				((PreparedStatement) st).setString(1, email);
				ResultSet rs = ((PreparedStatement)st).executeQuery();
				while(rs.next()){
					RoleDAO role = new RoleDAO(cn);
					DiscountDAO bonus = new DiscountDAO(cn);
					user.setId(rs.getInt("id_users"));
					user.setRole(role.findById(rs.getInt("id_role")));
					user.setNickname(rs.getString("nickname"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setBonus(bonus.findById(rs.getInt("id_bonus")));
					user.setSkype(rs.getString("skype"));
					user.setPhoneNumber(rs.getString("phone_number"));
					}
			}
		};
		exceptionHandling(method, st);
		return user;
	}
	
	/**Return User found by nickname
	 * @param nickname - nickname (String)
	 * @return User @See {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 */
	public User findByNickname(String nickname){
		PreparedStatement st = null;
		User user = new User();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_USER_BY_NICKNAME);
				((PreparedStatement) st).setString(1, nickname);
				ResultSet rs = ((PreparedStatement)st).executeQuery();
				while(rs.next()){
					RoleDAO role = new RoleDAO(cn);
					DiscountDAO bonus = new DiscountDAO(cn);
					user.setId(rs.getInt("id_users"));
					user.setRole(role.findById(rs.getInt("id_role")));
					user.setNickname(rs.getString("nickname"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setBonus(bonus.findById(rs.getInt("id_bonus")));
					user.setSkype(rs.getString("skype"));
					user.setPhoneNumber(rs.getString("phone_number"));
					}
			}
		};
		exceptionHandling(method, st);
		return user;
	}
	/**Update database record
	 * @param entity - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 * @return true if update successfully , else - otherwise
	 */
	public boolean updateUser(User entity){
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(UPDATE_USER);
				((PreparedStatement) st).setString(1,entity.getNickname());
				((PreparedStatement) st).setString(2, entity.getEmail());
				((PreparedStatement) st).setString(3, entity.getSkype());
				((PreparedStatement) st).setString(4, entity.getPhoneNumber());
				((PreparedStatement) st).setInt(5, entity.getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
	/**update user password
	 * @param entity - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 * @return true if update successfully, else - otherwise
	 */
	public boolean updateUserPassword(User entity){
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(UPDATE_PASSWORD);
				((PreparedStatement) st).setString(1,entity.getPassword());
				((PreparedStatement) st).setInt(2, entity.getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
	/**update user discount
	 * @param discount - Discount @see {@link Discount#Discount(int, int)}
	 * @param entity - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, Discount, String, String)}
	 * @return true if update was successful, false - otherwise
	 */
	public boolean updateUserDiscount(Discount discount, User entity){
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(UPDATE_DISCOUNT);
				((PreparedStatement) st).setInt(1,discount.getId());
				((PreparedStatement) st).setInt(2, entity.getId());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();	
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
}
