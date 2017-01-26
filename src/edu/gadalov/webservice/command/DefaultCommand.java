package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand extends AbstractCommand{
	private static final String MAIN_PAGE = "main.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		return MAIN_PAGE;
	}

}
