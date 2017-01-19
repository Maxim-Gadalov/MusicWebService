package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.TrackService;
import edu.gadalov.webservice.service.UserService;
import edu.gadalov.webservice.validation.CreditCardValidation;

public class BuyTrackCommand extends AbstractCommand{
	private static final String TRACKS_PAGE = "jsp/tracks.jsp";
	private static final String BUY_TRACK_PAGE = "jsp/pay.jsp";
	private static final String ERROR_MESSAGE_ORDER_TRACK = "Can not order track";

	@Override
	public String execute(HttpServletRequest request) {
		String nickname = request.getSession().getAttribute("nickname").toString();
		String idTrack = request.getParameter("idTrack");
		String cardNumber = request.getParameter("card-number");
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String cvc = request.getParameter("cvc");
		String expiryDate = request.getParameter("expires");
		String errorMessage = new String();
		String page = BUY_TRACK_PAGE;
		CreditCardValidation validation = new CreditCardValidation();
		TrackService service = new TrackService();
		UserService userService = new UserService();
		errorMessage = validation.creditCardValidation(cardNumber, cvc, expiryDate, firstName, lastName);
		User user = userService.getUser(nickname);
		AudioTrack track = service.getTrackById(Integer.valueOf(idTrack));
		float trackCost = track.getCost()*(1 - user.getBonus().getBonus()/100.0f);
	    if(errorMessage.isEmpty()){
	    	// add trackorder - forvard to the tracks;
	    	if(service.orderTrack(new TrackOrder(user,track))){
	    		page = TRACKS_PAGE;	
	    		if(user.getBonus().getBonus() != 0){
	    			userService.setDiscount(new Discount(1,0), user);
	    		}
	    	} else{
	    		errorMessage = ERROR_MESSAGE_ORDER_TRACK;
	    		request.setAttribute("errorMessage", errorMessage);
	    		request.setAttribute("singer", track.getSinger());
				request.setAttribute("trackName", track.getTrackName());
				request.setAttribute("cost", trackCost);
				request.setAttribute("idTrack", track.getId());
	    	}
	    } else{
	    	request.setAttribute("errorMessage", errorMessage);
	    	request.setAttribute("singer", track.getSinger());
			request.setAttribute("trackName", track.getTrackName());
			request.setAttribute("cost", trackCost);
			request.setAttribute("idTrack", track.getId());
	    }
		
		
		return page;
	}
}
