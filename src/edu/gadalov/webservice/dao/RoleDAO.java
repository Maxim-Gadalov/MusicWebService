package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.gadalov.webservice.entity.Role;

/**Role DAO class, @see {@link Role#Role(int, String)}.
 * @author Maxim Gadalov
 *
 */
public class RoleDAO extends AbstractDAO<Integer, Role>{
	private final static String SELECT_USER_ROLE_BY_ID = "SELECT `roles_name` FROM `mydb`.`roles` WHERE `id_roles` = ";
	private Connection cn;
	/**Create object with following field 
	 * @param cn - connection to the database
	 */
	public RoleDAO(Connection cn){
		this.cn = cn;		
	}
	@Override
	public Connection getConnection(){
		return cn;
	}

	@Override
	public List<Role> findAll() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Role findById(Integer id) {
		Statement st = null;
		Role role = new Role();
		ExceptionHandling method = new ExceptionHandling() {
			
			@Override
			public void run(Statement st) throws SQLException {
				st = cn.createStatement();
				ResultSet rs = st.executeQuery(SELECT_USER_ROLE_BY_ID+id);
				while(rs.next()){
					role.setId(id);
					role.setRoleName(rs.getString("roles_name"));
				}
			}
		};
		exceptionHandling(method, st);
		return role;
	}
	@Override
	public boolean create(Role entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Role entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteById(Integer id) {
		throw new UnsupportedOperationException();
	}
}
