package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.entity.Role;

public class RoleDAO extends AbstractDAO<Integer, Role>{
	private static final Logger LOG = LogManager.getLogger(RoleDAO.class);
	private final static String SELECT_USER_ROLE_BY_ID = "SELECT `roles_name` FROM `mydb`.`roles` WHERE `id_roles` = ";
	private Connection cn;
	public RoleDAO(Connection cn){
		this.cn = cn;		
	}

	@Override
	public List<Role> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Role findById(Integer id) {
		Statement st = null;
		Role role = null;
		try{
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(SELECT_USER_ROLE_BY_ID+id);
			while(rs.next()){
			role = new Role(id,rs.getString("roles_name"));
			}
		}
		catch(SQLException e){
			LOG.error(e);
		}
		finally{
			statementClose(st);	
		}
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
