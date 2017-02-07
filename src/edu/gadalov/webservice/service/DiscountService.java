package edu.gadalov.webservice.service;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.dao.DiscountDAO;
import edu.gadalov.webservice.entity.Discount;

/**Discount service class.
 * @author Maxim Gadalov
 *
 */
public class DiscountService {
	/**Return Discount found by id
	 * @param id - integer id
	 * @return Discount @see {@link Discount#Discount(int, int)}
	 */
	public Discount getDiscount(int id){
		DiscountDAO dao = new DiscountDAO(ConnectionPool.getInstance().getConnectionFromPool());
		try{
			return dao.findById(id);
		} finally{
			dao.close(dao.getConnection());
		}
	}

}
