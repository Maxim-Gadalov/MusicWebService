package edu.gadalov.webservice.entity;

import java.sql.Timestamp;
/**Entity class for interaction with database information and business logic.
 * Comment object.
 * @author Maxim Gadalov
 *
 */
public class Comment {
	private int id;
	private User user;
	private AudioTrack track;
	private String text;
	private Timestamp date;
	/**Create object with following parameters
	 * @param id - id of object similar PRIMARY KEY (int)
	 * @param user - user who write this comment @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 * @param track - track which was or will be commented @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 * @param text - comment text (String)
	 * @param date - date of comment @see {@link Timestamp#Timestamp(long)}
	 */
	public Comment(int id, User user, AudioTrack track, String text, Timestamp date) {
		this.id = id;
		this.user = user;
		this.track = track;
		this.text = text;
		this.date = date;
	}
	/**
	 * @return id current object
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id - new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return user who add the comment @see {@link User#User(int, Role, String, String, String, Discount, String, String)}
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user - new user to the current object
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return audio track @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 */
	public AudioTrack getTrack() {
		return track;
	}
	/**
	 * @param track - new audio track @see {@link AudioTrack#AudioTrack(int, User, String, String, String, String, float, String, boolean)}
	 */
	public void setTrack(AudioTrack track) {
		this.track = track;
	}
	/**
	 * @return text of comment (String)
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text - new comment text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return date of comment
	 */
	public Timestamp getDate() {
		return date;
	}
	/**
	 * @param date - new date of comment
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}	
}
