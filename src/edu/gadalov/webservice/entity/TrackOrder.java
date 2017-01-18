package edu.gadalov.webservice.entity;

public class TrackOrder {
	private User user;
	private AudioTrack track;
	public TrackOrder(User user, AudioTrack track){
		this.user = user;
		this.track = track;
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

}
