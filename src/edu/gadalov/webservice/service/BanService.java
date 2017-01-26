package edu.gadalov.webservice.service;


import edu.gadalov.webservice.dao.BanListDAO;
import edu.gadalov.webservice.entity.BanList;

/**Ban user service class.
 * @author Maxim Gadalov
 *
 */
public class BanService {
	/**Inset ban into database
	 * @param entity - BanList @see {@link BanList#BanList(int, edu.gadalov.webservice.entity.User, String, int)}
	 * @return true if insert was successful, false - otherwise
	 */
	public boolean banUser(BanList entity){
		boolean result = false;
		BanListDAO dao = new BanListDAO();
		try{
			result = dao.create(entity);
		} finally{
			dao.close(dao.getConnection());
		}
		return result;
	}
	/**delete ban user from database
	 * @param idUser - integer user id
	 * @return true if delete was successful, false - otherwise
	 */
	public boolean removeBan(int idUser){
		boolean result = false;
		BanListDAO dao = new BanListDAO();
		try{
			result = dao.deleteById(idUser);
		} finally{
			dao.close(dao.getConnection());
		}
		return result;
	}
	/**Check user ban
	 * @param idUser - integer id user
	 * @return true if user was not ban, false - otherwise
	 */
	public boolean checkBanUser(int idUser){
		BanList ban = null;
		BanListDAO dao = new BanListDAO();
		try{
			ban = dao.findById(idUser);
		}finally{
			dao.close(dao.getConnection());
		}
		return (ban == null);
	}
	/**Return ban info
	 * @param idUser - integer user id
	 * @return BanList @see {@link BanList#BanList(int, edu.gadalov.webservice.entity.User, String, int)}
	 */
	public BanList getBanInfo(int idUser){
		BanList ban = null;
		BanListDAO dao = new BanListDAO();
		try{
			ban = dao.findById(idUser);
		} finally{
			dao.close(dao.getConnection());
		}
		return ban;
	}

}
