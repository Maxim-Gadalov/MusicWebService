package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.DiscountService;
import edu.gadalov.webservice.service.UserService;

public class AssignDiscountCommand extends AdminPageCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String USER_ERROR = "User not found";
	private static final String DATABASE_ERROR = "Database connection was interrupted";
	private static final String SUCCESS_URL_PATH = "/MusicWebService/MusicServiceServlet?command=admin-page";
	
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null){
			return MAIN_PAGE;
		}
		String userLogin = request.getParameter("user-login");
		String discount = request.getParameter("discount");
		UserService service = new UserService();
		DiscountService discountService = new DiscountService();
		Discount bonus = discountService.getDiscount(Integer.valueOf(discount));
		User user = service.getUser(userLogin);
		String errorMessage = new String();
		if(user == null){
			errorMessage = USER_ERROR;
		}
		if(bonus == null){
			errorMessage = DATABASE_ERROR;
		}
		if(errorMessage.isEmpty()){
			if(service.setDiscount(bonus, user)){
				request.setAttribute("success", true);
				return SUCCESS_URL_PATH;
			}
		} else{
			request.setAttribute("discountErrorMessage", errorMessage);
		}
		
		return super.execute(request);
	}

}
