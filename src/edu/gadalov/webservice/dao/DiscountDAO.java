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

import edu.gadalov.webservice.entity.Discount;

public class DiscountDAO extends AbstractDAO<Integer, Discount>{
	private static final Logger LOG = LogManager.getLogger(DiscountDAO.class);
	private final static String SELECT_USER_DISCOUNT_BY_ID = "SELECT `discounts_value` FROM `mydb`.`discounts` WHERE `id_discounts` = ";
	private final static String SELECT_DISCOUNTS = "SELECT * FROM `mydb`.`discounts`";
	private final static String ADD_DISCOUNT = "INSERT INTO `mydb`.`discounts`(`discounts_value`) VALUES (?)";
	private final static String DELETE_DISCOUNT_BY_ID = "DELETE * FROM `mudb`.`discounts` WHERE `id_discounts` = ?";
	private final static String DELETE_DISCOUNT = "DELETE * FROM `mudb`.`discounts` WHERE `discounts_value` = ?";
	private Connection cn;
	public DiscountDAO(Connection cn){
		this.cn = cn;
	}
	public Connection getConnection(){
		return cn;
	}
	@Override
	public List<Discount> findAll() {
		List<Discount> discounts = new ArrayList<>();
		Statement st = null;
		try{
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(SELECT_DISCOUNTS);
			while(rs.next()){
				Discount discount = new Discount(
						rs.getInt("id_discounts"),
						rs.getInt("discounts_value")
						);
				discounts.add(discount);
			}
		} catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);
		}
		return discounts;		
	}

	@Override
	public Discount findById(Integer id) {
		Statement st = null;
		Discount bonus = null;
		try{
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(SELECT_USER_DISCOUNT_BY_ID+id);
			while(rs.next()){
			bonus = new Discount(id,rs.getInt("discounts_value"));
			}
		}
		catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);	
		}
		return bonus;
	}

	@Override
	public boolean create(Discount entity) {
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(ADD_DISCOUNT);
			st.setInt(1, entity.getBonus());
			st.executeUpdate();
			result = true;
		} catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);
		}
		
		return result;
	}

	@Override
	public boolean delete(Discount entity) {
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(DELETE_DISCOUNT);
			st.setInt(1, entity.getBonus());
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

	@Override
	public boolean deleteById(Integer id) {
		boolean result = false;
		PreparedStatement st = null;
		try{
			st = cn.prepareStatement(DELETE_DISCOUNT_BY_ID);
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

}
