package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.util.Bool;

/**Discount DAO class @see {@link Discount#Discount(int, int)}.
 * @author Maxim Gadalov
 *
 */
public class DiscountDAO extends AbstractDAO<Integer, Discount>{
	private final static String SELECT_USER_DISCOUNT_BY_ID = "SELECT `discounts_value` FROM `mydb`.`discounts` WHERE `id_discounts` = ?";
	private final static String SELECT_DISCOUNTS = "SELECT `id_discounts`,`discounts_value` FROM `mydb`.`discounts`";
	private final static String ADD_DISCOUNT = "INSERT INTO `mydb`.`discounts`(`discounts_value`) VALUES (?)";
	private final static String DELETE_DISCOUNT_BY_ID = "DELETE FROM `mudb`.`discounts` WHERE `id_discounts` = ?";
	private final static String DELETE_DISCOUNT = "DELETE FROM `mudb`.`discounts` WHERE `discounts_value` = ?";
	private final static String SELECT_DISCOUNT_BY_VALUE = "SELECT `discounts_value` FROM `mydb`.`discounts` WHERE `discounts_value` = ?";
	private Connection cn;
	/**Create object with following field 
	 * @param cn - connection to the database
	 */
	public DiscountDAO(Connection cn){
		this.cn = cn;
	}
	/**Return connection of current object
	 * @return connection
	 */
	public Connection getConnection(){
		return cn;
	}

	@Override
	public List<Discount> findAll() {
		List<Discount> discounts = new ArrayList<>();
		Statement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.createStatement();
				ResultSet rs = st.executeQuery(SELECT_DISCOUNTS);
				while(rs.next()){
					Discount discount = new Discount(
							rs.getInt("id_discounts"),
							rs.getInt("discounts_value")
							);
					discounts.add(discount);
				}
			}
		};
		exceptionHandling(method, st);
		return discounts;
	}

	@Override
	public Discount findById(Integer id) {
		PreparedStatement st = null;
		Discount bonus = new Discount();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_USER_DISCOUNT_BY_ID);
				((PreparedStatement) st).setInt(1, id);
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					bonus.setId(id);
					bonus.setBonus(rs.getInt("discounts_value"));
				}
			}
		};
		exceptionHandling(method, st);
		return bonus;
	}

	@Override // покуда отсутствует в бизнес логике
	public boolean create(Discount entity) {
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(ADD_DISCOUNT);
				((PreparedStatement) st).setInt(1, entity.getBonus());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}

	@Override // покуда отсутствует в бизнес логике
	public boolean delete(Discount entity) {
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(DELETE_DISCOUNT);
				((PreparedStatement) st).setInt(1, entity.getBonus());
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}

	@Override // покуда отсутствует в бизнес логике
	public boolean deleteById(Integer id) {
		Bool result = new Bool();
		PreparedStatement st = null;
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(DELETE_DISCOUNT_BY_ID);
				((PreparedStatement) st).setInt(1, id);
				((PreparedStatement) st).executeUpdate();
				result.setTrueValue();
			}
		};
		exceptionHandling(method, st);
		return result.getBoolValue();
	}
	/**Return Discount found by discount's value
	 * @param value - discount's value
	 * @return Discount object , @see {@link Discount#Discount(int, int)}
	 */
	public Discount findByValue(Integer value) {
		PreparedStatement st = null;
		Discount bonus = new Discount();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.prepareStatement(SELECT_DISCOUNT_BY_VALUE);
				((PreparedStatement) st).setInt(1, value);
				ResultSet rs = ((PreparedStatement) st).executeQuery();
				while(rs.next()){
					bonus.setId(rs.getInt("id_discounts"));
					bonus.setBonus(value);
				}
			}
		};
		exceptionHandling(method, st);
		return bonus;
	}
}
