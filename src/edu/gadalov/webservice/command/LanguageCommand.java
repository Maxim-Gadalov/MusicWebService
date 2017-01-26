package edu.gadalov.webservice.command;

import javax.servlet.http.HttpServletRequest;

public class LanguageCommand extends AbstractCommand{
	private static final String MAIN_PAGE = "main.jsp";
	private static final String RUSSIAN_LANGUAGE = "Russian";
	private static final String ENGLISH_LANGUAGE = "English";

	@Override
	public String execute(HttpServletRequest request) {
		String locale = request.getParameter("locale");
		if(ENGLISH_LANGUAGE.equals(locale)){
			request.getSession().setAttribute("locale", "en_GB");
		} else if(RUSSIAN_LANGUAGE.equals(locale)){
			request.getSession().setAttribute("locale", "ru_RU");
		}
		
		return MAIN_PAGE;
	}

}
