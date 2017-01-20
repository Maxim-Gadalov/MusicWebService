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

public class AdminMenuService {
	private static final String SAVE_DIR = "music";
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
	// перенести метод в команду и затестить 
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
