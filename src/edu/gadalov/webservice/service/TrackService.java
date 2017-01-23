package edu.gadalov.webservice.service;

import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.dao.AudioTrackDAO;
import edu.gadalov.webservice.dao.TrackOrderDAO;
import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;

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
