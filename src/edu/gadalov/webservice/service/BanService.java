package edu.gadalov.webservice.service;

import edu.gadalov.webservice.dao.UserBanDAO;
import edu.gadalov.webservice.entity.UserBan;

/**Ban user service class.
 * @author Maxim Gadalov
 *
 */
public class BanService {
	/**Inset ban into database
	 * @param entity - BanList @see {@link BanList#BanList(int, edu.gadalov.webservice.entity.User, String, int)}
	 * @return true if insert was successful, false - otherwise
	 */
	public boolean banUser(UserBan entity){
		boolean result = false;
		UserBanDAO dao = new UserBanDAO();
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
		UserBanDAO dao = new UserBanDAO();
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
		UserBan ban = new UserBan();
		UserBanDAO dao = new UserBanDAO();
		try{
			ban = dao.findById(idUser);
		}finally{
			dao.close(dao.getConnection());
		}
		return (ban.isUntapped());
	}
	/**Return ban info
	 * @param idUser - integer user id
	 * @return BanList @see {@link BanList#BanList(int, edu.gadalov.webservice.entity.User, String, int)}
	 */
	public UserBan getBanInfo(int idUser){
		UserBan ban = new UserBan();
		UserBanDAO dao = new UserBanDAO();
		try{
			ban = dao.findById(idUser);
		} finally{
			dao.close(dao.getConnection());
		}
		return ban;
	}

}
