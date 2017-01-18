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
import org.apache.logging.log4j.LogManager;

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
	private ConnectionPool(){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			LOG.fatal("Can not register JDBC driver "+e);
			throw new RuntimeException("Can not register JDBC driver");
		}
		initResource();
	}
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
	
	private  Connection createConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException e) {
			LOG.error("Connection not avalible "+e);
		}
		return conn;
	}
	private void createAllConnections(){
		for(int i = 0;i < MAX_CONNECTIONS;i++){
			Connection cn = createConnection();
			if(cn != null){
			connectionPool.add(createConnection());
			}
		}
	}
	public Connection getConnectionFromPool(){
		Connection cn = null;
		try {
			cn = connectionPool.take();
		} catch (InterruptedException e) {
			LOG.error(e);
		}
		return cn;
	}
	public void putConnectionBack(Connection conn){
		try {
			connectionPool.put(conn);
		} catch (InterruptedException e) {
			LOG.error(e);
		}	
	}
	public void destroyPool(){
		for(int i = 0;i < connectionPool.size();i++){
			try {
				connectionPool.take().close();
			} catch (SQLException | InterruptedException e) {
				LOG.error("Can not close connection "+e);
			}		
		}
	}
	private void initResource(){
		try{
		resource = ResourceBundle.getBundle("property.database");
		dbUrl = resource.getString("db.url");
		dbUser = resource.getString("db.user");
		dbPassword = resource.getString("db.password");
		} catch(MissingResourceException e){
			LOG.fatal(e);
			throw new RuntimeException("Database resource not found");
		}
	}
	private void checkConnection(){
		if(connectionPool.isEmpty()){
			throw new RuntimeException("Server can not connected to the database..."); 
		}
	}
}
