package edu.gadalov.webservice.service;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.dao.DiscountDAO;
import edu.gadalov.webservice.entity.Discount;

public class DiscountService {
	public Discount getDiscount(int id){
		Discount discount = null;
		DiscountDAO dao = new DiscountDAO(ConnectionPool.getInstance().getConnectionFromPool());
		try{
			discount = dao.findById(id);
		} finally{
			dao.close(dao.getConnection());
		}
		return discount;
	}

}
