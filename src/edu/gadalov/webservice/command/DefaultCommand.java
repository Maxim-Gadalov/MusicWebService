package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request) {
		return "main.jsp";
	}

}
