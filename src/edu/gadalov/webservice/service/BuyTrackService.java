package edu.gadalov.webservice.service;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.User;

public class BuyTrackService {
	public float getDiscountCost(User user, AudioTrack track){
		return track.getCost()*(1 - user.getBonus().getBonus()/100.0f);
	}

}
