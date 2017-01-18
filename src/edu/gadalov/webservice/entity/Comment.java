package edu.gadalov.webservice.entity;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private User user;
	private AudioTrack track;
	private String text;
	private Timestamp date;
	public Comment(int id, User user, AudioTrack track, String text, Timestamp date) {
		super();
		this.id = id;
		this.user = user;
		this.track = track;
		this.text = text;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public AudioTrack getTrack() {
		return track;
	}
	public void setTrack(AudioTrack track) {
		this.track = track;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}	

}
