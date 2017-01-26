package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.TrackOrder;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.BuyTrackService;
import edu.gadalov.webservice.service.TrackService;

/**Buy AudioTrack page forward command class.
 * @author Maxim Gadalov
 *
 */
public class BuyTrackPageCommand extends TracksPageCommand{
	private static final String BUY_TRACK_PAGE = "jsp/pay.jsp";
	private static final String LOGIN_PAGE = "jsp/login.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return LOGIN_PAGE;
		}
		User user = (User)request.getSession().getAttribute("user");
		String idTrack = request.getParameter("idTrack");
		TrackService service = new TrackService();
		BuyTrackService buyService = new BuyTrackService();
		String errorMessage = new String();
		AudioTrack track = service.getTrackById(Integer.valueOf(idTrack));
		TrackOrder order = new TrackOrder(user,track);
		String trackCost = String.format(java.util.Locale.US,"%.2f",buyService.getDiscountCost(user, track));
		errorMessage = service.checkTrackOrder(order);
		if(errorMessage.isEmpty()){
			request.setAttribute("singer", track.getSinger());
			request.setAttribute("trackName", track.getTrackName());
			request.setAttribute("cost", trackCost);
			request.setAttribute("idTrack", track.getId());
			return  BUY_TRACK_PAGE;
		} else{
			request.setAttribute("errorMessage", errorMessage);
			return super.execute(request);
		}
	}
}
