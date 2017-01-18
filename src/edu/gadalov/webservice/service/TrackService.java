package edu.gadalov.webservice.service;

import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.dao.AudioTrackDAO;
import edu.gadalov.webservice.entity.AudioTrack;

public class TrackService {
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

}
