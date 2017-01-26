package edu.gadalov.webservice.service;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;

/**Buy audio track service class.
 * @author Maxim Gadalov
 *
 */
public class BuyTrackService {
	/**Return new discount price
	 * @param user - User @see {@link User#User(int, edu.gadalov.webservice.entity.Role, String, String, String, edu.gadalov.webservice.entity.Discount, String, String)}
	 * @param track - AudioTrack @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @return new cost(Float)
	 */
	public float getDiscountCost(User user, AudioTrack track){
		return track.getCost()*(1 - user.getBonus().getBonus()/100.0f);
	}

}
