package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.connection.ConnectionPool;

public abstract class AbstractDAO<K,T> {
	private static final Logger LOG = LogManager.getLogger(AbstractDAO.class);
	public abstract List<T> findAll();
	public abstract T findById(K id);
	public abstract boolean create(T entity);
	public abstract boolean delete(T entity);
	public abstract boolean deleteById(K id);
	public  void close(Connection cn){
		ConnectionPool.getInstance().putConnectionBack(cn);
	}
	public static void statementClose(Statement st){
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				LOG.error("Can't close statement"+e);
			}
		}
		
	}
	
}
