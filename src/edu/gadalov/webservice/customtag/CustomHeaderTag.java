package edu.gadalov.webservice.customtag;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.localebundle.LocaleBundle;

public class CustomHeaderTag extends TagSupport{
	private static final Logger LOG = LogManager.getLogger(CustomHeaderTag.class);
	private static final long serialVersionUID = 2984735954509651502L;
	private static final String LOGO_IMG_PATH = "/MusicWebService/IMG/earphones.jpg";
	private static final String MENU_IMG_PATH = "/MusicWebService/IMG/menu-icon.png";
	private static final String ADMIN_ICON_PATH = "/MusicWebService/IMG/admin1.png";
	private static final String USER_ICON_PATH = "/MusicWebService/IMG/user.png";
	private static final String LOGIN_PAGE_PATH = "/MusicWebService/jsp/login.jsp";
	private static final String SIGN_UP_PAGE_PATH = "/MusicWebService/jsp/registration.jsp";
	private static final String HOME_PAGE_PATH = "/MusicWebService/main.jsp";
	private static final String SPLITTER = "_";
	private static final int LOCALE_LANGUAGE_INDEX = 0;
	private static final int LOCALE_COUNTRY_INDEX = 1;
	private User user;
	
	public void setUser(User user){
		this.user = user;
	}

