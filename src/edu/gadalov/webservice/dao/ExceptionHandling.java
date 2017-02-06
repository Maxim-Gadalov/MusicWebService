package edu.gadalov.webservice.dao;

import java.sql.SQLException;
import java.sql.Statement;


// нововведение
/**Abstract class for create and running logic functional code unit
 * @author Maxim Gadalov
 *
 */
public abstract class ExceptionHandling {
	/** run functional code of this method
	 * @param st Statement
	 * @throws SQLException handling exception
	 */
	public abstract void run(Statement st) throws SQLException;
}
