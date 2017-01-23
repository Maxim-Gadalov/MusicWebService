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
import edu.gadalov.webservice.entity.BanList;

public class BanListDAO extends AbstractDAO<Integer, BanList>{
	private static final Logger LOG = LogManager.getLogger(BanListDAO.class);
	private static final String SELECT_ALL = "SELECT * FROM `mydb`.`banlist`";
	private static final String ADD_BAN = "INSERT INTO `mydb`.`banlist` (`id_user`,`reason`,`id_admin`) VALUES (?,?,?)";
	private static final String REMOVE_BAN = "DELETE FROM `mydb`.`banlist` WHERE `id_user` = ?";
	private static final String SELECT_BAN_BY_USER = "SELECT * FROM `mydb`.`banlist` WHERE `id_user` = ?";
	private Connection cn = ConnectionPool.getInstance().getConnectionFromPool();
	public Connection getConnection(){
		return cn;
	}

	@Override
	public List<BanList> findAll() {
		 List<BanList> bans = new ArrayList<>();
		 Statement st = null;
		 try{
			 st = cn.createStatement();
			 ResultSet rs = st.executeQuery(SELECT_ALL);
			 while(rs.next()){
				 UserDAO userDAO = new UserDAO();
				 try{
				 BanList ban = new BanList(
						 rs.getInt("id_banlist"),
						 userDAO.findById(rs.getInt("id_user")),
						 rs.getString("reason"),
						 rs.getInt("id_admin"));
				 bans.add(ban);
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
		return bans;
	}

	@Override
	public BanList findById(Integer idUser) {
		BanList ban = null;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(SELECT_BAN_BY_USER);
			st.setInt(1, idUser);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				UserDAO userDAO = new UserDAO();
				try{
					ban = new BanList(
						      rs.getInt("id_banlist"),
						      userDAO.findById(rs.getInt("id_user")),
						      rs.getString("reason"),
						      rs.getInt("id_admin"));
				} finally{
					userDAO.close(userDAO.getConnection());
				}
			}
		} catch(SQLException e){
			LOG.error(e);
		} finally{
			statementClose(st);
		}
		return ban;
	}

	@Override
	public boolean create(BanList entity) {
		PreparedStatement st = null;
		boolean result = false;
		try{
			st = cn.prepareStatement(ADD_BAN);
			st.setInt(1,entity.getUser().getId());
			st.setString(2,entity.getReason());
			st.setInt(3, entity.getIdAdmin());
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
	public boolean delete(BanList entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer idUser) {
		PreparedStatement st = null;
		boolean result = false;
		try{
			st = cn.prepareStatement(REMOVE_BAN);
			st.setInt(1,idUser);
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