	@Override
	public int doStartTag(){
		String header = new String();
		Object locale = pageContext.getSession().getAttribute("locale");
		LocaleBundle bundle = null;
		if(locale !=null){
			String[] localeValues = locale.toString().split(SPLITTER);
			bundle = new LocaleBundle(new Locale(localeValues[LOCALE_LANGUAGE_INDEX],localeValues[LOCALE_COUNTRY_INDEX]));
		} else{
			bundle = new LocaleBundle();
		}
		if(user == null){
			header = "<img class=logo src="+LOGO_IMG_PATH+" alt=Logo>"
					+ "<div class=dropdown>"
					+ "<img class=dropimg src="+MENU_IMG_PATH+" alt=Menu>"
					+ "<div>"
					+ "<ul class=navigation>"
					+ "<li><a href="+HOME_PAGE_PATH+" title=Home>"+bundle.getValue("home")+"</a></li>"
					+ "<li><a href=# title=About&nbsp;us>"+bundle.getValue("about_us")+"</a></li>"
					+ "<li><a href=# onclick=document.getElementById('tracks').submit();return&nbsp;false;"
					+ "title=Tracks>"+bundle.getValue("tracks")+"</a>"
					+ "<ul>"
					+ "<li><a href=# onclick=document.getElementById('genre-name').value='Trance';"
					+ "document.getElementById('tracks').submit();return&nbsp;false;"
					+ "title=Trance>Trance</a></li>"
					+ " <li><a href=# onclick=document.getElementById('genre-name').value='House';"
					+ "document.getElementById('tracks').submit();return&nbsp;false;"
					+ "title=House>House</a></li>"
					+ "<li><a href=# onclick=document.getElementById('genre-name').value='Drum_&_Bass';"
					+ "document.getElementById('tracks').submit();return&nbsp;false;"
					+ "title=Drum&nbsp;&&nbsp;Bass>Drum&nbsp;&&nbsp;Bass</a></li>"
					+ "<li><a href=# onclick=document.getElementById('genre-name').value='Dubstep';"
					+ "document.getElementById('tracks').submit();return&nbsp;false;"
					+ "title=Dubstep>Dubstep</a></li>"
					+ "<li><a href=# onclick=document.getElementById('genre-name').value='Techno';"
					+ "document.getElementById('tracks').submit();return&nbsp;false;"
					+ "title=Techno>Techno</a></li>"
					+ "</ul>"
					+ "</li>"
					+ "<li><a href= title=Contact>"+bundle.getValue("contact")+"</a></li>"
					+ "</ul>"
					+ "</div>"
					+ "</div>"
					+ "<a href="+SIGN_UP_PAGE_PATH+" class=sign-up>"+bundle.getValue("sign_up")+"</a>"
					+ "<a href="+LOGIN_PAGE_PATH+" class=login>"+bundle.getValue("log_in")+"</a>";
		} else{
			if(user.getRole().getRoleName().equals("admin")){
				header = "<img class=logo src="+LOGO_IMG_PATH+" alt=Logo>"
						+ "<div class=dropdown>"
						+ "<img class=dropimg src="+MENU_IMG_PATH+" alt=Menu>"
						+ "<div>"
						+ "<ul class=navigation>"
						+ "<li><a href="+HOME_PAGE_PATH+" title=Home>"+bundle.getValue("home")+"</a></li>"
						+ "<li><a href= title=About&nbsp;us>"+bundle.getValue("about_us")+"</a></li>"
						+ "<li><a href=# onclick=document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Tracks>"+bundle.getValue("tracks")+"</a>"
						+ "<ul>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Trance';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Trance>Trance</a></li>"
						+ " <li><a href=# onclick=document.getElementById('genre-name').value='House';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=House>House</a></li>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Drum_&_Bass';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Drum&nbsp;&&nbsp;Bass>Drum&nbsp;&&nbsp;Bass</a></li>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Dubstep';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Dubstep>Dubstep</a></li>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Techno';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Techno>Techno</a></li>"
						+ "</ul>"
						+ "</li>"
						+ "<li><a href= title=Contact>"+bundle.getValue("contact")+"</a></li>"
						+ "<li><a href=# onclick=document.getElementById('form').submit();return&nbsp;false;"
						+ " title=Administration>"+bundle.getValue("administration")+"</a></li>"
						+ "</ul>"
						+ "</div>"
						+ "</div>"
						+ "<form class=header-form action=MusicServiceServlet method=POST>"
					    + "<input class=profile-button type=submit value="+bundle.getValue("log_out")+">"
					    + "<input value=logout type=hidden name=command>"
					    + "</form>"
					    + "<form class=header-form action=MusicServiceServlet method=POST>"
					    + "<input class=profile-button type=submit value="+bundle.getValue("my_profile")+">"
					    + "<input value=profile type=hidden name=command>"
					    + "</form>"
					    + "<img src="+ADMIN_ICON_PATH+" alt=profile class=user-img>"
					    + "<div class=profile-nickname>"+bundle.getValue("hello")+","+user.getNickname()+"</div>";
				header +=  "<form action=/MusicWebService/MusicServiceServlet method=POST id=form>"
						+ "<input type=hidden name=command value=admin-page>"
						+ "</form>";
			} else{
				header = "<img class=logo src="+LOGO_IMG_PATH+" alt=Logo>"
						+ "<div class=dropdown>"
						+ "<img class=dropimg src="+MENU_IMG_PATH+" alt=Menu>"
						+ "<div>"
						+ "<ul class=navigation>"
						+ "<li><a href="+HOME_PAGE_PATH+" title=Home>"+bundle.getValue("home")+"</a></li>"
						+ "<li><a href= title=About&nbsp;us>"+bundle.getValue("about_us")+"</a></li>"
						+ "<li><a href=# onclick=document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Tracks>"+bundle.getValue("tracks")+"</a>"
						+ "<ul>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Trance';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Trance>Trance</a></li>"
						+ " <li><a href=# onclick=document.getElementById('genre-name').value='House';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=House>House</a></li>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Drum_&_Bass';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Drum&nbsp;&&nbsp;Bass>Drum&nbsp;&&nbsp;Bass</a></li>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Dubstep';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Dubstep>Dubstep</a></li>"
						+ "<li><a href=# onclick=document.getElementById('genre-name').value='Techno';"
						+ "document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Techno>Techno</a></li>"
						+ "</ul>"
						+ "</li>"
						+ "<li><a href= title=Contact>"+bundle.getValue("contact")+"</a></li>"
						+ "</ul>"
						+ "</div>"
						+ "</div>"
						+ "<form class=header-form action=MusicServiceServlet method=POST>"
						+ "<input class=profile-button type=submit value="+bundle.getValue("log_out")+">"
						+ "<input value=logout type=hidden name=command>"
						+ "</form>"
						+ "<form class=header-form action=MusicServiceServlet method=POST>"
						+ "<input class=profile-button type=submit value="+bundle.getValue("my_profile")+">"
						+ "<input value=profile type=hidden name=command>"
						+ "</form>"
						+ "<img src="+USER_ICON_PATH+" alt=profile class=user-img>"
						+ "<div class=profile-nickname>"+bundle.getValue("hello")+","+user.getNickname()+"</div>";
			}
		}
		header += "<form action=/MusicWebService/MusicServiceServlet method=GET id=tracks>"
				+ "<input type=hidden name=genre id=genre-name value=>"
				+ "<input type=hidden name=command value=tracks-page>"
				+ "</form>";
		try {
			pageContext.getOut().write(header);
		} catch (IOException e) {
			LOG.error(e);
		}
		return SKIP_BODY;
	}
	@Override
	public int doEndTag(){
		return EVAL_PAGE;
	}

}
