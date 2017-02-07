package edu.gadalov.webservice.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.exception.DatabaseRequestException;

import org.apache.logging.log4j.LogManager;

/**Custom thread safety connection pool.
 * @author Maxim Gadalov
 *
 */
public class ConnectionPool {
	private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);
	private static String dbUrl; 
	private static String dbUser;
	private static String dbPassword;
	private static final int MAX_CONNECTIONS = 20;
	private static Lock lock = new ReentrantLock();
	private static AtomicBoolean checker = new AtomicBoolean();
	private static ConnectionPool pool;
	private static ResourceBundle resource;
	private  BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>(MAX_CONNECTIONS);
	/**
	 * Register JDBC driver and initialize resources.
	 */
	private ConnectionPool(){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			LOG.fatal("Can not register JDBC driver "+e);
			throw new DatabaseRequestException("Can not register JDBC driver");
		}
		initResource();
	}
	/**Thread safety initialize object method.
	 * @return ConnectionPool object
	 */
	public static ConnectionPool getInstance(){
		if (!checker.get()){
		try{
			lock.lock();
			if(pool == null){
			pool = new ConnectionPool();
			pool.createAllConnections();
			pool.checkConnection();
			}
			checker.set(true);
		}
		finally{
			lock.unlock();
		}
		}
		return pool;	
	}
	
	/**Create connection
	 * @return connection
	 */
	private  Connection createConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException e) {
			LOG.error("Connection not avalible "+e);
		}
		return conn;
	}
	/**
	 * Create all adjusted connection.
	 */
	private void createAllConnections(){
		for(int i = 0;i < MAX_CONNECTIONS;i++){
			Connection cn = createConnection();
			if(cn != null){
			connectionPool.add(createConnection());
			}
		}
	}
	/**Take connection from connection pool.
	 * @return connection
	 */
	public Connection getConnectionFromPool(){
		Connection cn = null;
		try {
			cn = connectionPool.take();
		} catch (InterruptedException e) {
			LOG.error(e);
		}
		return cn;
	}
	/**Put connection back to the pool
	 * @param conn - connection
	 */
	public void putConnectionBack(Connection conn){
		try {
			connectionPool.put(conn);
		} catch (InterruptedException e) {
			LOG.error(e);
		}	
	}
	/**
	 * Destroy ConnectionPool
	 */
	public void destroyPool(){
		for(int i = 0;i < connectionPool.size();i++){
			try {
				connectionPool.take().close();
			} catch (SQLException | InterruptedException e) {
				LOG.error("Can not close connection "+e);
			}		
		}
	}
	/**
	 * Initialize property resources 
	 */
	private void initResource(){
		try{
		resource = ResourceBundle.getBundle("property.database");
		dbUrl = resource.getString("db.url");
		dbUser = resource.getString("db.user");
		dbPassword = resource.getString("db.password");
		} catch(MissingResourceException e){
			LOG.fatal(e);
			throw new DatabaseRequestException("Database resource not found");
		}
	}
	/**
	 * Check created connections from ConnectionPool after initialize.
	 */
	private void checkConnection(){
		if(connectionPool.isEmpty()){
			throw new DatabaseRequestException("Server can not connected to the database...");
		}
	}
}
