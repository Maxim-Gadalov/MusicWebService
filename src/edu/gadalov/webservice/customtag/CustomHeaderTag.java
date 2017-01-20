package edu.gadalov.webservice.customtag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.entity.User;

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
	private User user;
	
	public void setUser(User user){
		this.user = user;
	}

	@Override
	public int doStartTag(){
		String header = new String();
		if(user == null){
			header = "<img class=logo src="+LOGO_IMG_PATH+" alt=Logo>"
					+ "<div class=dropdown>"
					+ "<img class=dropimg src="+MENU_IMG_PATH+" alt=Menu>"
					+ "<div>"
					+ "<ul class=navigation>"
					+ "<li><a href="+HOME_PAGE_PATH+" title=Home>Home</a></li>"
					+ "<li><a href= title=About&nbsp;us>About&nbsp;us</a></li>"
					+ "<li><a href=# onclick=document.getElementById('tracks').submit();return&nbsp;false;"
					+ "title=Tracks>Tracks</a>"
					+ "<ul>"
					+ "<li><a href= title=Trance>Trance</a></li>"
					+ " <li><a href= title=House>House</a></li>"
					+ "<li><a href= title=Drum&nbsp;&&nbsp;Bass>Drum&nbsp;&&nbsp;Bass</a></li>"
					+ "<li><a href= title=Dubstep>Dubstep</a></li>"
					+ "<li><a href= title=Techno>Techno</a></li>"
					+ "</ul>"
					+ "</li>"
					+ "<li><a href= title=Contact>Contact</a></li>"
					+ "</ul>"
					+ "</div>"
					+ "</div>"
					+ "<input class=search type=text name=search placeholder=Search...>"
					+ "<a href="+SIGN_UP_PAGE_PATH+" class=sign-up>Sign&nbsp;up</a>"
					+ "<a href="+LOGIN_PAGE_PATH+" class=login>Log&nbsp;in</a>";
		} else{
			if(user.getRole().getRoleName().equals("admin")){
				header = "<img class=logo src="+LOGO_IMG_PATH+" alt=Logo>"
						+ "<div class=dropdown>"
						+ "<img class=dropimg src="+MENU_IMG_PATH+" alt=Menu>"
						+ "<div>"
						+ "<ul class=navigation>"
						+ "<li><a href="+HOME_PAGE_PATH+" title=Home>Home</a></li>"
						+ "<li><a href= title=About&nbsp;us>About&nbsp;us</a></li>"
						+ "<li><a href=# onclick=document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Tracks>Tracks</a>"
						+ "<ul>"
						+ "<li><a href= title=Trance>Trance</a></li>"
						+ " <li><a href= title=House>House</a></li>"
						+ "<li><a href= title=Drum&nbsp;&&nbsp;Bass>Drum&nbsp;&&nbsp;Bass</a></li>"
						+ "<li><a href= title=Dubstep>Dubstep</a></li>"
						+ "<li><a href= title=Techno>Techno</a></li>"
						+ "</ul>"
						+ "</li>"
						+ "<li><a href= title=Contact>Contact</a></li>"
						+ "<li><a href=# onclick=document.getElementById('form').submit();return&nbsp;false;"
						+ " title=Administration>Administration</a></li>"
						+ "</ul>"
						+ "</div>"
						+ "</div>"
						+ "<input class=search type=text name=search placeholder=Search...>"
						+ "<form class=header-form action=MusicServiceServlet method=POST>"
					    + "<input class=profile-button type=submit value=Log&nbsp;out>"
					    + "<input value=logout type=hidden name=command>"
					    + "</form>"
					    + "<form class=header-form action=MusicServiceServlet method=POST>"
					    + "<input class=profile-button type=submit value=My&nbsp;profile>"
					    + "<input value=profile type=hidden name=command>"
					    + "</form>"
					    + "<img src="+ADMIN_ICON_PATH+" alt=profile class=user-img>"
					    + "<div class=profile-nickname>Hi,"+user.getNickname()+"</div>";
				header +=  "<form action=/MusicWebService/MusicServiceServlet method=POST id=form>"
						+ "<input type=hidden name=command value=admin-page>"
						+ "</form>";
			} else{
				header = "<img class=logo src="+LOGO_IMG_PATH+" alt=Logo>"
						+ "<div class=dropdown>"
						+ "<img class=dropimg src="+MENU_IMG_PATH+" alt=Menu>"
						+ "<div>"
						+ "<ul class=navigation>"
						+ "<li><a href="+HOME_PAGE_PATH+" title=Home>Home</a></li>"
						+ "<li><a href= title=About&nbsp;us>About&nbsp;us</a></li>"
						+ "<li><a href=# onclick=document.getElementById('tracks').submit();return&nbsp;false;"
						+ "title=Tracks>Tracks</a>"
						+ "<ul>"
						+ "<li><a href= title=Trance>Trance</a></li>"
						+ " <li><a href= title=House>House</a></li>"
						+ "<li><a href= title=Drum&nbsp;&&nbsp;Bass>Drum&nbsp;&&nbsp;Bass</a></li>"
						+ "<li><a href= title=Dubstep>Dubstep</a></li>"
						+ "<li><a href= title=Techno>Techno</a></li>"
						+ "</ul>"
						+ "</li>"
						+ "<li><a href= title=Contact>Contact</a></li>"
						+ "</ul>"
						+ "</div>"
						+ "</div>"
						+ "<input class=search type=text name=search placeholder=Search...>"
						+ "<form class=header-form action=MusicServiceServlet method=POST>"
						+ "<input class=profile-button type=submit value=Log&nbsp;out>"
						+ "<input value=logout type=hidden name=command>"
						+ "</form>"
						+ "<form class=header-form action=MusicServiceServlet method=POST>"
						+ "<input class=profile-button type=submit value=My&nbsp;profile>"
						+ "<input value=profile type=hidden name=command>"
						+ "</form>"
						+ "<img src="+USER_ICON_PATH+" alt=profile class=user-img>"
						+ "<div class=profile-nickname>Hi,"+user.getNickname()+"</div>";
			}
		}
		header += "<form action=/MusicWebService/MusicServiceServlet method=POST id=tracks>"
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
