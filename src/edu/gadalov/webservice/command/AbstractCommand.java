package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

/**Abstract command class.
 * @author Maxim Gadalov
 *
 */
public abstract class AbstractCommand {
	/**Command
	 * @param request - HttpServletRequest
	 * @return String page path
	 */
	public abstract String execute(HttpServletRequest request);

}
