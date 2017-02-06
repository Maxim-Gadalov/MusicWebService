package edu.gadalov.webservice.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.connection.ConnectionPool;

/**Abstract DAO class
 * @author Maxim Gadalov
 *
 * @param <K> - abstract key
 * @param <T> - abstract parameter
 */
public abstract class AbstractDAO<K,T> {
	private static final Logger LOG = LogManager.getLogger(AbstractDAO.class);
	/**
	 * @return List of objects 
	 */
	public abstract List<T> findAll();
	/**
	 * @param id - integer id value
	 * @return object found by id
	 */
	public abstract T findById(K id);
	/**
	 * @param entity - entity object that will be created
	 * @return true if object created, false - otherwise
	 */
	public abstract boolean create(T entity);
	/**
	 * @param entity - entity object that will be deleted
	 * @return true if object deleted, false - otherwise
	 */
	public abstract boolean delete(T entity);
	/**
	 * @param id - integer id value
	 * @return true if object deleted, false - otherwise
	 */
	public abstract boolean deleteById(K id);
	/**Put exist connection back to the pool
	 * @param cn - taken connection from pool 
	 */
	public  void close(Connection cn){
		ConnectionPool.getInstance().putConnectionBack(cn);
	}
	/**Close statement
	 * @param st - statement 
	 */
	public static void statementClose(Statement st){
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				LOG.error("Can't close statement"+e);
			}
		}
		
	} // нововведения
	/**Handling SQL Exceptions
	 * @param methodBody object of body logic function
	 * @param st Statement
	 */
	public void exceptionHandling(ExceptionHandling methodBody,Statement st){
		try{
			methodBody.run(st);
		} catch(SQLException e){
			LOG.error(e);
		} finally{
			statementClose(st);
		}
	}
	/**Return connection taken from ConnectionPool @see {@link ConnectionPool#getConnectionFromPool()}
	 * @return connection
	 */
	public abstract Connection getConnection();
}
