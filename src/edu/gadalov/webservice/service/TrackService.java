package edu.gadalov.webservice.service;

import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.dao.AudioTrackDAO;
import edu.gadalov.webservice.dao.TrackOrderDAO;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.TrackOrder;

public class TrackService {
	private static final String ERROR_MESSAGE_ORDER_TRACK = "You have already bought this track";
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
	public String checkTrackOrder(TrackOrder order){
		TrackOrderDAO trackOrder = new TrackOrderDAO();
		String errorMessage = new String();
		if(trackOrder.findByTrackOrder(order)){
			errorMessage = ERROR_MESSAGE_ORDER_TRACK;
		}
		return errorMessage;
		
	}
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

}
