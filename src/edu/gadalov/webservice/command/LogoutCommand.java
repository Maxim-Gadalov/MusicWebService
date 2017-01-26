package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

/**Log out command class.
 * @author Maxim Gadalov
 *
 */
public class LogoutCommand extends AbstractCommand {
	private static final String MAIN_PAGE = "main.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().invalidate();
		return MAIN_PAGE;
	}

}
