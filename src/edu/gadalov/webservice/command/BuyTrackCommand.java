package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.BuyTrackService;
import edu.gadalov.webservice.service.TrackService;
import edu.gadalov.webservice.service.UserService;
import edu.gadalov.webservice.validation.CreditCardValidation;

public class BuyTrackCommand extends AbstractCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String BUY_TRACK_PAGE = "jsp/pay.jsp";
	private static final String ERROR_MESSAGE_ORDER_TRACK = "Can not order track";
	private static final String SUCCESS_URL_PATH = "/MusicWebService/MusicServiceServlet?command=tracks-page";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
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
		BuyTrackService buyService = new BuyTrackService();
		errorMessage = validation.creditCardValidation(cardNumber, cvc, expiryDate, firstName, lastName);
		User user = (User)request.getSession().getAttribute("user");
		AudioTrack track = service.getTrackById(Integer.valueOf(idTrack));
		String trackCost = String.format(java.util.Locale.US,"%.2f",buyService.getDiscountCost(user, track));
	    if(errorMessage.isEmpty()){
	    	if(service.orderTrack(new TrackOrder(user,track))){	
	    		if(user.getBonus().getBonus() != 0){
	    			userService.setDiscount(new Discount(1,0), user);
	    			user.setBonus(new Discount(1,0));
	    			request.getSession().removeAttribute("user");
	    			request.getSession().setAttribute("user", user);
	    		}
	    		request.setAttribute("success", true);
	    		return SUCCESS_URL_PATH;
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
