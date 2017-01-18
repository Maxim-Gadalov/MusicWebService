package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

import edu.gadalov.webservice.entity.Discount;
import edu.gadalov.webservice.entity.Role;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.service.RegistrationService;

public class RegistrationCommand extends AbstractCommand{ 
	private static final String REGISTRATION_PAGE = "jsp/registration.jsp";
	private static final String MAIN_PAGE = "main.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		String message = new String();
		String page = REGISTRATION_PAGE;
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("mail");
		String password = request.getParameter("psw");
		User user = new User(1,new Role(1,"user"),nickname,email,password,new Discount(1,0),null,null);
		RegistrationService service = new RegistrationService();
		message = service.registration(user);
		request.setAttribute("errorMessage", message);
		if(message.isEmpty()){
			page = MAIN_PAGE;
			request.getSession().setAttribute("nickname", nickname);
			request.getSession().setAttribute("role", user.getRole().getRoleName());
		}
		return page;
	}

}
