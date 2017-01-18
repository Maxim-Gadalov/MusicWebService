package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommand {
	public abstract String execute(HttpServletRequest request);

}
