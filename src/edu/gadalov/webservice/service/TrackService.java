package edu.gadalov.webservice.service;

import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.dao.AudioTrackDAO;
import edu.gadalov.webservice.dao.TrackOrderDAO;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;

/**AudioTrack service class. @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
 * @author Maxim Gadalov
 *
 */
public class TrackService {
	private static final String ERROR_MESSAGE_ORDER_TRACK = "You have already bought this track";
	/**Return list of AudioTrack objects.
	 * @return list of AudioTrack.
	 */
	public List<AudioTrack> getAllTracks(){
		List<AudioTrack> list = new ArrayList<>();
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		try{
			list = trackDAO.findAll();
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return list;
	}
	/**Return AudioTrack found by id
	 * @param id - integer ID
	 * @return AudioTrack
	 */
	public AudioTrack getTrackById(Integer id){
		AudioTrack track = null;
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		try{
			track = trackDAO.findById(id);
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return track;
	}
	/**Check TrackOrder
	 * @param order - TrackOrder @see {@link TrackOrder#TrackOrder(User, AudioTrack)}
	 * @return empty String if AudioTrack was not ordered by User earlier, else String errorMessage
	 */
	public String checkTrackOrder(TrackOrder order){
		TrackOrderDAO trackOrder = new TrackOrderDAO();
		String errorMessage = new String();
		if(trackOrder.findByTrackOrder(order)){
			errorMessage = ERROR_MESSAGE_ORDER_TRACK;
		}
		return errorMessage;
		
	}
	/**Add track order to the database
	 * @param order - TrackOrder @see {@link TrackOrder#TrackOrder(User, AudioTrack)}
	 * @return - true if order track was add successfully, false - otherwise
	 */
	public boolean orderTrack(TrackOrder order){
		boolean result = false;
		TrackOrderDAO orderDAO = new TrackOrderDAO();
		try{
			result = orderDAO.create(order);
		} finally{
			orderDAO.close(orderDAO.getConnection());
		}
		return result;
	}
	/**Return list of AudioTracks found by User
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @return - list of AudioTrack
	 */
	public List<AudioTrack> getUserTracks(User user){
		List<AudioTrack> list = new ArrayList<>();
		List<TrackOrder> temp = new ArrayList<>();
		TrackOrderDAO orderDAO = new TrackOrderDAO();
		try{
			temp = orderDAO.findByUser(user);
			if(!temp.isEmpty()){
			for(int i = 0;i < temp.size();i++){
				list.add(temp.get(i).getTrack());
			}
			}
		} finally{
			orderDAO.close(orderDAO.getConnection());
		}
		return list;
	}
	/**Return list of AudioTracks found by visibility
	 * @return list of AudioTrack
	 */
	public List<AudioTrack> getVisibleTracks(){
		List<AudioTrack> list = new ArrayList<>();
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		try{
			list = trackDAO.findVisibleTracks();
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return list;
	}
	/**Return list of AudioTracks found by genre and visible
	 * @param genre - String genre of AudioTrack
	 * @return list Of AudioTrack
	 */
	public List<AudioTrack> getVisibleTracksByGenre(String genre){
		List<AudioTrack> list = new ArrayList<>();
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		try{
			list = trackDAO.findByGenre(genre);
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return list;
	}
	/**Set AudioTrack invisible status
	 * @param singer - String singer
	 * @param trackName - String track name
	 * @return true if update was successfully, else - otherwise
	 */
	public boolean deleteTrack(String singer, String trackName){
		boolean result = false;
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		try{
			result = trackDAO.deleteTrack(singer, trackName);
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return result;
	}
	/**Update database AudioTrack record
	 * @param track - AudioTrack @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @return true if update was successfully, false - otherwise
	 */
	public boolean updateTrack(AudioTrack track){
		boolean result = false;
		AudioTrackDAO trackDAO = new AudioTrackDAO();
		try{
			result = trackDAO.updateTrack(track);
		} finally{
			trackDAO.close(trackDAO.getConnection());
		}
		return result;
	}

}
