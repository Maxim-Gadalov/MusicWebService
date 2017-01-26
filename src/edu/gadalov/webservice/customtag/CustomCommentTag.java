package edu.gadalov.webservice.customtag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.gadalov.webservice.entity.AudioTrack;
import edu.gadalov.webservice.entity.Comment;
import edu.gadalov.webservice.entity.User;
import edu.gadalov.webservice.localebundle.LocaleBundle;
import edu.gadalov.webservice.service.CommentService;

/**Custom comment jstl tag. Show users comments, let authorized users make new comments and let admins edit all comments.
 * @author Maxim Gadalov
 *
 */
public class CustomCommentTag extends TagSupport{
	private static final long serialVersionUID = -5086917123425357906L;
	private static final Logger LOG = LogManager.getLogger(CustomCommentTag.class);
	private static final String USER_ROLE_NAME = "user";
	private static final String USER_NICKNAME_DIV_CLASS = "user-nickname";
	private static final String ADMIN_NICKNAME_DIV_CLASS = "admin-nickname";
	private static final String EDIT_ICON_PATH = "/MusicWebService/IMG/edit-icon.png";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private static final String SPLITTER = "_";
	private static final int LOCALE_LANGUAGE_INDEX = 0;
	private static final int LOCALE_COUNTRY_INDEX = 1;
	private User user;
	private AudioTrack track;
	public void setUser(User user){
		this.user = user;
	}
	public void setTrack(AudioTrack track){
		this.track = track;
	}
	@Override
	public int doStartTag(){
		String tagBody = new String();
		CommentService service = new CommentService();
		List<Comment> list = service.getTrackComments(track);
		Object locale = pageContext.getSession().getAttribute("locale");
		LocaleBundle bundle = null;
		if(locale !=null){
			String[] localeValues = locale.toString().split(SPLITTER);
			bundle = new LocaleBundle(new Locale(localeValues[LOCALE_LANGUAGE_INDEX],localeValues[LOCALE_COUNTRY_INDEX]));
		} else{
			bundle = new LocaleBundle();
		}
		if(user == null){
			if(!list.isEmpty()){
				tagBody = "<div class=comments>";
				for(int i = 0;i < list.size();i++){
					String divClass = new String();
					if(USER_ROLE_NAME.equals(list.get(i).getUser().getRole().getRoleName())){
						divClass = USER_NICKNAME_DIV_CLASS;
					} else{
						divClass = ADMIN_NICKNAME_DIV_CLASS;
					}
					tagBody += "<div class=comment>"
							+ "<div class="+divClass+">"+list.get(i).getUser().getNickname()+" :</div>"
							+ "<div class=comment-text>"+list.get(i).getText()+"</div>"
							+ "<div class=comment-date>"+dateFormat.format(list.get(i).getDate())+"</div>"
							+ "</div><br>";
				}
				tagBody	+= "</div>";
			}
			} else if(USER_ROLE_NAME.equals(user.getRole().getRoleName())){
				if(!list.isEmpty()){
					tagBody = "<div class=comments>";
					for(int i = 0;i < list.size();i++){
						String divClass = new String();
						if(USER_ROLE_NAME.equals(list.get(i).getUser().getRole().getRoleName())){
							divClass = USER_NICKNAME_DIV_CLASS;
						} else{
							divClass = ADMIN_NICKNAME_DIV_CLASS;
						}
						tagBody += "<div class=comment>"
								+ "<div class="+divClass+">"+list.get(i).getUser().getNickname()+" :</div>"
								+ "<div class=comment-text>"+list.get(i).getText()+"</div>"
								+ "<div class=comment-date>"+dateFormat.format(list.get(i).getDate())+"</div>"
								+ "</div><br>";
					}
					tagBody	+= "</div>";
				}
				tagBody +=  "<textarea class=area form=comment"+track.getId()+" name=comment_text required rows=3></textarea><br>"
						+ "<button onclick=document.getElementById('comment"+track.getId()+"').submit();return&nbsp;false;>"+bundle.getValue("submit")+"</button>";
		} else{
			if(!list.isEmpty()){
				tagBody = "<div class=comments>";
				for(int i = 0;i < list.size();i++){
					String divClass = new String();
					if(USER_ROLE_NAME.equals(list.get(i).getUser().getRole().getRoleName())){
						divClass = USER_NICKNAME_DIV_CLASS;
					} else{
						divClass = ADMIN_NICKNAME_DIV_CLASS;
					}
					tagBody += "<div class=comment>"
							+ "<div class="+divClass+">"+list.get(i).getUser().getNickname()+" :</div>"
							+ "<img class=edit-comment-img src="+EDIT_ICON_PATH+" alt=edit onclick=showPlayer('editComment"+list.get(i).getId()+"'); return false;>"
							+ "<div class=comment-text>"+list.get(i).getText()+"</div>"
							+ "<div class=comment-date>"+dateFormat.format(list.get(i).getDate())+"</div>"
							+ "</div><br>"
							+ "<div id=editComment"+list.get(i).getId()+" class=edit-comment style=display:none>"
							+ "<textarea name=editArea"+list.get(i).getId()+" class=area form=edit-form required>"+list.get(i).getText()+"</textarea>"
							+ "<button onclick=document.getElementById('commentId').value='"+list.get(i).getId()+"';"
							+ "document.getElementById('edit-form').submit();return&nbsp;false;>"+bundle.getValue("submit")+"</button>"
							+ "</div>";
				}
				tagBody	+= "</div>";
			}
			tagBody += "<textarea class=area form=comment"+track.getId()+" name=comment_text required rows=5></textarea><br>"
					+ "<button onclick=document.getElementById('comment"+track.getId()+"').submit();return&nbsp;false;>"+bundle.getValue("submit")+"</button>";
					
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
