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
import edu.gadalov.webservice.entity.User;

public class UserDAO extends AbstractDAO<Integer,User>{
	private static final Logger LOG = LogManager.getLogger(UserDAO.class);
	private final static String ADD_USER = "INSERT INTO `mydb`.`users` (`id_role`,`nickname`,`email`,`password`,`id_bonus`) VALUES (?,?,?,MD5(?),?)";
	private final static String SELECT_ALL_USERS = "SELECT * FROM `mydb`.`users`";
	private final static String SELECT_USER_BY_ID = "SELECT * FROM `mydb`.`users` WHERE `id_users` = ?";
	private final static String DELETE_USER_BY_ID = "DELETE * FROM `mudb`.`users` WHERE `id_users` = ?";
	private final static String SELECT_USER_BY_EMAIL = "SELECT * FROM `mydb`.`users` WHERE `email` = ?";
	private final static String SELECT_USER_BY_NICKNAME = "SELECT * FROM `mydb`.`users` WHERE `nickname` = ?";
	private final static String UPDATE_USER = "UPDATE `mydb`.`users` SET `nickname` = ? , `email` = ? , `skype` = ? , `phone_number` = ? WHERE `id_users` = ?";
	private final static String UPDATE_PASSWORD = "UPDATE `mydb`.`users` SET `password` = ? WHERE `id_users` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	public Connection getConnection(){
		return cn;
	}
	@Override
	public List<User> findAll() {
		 List<User> users = new ArrayList<>();
		 Statement st = null;
		 try{
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
		 } catch(SQLException e){
			 LOG.error(e);
		 }
		 finally{
			 statementClose(st);
		 }
		return users;
	}

	@Override
	public User findById(Integer id) {
		PreparedStatement st = null;
		User user = null;
		try{
			st = cn.prepareStatement(SELECT_USER_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				RoleDAO role = new RoleDAO(cn);
				 DiscountDAO bonus = new DiscountDAO(cn);
				 user = new User(
						 rs.getInt("id_users"),
						 role.findById(rs.getInt("id_role")),
						 rs.getString("nickname"),
						 rs.getString("email"),
						 rs.getString("password"),
						 bonus.findById(rs.getInt("id_bonus")),
						 rs.getString("skype"),
						 rs.getString("phone_number"));
			}
		}
		catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);	
		}
		return user;
	}

	@Override
	public boolean create(User entity) {
		PreparedStatement st = null;
		boolean result = false;
		try{
			st = cn.prepareStatement(ADD_USER);
			st.setInt(1,entity.getRole().getId());
			st.setString(2,entity.getNickname());
			st.setString(3, entity.getEmail());
			st.setString(4, entity.getPassword());
			st.setInt(5, entity.getBonus().getId());
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
	public boolean delete(User entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(DELETE_USER_BY_ID);
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
	public User findByEmail(String email){
		PreparedStatement st = null;
		User user = null;
		try{
			st = cn.prepareStatement(SELECT_USER_BY_EMAIL);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				RoleDAO role = new RoleDAO(cn);
				 DiscountDAO bonus = new DiscountDAO(cn);
				 user = new User(
						 rs.getInt("id_users"),
						 role.findById(rs.getInt("id_role")),
						 rs.getString("nickname"),
						 rs.getString("email"),
						 rs.getString("password"),
						 bonus.findById(rs.getInt("id_bonus")),
						 rs.getString("skype"),
						 rs.getString("phone_number"));
			}
		}catch(SQLException e){
			LOG.error(e);
			}
		finally{
			statementClose(st);
			}
		return user;
	}
	public User findByNickname(String nickname){
		PreparedStatement st = null;
		User user = null;
		try{
			st = cn.prepareStatement(SELECT_USER_BY_NICKNAME);
			st.setString(1, nickname);
			ResultSet rs = st.executeQuery();  
			while(rs.next()){
				RoleDAO role = new RoleDAO(cn);
				 DiscountDAO bonus = new DiscountDAO(cn);
				 user = new User(
						 rs.getInt("id_users"),
						 role.findById(rs.getInt("id_role")),
						 rs.getString("nickname"),
						 rs.getString("email"),
						 rs.getString("password"),
						 bonus.findById(rs.getInt("id_bonus")),
						 rs.getString("skype"),
						 rs.getString("phone_number"));
			}
		}catch(SQLException e){
			LOG.error(e);
			}
		finally{
			statementClose(st);
			}
		return user;
	}
	public boolean updateUser(User entity){
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(UPDATE_USER);
			st.setString(1,entity.getNickname());
			st.setString(2, entity.getEmail());
			st.setString(3, entity.getSkype());
			st.setString(4, entity.getPhoneNumber());
			st.setInt(5, entity.getId());
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
	public boolean updateUserPassword(User entity){
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(UPDATE_PASSWORD);
			st.setString(1,entity.getPassword());
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
}
