package edu.gadalov.webservice.customtag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**Custom pagination jstl tag. Paginate audio track pages.
 * @author Maxim Gadalov
 *
 */
public class CustomPaginationTag extends TagSupport{
	private static final long serialVersionUID = -4575319305979581968L;
	private static final Logger LOG = LogManager.getLogger(CustomPaginationTag.class);
	private static final int MAX_TRACKS_ON_PAGE = 10;
	private String numberOfElements;
	public void setNumberOfElements(String numberOfElements){
		this.numberOfElements = numberOfElements;
	}
	@Override
	public int doStartTag(){
		String tagBody = new String();
		int currentElements = Integer.valueOf(numberOfElements);
		float numberOfPages = currentElements/(float)MAX_TRACKS_ON_PAGE;
		if(!(numberOfPages <= 0)){
			tagBody = "<ul class=pagination>";
			for(int i = 0;i < (int)numberOfPages;i++){
				int lowerLimit = 1 + i*MAX_TRACKS_ON_PAGE;
				int upperLimit = MAX_TRACKS_ON_PAGE + i*MAX_TRACKS_ON_PAGE;
				String title = lowerLimit+"-"+upperLimit;
				tagBody += "<li><a href=# onclick=document.getElementById('"+title+"').submit();return&nbsp;false;"
						+ " title="+title+">"+title+"</a></li>";
				tagBody +=  "<form style=display:none; action=/MusicWebService/MusicServiceServlet method=GET id="+title+">"
						+ "<input type=hidden name=command value=pagination>"
						+ "<input type=hidden name=genre value="+pageContext.getRequest().getParameter("genre")+">"
						+ "<input type=hidden name=lowerLimit value="+lowerLimit+">"
						+ "<input type=hidden name=upperLimit value="+upperLimit+">"
						+ "</form>";
			}
			if((numberOfPages - (int)numberOfPages) > 0){
				int lowerLimit = 1 + (int)numberOfPages*MAX_TRACKS_ON_PAGE;
			    int upperLimit = currentElements;
			    String title = lowerLimit+"-"+upperLimit;
			    tagBody += "<li><a href=# onclick=document.getElementById('"+title+"').submit();return&nbsp;false;"
					    + " title=#"+title+">"+title+"</a></li>";
			    tagBody +=  "<form style=display:none; action=/MusicWebService/MusicServiceServlet method=GET id="+title+">"
					    + "<input type=hidden name=command value=pagination>"
					    + "<input type=hidden name=genre value="+pageContext.getRequest().getParameter("genre")+">"
					    + "<input type=hidden name=lowerLimit value="+lowerLimit+">"
					    + "<input type=hidden name=upperLimit value="+upperLimit+">"
					    + "</form>"
					    + "</ul>";
			    } else{
			    	tagBody += "</ul>";
			    }
		}
		try {
			pageContext.getOut().write(tagBody);
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
