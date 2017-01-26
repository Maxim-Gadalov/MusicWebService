package edu.gadalov.webservice.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import edu.gadalov.webservice.connection.ConnectionPool;
import edu.gadalov.webservice.dao.AudioTrackDAO;
import edu.gadalov.webservice.dao.DiscountDAO;
import edu.gadalov.webservice.dao.UserDAO;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;

/**Admin menu service class.
 * @author Maxim Gadalov
 *
 */
public class AdminMenuService {
	private static final String SAVE_DIR = "music";
	/**Return list of Users
	 * @return list of User objects
	 */
	public List<User> getAllUsers(){
		UserDAO userDAO = new UserDAO();
		List<User> list = new ArrayList<>();
		try{
			list = userDAO.findAll();
		} finally{
			userDAO.close(userDAO.getConnection());
		}
		return list;
	}
	/**Return AudioTracks
	 * @return list of AduioTrack objects
	 */
	public List<AudioTrack> getAllTracks(){
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		List<AudioTrack> list = new ArrayList<>();
		try{
			list = trackDAO.findAll();
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return list;
	}
	/**Upload music file to the server
	 * @param request - HttpServletRequest
	 * @return String file path
	 * @throws IOException @see {@link IOException#IOException()}
	 * @throws ServletException @see {@link ServletException#ServletException()}
	 */
	public String uploadTrack(HttpServletRequest request) throws IOException, ServletException{
		String savePath = request.getServletContext().getRealPath("") + SAVE_DIR;

        File fileSaveDir = new File(savePath); 
        String trackPath = new String();
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String fileName = new String();       
         for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();   
            if(fileName != null){  
            part.write(savePath + File.separator + fileName);
            trackPath = SAVE_DIR + File.separator + fileName;
            }  
        }
        return trackPath;
	}
	/**Insert AudioTrack into database
	 * @param track - AudioTrack @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @return true if insert was successful, false - otherwise
	 */
	public boolean createTrack(AudioTrack track){
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		boolean result = false;
		try{
			result = trackDAO.create(track);
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return result;
		
	}
	/**Return list of Discounts
	 * @return list of Discount objects
	 */
	public List<Discount> getDiscounts(){
		List<Discount> list = new ArrayList<>();
		DiscountDAO discountDAO = new DiscountDAO(ConnectionPool.getInstance().getConnectionFromPool());
		try{
			list = discountDAO.findAll();
		} finally{
			discountDAO.close(discountDAO.getConnection());
		}
		return list;
	}
}
